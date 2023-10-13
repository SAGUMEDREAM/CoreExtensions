package com.KafuuChino0722.coreextensions.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ArmorManager {

    public static final Item REALITY_VISION = registerItem("reality_vision", new ArmorItem(ArmorMaterials.IRON, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ANKLE_MONITOR = registerItem("ankle_monitor", new ArmorItem(ModArmorMaterials.IRONCOPY, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item PLATE_HELMET = registerItem("plate_helmet", new ArmorItem(ModArmorMaterials.NULL, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item PLATE_CHESTPLATE = registerItem("plate_chestplate", new ArmorItem(ModArmorMaterials.NULL, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));

    private static void addItemsToBattleGroup(FabricItemGroupEntries entries) {
        // 添加需要分类到战斗栏的物品
        entries.add(PLATE_HELMET);
        entries.add(PLATE_CHESTPLATE);
        entries.add(REALITY_VISION);
        entries.add(ANKLE_MONITOR);
        // 将其他需要分类到战斗栏的物品也添加到该物品组中
    }

    public static void load() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ArmorManager::addItemsToBattleGroup);
    }
    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier("minecraft", name), item);
    }
}
