package com.KafuuChino0722.coreextensions.core.zip.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PIGLIN_LOVED;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static net.minecraft.enchantment.EnchantmentTarget.*;

public class iZipEnchantment {
    public static final String FILE = Reference.File;

    public static void load(String[] paths) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/enchantment.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("enchant")) {
                                            Map<String, Object> items = Data.get("enchant");

                                            for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                                if (itemEntry.getValue() instanceof Map) {
                                                    Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();
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