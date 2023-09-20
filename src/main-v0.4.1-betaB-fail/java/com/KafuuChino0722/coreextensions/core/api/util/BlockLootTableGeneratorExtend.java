package com.KafuuChino0722.coreextensions.core.api.util;

import net.fabricmc.fabric.api.resource.conditions.v1.ConditionJsonProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Set;

public class BlockLootTableGeneratorExtend extends BlockLootTableGenerator {

    public final String id;

    protected BlockLootTableGeneratorExtend(Set<Item> explosionImmuneItems, FeatureSet requiredFeatures, String id) {
        super(explosionImmuneItems, requiredFeatures);
        this.id = id;
    }


    @Override
    public void generate() {

    }

    @Override
    public BlockLootTableGenerator withConditions(ConditionJsonProvider... conditions) {
        return super.withConditions(conditions);
    }

    public LootTable.Builder seedDrops(Block dropWithShears) {
        return BlockLootTableGenerator.dropsWithShears(
                dropWithShears,
                (LootPoolEntry.Builder)
                        this.applyExplosionDecay
                                (dropWithShears, ((LeafEntry.Builder)
                                        ItemEntry.builder(IdentifierManager.getItem(id))
                                                .conditionally(RandomChanceLootCondition
                                                        .builder(0.125f)))
                                        .apply(ApplyBonusLootFunction.uniformBonusCount(Enchantments.FORTUNE, 2))));
    }

}
