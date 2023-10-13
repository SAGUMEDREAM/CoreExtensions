package com.LoneDev.itemsadder.api;

import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.LootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.LoneDev.itemsadder.Main.IaPacks;
import static net.minecraft.loot.LootTables.LOOT_TABLES;

public class CustomLoots {

    public static Item item = Items.STONE;
    public static String type;
    public static double chance;
    public static int min_amount = 1;
    public static int max_amount = 1;
    public static VanillaBlockLootTableGenerator LootTableV = new VanillaBlockLootTableGenerator();

    public void load(String namespace, Map<String, Object> DataAll) {

        if(DataAll.containsKey("loots")) {
            try {
                Map<String, Object> loots = (Map<String, Object>) DataAll.get("loots");
                if(loots.containsKey("blocks")) {
                    Map<String, Object> blocks = (Map<String, Object>) loots.get("blocks");
                    if (blocks != null) {
                        for (Map.Entry<String, Object> blockEntry : blocks.entrySet()) {
                            String KeyHeader = blockEntry.getKey().toLowerCase();
                            Map<String, Object> blockData = (Map<String, Object>) blockEntry.getValue();
                            if(blockData.containsKey("type")) {
                                type = (String) blockData.get("type");
                            }
                            if(blockData.containsKey("items")) {
                                Map<String, Object> itemKey = (Map<String, Object>) blockData.get("items");
                                if (itemKey != null) {
                                    for (Map.Entry<String, Object> itemEntry : itemKey.entrySet()) {
                                        Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();
                                        if(itemData.containsKey("min_amount")) {
                                            min_amount = (int) itemData.get("min_amount");
                                        }
                                        if(itemData.containsKey("max_amount")) {
                                            max_amount = (int) itemData.get("max_amount");
                                        }
                                        if(itemData.containsKey("chance")) {
                                            chance = (double) itemData.get("chance");
                                        }
                                        item = Registries.ITEM.get(new Identifier((String) itemData.get("item")));
                                    }
                                }
                            }
                            if (type != null) {
                                IaPacks.addLootTable(Registries.BLOCK.get(new Identifier(type)).getLootTableId(),
                                        new VanillaBlockLootTableGenerator()
                                                .drops(Registries.BLOCK.get(new Identifier(type)), item)
                                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(max_amount, min_amount)))
                                                .apply((LootFunction.Builder) RandomChanceLootCondition.builder((float) chance))
                                );
                            }
                        }
                    }
                }
                if(loots.containsKey("fishing")) {
                    Map<String, Object> blocks = (Map<String, Object>) loots.get("fishing");
                    if (blocks != null) {
                        for (Map.Entry<String, Object> blockEntry : blocks.entrySet()) {
                            String KeyHeader = blockEntry.getKey().toLowerCase();
                            Map<String, Object> blockData = (Map<String, Object>) blockEntry.getValue();
                            int chance = 25;
                            if(blockData.containsKey("items")) {
                                Map<String, Object> itemKey = (Map<String, Object>) blockData.get("items");
                                if (itemKey != null) {
                                    for (Map.Entry<String, Object> itemEntry : itemKey.entrySet()) {
                                        Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();
                                        if(itemData.containsKey("min_amount")) {
                                            min_amount = (int) itemData.get("min_amount");
                                        }
                                        if(itemData.containsKey("max_amount")) {
                                            max_amount = (int) itemData.get("max_amount");
                                        }
                                        if(itemData.containsKey("chance")) {
                                            chance = (int) itemData.get("chance");
                                        }
                                        item = Registries.ITEM.get(new Identifier((String) itemData.get("item")));
                                    }
                                }
                            }
                            IaPacks.addLootTable(CustomLoots.register("gameplay/fishing/fish"),
                                            LootTable.builder().pool(
                                                    LootPool.builder()
                                                    .with((LootPoolEntry.Builder<?>)
                                                            ItemEntry.builder(item).weight(chance))
                                            ));
                            /*IaPacks.addLootTable(CustomLoots.register("gameplay/fishing/fish"),
                                            LootTable.builder().pool(
                                                    LootPool.builder()
                                                    .with((LootPoolEntry.Builder<?>)
                                                            ItemEntry.builder(Items.COD).weight(60)).with((LootPoolEntry.Builder<?>)
                                                                    ItemEntry.builder(Items.SALMON).weight(25)).with((LootPoolEntry.Builder<?>)
                                                                    ItemEntry.builder(Items.TROPICAL_FISH).weight(2)).with((LootPoolEntry.Builder<?>)
                                                                    ItemEntry.builder(Items.PUFFERFISH).weight(13))
                                            ));*/
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
    private static Identifier register(String id) {
        return CustomLoots.registerLootTable(new Identifier(id));
    }
    private static Identifier registerLootTable(Identifier id) {
        if (LOOT_TABLES.add(id)) {
            return id;
        }
        throw new IllegalArgumentException(id + " is already a registered built-in loot table");
    }
}
