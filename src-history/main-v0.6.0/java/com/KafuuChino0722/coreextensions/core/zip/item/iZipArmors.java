package com.KafuuChino0722.coreextensions.core.zip.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PIGLIN_LOVED;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static net.minecraft.item.ArmorItem.Type.*;

public class iZipArmors {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/armor.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> itemsData = yaml.load(new InputStreamReader(inputStream));

                                        if (itemsData != null && itemsData.containsKey("armors")) {
                                            Map<String, Object> items = itemsData.get("armors");

                                            for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                                if (itemEntry.getValue() instanceof Map) {
                                                    Map<String, Object> armorData = (Map<String, Object>) itemEntry.getValue();
                                                    String name = (String) armorData.get("name");
                                                    String lang_us = (String) armorData.get("lang_us");
                                                    String namespace = (String) armorData.get("namespace");
                                                    String id = (String) armorData.get("id");
                                                    String type = (String) armorData.get("types");
                                                    ArmorItem.Type typeArmor = null;

                                                    String typeArmorStr = type.toUpperCase();
                                                    if (Objects.equals(typeArmorStr, "HELMET")) {
                                                        typeArmor = HELMET;
                                                    } else if (Objects.equals(typeArmorStr, "CHESTPLATE")) {
                                                        typeArmor = CHESTPLATE;
                                                    } else if (Objects.equals(typeArmorStr, "LEGGINGS")) {
                                                        typeArmor = LEGGINGS;
                                                    } else if (Objects.equals(typeArmorStr, "BOOTS")) {
                                                        typeArmor = BOOTS;
                                                    } else {
                                                        typeArmor = HELMET;
                                                    }

                                                    Map<String, Object> properties = (Map<String, Object>) armorData.get("properties");
                                                    //ArmorItem.Type type = ArmorItem.Type.valueOf((String) armorData.get("types"));
                                                    Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("RepairIngredient", "minecraft:iron_ingot")));
                                                    int durability = properties.containsKey("Durability") ? (int) properties.get("Durability") : 250;
                                                    int protection = properties.containsKey("Protection") ? (int) properties.get("Protection") : 1;
                                                    double toughness = properties.containsKey("toughness") ? (double) properties.get("toughness") : 1.0;
                                                    double knockbackResistance = properties.containsKey("KnockbackResistance") ? (double) properties.get("KnockbackResistance") : 0.0;

                                                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                                                    ArmorMaterial material = new ArmorMaterial() {
                                                        @Override
                                                        public int getDurability(ArmorItem.Type type) {
                                                            return durability;
                                                        }

                                                        @Override
                                                        public int getProtection(ArmorItem.Type type) {
                                                            return protection;
                                                        }

                                                        @Override
                                                        public int getEnchantability() {
                                                            return 10; // 适当的附魔值
                                                        }

                                                        @Override
                                                        public SoundEvent getEquipSound() {
                                                            return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
                                                        }

                                                        @Override
                                                        public Ingredient getRepairIngredient() {
                                                            return Ingredient.ofItems(repairMaterialItem);
                                                        }

                                                        @Override
                                                        public String getName() {
                                                            return id;
                                                        }

                                                        @Override
                                                        public float getToughness() {
                                                            return (float) toughness;
                                                        }

                                                        @Override
                                                        public float getKnockbackResistance() {
                                                            return (float) knockbackResistance;
                                                        }
                                                    };

                                                    ArmorItem armorItem = new ArmorItem(material, typeArmor, new FabricItemSettings());
                                                    try {
                                                        Registry.register(Registries.ITEM, new Identifier(namespace, id), armorItem);
                                                    } catch (Exception e) {
                                                        setRegistry.set(namespace,id,armorItem);
                                                    }

                                                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                        RegItemGroupsContents.load(namespace,id,properties);
                                                    }

                                                    if (generate) {
                                                        Models.generate(namespace, id, "ITEM");
                                                    }

                                                    if(properties.containsKey("piglinLoved")) {
                                                        boolean piglinLoved;
                                                        if(properties.get("piglinLoved")!=null) {
                                                            piglinLoved = (boolean) properties.get("piglinLoved");
                                                            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                                if (piglinLoved) {
                                                                    TAG_PIGLIN_LOVED.add(new Identifier(namespace,id));
                                                                }
                                                            }
                                                        }
                                                    }

                                                    provider.add("item."+namespace+"."+id, name);

                                                    ReturnMessage.ArmorYMLRegister(name, namespace, id);


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
}