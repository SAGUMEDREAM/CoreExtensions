package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.core.api.item.Items;
import com.KafuuChino0722.coreextensions.core.api.item.FoodStewItem;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class RegItems {
    public static final String FILE = Reference.File;

    //public static final RuntimeResourcePack pack = RuntimeResourcePack.create(new Identifier("coreextensions", "items"));

    public static void load() {
        Yaml yaml = new Yaml();
        ModelJsonBuilder model;

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/item.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("items")) {
                                Map<String, Object> items = itemsData.get("items");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                                        Map<String, Object> Data = (Map<String, Object>) entry.getValue();
                                        String name = (String) itemData.get("name");
                                        String lang_us = (String) itemData.get("name");
                                        //String namespace = (String) itemData.get("namespace");
                                        String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                                        String id = (String) itemData.get("id");
                                        int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 64;
                                        String types = itemData.containsKey("types") ? (String) itemData.get("types") : "item";

                                        boolean generate = itemData.containsKey("generate") ? (boolean) itemData.get("generate") : true;

                                        if(Objects.equals(types, "item") || Objects.equals(types, "ITEM")) {
                                            Items.register(name, namespace, id, maxCount, itemData, generate);

                                        } else if(Objects.equals(types, "clickitem") || Objects.equals(types, "CLICKITEM")) { //Sword
                                            ClickItems.register(name, namespace, id, itemData, generate);

                                        } else if(Objects.equals(types, "ball") || Objects.equals(types, "BALL")) { //Ball
                                            Ball.register(name, namespace, id, maxCount, itemData, generate);

                                        } else if(Objects.equals(types, "food") || Objects.equals(types, "FOOD")) { //Food
                                            Food.register(name, namespace, id, maxCount, itemData, generate);

                                        } else if(Objects.equals(types, "stewitem") ||
                                                Objects.equals(types, "STEWITEM") ||
                                                Objects.equals(types, "STEW") ||
                                                Objects.equals(types, "stew") ||
                                                Objects.equals(types, "bowl") ||
                                                Objects.equals(types, "BOWL")
                                        ) {
                                            FoodStewItem.register(name, namespace, id, maxCount, itemData, generate);

                                        } else {
                                            ReturnMessage.ItemYMLTYPEERROR(name, namespace, id);
                                        }

                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_item_lang"));

                                        RRPCallback.BEFORE_VANILLA.register(b -> {
                                            packs.clearResources();
                                            packs.addLang(new Identifier(namespace, "en_us"),
                                                    LanguageProvider.create().add("item." +namespace +"."+id, lang_us));
                                            packs.addLang(new Identifier(namespace, "zh_cn"),
                                                    LanguageProvider.create().add("item." +namespace +"."+id, name));
                                            b.add(packs);
                                        });

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