package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.villager.Villager;
import com.KafuuChino0722.coreextensions.util.Reference;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


public class RegModifyVillager {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/villager@Overwrite.yml");

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
                                        //String block = (String) properties.get("block");

                                        //Villager.registerVillager(namespace,id,block);
                                        Villager.modifyTrades(namespace,id,villagerData);

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