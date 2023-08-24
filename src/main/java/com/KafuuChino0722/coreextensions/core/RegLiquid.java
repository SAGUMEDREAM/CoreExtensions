package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegLiquid {
    //别急，正在开发呢
    public static final String FILE = Reference.File;

    private static Block registerLiquidBlock(String namespace, String name) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, name), new Block(FabricBlockSettings.copyOf(Blocks.WATER)));
    }

    private static Item registerLiquidBlockItem(Block block) {
        return Registry.register(Registries.ITEM, Registries.BLOCK.getId(block), new BlockItem(block, new Item.Settings()));
    }

    public static final void load() {
        //Main.LOGGER.info("Liquid " + namespace + id + " registered!");
    }
    /*public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> liquidData = yaml.load(new FileReader(FILE +"liquids.yml"));

            if (liquidData != null && liquidData.containsKey("liquids")) {
                Map<String, Object> liquids = liquidData.get("liquids");

                for (Map.Entry<String, Object> entry : liquids.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> liquidConfig = (Map<String, Object>) entry.getValue();
                        String name = entry.getKey();
                        String namespace = (String) liquidConfig.get("namespace");
                        String id = (String) liquidConfig.get("id");

                        Block liquidBlock = registerLiquidBlock(namespace, id);
                        Item liquidBlockItem = registerLiquidBlockItem(liquidBlock);
                        BlockRenderLayerMap.INSTANCE.putBlock(liquidBlock, RenderLayer.getCutout());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
}