package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;
import static net.minecraft.enchantment.EnchantmentTarget.*;

public class EnchantRegistry {

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

    public static void register() {
        Map<String, Map<String, Object>> enchantData = IOFileManager.read("enchantment.yml");
        load(enchantData);
        Map<String, Map<String, Object>> enchantDataZ = IOFileManager.readZip("enchantment.yml");
        load(enchantDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("enchant")) {
            Map<String, Object> items = itemsData.get("enchant");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {

                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String name = (String) itemData.getOrDefault("name",entry.getKey());
                    String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                    String id = (String) itemData.get("id");
                    Map<String, Object> properties = itemData.containsKey("properties")? (Map<String, Object>) itemData.get("properties"):null;
                    String rarity = (String) properties.getOrDefault("rarity","COMMON");
                    Enchantment.Rarity rarityType = switch (rarity.toUpperCase()) {
                        case "COMMON" -> Enchantment.Rarity.COMMON;
                        case "UNCOMMON" -> Enchantment.Rarity.UNCOMMON;
                        case "RARE" -> Enchantment.Rarity.RARE;
                        case "EPIC", "VERY_RARE" -> Enchantment.Rarity.VERY_RARE;
                        default -> Enchantment.Rarity.COMMON;
                    };


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
                        switch (TargetSlotKeys.toUpperCase()) {
                            case "MAINHAND" -> slots = addSlot(slots, EquipmentSlot.MAINHAND);
                            case "OFFHAND" -> slots = addSlot(slots, EquipmentSlot.OFFHAND);
                            case "FEET" -> slots = addSlot(slots, EquipmentSlot.FEET);
                            case "LEGS" -> slots = addSlot(slots, EquipmentSlot.LEGS);
                            case "CHEST" -> slots = addSlot(slots, EquipmentSlot.CHEST);
                            case "HEAD" -> slots = addSlot(slots, EquipmentSlot.HEAD);
                            default -> {
                            }
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
                        if(Registries.ENCHANTMENT.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                            setRegistry.set(namespace, id, enchantment);
                        }
                    }

                    ReturnMessage.EnchantmentYMLRegister(name, namespace, id);

                    provider.add("enchantment." +namespace +"."+id, name);

                    if(Registries.ENCHANTMENT.containsId(new Identifier(namespace, id))) {
                        StorageRegistry.add(StorageRegistry.ENCHANTMENTS, new Identifier(namespace, id));
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

