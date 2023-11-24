package com.KafuuChino0722.coreextensions.core.api;

import net.minecraft.util.Rarity;

public class MethodRarity {
    public static Rarity getRarity(String RarityStr) {
        switch (RarityStr.toLowerCase()) {
            case "common" -> {
                return Rarity.COMMON;
            }
            case "uncommon" -> {
                return Rarity.UNCOMMON;
            }
            case "rare" -> {
                return Rarity.RARE;
            }
            case "epic" -> {
                return Rarity.EPIC;
            }
        }
        return Rarity.COMMON;
    }
}
