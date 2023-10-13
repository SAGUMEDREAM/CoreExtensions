package com.LoneDev.itemsadder.api;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Arrays;
import java.util.Map;

public class CustomBlocks {

    public static boolean generate;

    public static double hardness = -1.0;
    public static double blast_resistance = -1.0;
    public static int light_level = 0;
    public static boolean no_explosion;
    public static boolean cancel_drop;

    FabricBlockSettings blockSettings = FabricBlockSettings.create();

    public void register(String type, String namespace, String id, String blockType, FabricItemSettings itemSetting, boolean enabled, Map<String, Object> itemData) {

        if(enabled) {

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

                    if(blockType.equalsIgnoreCase("cubeAllBlock")) {
                        Block block = new Block(blockSettings);
                        registerBlock(namespace, id, block);
                        registerBlockItem(namespace, id, block, itemSetting);
                        if(generate) {
                            Models.generate(namespace, id, "CUBEALL");
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
                            Models.generate(namespace, id, "CUBEALL");
                        }
                    }

                }
            }
        }
    }

    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block, FabricItemSettings itemSetting) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, itemSetting));
    }

}
