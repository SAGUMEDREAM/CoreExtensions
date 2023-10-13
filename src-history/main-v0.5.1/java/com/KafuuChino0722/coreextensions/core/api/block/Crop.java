package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.block.*;
import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.Loots;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Crop {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound){
        int AGE = properties.containsKey("max_age") ? (int) properties.get("max_age") : 7;
        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.WHEAT)
                .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                .sounds(customSound);

        if (dropsNothing) {
            blockSettings.dropsNothing();
        }

        if(!Registries.ITEM.containsId(new Identifier(namespace,id+"_seeds"))) {
            RegItemGroupsContents.load(namespace,id+"_seeds",properties);
        }

        // 读取更多属性并设置到 blockSettings 中
        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
        if(AGE>=7){
            Block block = new CropBlocks7(blockSettings, namespace ,id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        } else if(AGE==6) {
            Block block = new CropBlocks6(blockSettings, namespace ,id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        } else if(AGE==5) {
            Block block = new CropBlocks5(blockSettings, namespace ,id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        } else if(AGE==4) {
            Block block = new CropBlocks4(blockSettings, namespace, id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        } else if(AGE==3) {
            Block block = new CropBlocks3(blockSettings, namespace, id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        } else if(AGE==2) {
            Block block = new CropBlocks2(blockSettings, namespace, id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        } else if(AGE==1) {
            Block block = new CropBlocks1(blockSettings, namespace, id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        } else {
            Block block = new CropBlocks(blockSettings, namespace, id);
            Item item = new BlockItem(block, new FabricItemSettings());
            try {
                registerBlock(namespace, id+"_block", block);
                registerBlockItem(namespace, id+"_age", block);
            } catch (Exception e) {
                setRegistry.set(namespace,id+"_block",block);
                setRegistry.set(namespace,id+"_age",item);
            }
            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block); // 设置渲染层
            }
        }

        Tags.Block.generateTags(namespace,id,properties);
        Loots.addCrop(namespace, id, blockData, properties, AGE);

        ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings()));
    }
}
