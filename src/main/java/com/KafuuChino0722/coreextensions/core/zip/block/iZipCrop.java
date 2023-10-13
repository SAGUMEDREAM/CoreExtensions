package com.KafuuChino0722.coreextensions.core.zip.block;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.block.Crop;
import com.KafuuChino0722.coreextensions.core.api.block.HighCrop;
import com.KafuuChino0722.coreextensions.core.api.block.Plants;
import com.KafuuChino0722.coreextensions.core.api.item.Seed;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipCrop {
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

                            if (!entry.isDirectory() && entry.getName().equals("data/crop.yml")) {
                                try (InputStream inputStream = zip.getInputStream(entry)) {
                                    Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                    if (Data != null && Data.containsKey("crops")) {
                                        Map<String, Object> blocks = Data.get("crops");

                                        for (Map.Entry<String, Object> itemEntry : blocks.entrySet()) {
                                            if (itemEntry.getValue() instanceof Map) {
                                                Map<String, Object> blockData = (Map<String, Object>) itemEntry.getValue();
                                                String name = (String) blockData.get("name");
                                                String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                                                String id = (String) blockData.get("id");
                                                String types = (String) blockData.getOrDefault("types","normal");
                                                int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;

                                                Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");

                                                String sound = (String) properties.get("sound");
                                                boolean dropsNothing = properties.containsKey("dropsNothing") ? (boolean) properties.get("dropsNothing") : false;
                                                boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;
                                                int AGE = properties.containsKey("max_age") ? (int) properties.get("max_age") : 7;

                                                String soundStr = sound.toUpperCase();
                                                BlockSoundGroup customSound = null;
                                                if (Objects.equals(soundStr, "STONE")) {
                                                    customSound = BlockSoundGroup.STONE;
                                                } else if (Objects.equals(soundStr, "WOOD")) {
                                                    customSound = BlockSoundGroup.WOOD;
                                                } else if (Objects.equals(soundStr, "GRAVEL")) {
                                                    customSound = BlockSoundGroup.GRAVEL;
                                                } else if (Objects.equals(soundStr, "METAL")) {
                                                    customSound = BlockSoundGroup.METAL;
                                                } else if (Objects.equals(soundStr, "GRASS")) {
                                                    customSound = BlockSoundGroup.GRASS;
                                                } else if (Objects.equals(soundStr, "WOOL")) {
                                                    customSound = BlockSoundGroup.WOOL;
                                                } else if (Objects.equals(soundStr, "SAND")) {
                                                    customSound = BlockSoundGroup.SAND;
                                                } else if (Objects.equals(soundStr, "CROP")) {
                                                    customSound = BlockSoundGroup.CROP;
                                                } else {
                                                    customSound = BlockSoundGroup.STONE;
                                                }

                                                if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                    RegItemGroupsContents.load(namespace,id,properties);
                                                    RegItemGroupsContents.load(namespace,id+"_seeds",properties);
                                                }

                                                if(types.equalsIgnoreCase("normal")){
                                                    Crop.register(name, namespace, id, blockData, properties, dropsNothing, customSound);
                                                    Seed.register(name, namespace, id, maxCount, blockData);
                                                    String typee = "CROP";
                                                    if (generate) {
                                                        Models.generateCrop(namespace, id, typee, AGE);
                                                    }
                                                } else if(types.equalsIgnoreCase("high")) {
                                                    HighCrop.register(name, namespace, id, blockData, properties, dropsNothing, customSound);
                                                    Seed.register(name, namespace, id, maxCount, blockData);
                                                    String typee = "HIGHCROP";
                                                    if (generate) {
                                                        Models.generateCrop(namespace, id, typee, AGE);
                                                    }
                                                } else if(types.equalsIgnoreCase("fruitbush")||types.equalsIgnoreCase("bush")||types.equalsIgnoreCase("fruit")) {
                                                    Plants.fruitbush.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                                }

                                                String lang_us = name;

                                                provider.add("item." +namespace +"."+id, lang_us)
                                                        .add("block." +namespace +"."+id, lang_us)
                                                        .add("item." +namespace +"."+id+"_seeds", lang_us)
                                                        .add("block." +namespace +"."+id+"_block", lang_us);

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
