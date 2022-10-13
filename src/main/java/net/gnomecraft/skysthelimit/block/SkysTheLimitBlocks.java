package net.gnomecraft.skysthelimit.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class SkysTheLimitBlocks {
    public static final Identifier FOG_CATCHER_BLOCK_ID = new Identifier(SkysTheLimit.MOD_ID, "fog_catcher");
    public static Block FOG_CATCHER_BLOCK = Registry.register(Registry.BLOCK, FOG_CATCHER_BLOCK_ID, new FogCatcherBlock(FabricBlockSettings.of(Material.METAL).hardness(1.0f).ticksRandomly()));

    public static void init() {
    }
}
