package com.KafuuChino0722.coreextensions.core.api;

import java.util.Map;

public class LootTableBuilder {
    public static void Build(String namespace, String id, String types, Map<String, Object> blockData, Map<String, Object> properties) {
        switch (types.toLowerCase()) {
            case "cube_all", "cubeall" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "cube" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "button" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "door" -> {
                MethodLootBuilder.addDoor(namespace, id, blockData, properties);
            }
            case "fence" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "fence_gate", "fencegate" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "pressure_plate", "pressureplate" -> {
                MethodLootBuilder.addDropPath(namespace, id, "blocks/", blockData, properties);
            }
            case "slab" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "uprightslab", "upright_slab" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "stairs" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "trapdoor" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "wall" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "sand" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "soulsand", "soul_sand" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "fire" -> {}
            case "log" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
                MethodLootBuilder.addDrop(namespace, "stripped_"+id, blockData, properties);
            }
            case "leaves" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "torch" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "lamp", "redstone_lamp", "redstonelamp" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "power","powerblock","power_block" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "bed" -> {}
            case "chest" -> {
                MethodLootBuilder.addDrop(namespace, id, blockData, properties);
            }
            case "glass" -> {
                MethodLootBuilder.addDropWithSilkTouch(namespace, id, blockData, properties);
            }
            case "glass_pane","glasspane" -> {
                MethodLootBuilder.addDropWithSilkTouch(namespace, id, blockData, properties);
            }
        }
    }
}
