package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.BedBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BedItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.Map;
import java.util.Objects;

public class Bed {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound , double hardness, double resistance, boolean generate){
        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.STONE)
                .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                .strength((float) hardness, (float) resistance)
                .sounds(customSound);


        if (dropsNothing) {
            blockSettings.dropsNothing();
        }

        String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";

        Rarity rarityType = null;
        if (Objects.equals(rarity, "COMMON") || Objects.equals(rarity, "common")) {
            rarityType = Rarity.COMMON;
        } else if (Objects.equals(rarity, "UNCOMMON") || Objects.equals(rarity, "uncommon")) {
            rarityType = Rarity.UNCOMMON;
        } else if (Objects.equals(rarity, "RARE") || Objects.equals(rarity, "rare")) {
            rarityType = Rarity.RARE;
        } else if (Objects.equals(rarity, "EPIC") || Objects.equals(rarity, "epic")) {
            rarityType = Rarity.EPIC;
        } else {
            rarityType = Rarity.COMMON;
        }

        Block bedBlock = new BedBlock(DyeColor.WHITE, blockSettings);
        Item item = new BedItem(bedBlock ,new Item.Settings().maxCount(maxCount).rarity(rarityType)//.maxDamage(attackDamage)
        );

        try {
            registerItem(namespace, id, item);
        } catch (Exception e) {
            setRegistry.set(namespace, id, item);
        }

        /*String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
            type = "BED";
            Models.generate(namespace, id, type);
        }*/

        Tags.generateBed(namespace,id);
        Tags.Item.generateTags(namespace,id,properties);
        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib

    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block, int maxCount) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings().maxCount(maxCount)));
    }

    public static final String type = "CUBEALL";

}
