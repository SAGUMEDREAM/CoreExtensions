package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.block.HighCrops;
import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.Loots;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
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
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

public class CropMethodH {
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

        // 读取更多属性并设置到 blockSettings 中
        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
        Block crops = new HighCrops(blockSettings, namespace ,id);

        try {
            registerBlock(namespace, id+"_block", crops);
            registerBlockItem(namespace, id+"_age", crops);
        } catch (Exception e) {
            setRegistry.set(namespace,id+"_block",crops);
            Item BlockItem = new BlockItem(crops, new FabricItemSettings().maxCount(maxCount));
            setRegistry.set(namespace,id+"_age",BlockItem);
        }

        Item seeds = new AliasedBlockItem(Registries.BLOCK.get(
                new Identifier(namespace, id+"_block")),
                new FabricItemSettings().maxCount(maxCount));

        Item product = new Item(new Item.Settings().maxCount(maxCount));

        try {
            registerSeedItem(namespace, id, seeds);
            registerItem(namespace, id, product);
        } catch (Exception e) {
            setRegistry.set(namespace,id+"_seeds",seeds);
            setRegistry.set(namespace,id,product);
        }
        if (generate) {
            Models.generateCrop(namespace, id, "HIGHCROP", AGE);
            Models.generate(namespace, id+"_seeds", "ITEM");
            Models.generate(namespace, id, "ITEM");
        }


        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage

        if(Reference.EnvType == EnvType.CLIENT) {
            setupRenderLayer.set(crops); // 设置渲染层
        }

        Tags.Block.generateTags(namespace,id,properties);
        Loots.addHighCrop(crops,product,seeds);

        ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
    }
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
}
