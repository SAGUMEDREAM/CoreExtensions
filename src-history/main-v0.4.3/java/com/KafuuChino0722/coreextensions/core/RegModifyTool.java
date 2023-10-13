package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class RegModifyTool {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/itemTool@Overwrite.yml");

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
                                        String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                                        String id = (String) itemData.get("id");
                                        String types = itemData.containsKey("types") ? (String) itemData.get("types") : "item";
                                        int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 64;
                                        Map<String, Object> properties = itemData.containsKey("properties")? (Map<String, Object>) itemData.get("properties"):null;

                                        Item item = IdentifierManager.getItem(namespace,id);

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
                                            Field field;
                                            try {
                                                field = Item.class.getDeclaredField("maxCount");
                                            } catch (NoSuchFieldException e) {
                                                field = Item.class.getDeclaredField("field_8013");
                                            }
                                            field.setAccessible(true);
                                            field.set(item, maxCount);
                                        }

                                        if(properties.containsKey("rarity")) {
                                            Field field;
                                            try {
                                                field = Item.class.getDeclaredField("rarity");
                                            } catch (NoSuchFieldException e) {
                                                field = Item.class.getDeclaredField("field_8009");
                                            }
                                            field.setAccessible(true);
                                            field.set(item, rarityType);
                                        }

                                        if(properties.containsKey("durability")) {
                                            int durability = properties.containsKey("durability") ? (int) properties.get("durability") : 200;
                                            Field field;
                                            try {
                                                field = Item.class.getDeclaredField("maxDamage");
                                            } catch (NoSuchFieldException e) {
                                                field = Item.class.getDeclaredField("field_8012");
                                            }
                                            field.setAccessible(true);
                                            field.set(item, durability);
                                        }

                                        if(!types.equalsIgnoreCase("shears")) {
                                            if(properties.containsKey("attackDamage")) {
                                                double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 1.0;
                                                Field field;
                                                try {
                                                    field = MiningToolItem.class.getDeclaredField("attackDamage");
                                                } catch (NoSuchFieldException e) {
                                                    field = MiningToolItem.class.getDeclaredField("field_7939");
                                                }
                                                field.setAccessible(true);
                                                field.set(item, (float) attackDamage);
                                            }
                                            if(properties.containsKey("attackSpeed")) {
                                                double attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : 1.0;
                                                Field field;
                                                try {
                                                    field = MiningToolItem.class.getDeclaredField("miningSpeed");
                                                } catch (NoSuchFieldException e) {
                                                    field = MiningToolItem.class.getDeclaredField("field_7940");
                                                }
                                                field.setAccessible(true);
                                                field.set(item, (float) attackSpeed);
                                            }
                                            if(properties.containsKey("level")) {
                                                String level = properties.containsKey("level") ? (String) properties.get("level") : "IRON";
                                                ToolMaterial material;
                                                if (Objects.equals(level, "0") || Objects.equals(level, "WOOD") || Objects.equals(level, "GOLD") || Objects.equals(level, "wood") || Objects.equals(level, "gold")) {
                                                    material = ToolMaterials.WOOD;
                                                } else if (Objects.equals(level, "1") || Objects.equals(level, "stone") || Objects.equals(level, "STONE")) {
                                                    material = ToolMaterials.STONE;
                                                } else if (Objects.equals(level, "2") || Objects.equals(level, "iron") || Objects.equals(level, "IRON")) {
                                                    material = ToolMaterials.IRON;
                                                } else if (Objects.equals(level, "3") || Objects.equals(level, "diamond") || Objects.equals(level, "DIAMOND")) {
                                                    material = ToolMaterials.DIAMOND;
                                                } else if (Objects.equals(level, "4") || Objects.equals(level, "netherite") || Objects.equals(level, "NETHERITE")) {
                                                    material = ToolMaterials.NETHERITE;
                                                } else {
                                                    material = ToolMaterials.IRON;
                                                }

                                                Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.get("repairMaterial")));
                                                if (repairMaterialItem instanceof ToolItem) {
                                                    material = ((ToolItem) repairMaterialItem).getMaterial();
                                                }

                                                Field field;
                                                try {
                                                    field = ToolItem.class.getDeclaredField("material");
                                                } catch (NoSuchFieldException e) {
                                                    field = ToolItem.class.getDeclaredField("field_7940");
                                                }
                                                field.setAccessible(true);
                                                field.set(item, material);
                                            }
                                        }

                                        RegItemGroupsContents.load(namespace,id,properties);

                                        respacks.addLang(new Identifier(namespace+"_"+id+"_mlang", "en_us"),
                                                LanguageProvider.create().add("item." +namespace +"."+id, lang_us));
                                        respacks.addLang(new Identifier(namespace+"_"+id+"_mlang", "zh_cn"),
                                                LanguageProvider.create().add("item." +namespace +"."+id, name));

                                    }
                                }
                            }
                        } catch (IOException | NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
    }
}