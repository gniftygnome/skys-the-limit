package net.gnomecraft.skysthelimit.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks;
import net.minecraft.tag.BlockTags;

public class SkysTheLimitBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public SkysTheLimitBlockTagProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    protected void generateTags() {
        // basic block tags
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(SkysTheLimitBlocks.FOG_CATCHER_BLOCK);
    }
}
