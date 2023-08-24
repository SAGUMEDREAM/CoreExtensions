package com.KafuuChino0722.coreextensions.outdate;

import com.KafuuChino0722.coreextensions.util.ArmorManager;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ItemManager {

    public static final Item RUBY = registerItem("ruby",new Item(new FabricItemSettings()));

    private static void addItemsToBattleGroup(FabricItemGroupEntries entries) {
        // 添加需要分类到战斗栏的物品
        //entries.add();

        // 将其他需要分类到战斗栏的物品也添加到该物品组中
    }

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {

    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,new Identifier("minecraft", name), item);

    }
    public static void load() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ItemManager::addItemsToIngredientTabItemGroup);
        ArmorManager.load();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ItemManager::addItemsToBattleGroup);
    }
}
