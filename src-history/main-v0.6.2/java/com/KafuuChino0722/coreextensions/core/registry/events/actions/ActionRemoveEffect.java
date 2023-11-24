package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import com.KafuuChino0722.coreextensions.core.api.MethodStatusEffectCategory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

public class ActionRemoveEffect implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> SEKey = (Map<String, Object>) Key.getOrDefault("addStatusEffect", null);
        if(SEKey!=null) {
            Map<String, Object> KeyE = (Map<String, Object>) SEKey.getOrDefault("settings", null);
            for (Map.Entry<String, Object> entry : KeyE.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> BData = (Map<String, Object>) entry.getValue();
                    String effectIdentifier = (String) BData.getOrDefault("effectIdentifier","speed");
                    boolean T = false;
                    StatusEffect STR2SE = Registries.STATUS_EFFECT.get(new Identifier(effectIdentifier));
                    if(effectIdentifier.equalsIgnoreCase("clear") || effectIdentifier.equalsIgnoreCase("minecraft:clear")) {
                        user.clearStatusEffects();
                        T = true;
                    }
                    if(STR2SE!=null && !T) {
                        user.removeStatusEffect(STR2SE);
                    }
                }
            }
        }
    }
}
