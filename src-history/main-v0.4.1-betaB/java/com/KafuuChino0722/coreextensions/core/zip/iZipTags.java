package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.api.util.TagsBuilder;
import com.KafuuChino0722.coreextensions.util.Reference;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class iZipTags {
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

                            if (!entry.isDirectory() && entry.getName().equals("data/tag.yml")) {
                                try (InputStream inputStream = zip.getInputStream(entry)) {
                                    Map<String, Map<String, Object>> itemsData = yaml.load(new InputStreamReader(inputStream));

                                    if (itemsData != null && itemsData.containsKey("tags")) {
                                        Map<String, Object> items = itemsData.get("tags");

                                        for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                            if (itemEntry.getValue() instanceof Map) {
                                                Map<String, Object> tagData = (Map<String, Object>) itemEntry.getValue();
                                                String name = (String) tagData.get("name");
                                                //String namespace = blocksData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                                String namespace = (String) tagData.getOrDefault("namespace","minecraft");
                                                String id = (String) tagData.get("id");
                                                String types = (String) tagData.get("types");
                                                if(types.equalsIgnoreCase("item")){
                                                    TagsBuilder.Blocks.createTag(namespace,id);
                                                } else if (types.equalsIgnoreCase("block")){
                                                    TagsBuilder.Items.createTag(namespace,id);
                                                } else if (types.equalsIgnoreCase("fluid")){
                                                    TagsBuilder.Fluid.createTag(namespace,id);
                                                }
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
