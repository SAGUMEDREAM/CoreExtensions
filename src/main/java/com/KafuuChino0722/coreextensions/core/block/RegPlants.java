package com.KafuuChino0722.coreextensions.core.block;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.CoreManager.respacks;


public class RegPlants {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/plant.yml");

                    if (blockYamlFile.exists() && blockYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(blockYamlFile));

                            if (blocksData != null && blocksData.containsKey("blocks")) {
                                Map<String, Object> blocks = blocksData.get("blocks");

                                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                                        String name = (String) blockData.get("name");
                                        //String namespace = blocksData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                        String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                                        String id = (String) blockData.get("id");
                                        String types = blockData.containsKey("types") ? (String) blockData.get("types") : "normal";
                                        int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;

                                        Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
                                        boolean dropsNothing = properties.containsKey("dropsNothing") && (boolean) properties.get("dropsNothing");
                                        String sound = (String) properties.get("sound");

                                        boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

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

                                        if(!Registries.BLOCK.containsId(new Identifier(namespace, id))) {
                                            if(types.equalsIgnoreCase("normal")){
                                                Plants.normal.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                            } else if(types.equalsIgnoreCase("high")||types.equalsIgnoreCase("highblock")||types.equalsIgnoreCase("high_block")) {
                                                Plants.high.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                            } else if(types.equalsIgnoreCase("seed")) {
                                                Plants.seeds.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                            }
                                        }

                                        provider.add("item." +namespace +"."+id, name)
                                                .add("block." +namespace +"."+id, name)
                                                .add("block." +namespace +".potted_"+id, name);

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