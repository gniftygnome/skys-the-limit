package net.gnomecraft.skysthelimit.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks.*;

public class SkysTheLimitItems {
    public static final Identifier IRON_BLOOM_ITEM_ID = new Identifier(SkysTheLimit.MOD_ID, "iron_bloom");
    public static final Identifier STONE_BUCKET_ITEM_ID = new Identifier(SkysTheLimit.MOD_ID, "stone_bucket");

    public static BlockItem FOG_CATCHER_ITEM = Registry.register(Registries.ITEM, FOG_CATCHER_BLOCK_ID, new BlockItem(FOG_CATCHER_BLOCK, new Item.Settings()));
    public static Item IRON_BLOOM_ITEM = Registry.register(Registries.ITEM, IRON_BLOOM_ITEM_ID, new Item(new Item.Settings()));
    public static Item STONE_BUCKET_ITEM = Registry.register(Registries.ITEM, STONE_BUCKET_ITEM_ID, new StoneBucketItem(new Item.Settings()));

    public static void init() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register((content) -> {
            if (SkysTheLimit.getConfig().fogCatcher) {
                content.add(FOG_CATCHER_ITEM);
            }
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register((content) -> {
            if (SkysTheLimit.getConfig().smeltDirtToIron) {
                content.add(IRON_BLOOM_ITEM);
            }
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register((content) -> {
            if (SkysTheLimit.getConfig().blastCobbleToLava) {
                content.add(STONE_BUCKET_ITEM);
            }
        });
    }
}
