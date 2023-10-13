package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegPortal {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> portalsData = yaml.load(new FileReader(FILE + "portals.yml"));

            if (portalsData != null && portalsData.containsKey("portals")) {
                Map<String, Object> portals = portalsData.get("portals");

                for (Map.Entry<String, Object> entry : portals.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> portalData = (Map<String, Object>) entry.getValue();
                        String portalName = entry.getKey();

                        String name = (String) portalData.get("name");
                        String frameBlockStr = (String) portalData.get("frameBlock");
                        Block frameBlock = Registries.BLOCK.get(new Identifier(frameBlockStr));
                        if (frameBlock == null) {
                            frameBlock = Blocks.BEDROCK; // 默认方块
                        }

                        String lightWithItemStr = (String) portalData.get("lightWithItem");
                        Item lightWithItem = Registries.ITEM.get(new Identifier(lightWithItemStr));
                        if (lightWithItem == null) {
                            lightWithItem = Items.POTATO; // 默认物品
                        }

                        String destDimIDStr = (String) portalData.get("destDimID");
                        Identifier destDimID = new Identifier(destDimIDStr);

                        List<Integer> tintColorList = (List<Integer>) portalData.get("tintColor");
                        int tintColorR = tintColorList.get(0);
                        int tintColorG = tintColorList.get(1);
                        int tintColorB = tintColorList.get(2);

                        // 使用上面获取到的信息来创建传送门
                        Main.LOGGER.info("Portal " + name + "<->" + portals + " registered!");
                        CustomPortalBuilder.beginPortal()
                                .frameBlock(frameBlock)
                                .lightWithItem(lightWithItem)
                                .destDimID(destDimID)
                                .tintColor(tintColorR, tintColorG, tintColorB)
                                .registerPortal();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
