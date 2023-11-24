package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionDamageItem implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> damageKey = (Map<String, Object>) Key.getOrDefault("damageItem", null);
        if(damageKey!=null) {
            PlayerEntity player = user.getCommandSource().getPlayer();
            int Int = (int) damageKey.getOrDefault("amount",0);
            if (player != null) {
                if (player.getActiveItem().isDamageable() && !player.getAbilities().creativeMode) {
                    user.getActiveItem().damage(Int, user, p -> p.sendToolBreakStatus(hand));
                }
            }
        }
    }
}
