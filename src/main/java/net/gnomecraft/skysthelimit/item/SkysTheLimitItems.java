package net.gnomecraft.skysthelimit.item;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;

import static net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks.*;

public class SkysTheLimitItems {
    public static BlockItem FOG_CATCHER_ITEM = Registry.register(Registry.ITEM, FOG_CATCHER_BLOCK_ID, new BlockItem(FOG_CATCHER_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));

    public static void init() {
    }
}
