package com.KafuuChino0722.coreextensions.util;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class ItemGroup {
    public static void load() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(content -> {
            content.addAfter(Items.EXPERIENCE_BOTTLE, VanillaManager.WATER);
            content.addAfter(VanillaManager.WATER, VanillaManager.LAVA);
            content.addAfter(VanillaManager.LAVA, VanillaManager.FIRE);
            content.addAfter(VanillaManager.FIRE, VanillaManager.SOUL_FIRE);
            content.addAfter(VanillaManager.SOUL_FIRE, VanillaManager.PISTON_HEAD);
            content.addAfter(VanillaManager.PISTON_HEAD, VanillaManager.MOVING_PISTON);
            content.addAfter(VanillaManager.MOVING_PISTON , VanillaManager.NETHER_PORTAL);
            content.addAfter(VanillaManager.NETHER_PORTAL, VanillaManager.END_PORTAL);
            content.addAfter(VanillaManager.END_PORTAL, VanillaManager.END_GATEWAY);
            content.addAfter(VanillaManager.END_GATEWAY, VanillaManager.PUMPKIN_STEM);
            content.addAfter(VanillaManager.PUMPKIN_STEM, VanillaManager.MELON_STEM);
            content.addAfter(VanillaManager.MELON_STEM, VanillaManager.BUBBLE_COLUMN);
            content.addAfter(VanillaManager.FIRE, BlockManager.FIRE_PLACEGODER_HEX);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(content -> {
            content.addAfter(Items.INFESTED_DEEPSLATE, BlockManager.CLIENT_REQUEST_PLACEHOLDER_BLOCK);
            content.addAfter(BlockManager.CLIENT_REQUEST_PLACEHOLDER_BLOCK, BlockManager.NETHERREACTOR);
            content.addAfter(BlockManager.NETHERREACTOR, BlockManager.POWERED_NETHERREACTOR);
            content.addAfter(BlockManager.POWERED_NETHERREACTOR, BlockManager.STONE_CUTTER);
            content.addAfter(BlockManager.STONE_CUTTER, BlockManager.CHEST_LOCKED);
            content.addAfter(BlockManager.CHEST_LOCKED, BlockManager.ELEMENT_CONSTRUTOR);
            content.addAfter(BlockManager.ELEMENT_CONSTRUTOR, BlockManager.COMPOUND_CREATOR);
            content.addAfter(BlockManager.COMPOUND_CREATOR, BlockManager.LAB_TABLE);
            content.addAfter(BlockManager.LAB_TABLE, BlockManager.CHEMISTRY_TABLE);
            content.addAfter(BlockManager.CHEMISTRY_TABLE, BlockManager.MATERIAL_REDUCER);

        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(content -> {
            content.addAfter(Items.CRYING_OBSIDIAN, BlockManager.GLOWINGOBSIDIAN);
            content.addAfter(Items.DIRT, BlockManager.DIRT_SLAB);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.NETHERITE_BLOCK, BlockManager.NETHERITE_STAIRS);
        });
    }
}
