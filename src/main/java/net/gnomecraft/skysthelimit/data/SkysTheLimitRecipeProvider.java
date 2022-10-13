package net.gnomecraft.skysthelimit.data;

import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.nhoryzon.mc.farmersdelight.registry.ItemsRegistry;
import eu.midnightdust.motschen.rocks.RocksMain;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
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
import net.minecraft.util.Identifier;
import net.wondiws98.craftablelava.CraftableLavaMain;
import net.wondiws98.craftablelava.item.LavaCrucible;

import java.util.Collections;
import java.util.function.Consumer;

public class SkysTheLimitRecipeProvider extends FabricRecipeProvider {
    public SkysTheLimitRecipeProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateRecipes(Consumer<RecipeJsonProvider> exporter) {
        // Skys the Limit mod recipes:
        new ShapedRecipeJsonBuilder(SkysTheLimitItems.FOG_CATCHER_ITEM, 1)
                .group("fog_catcher")
                .pattern("i i")
                .pattern(" i ")
                .pattern("III")
                .input('I', Items.IRON_INGOT)
                .input('i', Items.IRON_NUGGET)
                .criterion("has_iron", InventoryChangedCriterion.Conditions.items(Items.IRON_INGOT))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("fogCatcher")));

        // Sky block support recipes:
        new ShapelessRecipeJsonBuilder(Items.COBBLESTONE, 1)
                .group("stone")
                .input(SkysTheLimitItemTags.COMMON_STONES)
                .input(SkysTheLimitItemTags.COMMON_STONES)
                .input(SkysTheLimitItemTags.COMMON_STONES)
                .input(SkysTheLimitItemTags.COMMON_STONES)
                .criterion("has_stone", InventoryChangedCriterion.Conditions.items(
                        ItemPredicate.Builder.create().tag(SkysTheLimitItemTags.COMMON_STONES).build()))
                .offerTo(withConditions(exporter,
                        DefaultResourceConditions.allModsLoaded(RocksMain.MOD_ID),
                        SkysTheLimitResourceConditions.allConfigBooleansEnabled("stonesToCobblestone")));

        new ShapelessRecipeJsonBuilder(Items.GRASS_BLOCK, 1)
                .group("dirt")
                .input(Items.DIRT)
                .input(Items.WHEAT_SEEDS)
                .criterion("has_seeds", InventoryChangedCriterion.Conditions.items(Items.WHEAT_SEEDS))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("dirtToGrassBlock")));

        new ShapedRecipeJsonBuilder(ItemsRegistry.ROPE.get(), 1)
                .group("rope")
                .pattern("s  ")
                .pattern(" s ")
                .pattern("  s")
                .input('s', Items.STRING)
                .criterion("has_string", InventoryChangedCriterion.Conditions.items(Items.STRING))
                .offerTo(withConditions(exporter,
                        DefaultResourceConditions.allModsLoaded(FarmersDelightMod.MOD_ID),
                        SkysTheLimitResourceConditions.allConfigBooleansEnabled("stringToRope")));

        new ShapedRecipeJsonBuilder(Items.SADDLE, 1)
                .group("saddle")
                .pattern("lll")
                .pattern("s s")
                .pattern("i i")
                .input('l', Items.LEATHER)
                .input('s', Items.STRING)
                .input('i', Items.IRON_INGOT)
                .criterion("has_leather", InventoryChangedCriterion.Conditions.items(Items.LEATHER))
                .offerTo(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("craftableSaddle")));

        new ShapelessRecipeJsonBuilder(LavaCrucible.CRUCIBLE, 1)
                .group("misc")
                .input(Items.BUCKET)
                .input(RocksMain.CobblestoneSplitter)
                .input(RocksMain.CobblestoneSplitter)
                .input(RocksMain.CobblestoneSplitter)
                .input(RocksMain.CobblestoneSplitter)
                .input(RocksMain.CobblestoneSplitter)
                .input(RocksMain.CobblestoneSplitter)
                .criterion("has_cobble_stones", InventoryChangedCriterion.Conditions.items(RocksMain.CobblestoneSplitter))
                .offerTo(withConditions(exporter,
                        DefaultResourceConditions.allModsLoaded(CraftableLavaMain.MOD_ID, RocksMain.MOD_ID),
                        SkysTheLimitResourceConditions.allConfigBooleansEnabled("stonesToStoneBucket")));

        new ShapedRecipeJsonBuilder(Items.STRING, 1)
                .group("string")
                .pattern("bb ")
                .pattern(" b ")
                .pattern(" bb")
                .input('b', ItemsRegistry.TREE_BARK.get())
                .criterion("has_bark", InventoryChangedCriterion.Conditions.items(ItemsRegistry.TREE_BARK.get()))
                .offerTo(withConditions(exporter,
                        DefaultResourceConditions.allModsLoaded(FarmersDelightMod.MOD_ID),
                        SkysTheLimitResourceConditions.allConfigBooleansEnabled("barkToString")));

        offerSmelting(withConditions(exporter, SkysTheLimitResourceConditions.allConfigBooleansEnabled("snowToWater")),
                Collections.singletonList(Items.POWDER_SNOW_BUCKET),
                Items.WATER_BUCKET,
                0.1f, 200, "smelting");
    }

    @Override
    protected Identifier getRecipeIdentifier(Identifier identifier) {
        return new Identifier(SkysTheLimit.MOD_ID, identifier.getPath());
    }
}
