package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.Crop;
import com.KafuuChino0722.coreextensions.core.api.block.HighCrop;
import com.KafuuChino0722.coreextensions.core.api.block.Plants;
import com.KafuuChino0722.coreextensions.core.api.item.Seed;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.Reference;
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
import java.io.StringReader;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class RegCrop {
    public static final String FILE = Reference.File;



    public static void load() {
            Yaml yaml = new Yaml();


            File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

                if (subdirectories != null) {
                    for (File subdirectory : subdirectories) {
                        File BlockYamlFile = new File(subdirectory, "data/crop.yml");

                        if (BlockYamlFile.exists() && BlockYamlFile.isFile()) {
                            try {
                                Map<String, Map<String, Object>> Data = yaml.load(new FileReader(BlockYamlFile));

                                if (Data != null && Data.containsKey("Crops")) {
                                    Map<String, Object> blocks = Data.get("Crops");

                                    for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                        if (entry.getValue() instanceof Map) {
                                            Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
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
                                            }

                                            RegItemGroupsContents.load(namespace,id,properties);
                                            RegItemGroupsContents.load(namespace,id+"_seeds",properties);


                                            String lang_us = name;

                                            respacks.addLang(new Identifier(namespace+"_"+id, "en_us"),
                                                        LanguageProvider.create()
                                                                .add("item." +namespace +"."+id, lang_us)
                                                                .add("block." +namespace +"."+id, lang_us)
                                                                .add("item." +namespace +"."+id+"_seeds", lang_us)
                                                                .add("block." +namespace +"."+id+"_block", lang_us)
                                            );
                                            respacks.addLang(new Identifier(namespace+"_"+id, "zh_cn"),
                                                        LanguageProvider.create()
                                                                .add("item." +namespace +"."+id, name)
                                                                .add("block." +namespace +"."+id, name)
                                                                .add("item." +namespace +"."+id+"_seeds", name)
                                                                .add("block." +namespace +"."+id+"_block", name)
                                            );
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
