package net.gnomecraft.skysthelimit.loot;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.gnomecraft.skysthelimit.config.SkysTheLimitConfig;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitItemTags;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.TagEntry;

public class SkysTheLimitFishingLoot {
    public static void init() {
        SkysTheLimitConfig config = SkysTheLimit.getConfig();

        LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) -> {
            // Add copper and prismarine things to fishing loot.
            if (config.oceanLoot) {
                if (source.isBuiltin() && LootTables.FISHING_TREASURE_GAMEPLAY.equals(id)) {
                    tableBuilder.modifyPools((pools) -> {
                        pools.with(ItemEntry.builder(Items.COPPER_INGOT))
                                .with(ItemEntry.builder(Items.PRISMARINE_CRYSTALS))
                                .with(ItemEntry.builder(Items.PRISMARINE_SHARD).weight(2));
                    });
                }
            }

            // Add This Rocks mod rock splitters to fishing loot if the mod is loaded.
            if (config.rocksLoot && FabricLoader.getInstance().isModLoaded("rocks")) {
                if (source.isBuiltin() && LootTables.FISHING_JUNK_GAMEPLAY.equals(id)) {
                    tableBuilder.modifyPools((pools) -> {
                        pools.with(TagEntry.expandBuilder(SkysTheLimitItemTags.COMMON_STONES).weight(10));
                    });
                }
            }
        });
    }
}