package com.KafuuChino0722.coreextensions.core.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PIGLIN_LOVED;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class RegTool {
    public static final String FILE = Reference.File;

    //public static final RuntimeResourcePack pack = RuntimeResourcePack.create(new Identifier("coreextensions", "items"));

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/itemTool.yml");

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
            }
        }
    }
}