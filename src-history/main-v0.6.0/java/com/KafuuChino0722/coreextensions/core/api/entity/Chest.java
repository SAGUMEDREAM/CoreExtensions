package com.KafuuChino0722.coreextensions.core.api.entity;

import com.KafuuChino0722.coreextensions.block.ChestBlockExtends;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import com.mojang.datafixers.types.Type;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.MapColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.Util;

import java.util.Map;
import java.util.Objects;



public class Chest {

    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, double hardness, double resistance, boolean generate) {

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
        Block chest_block = new ChestBlockExtends(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.5f).sounds(BlockSoundGroup.WOOD).burnable(), () -> BlockEntityType.CHEST);
        Item item = new BlockItem(chest_block, new Item.Settings().maxCount(maxCount).rarity(rarityType));
        try {
            Registry.register(Registries.ITEM, new Identifier(namespace,id), item);
            Registry.register(Registries.BLOCK, new Identifier(namespace,id), chest_block);
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.add(item));
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.add(item));
        } catch (Exception e) {
            setRegistry.set(namespace,id,chest_block);
            setRegistry.set(namespace,id,item);
        }

        if(generate) {
            Models.generate(namespace, id, "CHEST");
        }
    }
    private static <T extends BlockEntity> BlockEntityType<T> create(String id, BlockEntityType.Builder<T> builder) {
        Type<?> type = Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id);
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, id, builder.build(type));
    }
}
