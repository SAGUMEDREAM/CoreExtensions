package com.KafuuChino0722.coreextensions.core.api;

import net.minecraft.block.MapColor;

import static net.minecraft.block.MapColor.*;

public class MethodMapColor {
    public static MapColor get(String type) {

        String ToLow = type.toLowerCase();
        
        switch (ToLow.toUpperCase()) {
            case "CLEAR" -> {
                return CLEAR;
            }
            case "PALE_GREEN" -> {
                return PALE_GREEN;
            }
            case "PALE_YELLOW" -> {
                return PALE_YELLOW;
            }
            case "WHITE_GRAY" -> {
                return WHITE_GRAY;
            }
            case "BRIGHT_RED" -> {
                return BRIGHT_RED;
            }
            case "PALE_PURPLE" -> {
                return PALE_PURPLE;
            }
            case "IRON_GRAY" -> {
                return IRON_GRAY;
            }
            case "DARK_GREEN" -> {
                return DARK_GREEN;
            }
            case "WHITE" -> {
                return WHITE;
            }
            case "LIGHT_BLUE_GRAY" -> {
                return LIGHT_BLUE_GRAY;
            }
            case "DIRT_BROWN" -> {
                return DIRT_BROWN;
            }
            case "STONE_GRAY" -> {
                return STONE_GRAY;
            }
            case "WATER_BLUE" -> {
                return WATER_BLUE;
            }
            case "OAK_TAN" -> {
                return OAK_TAN;
            }
            case "OFF_WHIT" -> {
                return OFF_WHITE;
            }
            case "ORANGE" -> {
                return ORANGE;
            }
            case "MAGENTA" -> {
                return MAGENTA;
            }
            case "LIGHT_BLUE" -> {
                return LIGHT_BLUE;
            }
            case "YELLOW" -> {
                return YELLOW;
            }
            case "LIME" -> {
                return LIME;
            }
            case "PINK" -> {
                return PINK;
            }
            case "GRAY" -> {
                return GRAY;
            }
            case "LIGHT_GRAY" -> {
                return LIGHT_GRAY;
            }
            case "CYAN" -> {
                return CYAN;
            }
            case "PURPLE" -> {
                return PURPLE;
            }
            case "BLUE" -> {
                return BLUE;
            }
            case "BROWN" -> {
                return BROWN;
            }
            case "GREEN" -> {
                return GREEN;
            }
            case "RED" -> {
                return RED;
            }
            case "BLACK" -> {
                return BLACK;
            }
            case "GOLD" -> {
                return GOLD;
            }
            case "DIAMOND_BLUE" -> {
                return DIAMOND_BLUE;
            }
            case "LAPIS_BLUE" -> {
                return LAPIS_BLUE;
            }
            case "EMERALD_GREEN" -> {
                return EMERALD_GREEN;
            }
            case "SPRUCE_BROWN" -> {
                return SPRUCE_BROWN;
            }
            case "DARK_RED" -> {
                return DARK_RED;
            }
            case "TERRACOTTA_WHITE" -> {
                return TERRACOTTA_WHITE;
            }
            case "TERRACOTTA_ORANGE" -> {
                return TERRACOTTA_ORANGE;
            }
            case "TERRACOTTA_MAGENTA" -> {
                return TERRACOTTA_MAGENTA;
            }
            case "TERRACOTTA_LIGHT_BLUE" -> {
                return TERRACOTTA_LIGHT_BLUE;
            }
            case "TERRACOTTA_YELLOW" -> {
                return TERRACOTTA_YELLOW;
            }
            case "TERRACOTTA_LIME" -> {
                return TERRACOTTA_LIME;
            }
            case "TERRACOTTA_PINK" -> {
                return TERRACOTTA_PINK;
            }
            case "TERRACOTTA_GRAY" -> {
                return TERRACOTTA_GRAY;
            }
            case "TERRACOTTA_LIGHT_GRAY" -> {
                return TERRACOTTA_LIGHT_GRAY;
            }
            case "TERRACOTTA_CYAN " -> {
                return TERRACOTTA_CYAN;
            }
            case "TERRACOTTA_PURPLE" -> {
                return TERRACOTTA_PURPLE;
            }
            case "TERRACOTTA_BLUE" -> {
                return TERRACOTTA_BLUE;
            }
            case "TERRACOTTA_BROWN" -> {
                return TERRACOTTA_BROWN;
            }
            case "TERRACOTTA_GREEN" -> {
                return TERRACOTTA_GREEN;
            }
            case "TERRACOTTA_RED" -> {
                return TERRACOTTA_RED;
            }
            case "TERRACOTTA_BLACK" -> {
                return TERRACOTTA_BLACK;
            }
            case "DULL_RED" -> {
                return DULL_RED;
            }
            case "DULL_PINK" -> {
                return DULL_PINK;
            }
            case "DARK_CRIMSON" -> {
                return DARK_CRIMSON;
            }
            case "TEAL" -> {
                return TEAL;
            }
            case "DARK_AQUA" -> {
                return DARK_AQUA;
            }
            case "DARK_DULL_PINK" -> {
                return DARK_DULL_PINK;
            }
            case "BRIGHT_TEAL" -> {
                return BRIGHT_TEAL;
            }
            case "DEEPSLATE_GRAY" -> {
                return DEEPSLATE_GRAY;
            }
            case "RAW_IRON_PINK" -> {
                return RAW_IRON_PINK;
            }
            case "LICHEN_GREEN" -> {
                return LICHEN_GREEN;
            }
        }
        return MapColor.CLEAR;
        
    }
}
