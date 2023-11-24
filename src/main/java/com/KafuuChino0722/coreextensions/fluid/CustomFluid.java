package com.KafuuChino0722.coreextensions.fluid;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class CustomFluid extends FlowableFluid {

    public FluidVoid Void;

    public CustomFluid(FluidVoid Void) {
        super();
        this.Void = Void;
    }

    @Override
    public Fluid getFlowing() {
        return this.Void.FLOWING_WATER;
    }

    @Override
    public Fluid getStill() {
        return this.Void.STILL_WATER;
    }

    // 是否可以生成无限水
    @Override
    protected final boolean isInfinite(World world) {
        return this.Void.isInfinite;
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity()?world.getBlockEntity(pos) : null;
        Block.dropStacks(state,world,pos,blockEntity);
    }

    @Override
    protected int getFlowSpeed(WorldView world) {
        return this.Void.flowSpeed;
    }

    @Override
    protected int getLevelDecreasePerBlock(WorldView world) {
        return 1;
    }

    @Override
    public boolean matchesType(Fluid fluid) {
        return fluid == getStill() || fluid == getFlowing();
    }

    @Override
    public Item getBucketItem() {
        //return RegFluids.WATER_BUCKET;
        return this.Void.WATER_BUCKET;
    }

    @Override
    protected boolean canBeReplacedWith(FluidState state, BlockView world, BlockPos pos, Fluid fluid, Direction direction) {
        return false;
    }
    // 方块的更新频率
    @Override
    public int getTickRate(WorldView world) {
        return 5;
    }

    // 爆炸抗性
    @Override
    protected float getBlastResistance() {
        return 100f;
    }

    @Override
    protected final BlockState toBlockState(FluidState state) {
        return this.Void.WATER_BLOCK.getDefaultState().with(Properties.LEVEL_15,getBlockStateLevel(state));

    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    public static class Flowing extends CustomFluid {
        public Flowing(FluidVoid Void) {
            super(Void);
        }

        @Override
        protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
            super.appendProperties(builder);
            builder.add(LEVEL);
        }

        @Override
        public int getLevel(FluidState state) {
            return state.get(LEVEL);
        }

        @Override
        public boolean isStill(FluidState state) {
            return false;
        }
    }

    public static class Still extends CustomFluid {
        public Still(FluidVoid Void) {
            super(Void);
        }

        @Override
        public int getLevel(FluidState state) {
            return 8;
        }

        @Override
        public boolean isStill(FluidState state) {
            return true;
        }
    }

}
