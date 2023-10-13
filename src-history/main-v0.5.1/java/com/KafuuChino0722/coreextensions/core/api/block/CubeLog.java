package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_LOGS_BLOCK;
import static com.KafuuChino0722.coreextensions.CoreManager.TAG_LOGS_ITEM;

public class CubeLog {
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound ,double hardness, double resistance, boolean generate){
        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.STONE)
                .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                .strength((float) hardness, (float) resistance)
                .sounds(customSound).instrument(Instrument.BASS).burnable();


        if (dropsNothing) {
            blockSettings.dropsNothing();
        }

        if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
            RegItemGroupsContents.load(namespace,"stripped_"+id,properties);
        }

        // 读取更多属性并设置到 blockSettings 中
        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
        Block block = new PillarBlock(blockSettings);
        Block stripped_block = new PillarBlock(blockSettings);
        try {
            registerBlock(namespace, id, block);
            registerBlockItem(namespace, id, block, maxCount);
            registerBlock(namespace, "stripped_"+id, stripped_block);
            registerBlockItem(namespace, "stripped_"+id, stripped_block, maxCount);
            StrippableBlockRegistry.register(block,stripped_block);
        } catch (Exception e) {
            setRegistry.set(namespace,id,block);
            Item BlockItem = new BlockItem(block, new FabricItemSettings().maxCount(maxCount));
            setRegistry.set(namespace,id,BlockItem);

            setRegistry.set(namespace,"stripped_"+id,stripped_block);
            Item BlockItemStripped = new BlockItem(stripped_block, new FabricItemSettings().maxCount(maxCount));
            setRegistry.set(namespace,"stripped_"+id,BlockItemStripped);
            StrippableBlockRegistry.register(block,stripped_block);
        }



        if(Reference.EnvType == EnvType.CLIENT) {
            setupRenderLayer.set(block, properties);
            setupRenderLayer.set(stripped_block, properties);// 设置渲染层
        }

        boolean canFire = properties.containsKey("canFire") ? (boolean) properties.get("canFire") : false;
        if (canFire) {
            FlammableBlockRegistry.getDefaultInstance().add(Registries.BLOCK.get(new Identifier(namespace,id)),5 , 20);
        }

        if(FabricLoader.getInstance().isModLoaded("polymc")&&properties.containsKey("polyinfo")) {
            Map<String, Object> polyinfo = (Map<String, Object>) properties.get("polyinfo");
            Block vanillaBlock = IdentifierManager.getBlock((String) polyinfo.getOrDefault("vanilla","minecraft:stone"));
            PolyMcLoader.loadBlock.block(block,vanillaBlock);
        }

        if(generate) {
            Models.generate(namespace, id, "TREELOG");
            Models.generate(namespace, "stripped_"+id, "TREELOG");
        }

        TAG_LOGS_ITEM.add(new Identifier(namespace, id));
        TAG_LOGS_ITEM.add(new Identifier(namespace, "stripped_"+id));
        TAG_LOGS_BLOCK.add(new Identifier(namespace, id));
        TAG_LOGS_BLOCK.add(new Identifier(namespace, "stripped_"+id));

        Tags.Block.generateTags(namespace,id,properties);
        Loots.addDrop(namespace, id, blockData, properties);
        Loots.addDrop(namespace, "stripped_"+id, blockData, properties);

        ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block, int maxCount) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings().maxCount(maxCount)));
    }
}