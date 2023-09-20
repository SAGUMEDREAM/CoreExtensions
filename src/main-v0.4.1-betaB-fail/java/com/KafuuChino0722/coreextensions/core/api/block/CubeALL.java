package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.api.util.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

public class CubeALL {
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound ,double hardness, double resistance, boolean generate){
        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.STONE)
                .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                .strength((float) hardness, (float) resistance)
                .sounds(customSound);


        if (dropsNothing) {
            blockSettings.dropsNothing();
        }

        // 读取更多属性并设置到 blockSettings 中
        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
        //Block block = new Block(blockSettings);

        if (!Registries.BLOCK.containsId(new Identifier(namespace, id))) {
            Registry.register(Registries.BLOCK, new Identifier(namespace, id), new Block(blockSettings));
        }
        if (!Registries.ITEM.containsId(new Identifier(namespace, id))) {
            Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(new Block(blockSettings), new FabricItemSettings().maxCount(maxCount)));
        }

        if(FabricLoader.getInstance().isModLoaded("polymc")&&properties.containsKey("polyinfo")) {
            Map<String, Object> polyinfo = (Map<String, Object>) properties.get("polyinfo");
            Block vanillaBlock = IdentifierManager.getBlock((String) polyinfo.getOrDefault("vanilla","minecraft:stone"));
            PolyMcLoader.loadBlock.block(new Block(blockSettings),vanillaBlock);
        }

        if(Reference.EnvType == EnvType.CLIENT) {
            setupRenderLayer.set(new Block(blockSettings), properties); // 设置渲染层
        }

        String type = "CUBEALL";
        if(generate) {
            Models.generate(namespace, id, type);
        }
        Tags.Block.generateTags(namespace,id,properties);
        Loots.addDrop(namespace, id, blockData, properties);

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