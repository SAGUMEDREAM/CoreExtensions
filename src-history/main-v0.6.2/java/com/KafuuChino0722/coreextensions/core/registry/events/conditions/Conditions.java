package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class Conditions {
    public static boolean getMatch() {
        return false;
    }

    public static boolean getCheckPlayer(PlayerEntity user, Map<String, Object> playerKey) {
        boolean fail;
        fail = PlayerHealth.get(user,playerKey);
        if(fail) return true;
        fail = PlayerArmor.get(user,playerKey);
        if(fail) return true;
        fail = PlayerAir.get(user,playerKey);
        if(fail) return true;
        fail = PlayerCreativeMode.get(user,playerKey);
        if(fail) return true;
        fail = PlayerClimbing.get(user,playerKey);
        if(fail) return true;
        fail = PlayerSneaking.get(user,playerKey);
        if(fail) return true;
        fail = PlayerPose.get(user,playerKey);
        if(fail) return true;
        fail = PlayerPose.get(user,playerKey);
        if(fail) return true;
        fail = PlayerHasEffect.get(user,playerKey);
        if(fail) return true;
        return false;
    }

    public static boolean getCheckBlock(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, Map<String, Object> blockKey) {
        boolean fail;
        fail = BlockPose.get(player, pos, blockKey);
        if(fail) return true;
        fail = BlockStrongRedStonePower.get(state, world, pos, player, hand, hit, blockKey);
        if(fail) return true;
        return false;
    }
    public static boolean getCheckBlock(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, Map<String, Object> blockKey) {
        boolean fail;
        fail = BlockPose.get(player, pos, blockKey);
        if(fail) return true;
        return false;
    }
}
