package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class iZipComposting {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/composting.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> itemsData = yaml.load(new InputStreamReader(inputStream));

                                        if (itemsData != null && itemsData.containsKey("items")) {
                                            Map<String, Object> items = itemsData.get("items");

                                            for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                                if (itemEntry.getValue() instanceof Map) {
                                                    Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();

                                                    String name = (String) itemData.get("name");
                                                    String namespace = (String) itemData.getOrDefault("namespace", "minecraft");
                                                    String id = (String) itemData.get("id");
                                                    double chance = (double) itemData.get("chance");

                                                    Item ItemResources = Registries.ITEM.get(new Identifier(namespace, id));
                                                    CompostingChanceRegistry.INSTANCE.add(ItemResources, (float) chance);

                                                    ReturnMessage.CompostingYMLRegister(name, namespace, id); //returnMessage

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