package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionSetAir implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> decrementKey = (Map<String, Object>) Key.getOrDefault("setAir", null);
        if(decrementKey!=null) {
            PlayerEntity player = user.getCommandSource().getPlayer();
            int airIntS = (int) decrementKey.getOrDefault("value", user.getMaxAir());
            if (player != null && !player.getAbilities().creativeMode) {
                user.setAir(airIntS);
            }
        }
    }
}
