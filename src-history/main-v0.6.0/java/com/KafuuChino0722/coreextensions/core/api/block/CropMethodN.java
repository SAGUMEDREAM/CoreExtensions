package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.block.*;
import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.Loots;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

public class CropMethodN {
    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings()));
    }

    public static Item registerSeedItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id+"_seeds"), item);
    }

    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static void register(String name, String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, int maxCount, boolean generate){
        int AGE = properties.containsKey("max_age") ? (int) properties.get("max_age") : 7;
        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.WHEAT)
                .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                .sounds(customSound);

        if (dropsNothing) {
            blockSettings.dropsNothing();
        }

        if(!Registries.ITEM.containsId(new Identifier(namespace,id+"_age"))) {
            RegItemGroupsContents.load(namespace,id,properties);
            RegItemGroupsContents.load(namespace,id+"_seeds",properties);
        }

        Block crop = null;
        // 读取更多属性并设置到 blockSettings 中
        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
        try {
            if(AGE>=7){
                crop = new CropBlocks7(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            } else if(AGE==6) {
                crop = new CropBlocks6(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            } else if(AGE==5) {
                crop = new CropBlocks5(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            } else if(AGE==4) {
                crop = new CropBlocks4(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            } else if(AGE==3) {
                crop = new CropBlocks3(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            } else if(AGE==2) {
                crop = new CropBlocks2(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            } else if(AGE==1) {
                crop = new CropBlocks1(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            } else {
                crop = new CropBlocks(blockSettings, namespace ,id);
                registerBlock(namespace, id+"_block", crop);
                registerBlockItem(namespace, id+"_age", crop);

                if(Reference.EnvType == EnvType.CLIENT) {
                    setupRenderLayer.set(crop); // 设置渲染层
                }
            }
        } catch (Exception e) {

        }

        Item seedItem = new AliasedBlockItem(crop,
                new FabricItemSettings().maxCount(maxCount));

        Item item = new Item(new Item.Settings().maxCount(maxCount));

        try {
            registerSeedItem(namespace, id, seedItem);
            registerItem(namespace, id, item);
        } catch (Exception e) {

        }
        if (generate) {
            Models.generateCrop(namespace, id, "CROP", AGE);
            Models.generate(namespace, id+"_seeds", "ITEM");
            Models.generate(namespace, id, "ITEM");
        }

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage

        Tags.Block.generateTags(namespace,id,properties);
        Loots.addCrop(crop,item,seedItem,AGE);

        ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
    }

}
