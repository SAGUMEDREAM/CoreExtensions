package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionSetFireTick implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> HKey = (Map<String, Object>) Key.getOrDefault("setFire", null);
        if(HKey!=null) {
            PlayerEntity player = user.getCommandSource().getPlayer();
            int ints = (int) HKey.getOrDefault("value", user.getFireTicks());
            if (player != null && !player.getAbilities().creativeMode) {
                user.setFireTicks(ints);
            }
        }
    }
}
