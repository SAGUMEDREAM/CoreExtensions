package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Map;

public class BlockStrongRedStonePower {
    public static boolean get(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Map<String, Object> blockKey) {
        if(blockKey.containsKey("redstone_power")) {
            int F_Count = 0;
            int a;
            int b;
            {
                a = state.getStrongRedstonePower(world, pos, Direction.UP);
                b = (int) blockKey.getOrDefault("redstone_power", 0);
                if(a >= b) return false;
            }
            {
                a = state.getStrongRedstonePower(world, pos, Direction.DOWN);
                b = (int) blockKey.getOrDefault("redstone_power", 0);
                if(a >= b) return false;
            }
            {
                a = state.getStrongRedstonePower(world, pos, Direction.NORTH);
                b = (int) blockKey.getOrDefault("redstone_power", 0);
                if(a >= b) return false;
            }
            {
                a = state.getStrongRedstonePower(world, pos, Direction.SOUTH);
                b = (int) blockKey.getOrDefault("redstone_power", 0);
                if(a >= b) return false;
            }
            {
                a = state.getStrongRedstonePower(world, pos, Direction.WEST);
                b = (int) blockKey.getOrDefault("redstone_power", 0);
                if(a >= b) return false;
            }
            {
                a = state.getStrongRedstonePower(world, pos, Direction.EAST);
                b = (int) blockKey.getOrDefault("redstone_power", 0);
                if(a >= b) return false;
            }

            return true;
        }
        return false;
    }
}
