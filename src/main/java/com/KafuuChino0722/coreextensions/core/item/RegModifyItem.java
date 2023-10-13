package com.KafuuChino0722.coreextensions.core.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.item.ClickItem;
import com.KafuuChino0722.coreextensions.item.CustomItem;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.SnowballItem;
import net.minecraft.item.StewItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.PlatformBridge;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class RegModifyItem {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/item@Overwrite.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("items")) {
                                Map<String, Object> items = itemsData.get("items");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {

                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                                        String name = (String) itemData.get("name");
                                        String lang_us = (String) itemData.get("name");
                                        //String namespace = (String) itemData.get("namespace");
                                        String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                                        String id = (String) itemData.get("id");
                                        String types = itemData.containsKey("types") ? (String) itemData.get("types") : "item";
                                        int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 64;
                                        Map<String, Object> properties = itemData.containsKey("properties")? (Map<String, Object>) itemData.get("properties"):null;
                                        int maxDamage = properties.containsKey("maxDamage") ? (int) properties.get("maxDamage") : 0;
                                        boolean fireproof = properties.containsKey("fireproof") ? (boolean) properties.get("fireproof") : false;
                                        Item item = IdentifierManager.getItem(namespace,id);
                                        FabricItemSettings ModifyItemSettings = new FabricItemSettings();

                                        String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";

                                        Rarity rarityType = null;
                                        if (Objects.equals(rarity, "COMMON") || Objects.equals(rarity, "common")) {
                                            rarityType = Rarity.COMMON;
                                        } else if (Objects.equals(rarity, "UNCOMMON") || Objects.equals(rarity, "uncommon")) {
                                            rarityType = Rarity.UNCOMMON;
                                        } else if (Objects.equals(rarity, "RARE") || Objects.equals(rarity, "rare")) {
                                            rarityType = Rarity.RARE;
                                        } else if (Objects.equals(rarity, "EPIC") || Objects.equals(rarity, "epic")) {
                                            rarityType = Rarity.EPIC;
                                        } else {
                                            rarityType = Rarity.COMMON;
                                        }

                                        if(itemData.containsKey("maxCount")) {
                                            ModifyItemSettings.maxCount(maxCount);
                                        }

                                        if(properties.containsKey("maxDamage")) {
                                            ModifyItemSettings.maxDamage(maxDamage);
                                        }

                                        if(properties.containsKey("fireproof")) {
                                            if(fireproof) {
                                                ModifyItemSettings.fireproof();
                                            }
                                        }

                                        if(properties.containsKey("rarity")) {
                                            ModifyItemSettings.rarity(rarityType);
                                        }

                                        if(types.equalsIgnoreCase("food") || types.equalsIgnoreCase("stewitem")) {
                                            FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder();
                                            if(properties.containsKey("hunger")) {
                                                int hunger = properties.containsKey("hunger") ? (int) properties.get("hunger") : 1;
                                                foodComponentBuilder.hunger((int) hunger);
                                                ModifyItemSettings.food(foodComponentBuilder.build());
                                            }
                                            if(properties.containsKey("hunger")) {
                                                double saturationModifier = properties.containsKey("saturationModifier") ? (double) properties.get("saturationModifier") : 1.0;
                                                foodComponentBuilder.saturationModifier((float) saturationModifier);
                                                ModifyItemSettings.food(foodComponentBuilder.build());
                                            }
                                            if(properties.containsKey("meat")) {
                                                boolean isMeat = properties.containsKey("meat") ? (boolean) properties.get("meat") : false;
                                                if (isMeat) {
                                                    foodComponentBuilder.meat();
                                                }
                                            }
                                            ModifyItemSettings.food(foodComponentBuilder.build());
                                        }

                                        Item setItem;
                                        if(types.equalsIgnoreCase("item")) {
                                            setItem = new Item(ModifyItemSettings);
                                            setRegistry.set(namespace,id,setItem);
                                        } else if(types.equalsIgnoreCase("clickitem")) { //ClickItem
                                            setItem = new ClickItem(ModifyItemSettings);
                                            setRegistry.set(namespace,id,setItem);
                                        } else if(types.equalsIgnoreCase("ball")) { //Ball
                                            setItem = new SnowballItem(ModifyItemSettings);setRegistry.set(namespace,id,setItem);
                                        } else if(types.equalsIgnoreCase("food")) { //Food
                                            setItem = new Item(ModifyItemSettings);setRegistry.set(namespace,id,setItem);
                                        } else if(types.equalsIgnoreCase("stewitem") || types.equalsIgnoreCase("bowl")) {
                                            setItem = new StewItem(ModifyItemSettings);setRegistry.set(namespace,id,setItem);
                                        }

                                        provider.add("item." +namespace +"."+id, name);

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