package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.client.render.RenderLayer;
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

import static com.KafuuChino0722.coreextensions.Main.pack;


public class RegBlocks {
    public static final String FILE = Reference.File;

    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings()));
    }

    public static void setupRenderLayer(Block block, Map<String, Object> blockData) {
        boolean shouldUseCutoutLayer = (boolean) blockData.getOrDefault("useCutoutLayer", false);

        if (shouldUseCutoutLayer) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }
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
                                        String lang_us = (String) blockData.get("lang_us");
                                        String namespace = (String) blockData.get("namespace");
                                        String id = (String) blockData.get("id");

                                        String types = blockData.containsKey("types") ? (String) blockData.get("types") : "CUBE_ALL";

                                        double hardness = blockData.containsKey("hardness") ? (double) blockData.get("hardness") : 1.0;
                                        double resistance = blockData.containsKey("resistance") ? (double) blockData.get("resistance") : 3.0;
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
                                            customSound = BlockSoundGroup.STONE;
                                        }

                                        if (Objects.equals(types, "cube_all") || Objects.equals(types, "CUBE_ALL") || Objects.equals(types, "CUBEALL")) {
                                            CubeALL.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "cube") || Objects.equals(types, "CUBE")) {
                                            Cube.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "button") || Objects.equals(types, "BUTTON")) {
                                            Button.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "door") || Objects.equals(types, "DOOR")) {
                                            Door.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "fence") || Objects.equals(types, "FENCE")) {
                                            Fence.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "fence_gate") || Objects.equals(types, "fencegate") || Objects.equals(types, "FENCE_GATE")|| Objects.equals(types, "FENCEGATE")) {
                                            FenceGate.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "pressure_plate") || Objects.equals(types, "pressureplate") || Objects.equals(types, "PRESSURE_PLATE") || Objects.equals(types, "PRESSUREPLATE")) {
                                            PressurePlate.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "slab") || Objects.equals(types, "SLAB")) {
                                            Slab.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "stairs") || Objects.equals(types, "STAIRS")) {
                                            Stairs.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "trapdoor") || Objects.equals(types, "TRAPDOOR")) {
                                            TrapDoor.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "wall") || Objects.equals(types, "WALL")) {
                                            Wall.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "sand") || Objects.equals(types, "SAND") || Objects.equals(types, "FallingBlock")) {
                                            Sand.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "soulsand") || Objects.equals(types, "SOULSAND") || Objects.equals(types, "soul_sand") || Objects.equals(types, "SOUL_SAND")) {
                                            SoulSand.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else if (Objects.equals(types, "fire") || Objects.equals(types, "FIRE")) {
                                            Fire.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);

                                        } else {
                                            ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                                        }


                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier("coreextensions", "blocks_"+namespace+id));

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