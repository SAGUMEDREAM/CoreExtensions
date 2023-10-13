package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
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
import java.util.Map;
import java.util.Objects;


public class RegBlocks {
    public static final String FILE = Reference.File;

    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings()));
    }

    private static void generate() {

    }

    public static void load() {
        Yaml yaml = new Yaml();
        ModelJsonBuilder model;

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/block.yml");

                    if (blockYamlFile.exists() && blockYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(blockYamlFile));

                            if (blocksData != null && blocksData.containsKey("blocks")) {
                                Map<String, Object> blocks = blocksData.get("blocks");

                                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                                        String name = (String) blockData.get("name");
                                        String namespace = (String) blockData.get("namespace");
                                        String id = (String) blockData.get("id");
                                        String types = blockData.containsKey("types") ? (String) blockData.get("types") : "CUBE_ALL";
                                        int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;

                                        Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
                                        double hardness = properties.containsKey("hardness") ? (double) properties.get("hardness") : 1.0;
                                        double resistance = properties.containsKey("resistance") ? (double) properties.get("resistance") : 3.0;
                                        boolean dropsNothing = properties.containsKey("dropsNothing") ? (boolean) properties.get("dropsNothing") : false;
                                        String sound = (String) properties.get("sound");

                                        boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

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
                                        } else if (Objects.equals(sound, "CROP")) {
                                            customSound = BlockSoundGroup.CROP;
                                        } else {
                                            customSound = BlockSoundGroup.STONE;
                                        }

                                        if (types.equalsIgnoreCase("cube_all")||types.equalsIgnoreCase("cubeall")) {
                                            CubeALL.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("cube")) {
                                            Cube.register(name, namespace, id, maxCount, properties, blockData, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("button")) {
                                            Button.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("door")) {
                                            Door.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("fence")) {
                                            Fence.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("fence_gate")||types.equalsIgnoreCase("fencegate")) {
                                            FenceGate.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("pressure_plate")||types.equalsIgnoreCase("pressureplate")) {
                                            PressurePlate.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("slab")) {
                                            Slab.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("stairs")) {
                                            Stairs.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("trapdoor")) {
                                            TrapDoor.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("wall")) {
                                            Wall.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("sand")) {
                                            Sand.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("soulsand")||types.equalsIgnoreCase("soul_sand")) {
                                            SoulSand.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("fire")) {
                                            Fire.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("log")) {
                                            CubeLog.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("leaves")) {
                                            Leaves.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("torch")) {
                                            Torch.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else if (types.equalsIgnoreCase("lamp")||types.equalsIgnoreCase("redstone_lamp")||types.equalsIgnoreCase("redstonelamp")) {
                                            RedStoneLamp.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                        } else {
                                            ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                                        }

                                        boolean canFire = properties.containsKey("flammable") ? (boolean) properties.get("flammable") : false;

                                        if (canFire && properties.containsKey("flammable")) {
                                            FlammableBlockRegistry.getInstance(Registries.BLOCK.get(new Identifier(namespace,id)));
                                        }

                                        RegItemGroupsContents.load(namespace,id,properties);

                                        String lang_us = name;

                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_block_lang"));

                                        RRPCallback.BEFORE_VANILLA.register(b -> {
                                            packs.clearResources();
                                            packs.addLang(new Identifier(namespace, "en_us"),
                                                    LanguageProvider.create().add("block." +namespace +"."+id, lang_us));
                                            packs.addLang(new Identifier(namespace, "zh_cn"),
                                                    LanguageProvider.create().add("block." +namespace +"."+id, name));
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