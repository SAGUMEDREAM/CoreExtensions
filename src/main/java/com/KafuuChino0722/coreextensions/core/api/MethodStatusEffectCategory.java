package com.KafuuChino0722.coreextensions.core.api;

import net.minecraft.entity.effect.StatusEffectCategory;

public class MethodStatusEffectCategory {
    public static StatusEffectCategory getCategory(String categoryStr) {
        switch (categoryStr.toUpperCase()) {
            case "BENEFICIAL" -> {
                return StatusEffectCategory.BENEFICIAL;
            }
            case "HARMFUL" -> {
                return StatusEffectCategory.HARMFUL;
            }
            case "NEUTRAL" -> {
                return StatusEffectCategory.NEUTRAL;
            }
            default -> {
                return StatusEffectCategory.BENEFICIAL;
            }
        }
    }
}
