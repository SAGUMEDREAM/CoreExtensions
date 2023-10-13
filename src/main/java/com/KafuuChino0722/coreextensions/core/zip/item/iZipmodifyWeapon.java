package com.KafuuChino0722.coreextensions.core.zip.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
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
                                                    boolean fireproof = properties.containsKey("fireproof") ? (boolean) properties.get("fireproof") : false;
                                                    Item item = IdentifierManager.getItem(namespace,id);
                                                    FabricItemSettings ModifyItemSettings = new FabricItemSettings();

                                                    String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";

                                                    double attackDamage;
                                                    double attackSpeed;
                                                    Item repairMaterialItem;
                                                    int durability = Registries.ITEM.get(new Identifier(namespace,id)).getMaxDamage();
                                                    ToolMaterial material = null;
                                                    String level = properties.containsKey("level") ? (String) properties.get("level") : "IRON";
                                                    attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 1.0;
                                                    attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : 1.0;
                                                    repairMaterialItem = properties.containsKey("repairMaterial") ? Registries.ITEM.get(new Identifier((String) properties.get("repairMaterial"))) : Registries.ITEM.get(new Identifier("iron_ingot"));

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

                                                    if(properties.containsKey("rarity")) {
                                                        ModifyItemSettings.rarity(rarityType);
                                                    }

                                                    if(properties.containsKey("durability")) {
                                                        durability = properties.containsKey("durability") ? (int) properties.get("durability") : 200;
                                                        ModifyItemSettings.maxDamage(durability);
                                                    }

                                                    if(properties.containsKey("fireproof")) {
                                                        if(fireproof) {
                                                            ModifyItemSettings.fireproof();
                                                        }
                                                    }

                                                    if(!types.equalsIgnoreCase("shears")) {
                                                        if(properties.containsKey("level")) {
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

                                                            repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.get("repairMaterial")));
                                                            if (repairMaterialItem instanceof ToolItem) {
                                                                material = ((ToolItem) repairMaterialItem).getMaterial();
                                                            }

                                                        }
                                                    }

                                                    Item setItem;
                                                    if (types.equalsIgnoreCase("sword")) { //Pickaxe
                                                        setItem = Registry.register(Registries.ITEM, new Identifier(namespace, id),
                                                                new SwordItem(material, (int) attackDamage, (float) attackSpeed, ModifyItemSettings));
                                                        setRegistry.set(namespace,id,setItem);

                                                    } else if (types.equalsIgnoreCase("bow")) { //Pickaxe
                                                        setItem = Registry.register(Registries.ITEM, new Identifier(namespace, id),
                                                                new BowItem(ModifyItemSettings));
                                                        setRegistry.set(namespace,id,setItem);

                                                    } else if (types.equalsIgnoreCase("shield")) { //Pickaxe
                                                        setItem = Registry.register(Registries.ITEM, new Identifier(namespace, id),
                                                                new FabricShieldItem((Item.Settings) ModifyItemSettings, 10, 13, repairMaterialItem));
                                                        setRegistry.set(namespace,id,setItem);

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}