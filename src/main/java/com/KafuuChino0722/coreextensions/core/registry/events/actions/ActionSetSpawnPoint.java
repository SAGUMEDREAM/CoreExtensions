package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;

import java.util.Map;

public class ActionSetSpawnPoint implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> HKey = (Map<String, Object>) Key.getOrDefault("setSpawnPoint", null);
        if(HKey!=null) {
            ServerPlayerEntity player = user.getCommandSource().getPlayer();
            boolean a = (boolean) HKey.getOrDefault("enable",false);
            if (player != null && a) {
                RegistryKey<World> Dim = player.getSpawnPointDimension();
                BlockPos BlPos = player.getBlockPos();
                player.setSpawnPoint(Dim, BlPos,0F,true,false);
            }
        }
    }
}
