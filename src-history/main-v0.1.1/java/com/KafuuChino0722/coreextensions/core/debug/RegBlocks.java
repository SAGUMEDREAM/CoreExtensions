package com.KafuuChino0722.coreextensions.core.debug;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.block.RealBlock;
import com.KafuuChino0722.coreextensions.core.api.CubeALL;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
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
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.Main.pack;
import static net.minecraft.block.Blocks.STONE;


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

    public static void RegCubeALL() {

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

                                        String types = (String) blockData.get("types");

                                        double hardness = (Double) blockData.get("hardness");
                                        double resistance = (Double) blockData.get("resistance");
                                        boolean dropsNothing = (boolean) blockData.get("dropsNothing");
                                        String sound = (String) blockData.get("sound");

                                        BlockSoundGroup customSound = BlockSoundGroup.STONE;
                                        if (Objects.equals(sound, "STONE")) {
                                            customSound = BlockSoundGroup.STONE;
                                        }
                                        if (Objects.equals(sound, "WOOD")) {
                                            customSound = BlockSoundGroup.WOOD;
                                        }
                                        if (Objects.equals(sound, "GRAVEL")) {
                                            customSound = BlockSoundGroup.GRAVEL;
                                        }
                                        if (Objects.equals(sound, "METAL")) {
                                            customSound = BlockSoundGroup.METAL;
                                        }
                                        if (Objects.equals(sound, "GRASS")) {
                                            customSound = BlockSoundGroup.GRASS;
                                        }
                                        if (Objects.equals(sound, "WOOL")) {
                                            customSound = BlockSoundGroup.WOOL;
                                        }
                                        if (Objects.equals(sound, "SAND")) {
                                            customSound = BlockSoundGroup.SAND;
                                        }

                                        if (Objects.equals(types, "cube_all")) {
                                            CubeALL.register(name, namespace, id, blockData, dropsNothing, customSound, hardness, resistance);
                                            /*FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);


                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new Block(blockSettings);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层*/


                                        } else if (Objects.equals(types, "cube")) {
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new RealBlock(blockSettings);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "button")) {
                                            boolean wooden = (boolean) blockData.get("wooden");
                                            int pressTicks = (int) blockData.get("pressTicks");
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            } else {
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new ButtonBlock(blockSettings, BlockSetType.OAK, pressTicks, wooden);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            Main.LOGGER.info("Block " + name + "<->" + namespace + ":" + id + " registered!");
                                        } else if (Objects.equals(types, "door")) {
                                            boolean canOpenByHand = (boolean) blockData.get("canOpenByHand");
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            BlockSetType blockType;
                                            if (canOpenByHand) {
                                                blockType = BlockSetType.OAK;
                                            } else {
                                                blockType = BlockSetType.IRON;
                                            }

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new DoorBlock(blockSettings, blockType);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层

                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "fence")) {
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new FenceBlock(blockSettings);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "fence_gate")) {
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new FenceGateBlock(blockSettings, WoodType.ACACIA);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "pressure_plate")) {
                                            boolean SetType = (boolean) blockData.get("stone");
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            } else {
                                                blockSettings.dropsNothing();
                                            }
                                            BlockSetType blockType;
                                            if (SetType) {
                                                blockType = BlockSetType.STONE;
                                            } else {
                                                blockType = BlockSetType.OAK;
                                            }

                                            PressurePlateBlock.ActivationRule PPBlock = PressurePlateBlock.ActivationRule.EVERYTHING;

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new PressurePlateBlock(PPBlock, blockSettings, blockType);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "slab")) {
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new SlabBlock(blockSettings);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "stairs")) {
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new StairsBlock(STONE.getDefaultState(), blockSettings);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "trapdoor")) {
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new TrapdoorBlock(blockSettings, BlockSetType.OAK);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else if (Objects.equals(types, "wall")) {
                                            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                                    .strength((float) hardness, (float) resistance)
                                                    .sounds(customSound);

                                            if (dropsNothing) {
                                                blockSettings.dropsNothing();
                                            }

                                            // 读取更多属性并设置到 blockSettings 中
                                            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                                            Block block = new WallBlock(blockSettings);
                                            registerBlock(namespace, id, block);
                                            registerBlockItem(namespace, id, block);
                                            setupRenderLayer(block, blockData); // 设置渲染层
                                            ReturnMessage.BlockYMLRegister(name, namespace, id);
                                        } else {
                                            ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                                        }


                                        RRPCallback.BEFORE_VANILLA.register(b -> {
                                            pack.clearResources();
                                            pack.addLang(new Identifier(namespace, "en_us"), LanguageProvider.create()
                                                    .add("block." + namespace + "." + id, lang_us));
                                            pack.addLang(new Identifier(namespace, "zh_cn"), LanguageProvider.create()
                                                    .add("block." + namespace + "." + id, name));
                                            b.add(pack);
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