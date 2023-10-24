package net.gnomecraft.skysthelimit.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks;

public class SkysTheLimitBlockLootTableProvider extends FabricBlockLootTableProvider {
    protected SkysTheLimitBlockLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        // simple blocks
        addDrop(SkysTheLimitBlocks.FOG_CATCHER_BLOCK);
    }
}
