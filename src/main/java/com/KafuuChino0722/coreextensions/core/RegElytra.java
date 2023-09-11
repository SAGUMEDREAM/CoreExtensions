package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.item.ElytraItem;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static net.minecraft.item.ArmorItem.Type.*;

public class RegElytra {
    public static final String FILE = Reference.File;

    public static Item registerElytraItem(String namespace, String id, int durability) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new ElytraItem(new FabricItemSettings().maxDamage(durability).rarity(Rarity.UNCOMMON), namespace, id));
    }

    //public static final RuntimeResourcePack pack = RuntimeResourcePack.create(new Identifier("coreextensions", "items"));

    public static void load() {
        Yaml yaml = new Yaml();
        ModelJsonBuilder model;

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
                                        Map<String, Object> armorEntry = (Map<String, Object>) entry.getValue();
                                        String name = (String) armorEntry.get("name");
                                        String lang_us = (String) armorEntry.get("lang_us");
                                        String namespace = (String) armorEntry.get("namespace");
                                        String id = (String) armorEntry.get("id");
                                        String typeString = (String) armorEntry.get("types");
                                        int durability = (int) armorEntry.get("Durability");
                                        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) armorEntry.get("RepairIngredient")));

                                        boolean generate = armorEntry.containsKey("generate") ? (boolean) armorEntry.get("generate") : true;

                                        registerElytraItem(namespace, id, durability);

                                        String typee = "ELYTRA";
                                        if (generate) {
                                            Models.generate(namespace, id, typee);
                                        }
                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_itemLang"));

                                        RRPCallback.BEFORE_VANILLA.register(b -> {
                                            packs.clearResources();
                                            packs.addLang(new Identifier(namespace, "en_us"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, lang_us));
                                            packs.addLang(new Identifier(namespace, "zh_cn"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, name));
                                            b.add(packs);
                                        });

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