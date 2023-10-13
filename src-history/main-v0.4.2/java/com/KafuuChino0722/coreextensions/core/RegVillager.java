package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.core.api.villager.Villager;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
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

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;


public class RegVillager {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/villager.yml");

                    if (blockYamlFile.exists() && blockYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(blockYamlFile));

                            if (blocksData != null && blocksData.containsKey("villagers")) {
                                Map<String, Object> info = blocksData.get("villagers");

                                for (Map.Entry<String, Object> entry : info.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> villagerData = (Map<String, Object>) entry.getValue();
                                        String name = (String) villagerData.get("name");
                                        String namespace = (String) villagerData.getOrDefault("namespace","minecraft");
                                        String id = (String) villagerData.get("id");
                                        //int maxLevel = 1;
                                        Map<String, Object> properties = (Map<String, Object>) villagerData.get("properties");
                                        String block = (String) properties.get("block");

                                        Villager.registerVillager(namespace,id,block);
                                        Villager.registerTrades(namespace,id,villagerData);


                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "en_us"),
                                                    LanguageProvider.create().add("entity.minecraft.villager."+id+"_master", name));
                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "zh_cn"),
                                                    LanguageProvider.create().add("entity.minecraft.villager."+id+"_master", name));

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