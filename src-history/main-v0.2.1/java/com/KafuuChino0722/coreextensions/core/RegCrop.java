package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.Crop;
import com.KafuuChino0722.coreextensions.core.api.item.Seed;
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

public class RegCrop {
    public static final String FILE = Reference.File;



    public static void load() {
            Yaml yaml = new Yaml();
            ModelJsonBuilder model;

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
                                            String namespace = (String) blockData.get("namespace");
                                            String id = (String) blockData.get("id");
                                            int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;
                                            boolean dropsNothing = blockData.containsKey("dropsNothing") ? (boolean) blockData.get("dropsNothing") : false;
                                            String sound = (String) blockData.get("sound");

                                            BlockSoundGroup customSound = null;
                                            if (Objects.equals(sound, "STONE")) {
                                                customSound = BlockSoundGroup.STONE;
                                            } else if (Objects.equals(sound, "WOOD")) {
                                                customSound = BlockSoundGroup.WOOD;
                                            } else if (Objects.equals(sound, "GRAVEL")) {
                                                customSound = BlockSoundGroup.GRAVEL;
                                            } else if (Objects.equals(sound, "METAL")) {
                                                customSound = BlockSoundGroup.METAL;
                                            } else if (Objects.equals(sound, "GRASS")) {
                                                customSound = BlockSoundGroup.GRASS;
                                            } else if (Objects.equals(sound, "WOOL")) {
                                                customSound = BlockSoundGroup.WOOL;
                                            } else if (Objects.equals(sound, "SAND")) {
                                                customSound = BlockSoundGroup.SAND;
                                            } else {
                                                customSound = BlockSoundGroup.CROP;
                                            }

                                            Crop.register(name, namespace, id, blockData, dropsNothing, customSound);
                                            Seed.register(name, namespace, id, maxCount, blockData);

                                            RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier("coreextensions", "crops_"+namespace+id));

                                            String lang_us = name;

                                            RRPCallback.BEFORE_VANILLA.register(b -> {
                                                packs.clearResources();
                                                packs.addLang(new Identifier(namespace, "en_us"),
                                                        LanguageProvider.create()
                                                                .add("block." +namespace +"."+id, lang_us)
                                                                .add("item." +namespace +"."+id+"_seeds", lang_us));
                                                packs.addLang(new Identifier(namespace, "zh_cn"),
                                                        LanguageProvider.create()
                                                                .add("block." +namespace +"."+id, name)
                                                                .add("item." +namespace +"."+id+"_seeds", name));
                                                b.add(packs);
                                            });
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