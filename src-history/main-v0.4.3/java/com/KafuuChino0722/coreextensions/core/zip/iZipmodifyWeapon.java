package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipmodifyWeapon {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/itemWeapon@Overwrite.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("items")) {
                                            Map<String, Object> blocks = Data.get("items");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> itemData = (Map<String, Object>) DataEntry.getValue();

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

                                                    if(!types.equalsIgnoreCase("shield")) {
                                                        if(properties.containsKey("attackDamage")) {
                                                            double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 1.0;
                                                            Field field;
                                                            try {
                                                                field = SwordItem.class.getDeclaredField("attackDamage");
                                                            } catch (NoSuchFieldException e) {
                                                                field = SwordItem.class.getDeclaredField("field_8920");
                                                            }
                                                            field.setAccessible(true);
                                                            field.set(item, (float) attackDamage);
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
                                    } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
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