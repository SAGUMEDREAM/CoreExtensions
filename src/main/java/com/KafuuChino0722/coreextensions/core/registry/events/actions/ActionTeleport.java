package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionTeleport implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> TKey = (Map<String, Object>) Key.getOrDefault("teleport", null);
        if(TKey!=null) {
            PlayerEntity player = user.getCommandSource().getPlayer();
            double pX = user.getX();
            double pY = user.getY();
            double pZ = user.getZ();
            double X = (double) TKey.getOrDefault("X",user.getMaxAir());
            double Y = (double) TKey.getOrDefault("Y",user.getMaxAir());
            double Z = (double) TKey.getOrDefault("Z",user.getMaxAir());
            if (player != null) {
                user.teleport(X,Y,Z);
            }
        }
    }
}
