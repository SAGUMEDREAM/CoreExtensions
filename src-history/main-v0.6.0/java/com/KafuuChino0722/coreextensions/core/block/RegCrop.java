package com.KafuuChino0722.coreextensions.core.block;

import com.KafuuChino0722.coreextensions.core.api.block.CropMethodH;
import com.KafuuChino0722.coreextensions.core.api.block.CropMethodN;
import com.KafuuChino0722.coreextensions.core.api.block.Plants;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.sound.BlockSoundGroup;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

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

                                if (Data != null && Data.containsKey("crops")) {
                                    Map<String, Object> blocks = Data.get("crops");

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
                                                //Crop.register(name, namespace, id, blockData, properties, dropsNothing, customSound);
                                                //Seed.register(name, namespace, id, maxCount, blockData);
                                                CropMethodN.register(name, namespace, id, blockData, properties, dropsNothing, customSound, AGE, generate);
                                            } else if(types.equalsIgnoreCase("high")) {
                                                //HighCrop.register(name, namespace, id, blockData, properties, dropsNothing, customSound);
                                                //Seed.register(name, namespace, id, maxCount, blockData);
                                                CropMethodH.register(name, namespace, id, blockData, properties, dropsNothing, customSound, AGE, generate);
                                            } else if(types.equalsIgnoreCase("fruitbush")||types.equalsIgnoreCase("bush")||types.equalsIgnoreCase("fruit")) {
                                                Plants.fruitbush.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                            }

                                            provider.add("item." +namespace +"."+id, name)
                                                    .add("block." +namespace +"."+id, name)
                                                    .add("item." +namespace +"."+id+"_seeds", name)
                                                    .add("block." +namespace +"."+id+"_block", name);

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
