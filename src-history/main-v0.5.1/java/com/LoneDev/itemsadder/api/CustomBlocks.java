package com.LoneDev.itemsadder.api;

import com.KafuuChino0722.coreextensions.block.CubeBlock;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.util.List;
import java.util.Map;

import static com.LoneDev.itemsadder.Main.IaPacks;

public class CustomBlocks {

    public static boolean generate;

    public static double hardness = -1.0;
    public static double blast_resistance = -1.0;
    public static int light_level = 0;
    public static boolean no_explosion;
    public static boolean cancel_drop;

    public static String[] break_tools_whitelist;

    FabricBlockSettings blockSettings = FabricBlockSettings.create();

    public void register(String type, String namespace, String id, String blockType, FabricItemSettings itemSetting, boolean enabled, Map<String, Object> itemData, int KeyNumber, String model_path) {

        if(enabled) {
            blockType = "cubeRotBlock";
            if(KeyNumber == 6) {

            }

            if(itemData.containsKey("resource")) {
                Map<String, Object> resourcekey = (Map<String, Object>) itemData.get("resource");

                if(resourcekey.containsKey("generate")) {
                    generate = (boolean) resourcekey.get("generate");
                } else {
                    generate = true;
                }
            }

            if(itemData.containsKey("specific_properties")) {
                Map<String, Object> specifickey = (Map<String, Object>) itemData.get("specific_properties");

                if(specifickey.containsKey("block")) {
                    Map<String, Object> blockKey = (Map<String, Object>) specifickey.get("block");
                    if (blockKey.containsKey("hardness")) {
                        hardness = (double) blockKey.get("hardness");
                    }
                    if (blockKey.containsKey("blast_resistance")) {
                        blast_resistance = (double) blockKey.get("blast_resistance");
                    }
                    if(hardness != -1.0 && blast_resistance != -1.0) {
                        hardness = hardness;
                        blast_resistance = blast_resistance;
                        blockSettings = blockSettings.strength((float) hardness, (float) blast_resistance);
                    } else if(hardness!=-1.0 && blast_resistance==-1.0) {
                        hardness = hardness;
                        blockSettings = blockSettings.strength((float) hardness);
                    }

                    if(blockKey.containsKey("light_level")) {
                        light_level = (int) blockKey.get("light_level");
                        blockSettings = blockSettings.luminance((int) light_level);
                    }

                    if(blockKey.containsKey("no_explosion")) {
                        no_explosion = (boolean) blockKey.get("no_explosion");
                        if(no_explosion) {
                            blockSettings = blockSettings.strength((float) hardness, 1200.0f);
                        }
                    }

                    if(blockKey.containsKey("cancel_drop")) {
                        cancel_drop = (boolean) blockKey.get("cancel_drop");
                    } else {
                        cancel_drop = false;
                    }

                    if(cancel_drop) {
                        blockSettings.dropsNothing();
                    } else {
                        blockSettings.drops(new Identifier(namespace,id));
                    }

                    if (blockKey.containsKey("break_tools_whitelist")) {
                        List<String> whitelist = (List<String>) blockKey.get("break_tools_whitelist");
                        break_tools_whitelist = whitelist.toArray(new String[0]);
                        for (String Line : break_tools_whitelist) {
                            Line = Line.toLowerCase();

                            if(Line.contains("pickaxe")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("axe")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("shovel")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("hoe")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("shears")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("stone")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_STONE_TOOL).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("iron")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_IRON_TOOL).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("diamond")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_DIAMOND_TOOL).add(new Identifier(namespace,id)));
                            }
                            if(Line.contains("netherite")) {
                                IaPacks.addTag(IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(4)).add(new Identifier(namespace,id)));
                            }

                        }
                    }

                    if(blockType.equalsIgnoreCase("cubeAllBlock")) {
                        Block block = new Block(blockSettings);
                        registerBlock(namespace, id, block);
                        registerBlockItem(namespace, id, block, itemSetting);
                        if(generate) {
                            if(itemData.containsKey("resource")) {
                                Map<String, Object> resourcekey = (Map<String, Object>) itemData.get("resource");
                                if(resourcekey.containsKey("texture")) {
                                    List<String> textures = (List<String>) resourcekey.get("texture");
                                    String Texture = textures.get(0);
                                    CustomModels.generate(namespace, id, "CUBEALL", Texture);
                                } else {
                                    CustomModels.generate(namespace, id, "CUBEALL");
                                }
                            } else {
                                CustomModels.generate(namespace, id, "CUBEALL");
                            }
                        }
                    }

                    if(blockType.equalsIgnoreCase("cubeTransParentBlock")) {
                        Block block = new Block(blockSettings);
                        registerBlock(namespace, id, block);
                        registerBlockItem(namespace, id, block, itemSetting);
                        if(Reference.EnvType == EnvType.CLIENT) {
                            setupRenderLayer.set(block);
                        }
                        if(generate) {
                            if(itemData.containsKey("resource")) {
                                Map<String, Object> resourcekey = (Map<String, Object>) itemData.get("resource");
                                if(resourcekey.containsKey("texture")) {
                                    List<String> textures = (List<String>) resourcekey.get("texture");
                                    String Texture = textures.get(0);
                                    CustomModels.generate(namespace, id, "CUBEALL", Texture);
                                } else {
                                    CustomModels.generate(namespace, id, "CUBEALL");
                                }
                            } else {
                                CustomModels.generate(namespace, id, "CUBEALL");
                            }
                        }
                    }

                    if(blockType.equalsIgnoreCase("cubeFurniture")) {
                        Block block = new CubeBlock(blockSettings);
                        registerBlock(namespace, id, block);
                        registerBlockItem(namespace, id, block, itemSetting);
                        if(Reference.EnvType == EnvType.CLIENT) {
                            setupRenderLayer.set(block);
                        }
                        if(generate) {
                            if(itemData.containsKey("resource")) {
                                Map<String, Object> resourcekey = (Map<String, Object>) itemData.get("resource");
                                if(resourcekey.containsKey("model_path")) {
                                    CustomModels.generateCustom(namespace, id, "CUBE", model_path);
                                }
                            }

                        }
                    }

                    if(blockType.equalsIgnoreCase("cubeRotBlock")) {
                        Block block = new CubeBlock(blockSettings);
                        registerBlock(namespace, id, block);
                        registerBlockItem(namespace, id, block, itemSetting);
                        if(Reference.EnvType == EnvType.CLIENT) {
                            setupRenderLayer.set(block);
                        }
                        if(generate) {
                            if(itemData.containsKey("resource")) {
                                Map<String, Object> resourcekey = (Map<String, Object>) itemData.get("resource");
                                if(resourcekey.containsKey("texture")) {
                                    List<String> textures = (List<String>) resourcekey.get("texture");
                                    String down = textures.get(0);
                                    String east = textures.get(1);
                                    String north = textures.get(2);
                                    String south = textures.get(3);
                                    String up = textures.get(4);
                                    String west = textures.get(5);

                                    CustomModels.generate(namespace, id, "CUBE",
                                            down, east, north, south, up, west);
                                }
                            }

                        }
                    }
                }
            }
        }
    }

    public static Block registerBlock(String namespace, String id, Block block) {
        if(Registries.BLOCK.containsId(new Identifier(namespace,id))) {
            return setRegistry.set(namespace,id, block);
        }
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block, FabricItemSettings itemSetting) {
        if(Registries.BLOCK.containsId(new Identifier(namespace,id))) {
            return setRegistry.set(namespace,id,new BlockItem(block, itemSetting));
        }
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, itemSetting));
    }

}
