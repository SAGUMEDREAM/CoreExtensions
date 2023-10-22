package com.KafuuChino0722.coreextensions.core.zip.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
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

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PIGLIN_LOVED;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class iZipItems {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/item.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("items")) {
                                            Map<String, Object> items = Data.get("items");

                                            for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                                if (itemEntry.getValue() instanceof Map) {
                                                    Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();
                                                    String name = (String) itemData.get("name");
                                                    String lang_us = (String) itemData.get("name");
                                                    String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                                                    String id = (String) itemData.get("id");
                                                    int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 64;
                                                    String types = itemData.containsKey("types") ? (String) itemData.get("types") : "item";

                                                    Map<String, Object> properties = itemData.containsKey("properties") ? (Map<String, Object>) itemData.get("properties") : null;

                                                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                                                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                        RegItemGroupsContents.load(namespace,id,properties);
                                                    }

                                                    switch (types.toLowerCase()) {
                                                        case "item" ->
                                                                Items.register(name, namespace, id, maxCount, itemData, properties, generate);
                                                        case "clickitem" ->
                                                                ClickItems.register(name, namespace, id, itemData, properties, generate);
                                                        case "ball" ->
                                                                Ball.register(name, namespace, id, maxCount, itemData, properties, generate);
                                                        case "food" ->
                                                                Food.register(name, namespace, id, maxCount, itemData, properties, generate);
                                                        case "stewitem", "bowl" ->
                                                                FoodStewItem.register(name, namespace, id, maxCount, itemData, properties, generate);
                                                        default ->
                                                                ReturnMessage.ItemYMLTYPEERROR(name, namespace, id);
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