package net.gnomecraft.skysthelimit.mixin;

import net.gnomecraft.skysthelimit.SkysTheLimit;
import net.minecraft.block.Blocks;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EnderDragonFight.class)
public class MixinEnderDragonFight {
    @Shadow
    @Final
    private BlockPos origin;

    @Shadow
    private boolean previouslyKilled;

    @Shadow
    @Final
    private ServerWorld world;

    @Inject(method = "dragonKilled", at = @At("HEAD"))
    private void skysthelimit$alwaysDragonEgg(EnderDragonEntity dragon, CallbackInfo ci) {
        if (SkysTheLimit.getConfig().alwaysDragonEgg && previouslyKilled) {
            world.setBlockState(
                    world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, EndPortalFeature.offsetOrigin(origin)),
                    Blocks.DRAGON_EGG.getDefaultState());
        }
    }
}
