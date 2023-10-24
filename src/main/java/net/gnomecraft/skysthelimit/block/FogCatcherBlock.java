package net.gnomecraft.skysthelimit.block;

import net.minecraft.block.*;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.biome.Biome;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class FogCatcherBlock extends Block {
    private static final VoxelShape DRIP_COLLISION_SHAPE = Block.createCuboidShape(6.0, 0.0, 6.0, 10.0, 16.0, 10.0);
    private static final float DRIP_CHANCE_WATER = 0.17578125f;  // PointedDripstoneBlock.field_31211

    public FogCatcherBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (random.nextFloat() <= 0.12f && !FogCatcherBlock.getDripFluid(world, pos).equals(Fluids.EMPTY)) {
            FogCatcherBlock.createParticle(world, pos, state);
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        FogCatcherBlock.dripTick(state, world, pos, random.nextFloat());
    }

    public static void dripTick(BlockState state, ServerWorld world, BlockPos pos, float dripChance) {
        float localizedDripChance = DRIP_CHANCE_WATER;

        // Fill rate adjustments.
        Biome biome = world.getBiome(pos).value();
        if (biome != null) {
            long time = world.getTimeOfDay();
            // Not during the day in non-precipitating biomes.
            if (biome.getTemperature() >= 0.95f && time < 18000) {
                localizedDripChance = 0.0f;
            }
            // Not at night in frozen biomes.
            else if (biome.getTemperature() <= 0.15f && time > 18000) {
                localizedDripChance = 0.0f;
            }
            // Twice as fast in the wettest biomes as in the driest.
            else {
                localizedDripChance *= 0.5f + 0.5f * world.getBiome(pos).value().weather.downfall();
            }
        }
        if (dripChance > localizedDripChance) {
            return;
        }

        // Must be dripping a real fluid.
        Fluid fluid = FogCatcherBlock.getDripFluid(world, pos);
        if (fluid.equals(Fluids.EMPTY)) {
            return;
        }

        // Must have a cauldron below.
        BlockPos cauldronPos = FogCatcherBlock.getCauldronPos(world, pos, fluid);
        if (cauldronPos == null) {
            return;
        }

        // Tell the cauldron to get to work.
        world.syncWorldEvent(1504, pos, 0);
        world.scheduleBlockTick(cauldronPos,
                world.getBlockState(cauldronPos).getBlock(),
                50 + pos.getY() - cauldronPos.getY());
    }

    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.union(
                Block.createCuboidShape(0, 1, 0, 16, 3, 16),
                Block.createCuboidShape(7, 0, 7, 9, 16, 9)
        );
    }

    @Override
    public boolean isShapeFullCube(BlockState state, BlockView world, BlockPos pos) {
        return false;
    }

    public static void createParticle(World world, BlockPos pos, BlockState state) {
        Vec3d lv = state.getModelOffset(world, pos);
        double d = 0.0625;
        double e = (double)pos.getX() + 0.5 + lv.x;
        double f = (double)pos.getY() - d;
        double g = (double)pos.getZ() + 0.5 + lv.z;
        DefaultParticleType lv3 = ParticleTypes.DRIPPING_DRIPSTONE_WATER;
        world.addParticle(lv3, e, f, g, 0.0, 0.0, 0.0);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return false;
    }

    @Nullable
    private static BlockPos getCauldronPos(World world, BlockPos pos, Fluid fluid) {
        Predicate<BlockState> predicate = state -> state.getBlock() instanceof AbstractCauldronBlock && ((AbstractCauldronBlock)state.getBlock()).canBeFilledByDripstone(fluid);
        BiPredicate<BlockPos, BlockState> biPredicate = (testPos, state) -> FogCatcherBlock.canDripThrough(world, testPos, state);
        return FogCatcherBlock.searchInDirection(world, pos, Direction.DOWN.getDirection(), biPredicate, predicate, 11).orElse(null);
    }

    @Nullable
    public static BlockPos getDripPos(World world, BlockPos pos) {
        BiPredicate<BlockPos, BlockState> biPredicate = (testPos, state) -> FogCatcherBlock.canDripThrough(world, testPos, state);
        return FogCatcherBlock.searchInDirection(world, pos, Direction.UP.getDirection(), biPredicate, (state) -> state.isOf(SkysTheLimitBlocks.FOG_CATCHER_BLOCK), 11).orElse(null);
    }

    public static Fluid getDripFluid(World world, BlockPos pos) {
        return world.isSkyVisible(pos) ? Fluids.WATER : Fluids.EMPTY;
    }

    private static Optional<BlockPos> searchInDirection(WorldAccess world, BlockPos pos, Direction.AxisDirection direction, BiPredicate<BlockPos, BlockState> continuePredicate, Predicate<BlockState> stopPredicate, int range) {
        Direction lv = Direction.get(direction, Direction.Axis.Y);
        BlockPos.Mutable lv2 = pos.mutableCopy();
        for (int j = 1; j < range; ++j) {
            lv2.move(lv);
            BlockState lv3 = world.getBlockState(lv2);
            if (stopPredicate.test(lv3)) {
                return Optional.of(lv2.toImmutable());
            }
            if (!world.isOutOfHeightLimit(lv2.getY()) && continuePredicate.test(lv2, lv3)) continue;
            return Optional.empty();
        }
        return Optional.empty();
    }

    private static boolean canDripThrough(BlockView world, BlockPos pos, BlockState state) {
        if (state.isAir()) {
            return true;
        }
        if (state.isOpaqueFullCube(world, pos)) {
            return false;
        }
        if (!state.getFluidState().isEmpty()) {
            return false;
        }
        VoxelShape lv = state.getCollisionShape(world, pos);
        return !VoxelShapes.matchesAnywhere(DRIP_COLLISION_SHAPE, lv, BooleanBiFunction.AND);
    }
}
