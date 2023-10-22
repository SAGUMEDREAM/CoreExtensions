package com.KafuuChino0722.coreextensions.core.item;

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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class RegElytra {
    public static final String FILE = Reference.File;

    public static net.minecraft.item.Item registerItem(String namespace, String id, net.minecraft.item.Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    //public static final RuntimeResourcePack pack = RuntimeResourcePack.create(new Identifier("coreextensions", "items"));

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/armorElytra.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("armors")) {
                                Map<String, Object> items = itemsData.get("armors");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> armorData = (Map<String, Object>) entry.getValue();
                                        String name = (String) armorData.get("name");
                                        String lang_us = (String) armorData.get("lang_us");
                                        String namespace = (String) armorData.get("namespace");
                                        String id = (String) armorData.get("id");

                                        Map<String, Object> properties = (Map<String, Object>) armorData.get("properties");

                                        int durability = properties.containsKey("Durability") ? (int) properties.get("Durability") : 250;
                                        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("RepairIngredient","minecraft:iron_ingot")));
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


                                        provider.add("item."+namespace+"."+id, name);

                                        ReturnMessage.ArmorYMLRegister(name,namespace,id);

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