package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class PlayerHasEffect implements ConditionsInterface{

    public static boolean get(LivingEntity user, Map<String, Object> Key) {
        List<String> effects = (List<String>) Key.getOrDefault("hasStatusEffect", null);
        if (effects != null) {
            ItemStack itemStack = user.getActiveItem();
            if (effects.contains("*")) return false;
            for (String Str : effects) {
                StatusEffect targetStatusEffect = Registries.STATUS_EFFECT.get(new Identifier(Str));
                if (Registries.STATUS_EFFECT.containsId(new Identifier(Str))) {
                    if (user.hasStatusEffect(targetStatusEffect)) {
                        return false;
                    }
                }
            }
            return true;
        }
        return false;
    }
}
