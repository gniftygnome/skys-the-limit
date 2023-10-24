package net.gnomecraft.skysthelimit.data;

import eu.midnightdust.motschen.rocks.RocksMain;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitBlockTags;
import net.gnomecraft.skysthelimit.tag.SkysTheLimitItemTags;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SkysTheLimitItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public SkysTheLimitItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture, @Nullable BlockTagProvider blockTagProvider) {
        super(output, completableFuture, blockTagProvider);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        // Make sure This Rocks! splitters are in c:stones
        this.getOrCreateTagBuilder(SkysTheLimitItemTags.COMMON_STONES)
                .add(RocksMain.CobblestoneSplitter)
                .add(RocksMain.AndesiteSplitter)
                .add(RocksMain.DioriteSplitter)
                .add(RocksMain.GraniteSplitter);

        // Copy c:stone from the block tag
        this.copy(SkysTheLimitBlockTags.COMMON_STONE, SkysTheLimitItemTags.COMMON_STONE);

        // Add c:stone to the stone tool crafting materials
        this.getOrCreateTagBuilder(ItemTags.STONE_TOOL_MATERIALS)
                .addTag(SkysTheLimitItemTags.COMMON_STONE);
    }
}
