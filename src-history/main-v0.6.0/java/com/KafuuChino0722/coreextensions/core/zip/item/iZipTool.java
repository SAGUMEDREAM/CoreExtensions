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

public class iZipTool {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] zipFiles = coreDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));

            if (zipFiles != null) {
                for (File zipFile : zipFiles) {
                    try (ZipFile zip = new ZipFile(zipFile)) {
                        Enumeration<? extends ZipEntry> entries = zip.entries();

                        while (entries.hasMoreElements()) {
                            ZipEntry entry = entries.nextElement();

                            if (!entry.isDirectory() && entry.getName().equals("data/itemTool.yml")) {
                                try (InputStream inputStream = zip.getInputStream(entry)) {
                                    Map<String, Map<String, Object>> itemsData = yaml.load(new InputStreamReader(inputStream));

                                    if (itemsData != null && itemsData.containsKey("items")) {
                                        Map<String, Object> items = itemsData.get("items");

                                        for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                            if (itemEntry.getValue() instanceof Map) {
                                                Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();
                                                String name = (String) itemData.get("name");
                                                String lang_us = (String) itemData.get("name");
                                                String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                                                String id = (String) itemData.get("id");
                                                int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 1;
                                                String types = itemData.containsKey("types") ? (String) itemData.get("types") : "ITEM";
                                                Map<String, Object> properties = (Map<String, Object>) itemData.get("properties");

                                                boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                                                String level = properties.containsKey("level") ? (String) properties.get("level") : "IRON";

                                                if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                    RegItemGroupsContents.load(namespace,id,properties);
                                                }

                                                if (types.equalsIgnoreCase("pickaxe")) { //Pickaxe
                                                    Pickaxe.register(name, namespace, id, itemData, properties, level, generate);
                                                } else if (types.equalsIgnoreCase("axe")) { //Axe
                                                    Axe.register(name, namespace, id, itemData, properties, level, generate);
                                                } else if (types.equalsIgnoreCase("shovel")) { //Shovel
                                                    Shovel.register(name, namespace, id, itemData, properties, level, generate);
                                                } else if (types.equalsIgnoreCase("hoe")) { //Hoes
                                                    Hoe.register(name, namespace, id, itemData, properties, level, generate);
                                                } else if (types.equalsIgnoreCase("shears")) { //Shears
                                                    Shears.register(name, namespace, id, itemData, properties, level, generate);
                                                } else {
                                                    ReturnMessage.ToolYMLTYPEERROR(name, namespace, id);
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
