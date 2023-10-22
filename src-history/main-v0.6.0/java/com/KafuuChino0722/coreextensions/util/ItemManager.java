package com.KafuuChino0722.coreextensions.util;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

public class ItemManager {

    //removed
    public static final Item RUBY = registerItem("ruby",new Item(new FabricItemSettings()));

    //3D Shareware v1.34
    public static final Item THREED = registerItem("3d",new Item(new FabricItemSettings()));
    public static final Item BLUEKEY = registerItem("blue_key",new Item(new FabricItemSettings()));
    public static final Item REDKEY = registerItem("red_key",new Item(new FabricItemSettings()));
    public static final Item YELLOWKEY = registerItem("yellow_key",new Item(new FabricItemSettings()));
    public static final Item MOJANG_DEV = registerItem("mojang_dev",new Item(new FabricItemSettings()));
    //15w14a
    public static final Item LOVED_GOLD_SWORD = registerItem("loved_gold_sword",
            new SwordItem(ToolMaterials.IRON, 3, -2f,
                    new FabricItemSettings().maxCount(1)));
    //RV-Pre1
    public static final Item SMARTER_WATCH = registerItem("smarter_watch",new Item(new FabricItemSettings()));
    //20w14infinite
    public static final Item FOOTPRINT = registerItem("footprint",new Item(new FabricItemSettings()));
    public static final Item FINE_ITEM = registerItem("fine_item",new Item(new FabricItemSettings()));
    //23w13a_or_b
    public static final Item LA_BAGUETTE = registerItem("la_baguette",
            new SwordItem(ToolMaterials.STONE, 1, -2f,
                    new FabricItemSettings().maxCount(1)));
    public static final Item LE_TRICOLORE = registerItem("le_tricolore",new Item(new FabricItemSettings()));
    public static final Item LONG_STRING = registerItem("long_string",new Item(new FabricItemSettings()));
    //EDUCATAL EDITION
    public static final Item PORTFOLIO = registerItem((String)"portfolio",
            (Item)(new WritableBookItem((new Item.Settings()).maxCount(1))));
    public static final Item ICE_BOMB = registerItem((String)"ice_bomb",
            (Item)(new SnowballItem((new Item.Settings()).maxCount(16))));
    public static final Item BORDER = Items.register(new BlockItem(BlockManager.BORDER,
            (new Item.Settings()).rarity(Rarity.EPIC)));
    //OTHER
    public static final Item CREEPER_CRUNCH = registerItem("creeper_crunch",
            new Item((new Item.Settings())
                    .food(FoodComponents.BREAD)));
    //StoryMode


    private static void addItemsToBattleGroup(FabricItemGroupEntries entries) {
        // 添加需要分类到战斗栏的物品
        entries.add(LOVED_GOLD_SWORD);
        entries.add(SMARTER_WATCH);
        entries.add(LA_BAGUETTE);
        // 将其他需要分类到战斗栏的物品也添加到该物品组中
    }
    private static void addItemsToFOODGroup(FabricItemGroupEntries entries) {
        // 添加需要分类到战斗栏的物品
        entries.add(CREEPER_CRUNCH);
        // 将其他需要分类到战斗栏的物品也添加到该物品组中
    }

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(RUBY);
        entries.add(REDKEY);
        entries.add(BLUEKEY);
        entries.add(YELLOWKEY);
        entries.add(THREED);
        entries.add(FINE_ITEM);
        entries.add(FOOTPRINT);
        entries.add(LE_TRICOLORE);
        entries.add(LONG_STRING);
        entries.add(PORTFOLIO);
        entries.add(ICE_BOMB);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM,new Identifier("minecraft", name), item);
    }

    public static void load() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ItemManager::addItemsToIngredientTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ItemManager::addItemsToFOODGroup);
        ArmorManager.load();
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(ItemManager::addItemsToBattleGroup);
    }
}
