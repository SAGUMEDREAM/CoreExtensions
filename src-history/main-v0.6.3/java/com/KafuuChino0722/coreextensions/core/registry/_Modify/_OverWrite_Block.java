package com.KafuuChino0722.coreextensions.core.registry._Modify;

import com.KafuuChino0722.coreextensions.block.CubeBlock;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.BlockSoundGroup;

import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static net.minecraft.block.Blocks.OAK_PLANKS;

public class _OverWrite_Block {
    public static void register() {
        Map<String, Map<String, Object>> blockData = IOFileManager.read("block@Overwrite.yml");
        load(blockData);
        Map<String, Map<String, Object>> blockDataZ = IOFileManager.readZip("block@Overwrite.yml");
        load(blockDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("blocks")) {
            Map<String, Object> items = itemsData.get("blocks");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {

                    Map<String, Object> blockData = (Map<String, Object>) entry.getValue();

                    String name = (String) blockData.get("name");
                    String lang_us = (String) blockData.getOrDefault("name",null);
                    //String namespace = (String) itemData.get("namespace");
                    String namespace = blockData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                    String id = (String) blockData.get("id");
                    String types = blockData.containsKey("types") ? (String) blockData.get("types") : "CUBE_ALL";
                    int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;
                    Map<String, Object> properties = blockData.containsKey("properties")? (Map<String, Object>) blockData.get("properties"):null;
                    String sound = (String) properties.get("sound");

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

                    Block block = IdentifierManager.getBlock(namespace,id);
                    FabricBlockSettings ModifyBlockSettings = FabricBlockSettings.create();

                    boolean wooden = properties.containsKey("wooden") ? (boolean) properties.get("wooden") : false;
                    int pressTicks = properties.containsKey("pressTicks") ? (int) properties.get("pressTicks") : 20;
                    boolean canOpenByHand = properties.containsKey("canOpenByHand") ? (boolean) properties.get("canOpenByHand") : true;
                    BlockSetType blockType = properties.containsKey("canOpenByHand")&&canOpenByHand ? BlockSetType.OAK : BlockSetType.IRON;

                    if(properties != null) {
                        if(blockData.containsKey("maxCount")) {
                            FabricItemSettings ModifyItemSettings = new FabricItemSettings();
                            ModifyItemSettings.maxCount(maxCount);
                            Item setItem;
                            setItem = new Item(ModifyItemSettings);
                            setRegistry.set(namespace,id,setItem);
                        }
                        if (properties.containsKey("resistance")) {
                            double resistance = properties.containsKey("resistance") ? (double) properties.get("resistance") : 3.0;
                            ModifyBlockSettings.resistance((float) resistance);
                        }
                        if (properties.containsKey("hardness")) {
                            double hardness = properties.containsKey("hardness") ? (double) properties.get("hardness") : 3.0;
                            ModifyBlockSettings.resistance((float) hardness);
                        }
                        if (properties.containsKey("sound")) {
                            ModifyBlockSettings.sounds(customSound);
                        }
                        if (properties.containsKey("lightLevel")) {
                            int lightLevel = properties.containsKey("lightLevel") ? (int) properties.get("lightLevel") : 0;
                            ModifyBlockSettings.luminance(lightLevel);
                        }
                        if (properties.containsKey("useCutoutLayer")) {
                            if(Reference.EnvType == EnvType.CLIENT) {
                                setupRenderLayer.set(block, properties); // 设置渲染层
                            }
                        }
                        if (properties.containsKey("dropsNothing")) {
                            boolean dropsNothing = properties.containsKey("dropsNothing") ? (boolean) properties.get("dropsNothing") : false;
                            if(dropsNothing) {
                                ModifyBlockSettings.dropsNothing();
                            }
                        }
                    }
                    Block setBlock;
                    if (types.equalsIgnoreCase("cube_all")||types.equalsIgnoreCase("cubeall")) {
                        setBlock = new Block(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("cube")) {
                        setBlock = new CubeBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("button")) {
                        setBlock = new ButtonBlock(ModifyBlockSettings, BlockSetType.OAK, pressTicks, wooden);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("door")) {
                        setBlock = new DoorBlock(ModifyBlockSettings, blockType);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("fence")) {
                        setBlock = new FenceBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("fence_gate")||types.equalsIgnoreCase("fencegate")) {
                        setBlock = new FenceGateBlock(ModifyBlockSettings, WoodType.ACACIA);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("pressure_plate")||types.equalsIgnoreCase("pressureplate")) {
                        PressurePlateBlock.ActivationRule PPBlock = PressurePlateBlock.ActivationRule.EVERYTHING;
                        setBlock = new PressurePlateBlock(PPBlock, ModifyBlockSettings, blockType);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("slab")) {
                        setBlock = new SlabBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("stairs")) {
                        setBlock = new StairsBlock(OAK_PLANKS.getDefaultState(), ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("trapdoor")) {
                        setBlock = new TrapdoorBlock(ModifyBlockSettings, blockType);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("wall")) {
                        setBlock = new WallBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("sand")) {
                        setBlock = new SandBlock(11098145, ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("soulsand")||types.equalsIgnoreCase("soul_sand")) {
                        setBlock = new SoulSandBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("fire")) {

                    } else if (types.equalsIgnoreCase("log")) {
                        setBlock = new PillarBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("leaves")) {
                        setBlock = new LeavesBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("torch")) {
                        setBlock = new TorchBlock(ModifyBlockSettings, ParticleTypes.FLAME);
                        setRegistry.set(namespace,id,setBlock);
                    } else if (types.equalsIgnoreCase("lamp")||types.equalsIgnoreCase("redstone_lamp")||types.equalsIgnoreCase("redstonelamp")) {
                        setBlock = new RedstoneLampBlock(ModifyBlockSettings);
                        setRegistry.set(namespace,id,setBlock);
                    }

                    provider.add("block." +namespace +"."+id, name);

                }
            }
        }
    }
}