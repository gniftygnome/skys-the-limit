package net.gnomecraft.skysthelimit.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks;

public class SkysTheLimitBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected SkysTheLimitBlockLootTableProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateBlockLootTables() {
        // simple blocks
        addDrop(SkysTheLimitBlocks.FOG_CATCHER_BLOCK);
    }
}
