package com.KafuuChino0722.coreextensions.core.registry.events.actions.special;

import com.KafuuChino0722.coreextensions.core.registry.events.actions.ActionInterface;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

public class ActionDamage implements ActionInterface {
    public static void run (PlayerEntity user, LivingEntity entity, Hand hand, Map<String, Object> Key) {
        Map<String, Object> SEKey = (Map<String, Object>) Key.getOrDefault("damageEntity", null);
        if(SEKey!=null) {
            String target = (String) SEKey.getOrDefault("target",null);
            double damage = (double) SEKey.getOrDefault("damage",1);
            if(target!=null) {
                if(target.equalsIgnoreCase("minecraft:self") || target.equalsIgnoreCase("self")) {
                    entity.damage(user.getRecentDamageSource(), (float) damage);
                } else {
                    user.damage(user.getRecentDamageSource(), (float) damage);
                }
            }
        }
    }
}
