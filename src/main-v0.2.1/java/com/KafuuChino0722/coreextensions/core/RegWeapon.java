package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.item.*;
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

public class RegWeapon {
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
                    File itemYamlFile = new File(subdirectory, "data/itemWeapon.yml");

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
                                        int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 1;
                                        String types = itemData.containsKey("types") ? (String) itemData.get("types") : "item";
                                        String level = itemData.containsKey("level") ? (String) itemData.get("level") : "IRON";

                                        boolean generate = itemData.containsKey("generate") ? (boolean) itemData.get("generate") : true;

                                        if(Objects.equals(types, "sword") || Objects.equals(types, "SWORD")) { //Sword
                                            Sword.register(name, namespace, id, itemData, level, generate);

                                        } else if(Objects.equals(types, "shield") || Objects.equals(types, "SHIELD")) { //Food
                                            Shield.register(name, namespace, id, itemData);

                                        } else {
                                            ReturnMessage.WeaponYMLTYPEERROR(name, namespace, id);
                                        }

                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id));

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