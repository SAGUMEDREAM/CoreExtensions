package com.KafuuChino0722.coreextensions.fluid;

import com.KafuuChino0722.coreextensions.core.block.RegFluids;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;


public class CustomFluidUnzipOld extends FlowableFluid {

    public String KeyTitle;


    public CustomFluidUnzipOld(String KeyTitle) {
        super();
        this.KeyTitle = KeyTitle;
    }

    @Override
    public final Fluid getFlowing() {
        //return RegFluids.FLOWING_WATER;
        return Registries.FLUID.get((new Identifier((String) RegFluids.returnN(KeyTitle,"namespace"),"flowing_"+(String) RegFluids.returnN(KeyTitle,"id"))));
    }

    @Override
    public final Fluid getStill() {
        //return RegFluids.STILL_WATER;
        return Registries.FLUID.get((new Identifier((String) RegFluids.returnN(KeyTitle,"namespace"),(String) RegFluids.returnN(KeyTitle,"id"))));
    }

    // 是否可以生成无限水
    @Override
    protected final boolean isInfinite(World world) {
        return (boolean) RegFluids.returnO(KeyTitle,"isInfinite");
    }

    @Override
    protected void beforeBreakingBlock(WorldAccess world, BlockPos pos, BlockState state) {
        final BlockEntity blockEntity = state.hasBlockEntity()?world.getBlockEntity(pos) : null;
        Block.dropStacks(state,world,pos,blockEntity);
    }

    @Override
    protected final int getFlowSpeed(WorldView world) {
        return (int) RegFluids.returnO(KeyTitle,"flowSpeed");
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
    public final Item getBucketItem() {
        //return RegFluids.WATER_BUCKET;
        return Registries.ITEM.get(new Identifier((String) RegFluids.returnN(KeyTitle,"namespace"),(String) RegFluids.returnN(KeyTitle,"id")+"_bucket"));
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
        //return RegFluids.WATER_BLOCK.getDefaultState().with(Properties.LEVEL_15,getBlockStateLevel(state));
        try {
            return Registries.BLOCK.get(new Identifier((String) RegFluids.returnN(KeyTitle,"namespace"),(String) RegFluids.returnN(KeyTitle,"id"))).getDefaultState().with(Properties.LEVEL_15,getBlockStateLevel(state));
        } catch (Exception e) {
            return Blocks.WATER.getDefaultState().with(Properties.LEVEL_15,getBlockStateLevel(state));
        }

    }

    @Override
    public boolean isStill(FluidState state) {
        return false;
    }

    @Override
    public int getLevel(FluidState state) {
        return 0;
    }

    public static class Flowing extends CustomFluidUnzipOld {
        public Flowing(String KeyTitle) {
            super(KeyTitle);
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


    public static class Still extends CustomFluidUnzipOld {
        public Still(String KeyTitle) {
            super(KeyTitle);
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
