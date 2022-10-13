package net.gnomecraft.skysthelimit.mixin;

import net.gnomecraft.skysthelimit.block.FogCatcherBlock;
import net.minecraft.block.Block;
import net.minecraft.block.PointedDripstoneBlock;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(PointedDripstoneBlock.class)
public class MixinPointedDripstoneBlock extends Block {
    public MixinPointedDripstoneBlock(Settings settings) {
        super(settings);
    }

    @Inject(method = "getDripPos",
            at = @At(value = "RETURN"),
            cancellable = true,
            locals = LocalCapture.NO_CAPTURE
    )
    private static void skysthelimit$extendGetDripPos(World world, BlockPos pos, CallbackInfoReturnable<BlockPos> cir) {
        // In lieu of getting cauldron blocks to call our getDripPos,
        // we make PointedDripstoneBlock check it for us.
        if (cir.getReturnValue() == null) {
            cir.setReturnValue(FogCatcherBlock.getDripPos(world, pos));
        }
    }

    @Inject(method = "getDripFluid(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/fluid/Fluid;",
            at = @At(value = "RETURN"),
            cancellable = true,
            locals = LocalCapture.NO_CAPTURE
    )
    private static void skysthelimit$extendGetDripFluid(ServerWorld world, BlockPos pos, CallbackInfoReturnable<Fluid> cir) {
        // In lieu of getting cauldron blocks to call our getDripFluid,
        // we make PointedDripstoneBlock check it for us.
        if (cir.getReturnValue() == Fluids.EMPTY) {
            cir.setReturnValue(FogCatcherBlock.getDripFluid(world, pos));
        }
    }
}
