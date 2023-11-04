package com.LoneDev.itemsadder.util;

import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;


import static com.LoneDev.itemsadder.Main.IaPacks;

public class IaLoot {
    public static void addDrop(String namespace, String id) {

        IaPacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id))).type(LootContextTypes.BLOCK).randomSequenceId(new Identifier(namespace,"blocks/"+id)));

    }
    public static void addDrop(Block block) {

        IaPacks.addLootTable(block.getLootTableId(), new VanillaBlockLootTableGenerator().drops(block).type(LootContextTypes.BLOCK).randomSequenceId(block.getLootTableId()));

    }
}
