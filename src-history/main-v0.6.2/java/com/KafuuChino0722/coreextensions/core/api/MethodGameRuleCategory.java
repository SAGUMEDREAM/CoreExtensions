package com.KafuuChino0722.coreextensions.core.api;

import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

import static net.minecraft.world.GameRules.Category.*;

public class MethodGameRuleCategory extends GameRules {
    public static GameRules.Category getCatergory(String Identifier) {
        switch (Identifier.toLowerCase()) {
            case "gamerule.category.player","player" -> {
                return PLAYER;
            }
            case "gamerule.category.mobs","mobs" -> {
                return MOBS;
            }
            case "gamerule.category.spawning","spawning" -> {
                return SPAWNING;
            }
            case "gamerule.category.drops","drops" -> {
                return DROPS;
            }
            case "gamerule.category.updates","updates" -> {
                return UPDATES;
            }
            case "gamerule.category.chat","chat" -> {
                return CHAT;
            }
            case "gamerule.category.misc","misc" -> {
                return MISC;
            }
            default -> {
                return MISC;
            }
        }
    }
}
