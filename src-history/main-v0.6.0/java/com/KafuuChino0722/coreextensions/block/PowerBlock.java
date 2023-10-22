package com.KafuuChino0722.coreextensions.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;

public class PowerBlock
        extends Block {
    public int power;
    public PowerBlock(AbstractBlock.Settings settings, int power) {
        super(settings);
        this.power = power;
    }

    @Override
    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    @Override
    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return this.power;
    }
}

