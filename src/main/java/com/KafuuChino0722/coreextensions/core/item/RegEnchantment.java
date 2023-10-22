package com.KafuuChino0722.coreextensions.core.item;

import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static net.minecraft.enchantment.EnchantmentTarget.*;

public class RegEnchantment {
    public static final String FILE = Reference.File;

    public static void test() {
        Enchantment enchantment = new Enchantment(Enchantment.Rarity.UNCOMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[] {EquipmentSlot.MAINHAND}) {
            @Override
            public int getMinPower(int level) {
                return 1;
            }
            @Override
            public int getMaxLevel() {
                return 3;
            }
        };

        Registry.register(Registries.ENCHANTMENT, new Identifier("tutorial", "frost"),enchantment);
    }
    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/enchantment.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("enchant")) {
                                Map<String, Object> items = itemsData.get("enchant");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {

                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                                        String name = (String) itemData.get("name");
                                        String lang_us = (String) itemData.get("name");
                                        String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                                        String id = (String) itemData.get("id");
                                        Map<String, Object> properties = itemData.containsKey("properties")? (Map<String, Object>) itemData.get("properties"):null;
                                        String rarity = (String) properties.getOrDefault("rarity","COMMON");
                                        Enchantment.Rarity rarityType = null;
                                        if (rarity.equalsIgnoreCase("COMMON")) {
                                            rarityType = Enchantment.Rarity.COMMON;
                                        } else if (rarity.equalsIgnoreCase("UNCOMMON")) {
                                            rarityType = Enchantment.Rarity.UNCOMMON;
                                        } else if (rarity.equalsIgnoreCase("RARE")) {
                                            rarityType = Enchantment.Rarity.RARE;
                                        } else if (rarity.equalsIgnoreCase("EPIC")||rarity.equalsIgnoreCase("VERY_RARE")) {
                                            rarityType = Enchantment.Rarity.VERY_RARE;
                                        } else {
                                            rarityType = Enchantment.Rarity.COMMON;
                                        }

                                        String Target = (String) properties.getOrDefault("target","NULL");
                                        EnchantmentTarget EnchantmentTargetType = null;
                                        switch (Target.toUpperCase()) {
                                            case "ARMOR" -> EnchantmentTargetType = ARMOR;
                                            case "ARMOR_FEET" -> EnchantmentTargetType = ARMOR_FEET;
                                            case "ARMOR_LEGS" -> EnchantmentTargetType = ARMOR_LEGS;
                                            case "ARMOR_CHEST" -> EnchantmentTargetType = ARMOR_CHEST;
                                            case "ARMOR_HEAD" -> EnchantmentTargetType = ARMOR_HEAD;
                                            case "WEAPON" -> EnchantmentTargetType = WEAPON;
                                            case "DIGGER" -> EnchantmentTargetType = DIGGER;
                                            case "FISHING_ROD" -> EnchantmentTargetType = FISHING_ROD;
                                            case "TRIDENT" -> EnchantmentTargetType = TRIDENT;
                                            case "BREAKABLE" -> EnchantmentTargetType = BREAKABLE;
                                            case "BOW" -> EnchantmentTargetType = BOW;
                                            case "CROSSBOW" -> EnchantmentTargetType = CROSSBOW;
                                            case "VANISHABLE" -> EnchantmentTargetType = VANISHABLE;
                                            default -> {
                                            }
                                        }
                                        List<String> TargetSlots = (List<String>) properties.get("slots");
                                        //String TargetSlots = (String) properties.getOrDefault("slots","NULL");
                                        EquipmentSlot[] slots = new EquipmentSlot[] {};

                                        for(String TargetSlotKeys : TargetSlots) {
                                            if (TargetSlotKeys.toUpperCase().equals("MAINHAND")) {
                                                slots = addSlot(slots, EquipmentSlot.MAINHAND);
                                            }
                                            if (TargetSlotKeys.toUpperCase().equals("OFFHAND")) {
                                                slots = addSlot(slots, EquipmentSlot.OFFHAND);
                                            }
                                            if (TargetSlotKeys.toUpperCase().equals("FEET")) {
                                                slots = addSlot(slots, EquipmentSlot.FEET);
                                            }
                                            if (TargetSlotKeys.toUpperCase().equals("LEGS")) {
                                                slots = addSlot(slots, EquipmentSlot.LEGS);
                                            }
                                            if (TargetSlotKeys.toUpperCase().equals("CHEST")) {
                                                slots = addSlot(slots, EquipmentSlot.CHEST);
                                            }
                                            if (TargetSlotKeys.toUpperCase().equals("HEAD")) {
                                                slots = addSlot(slots, EquipmentSlot.HEAD);
                                            }
                                        }

                                        int MinPower = (int) properties.getOrDefault("MinPower",1);
                                        int MaxLevel = (int) properties.getOrDefault("MaxLevel",3);

                                        Enchantment enchantment = new Enchantment(rarityType, EnchantmentTargetType, slots) {
                                            @Override
                                            public int getMinPower(int level) {
                                                return MinPower;
                                            }
                                            @Override
                                            public int getMaxLevel() {
                                                return MaxLevel;
                                            }
                                        };
                                        try {
                                            Registry.register(Registries.ENCHANTMENT, new Identifier(namespace, id), enchantment);
                                        } catch (Exception e) {
                                            setRegistry.set(namespace,id,enchantment);
                                        }

                                        ReturnMessage.EnchantmentYMLRegister(name, namespace, id);

                                        provider.add("enchantment." +namespace +"."+id, name);

                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    public static EquipmentSlot[] addSlot(EquipmentSlot[] array, EquipmentSlot newSlot) {
        EquipmentSlot[] newArray = new EquipmentSlot[array.length + 1];
        System.arraycopy(array, 0, newArray, 0, array.length);
        newArray[newArray.length - 1] = newSlot;
        return newArray;
    }
}

