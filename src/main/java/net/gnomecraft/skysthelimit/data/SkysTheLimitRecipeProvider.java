package net.gnomecraft.skysthelimit.data;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.nhoryzon.mc.farmersdelight.registry.ItemsRegistry;
import eu.midnightdust.motschen.rocks.RocksMain;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.DefaultResourceConditions;
import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.gnomecraft.skysthelimit.fabricresourcecondition.SkysTheLimitResourceConditions;
import net.gnomecraft.skysthelimit.item.SkysTheLimitItems;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitItemTags;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class SkysTheLimitRecipeProvider extends FabricRecipeProvider {
    public SkysTheLimitRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        // Skys the Limit mod recipes:
        new ShapedRecipeJsonBuilder(RecipeCategory.DECORATIONS, SkysTheLimitItems.FOG_CATCHER_ITEM, 1)
                .group("fog_catcher")
                .pattern("i i")
                .pattern(" i ")
                .pattern("III")
                .input('I', Items.IRON_INGOT)
                .input('i', Items.IRON_NUGGET)
                .criterion("has_iron", InventoryChangedCriterion.Conditions.items(Items.IRON_INGOT))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("fogCatcher")));

        new ShapelessRecipeJsonBuilder(RecipeCategory.MISC, SkysTheLimitItems.STONE_BUCKET_ITEM, 1)
                .group("misc")
                .input(Items.BUCKET)
                .input(Items.COBBLESTONE)
                .criterion("has_cobblestone", InventoryChangedCriterion.Conditions.items(Items.COBBLESTONE))
                .offerTo(withConditions(exporter,
                                SkysTheLimitResourceConditions.allConfigBooleansEnabled("blastCobbleToLava")),
                        "stone_bucket_from_cobblestone");

        new ShapelessRecipeJsonBuilder(RecipeCategory.MISC, SkysTheLimitItems.STONE_BUCKET_ITEM, 1)
                .group("misc")
                .input(Items.BUCKET)
                .input(Ingredient.fromTag(SkysTheLimitItemTags.COMMON_STONES), 6)
                .criterion("has_cobble_stones", InventoryChangedCriterion.Conditions.items(RocksMain.CobblestoneSplitter))
                .offerTo(withConditions(exporter,
                                DefaultResourceConditions.allModsLoaded(RocksMain.MOD_ID),
                                SkysTheLimitResourceConditions.allConfigBooleansEnabled("blastCobbleToLava", "stonesToStoneBucket")),
                        "stone_bucket_from_stones");

        // Sky block support recipes:
        new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, Items.COBBLESTONE, 1)
                .group("stone")
                .pattern("ss")
                .pattern("ss")
                .input('s', SkysTheLimitItemTags.COMMON_STONES)
                .criterion("has_stone", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().tag(SkysTheLimitItemTags.COMMON_STONES).build()))
                .offerTo(withConditions(exporter,
                        DefaultResourceConditions.allModsLoaded(RocksMain.MOD_ID),
                        SkysTheLimitResourceConditions.allConfigBooleansEnabled("stonesToCobblestone")));

        new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, Items.GRASS_BLOCK, 1)
                .group("dirt")
                .input(Items.DIRT)
                .input(Items.WHEAT_SEEDS)
                .criterion("has_seeds", InventoryChangedCriterion.Conditions.items(Items.WHEAT_SEEDS))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("dirtToGrassBlock")));

        new ShapedRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, Items.MYCELIUM, 1)
                .group("dirt")
                .pattern("mmm")
                .pattern("mdm")
                .pattern("mmm")
                .input('d', ItemsRegistry.RICH_SOIL.get())
                .input('m', Ingredient.ofItems(Items.BROWN_MUSHROOM, Items.RED_MUSHROOM))
                .criterion("has_mushrooms", InventoryChangedCriterion.Conditions.items(ItemsRegistry.RICH_SOIL.get()))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("richSoilToMycelium")));

        new ShapelessRecipeJsonBuilder(RecipeCategory.BUILDING_BLOCKS, Items.MOSS_BLOCK, 2)
                .group("moss")
                .input(Items.BONE_MEAL)
                .input(Items.MOSS_CARPET)
                .input(Items.MOSS_CARPET)
                .input(Items.MOSS_CARPET)
                .criterion("has_moss", InventoryChangedCriterion.Conditions.items(Items.MOSS_CARPET))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("mossCarpetToBlocks")));

        offerCompactingRecipe(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("smeltDirtToIron")),
                RecipeCategory.MISC,
                Items.RAW_IRON,
                SkysTheLimitItems.IRON_BLOOM_ITEM);

        new ShapedRecipeJsonBuilder(RecipeCategory.MISC, ItemsRegistry.ROPE.get(), 1)
                .group("rope")
                .pattern("s  ")
                .pattern(" s ")
                .pattern("  s")
                .input('s', Items.STRING)
                .criterion("has_string", InventoryChangedCriterion.Conditions.items(Items.STRING))
                .offerTo(withConditions(exporter,
                        DefaultResourceConditions.allModsLoaded(FarmersDelightMod.MOD_ID),
                        SkysTheLimitResourceConditions.allConfigBooleansEnabled("stringToRope")));

        new ShapedRecipeJsonBuilder(RecipeCategory.TRANSPORTATION, Items.SADDLE, 1)
                .group("saddle")
                .pattern("lll")
                .pattern("s s")
                .pattern("i i")
                .input('l', Items.LEATHER)
                .input('s', Items.STRING)
                .input('i', Items.IRON_INGOT)
                .criterion("has_leather", InventoryChangedCriterion.Conditions.items(Items.LEATHER))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("craftableSaddle")));

        new ShapedRecipeJsonBuilder(RecipeCategory.MISC, Items.STRING, 1)
                .group("string")
                .pattern("bb ")
                .pattern(" b ")
                .pattern(" bb")
                .input('b', ItemsRegistry.TREE_BARK.get())
                .criterion("has_bark", InventoryChangedCriterion.Conditions.items(ItemsRegistry.TREE_BARK.get()))
                .offerTo(withConditions(exporter,
                        DefaultResourceConditions.allModsLoaded(FarmersDelightMod.MOD_ID),
                        SkysTheLimitResourceConditions.allConfigBooleansEnabled("barkToString")));

        offerBlasting(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("smeltDirtToIron")),
                Collections.singletonList(Items.DIRT),
                RecipeCategory.MISC,
                SkysTheLimitItems.IRON_BLOOM_ITEM,
                0.1f, 100, "blasting");

        offerBlasting(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("blastCobbleToLava")),
                List.of(SkysTheLimitItems.STONE_BUCKET_ITEM),
                RecipeCategory.MISC,
                Items.LAVA_BUCKET,
                2.4f, 24000, "blasting");

        offerSmelting(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("smeltDirtToIron")),
                Collections.singletonList(Items.DIRT),
                RecipeCategory.MISC,
                SkysTheLimitItems.IRON_BLOOM_ITEM,
                0.1f, 200, "smelting");

        offerSmelting(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("snowToWater")),
                Collections.singletonList(Items.POWDER_SNOW_BUCKET),
                RecipeCategory.MISC,
                Items.WATER_BUCKET,
                0.1f, 200, "smelting");
    }

    @Override
    protected Identifier getRecipeIdentifier(Identifier identifier) {
        return Identifier.of(SkysTheLimit.MOD_ID, identifier.getPath());
    }
}
