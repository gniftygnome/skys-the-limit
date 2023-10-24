package net.gnomecraft.skysthelimit.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitBlockTags;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class SkysTheLimitBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public SkysTheLimitBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // basic block tags
        this.getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(SkysTheLimitBlocks.FOG_CATCHER_BLOCK);

        // Make sure vanilla overworld base stone is in c:stone
        this.getOrCreateTagBuilder(SkysTheLimitBlockTags.COMMON_STONE)
                .add(Blocks.STONE)
                .add(Blocks.DEEPSLATE)
                .add(Blocks.ANDESITE)
                .add(Blocks.DIORITE)
                .add(Blocks.GRANITE);
    }
}
