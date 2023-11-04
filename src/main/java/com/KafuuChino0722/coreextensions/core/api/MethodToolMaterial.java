package com.KafuuChino0722.coreextensions.core.api;

import com.KafuuChino0722.coreextensions.util.ModToolMaterials;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;

public class MethodToolMaterial {
    public static ToolMaterial getToolMaterial(String ToolMaterialStr) {
        switch (ToolMaterialStr.toLowerCase()) {
            case "0", "wood", "gold" -> {
                return ToolMaterials.WOOD;
            }
            case "1", "stone" -> {
                return ToolMaterials.STONE;
            }
            case "2", "iron" -> {
                return ToolMaterials.IRON;
            }
            case "3", "diamond" -> {
                return ToolMaterials.DIAMOND;
            }
            case "4", "netherite" -> {
                return ToolMaterials.NETHERITE;
            }
            case "custom" -> {
                return ModToolMaterials.CUSTOM;
            }
            default -> {
                return ToolMaterials.IRON;
            }
        }
    }
}