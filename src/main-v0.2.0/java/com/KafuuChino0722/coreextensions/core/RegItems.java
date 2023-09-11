package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.core.api.item.Items;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
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

import static com.KafuuChino0722.coreextensions.Main.pack;

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

                                        if(Objects.equals(types, "item") || Objects.equals(types, "ITEM")) {
                                            Items.register(name, namespace, id, maxCount, itemData);

                                        } else if(Objects.equals(types, "clickitem") || Objects.equals(types, "CLICKITEM")) { //Sword
                                            ClickItems.register(name, namespace, id, itemData);

                                        } else if(Objects.equals(types, "sword") || Objects.equals(types, "SWORD")) { //Sword
                                            Sword.register(name, namespace, id, itemData);

                                        } else if(Objects.equals(types, "pickaxe") || Objects.equals(types, "PICKAXE")) { //Pickaxe
                                            Pickaxe.register(name, namespace, id, itemData);

                                        } else if(Objects.equals(types, "axe") || Objects.equals(types, "AXE")) { //Axe
                                            Axe.register(name, namespace, id, itemData);

                                        } else if(Objects.equals(types, "shovel") || Objects.equals(types, "SHOVEL")) { //Shovel
                                            Shovel.register(name, namespace, id, itemData);

                                        } else if(Objects.equals(types, "hoe") || Objects.equals(types, "HOE")) { //Hoes
                                            Hoe.register(name, namespace, id, itemData);

                                        } else if(Objects.equals(types, "ball") || Objects.equals(types, "BALL")) { //Ball
                                            Ball.register(name, namespace, id, maxCount, itemData);

                                        } else if(Objects.equals(types, "food") || Objects.equals(types, "FOOD")) { //Food
                                            Food.register(name, namespace, id, maxCount, itemData);

                                        } else if(Objects.equals(types, "shield") || Objects.equals(types, "SHIELD")) { //Food
                                            Shield.register(name, namespace, id, itemData);

                                        } else {
                                            ReturnMessage.ItemYMLTYPEERROR(name, namespace, id);
                                        }

                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier("coreextensions", "items_"+namespace+id));

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