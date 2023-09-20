package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.api.util.modifyLootTables;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class iZipmodifyLootTable {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

            if (zipFiles != null) {
                for (File zipFile : zipFiles) {
                    try (ZipFile zip = new ZipFile(zipFile)) {
                        Enumeration<? extends ZipEntry> entries = zip.entries();

                        while (entries.hasMoreElements()) {
                            ZipEntry entry = entries.nextElement();

                            if (!entry.isDirectory() && entry.getName().equals("data/modifyLootTables.yml")) {
                                try (InputStream inputStream = zip.getInputStream(entry)) {
                                    Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                    if (Data != null && Data.containsKey("loots")) {
                                        Map<String, Object> blocks = Data.get("loots");

                                        for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                            if (DataEntry.getValue() instanceof Map) {
                                                Map<String, Object> LootTablesData = (Map<String, Object>) DataEntry.getValue();

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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
