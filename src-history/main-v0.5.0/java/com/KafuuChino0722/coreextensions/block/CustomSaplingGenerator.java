package com.KafuuChino0722.coreextensions.block;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public class CustomSaplingGenerator extends SaplingGenerator {


    private static RegistryKey<ConfiguredFeature<?,?>> KEY;

    public CustomSaplingGenerator(RegistryKey<ConfiguredFeature<?,?>> treeKey) {
        this.KEY = treeKey;
    }

    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return this.KEY;
    }
}
