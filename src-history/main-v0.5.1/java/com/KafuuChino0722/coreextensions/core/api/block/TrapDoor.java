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
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSetType;
import net.minecraft.block.TrapdoorBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_TRAPDOORS_BLOCK;
import static com.KafuuChino0722.coreextensions.CoreManager.TAG_TRAPDOORS_ITEM;

public class TrapDoor {
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound ,double hardness, double resistance, boolean generate){
        boolean canOpenByHand = properties.containsKey("canOpenByHand") ? (boolean) properties.get("canOpenByHand") : true;
        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                .strength((float) hardness, (float) resistance)
                .sounds(customSound);

        BlockSetType blockType;
        if (canOpenByHand) {
            blockType = BlockSetType.OAK;
        } else {
            blockType = BlockSetType.IRON;
        }

        if (dropsNothing) {
            blockSettings.dropsNothing();
        }

        // 读取更多属性并设置到 blockSettings 中
        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
        Block block = new TrapdoorBlock(blockSettings, blockType);
        try {
            registerBlock(namespace, id, block);
            registerBlockItem(namespace, id, block, maxCount);
        } catch (Exception e) {
            setRegistry.set(namespace,id,block);
            Item BlockItem = new BlockItem(block, new FabricItemSettings().maxCount(maxCount));
            setRegistry.set(namespace,id,BlockItem);
        }

        if(FabricLoader.getInstance().isModLoaded("polymc")&&properties.containsKey("polyinfo")) {
            Map<String, Object> polyinfo = (Map<String, Object>) properties.get("polyinfo");
            Block vanillaBlock = IdentifierManager.getBlock((String) polyinfo.getOrDefault("vanilla","minecraft:stone"));
            PolyMcLoader.loadBlock.block(block,vanillaBlock);
        }

        if(Reference.EnvType == EnvType.CLIENT) {
            setupRenderLayer.set(block, properties); // 设置渲染层
        }

        if(generate) {
            Models.generate(namespace, id, "TRAPDOOR");
        }

        TAG_TRAPDOORS_ITEM.add(new Identifier(namespace, id));
        TAG_TRAPDOORS_BLOCK.add(new Identifier(namespace, id));

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