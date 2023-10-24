package net.gnomecraft.skysthelimit.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class SkysTheLimitBlocks {
    public static final Identifier FOG_CATCHER_BLOCK_ID = new Identifier(SkysTheLimit.MOD_ID, "fog_catcher");

    public static Block FOG_CATCHER_BLOCK = Registry.register(Registries.BLOCK, FOG_CATCHER_BLOCK_ID, new FogCatcherBlock(FabricBlockSettings.create().hardness(1.0f).mapColor(MapColor.IRON_GRAY).sounds(BlockSoundGroup.METAL).ticksRandomly()));

    public static void init() {
    }
}
