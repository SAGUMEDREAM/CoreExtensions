package com.KafuuChino0722.coreextensions.core.api.util;

import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class modifyLootTables {

    public static void create(String namespace, String path, String item, double chance, int count) {

        LootTableEvents.MODIFY.register(((resourceManager, lootManager, id, tableBuilder, source) -> {
            id = new Identifier(namespace,path);
            LootPool.Builder poolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(count))
                    .conditionally(RandomChanceLootCondition.builder((float) chance))
                    .with(ItemEntry.builder(IdentifierManager.getItem(item)))
                    .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f,1.0f)));
            tableBuilder.pool(poolBuilder.build());
        }));
    }
}
