package com.KafuuChino0722.coreextensions.core.api.itemgroups;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class itemGroupsManager {

    public static Item getID(@Nullable String namespace, String id) {
        return Registries.ITEM.get(new Identifier(namespace,id));
    }
    public static Item getID(@Nullable String item) {
        return Registries.ITEM.get(new Identifier(item));
    }

    public static void CUSTOM(String namespace, String id, String GroupsId){
        ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(GroupsId))).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                if(content.shouldShowOpRestrictedItems())
                content.add(getID(namespace,id));
            }
        });
    }
    public static void BUILDING_BLOCKS(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void COLORED_BLOCKS(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COLORED_BLOCKS).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void NATURAL(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void FUNCTIONAL(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void REDSTONE(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void HOTBAR(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.HOTBAR).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void SEARCH(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SEARCH).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void TOOLS(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void COMBAT(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void FOOD_AND_DRINK(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void INGREDIENTS(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void SPAWN_EGGS(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void OPERATOR(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
    public static void INVENTORY(String namespace, String id){
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INVENTORY).register(content -> {
            if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                content.add(getID(namespace,id));
            }
        });
    }
}
