package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Fence {
    public static void register(String name, String namespace, String id, Map<String, Object> blockData, Boolean dropsNothing, BlockSoundGroup customSound ,double hardness, double resistance){
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

        ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
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
}
