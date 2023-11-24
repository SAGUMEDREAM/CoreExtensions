package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionRemoveHealth implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> HKey = (Map<String, Object>) Key.getOrDefault("removeHealth", null);
        if(HKey!=null) {
            PlayerEntity player = user.getCommandSource().getPlayer();
            double airIntS = (double) HKey.getOrDefault("value", 0);
            if (player != null && !player.getAbilities().creativeMode) {
                user.setHealth(user.getHealth() - (float) airIntS);
            }
        }
    }
}
