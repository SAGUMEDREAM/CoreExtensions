package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.core.api.item.Items;
import com.KafuuChino0722.coreextensions.core.api.item.FoodStewItem;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
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

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class RegItems {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

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
                                        String name = (String) itemData.get("name");
                                        String lang_us = (String) itemData.get("name");
                                        //String namespace = (String) itemData.get("namespace");
                                        String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                                        String id = (String) itemData.get("id");
                                        int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 64;
                                        String types = itemData.containsKey("types") ? (String) itemData.get("types") : "item";

                                        Map<String, Object> properties = itemData.containsKey("properties")? (Map<String, Object>) itemData.get("properties"):null;

                                        boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;


                                        if(!Registries.ITEM.containsId(new Identifier(namespace, id))) {
                                            if(types.equalsIgnoreCase("item")) {
                                                Items.register(name, namespace, id, maxCount, itemData, properties, generate);

                                            } else if(types.equalsIgnoreCase("clickitem")) { //Sword
                                                ClickItems.register(name, namespace, id, itemData, properties, generate);

                                            } else if(types.equalsIgnoreCase("ball")) { //Ball
                                                Ball.register(name, namespace, id, maxCount, itemData, properties, generate);

                                            } else if(types.equalsIgnoreCase("food")) { //Food
                                                Food.register(name, namespace, id, maxCount, itemData, properties, generate);

                                            } else if(types.equalsIgnoreCase("stewitem") || types.equalsIgnoreCase("bowl")) {
                                                FoodStewItem.register(name, namespace, id, maxCount, itemData, properties, generate);

                                            } else {
                                                ReturnMessage.ItemYMLTYPEERROR(name, namespace, id);
                                            }
                                        } else {
                                            Info.custom(namespace+":"+id+" has been registered twice and has automatically prevented the game from crashing","ItemManager");
                                        }

                                        RegItemGroupsContents.load(namespace,id,properties);

                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "en_us"),
                                                LanguageProvider.create().add("item." +namespace +"."+id, lang_us));
                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "zh_cn"),
                                                LanguageProvider.create().add("item." +namespace +"."+id, name));
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