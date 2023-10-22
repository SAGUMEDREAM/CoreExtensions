package com.KafuuChino0722.coreextensions.core.zip.entity;

import com.KafuuChino0722.coreextensions.core.api.villager.Villager;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_VILlAGER_JOB_SITE;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class iZipVillager {
    public static final String FILE = Reference.File;

    public static void load(String[] paths) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/villager.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("villagers")) {
                                            Map<String, Object> blocks = Data.get("villagers");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> villagerData = (Map<String, Object>) DataEntry.getValue();

                                                    String name = (String) villagerData.get("name");
                                                    String namespace = (String) villagerData.getOrDefault("namespace", "minecraft");
                                                    String id = (String) villagerData.get("id");
                                                    //int maxLevel = 1;
                                                    Map<String, Object> properties = (Map<String, Object>) villagerData.get("properties");
                                                    String block = (String) properties.getOrDefault("block","command_block");

                                                    Villager.registerVillager(namespace, id, block);
                                                    Villager.registerTrades(namespace, id, villagerData);

                                                    TAG_VILlAGER_JOB_SITE.add(new Identifier(namespace,id+"_poi"));

                                                    provider.add("entity.minecraft.villager."+id+"_master", name);

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
}