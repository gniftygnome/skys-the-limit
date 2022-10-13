package net.gnomecraft.skysthelimit;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.gnomecraft.skysthelimit.block.SkysTheLimitBlocks;
import net.minecraft.client.render.RenderLayer;

@Environment(EnvType.CLIENT)
public class SkysTheLimitClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        // Rendering of transparent and translucent blocks.
        BlockRenderLayerMap.INSTANCE.putBlock(SkysTheLimitBlocks.FOG_CATCHER_BLOCK, RenderLayer.getTranslucent());
    }
}
