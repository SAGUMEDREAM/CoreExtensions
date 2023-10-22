package com.KafuuChino0722.coreextensions.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class VanillaManager {

    public static final Item WATER = register(Blocks.WATER);
    public static final Item LAVA = register(Blocks.LAVA);
    public static final Item FIRE = register(Blocks.FIRE);
    public static final Item SOUL_FIRE = register(Blocks.SOUL_FIRE);
    public static final Item PISTON_HEAD = register(Blocks.PISTON_HEAD);
    public static final Item MOVING_PISTON = register(Blocks.MOVING_PISTON);
    public static final Item NETHER_PORTAL = register(Blocks.NETHER_PORTAL);
    public static final Item END_PORTAL = register(Blocks.END_PORTAL);
    public static final Item END_GATEWAY = register(Blocks.END_GATEWAY);
    public static final Item PUMPKIN_STEM = register(Blocks.PUMPKIN_STEM);
    public static final Item MELON_STEM = register(Blocks.MELON_STEM);
    public static final Item BUBBLE_COLUMN = register(Blocks.BUBBLE_COLUMN);

    public void Items() {
    }

    public static Item register(Block block) {
        return register(new BlockItem(block, new Item.Settings()));
    }

    public static Item register(Block block, Block... blocks) {
        BlockItem blockItem = new BlockItem(block, new Item.Settings());
        Block[] var3 = blocks;
        int var4 = blocks.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Block block2 = var3[var5];
            Item.BLOCK_ITEMS.put(block2, blockItem);
        }

        return register(blockItem);
    }

    public static Item register(BlockItem item) {
        return register((Block)item.getBlock(), (Item)item);
    }

    public static Item register(Block block, Item item) {
        return register(Registries.BLOCK.getId(block), item);
    }

    public static Item register(String id, Item item) {
        return register(new Identifier(id), item);
    }

    public static Item register(Identifier id, Item item) {
        return register(RegistryKey.of(Registries.ITEM.getKey(), id), item);
    }

    public static Item register(RegistryKey<Item> key, Item item) {
        if (item instanceof BlockItem) {
            ((BlockItem)item).appendBlocks(Item.BLOCK_ITEMS, item);
        }

        return (Item)Registry.register(Registries.ITEM, key, item);
    }

    public static void load() {
    }
}
