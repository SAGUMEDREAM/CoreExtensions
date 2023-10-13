package com.KafuuChino0722.coreextensions.core.zip.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.item.ElytraItem;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
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
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipElytra {
    public static final String FILE = Reference.File;

    public static net.minecraft.item.Item registerItem(String namespace, String id, net.minecraft.item.Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }


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

                                if (!entry.isDirectory() && entry.getName().equals("data/armorElytra.yml")) {
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

                                                    Map<String, Object> properties = (Map<String, Object>) armorData.get("properties");

                                                    int durability = properties.containsKey("Durability") ? (int) properties.get("Durability") : 250;
                                                    Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("RepairIngredient", "minecraft:iron_ingot")));
                                                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                                                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                        RegItemGroupsContents.load(namespace,id,properties);
                                                    }

                                                    Item item = new ElytraItem(new FabricItemSettings().maxDamage(durability).rarity(Rarity.UNCOMMON), namespace, id);
                                                    try {
                                                        registerItem(namespace,id,item);
                                                    } catch (Exception e) {
                                                        setRegistry.set(namespace,id,item);
                                                    }

                                                    if (generate) {
                                                        Models.generate(namespace, id, "ELYTRA");
                                                    }

                                                    provider.add("item." +namespace +"."+id, name);

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