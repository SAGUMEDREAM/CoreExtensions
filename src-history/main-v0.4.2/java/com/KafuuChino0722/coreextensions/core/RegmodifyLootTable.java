package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.util.modifyLootTables;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegmodifyLootTable {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/modifyLootTables.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("loots")) {
                                Map<String, Object> items = itemsData.get("loots");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> LootTablesData = (Map<String, Object>) entry.getValue();
                                        String name = (String) LootTablesData.get("name");
                                        String namespace = (String) LootTablesData.getOrDefault("namespace","minecraft");
                                        String path = (String) LootTablesData.get("path");
                                        String item = (String) LootTablesData.get("item");
                                        double chance = (double) LootTablesData.getOrDefault("chance",1.0);
                                        int count = (int) LootTablesData.getOrDefault("count",1.0);

                                        modifyLootTables.create(namespace,path,item,chance,count);

                                        ReturnMessage.LootsYMLRegister(name,namespace,path,item);

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