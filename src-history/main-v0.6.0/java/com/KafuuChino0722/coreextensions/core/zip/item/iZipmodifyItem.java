package com.KafuuChino0722.coreextensions.core.zip.item;

import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.item.ClickItem;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class iZipmodifyItem {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/modifyItems.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("items")) {
                                            Map<String, Object> blocks = Data.get("items");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> itemData = (Map<String, Object>) DataEntry.getValue();

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
                                                    List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;

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
                                                        setItem = new Item(ModifyItemSettings) {
                                                            @Override
                                                            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                                                if (tooltipMsg != null) {
                                                                    for (String Line : tooltipMsg) {
                                                                        tooltip.add(Text.of("§5§o"+Line));
                                                                    }
                                                                }
                                                            }
                                                        };
                                                        setRegistry.set(namespace,id,setItem);
                                                    } else if(types.equalsIgnoreCase("clickitem")) { //ClickItem
                                                        setItem = new ClickItem(ModifyItemSettings) {
                                                            @Override
                                                            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                                                if (tooltipMsg != null) {
                                                                    for (String Line : tooltipMsg) {
                                                                        tooltip.add(Text.of("§5§o"+Line));
                                                                    }
                                                                }
                                                            }
                                                        };
                                                        setRegistry.set(namespace,id,setItem);
                                                    } else if(types.equalsIgnoreCase("ball")) { //Ball
                                                        setItem = new SnowballItem(ModifyItemSettings) {
                                                            @Override
                                                            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                                                if (tooltipMsg != null) {
                                                                    for (String Line : tooltipMsg) {
                                                                        tooltip.add(Text.of("§5§o"+Line));
                                                                    }
                                                                }
                                                            }
                                                        };
                                                        setRegistry.set(namespace,id,setItem);
                                                    } else if(types.equalsIgnoreCase("food")) { //Food
                                                        setItem = new Item(ModifyItemSettings) {
                                                            @Override
                                                            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                                                if (tooltipMsg != null) {
                                                                    for (String Line : tooltipMsg) {
                                                                        tooltip.add(Text.of("§5§o"+Line));
                                                                    }
                                                                }
                                                            }
                                                        };
                                                        setRegistry.set(namespace,id,setItem);
                                                    } else if(types.equalsIgnoreCase("stewitem") || types.equalsIgnoreCase("bowl")) {
                                                        setItem = new StewItem(ModifyItemSettings) {
                                                            @Override
                                                            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                                                if (tooltipMsg != null) {
                                                                    for (String Line : tooltipMsg) {
                                                                        tooltip.add(Text.of("§5§o"+Line));
                                                                    }
                                                                }
                                                            }
                                                        };
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