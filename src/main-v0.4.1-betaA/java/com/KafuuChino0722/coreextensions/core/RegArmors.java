package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static net.minecraft.item.ArmorItem.Type.*;
import static net.minecraft.item.ArmorItem.Type.BOOTS;

public class RegArmors {
    public static final String FILE = Reference.File;

    //public static final RuntimeResourcePack pack = RuntimeResourcePack.create(new Identifier("coreextensions", "items"));

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/armor.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("armors")) {
                                Map<String, Object> items = itemsData.get("armors");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> armorData = (Map<String, Object>) entry.getValue();
                                        String name = (String) armorData.get("name");
                                        String lang_us = (String) armorData.get("lang_us");
                                        String namespace = (String) armorData.get("namespace");
                                        String id = (String) armorData.get("id");
                                        String type = (String) armorData.get("types");
                                        ArmorItem.Type typeArmor = null;

                                        String typeArmorStr = type.toUpperCase();
                                        if(Objects.equals(typeArmorStr, "HELMET")) {
                                            typeArmor = HELMET;
                                        } else if(Objects.equals(typeArmorStr, "CHESTPLATE")) {
                                            typeArmor = CHESTPLATE;
                                        } else if(Objects.equals(typeArmorStr, "LEGGINGS")) {
                                            typeArmor = LEGGINGS;
                                        } else if(Objects.equals(typeArmorStr, "BOOTS")) {
                                            typeArmor = BOOTS;
                                        } else {
                                            typeArmor = HELMET;
                                        }

                                        Map<String, Object> properties = (Map<String, Object>) armorData.get("properties");
                                        //ArmorItem.Type type = ArmorItem.Type.valueOf((String) armorData.get("types"));
                                        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("RepairIngredient","minecraft:iron_ingot")));
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
                                                return namespace + ":" + id;
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

                                            Registry.register(Registries.ITEM, new Identifier(namespace, id), armorItem);

                                            String typee = "ITEM";
                                            if (generate) {
                                                Models.generate(namespace, id, typee);
                                            }

                                        RegItemGroupsContents.load(namespace,id,properties);

                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_armoritem_lang"));

                                        RRPCallback.BEFORE_VANILLA.register(b -> {
                                            packs.clearResources();
                                            packs.addLang(new Identifier(namespace, "en_us"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, lang_us));
                                            packs.addLang(new Identifier(namespace, "zh_cn"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, name));
                                            b.add(packs);
                                        });

                                        ReturnMessage.ArmorYMLRegister(name,namespace,id);

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