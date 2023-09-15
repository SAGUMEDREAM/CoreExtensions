package com.KafuuChino0722.coreextensions.core.api.util;

import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockStateModelGeneratorExtends extends BlockStateModelGenerator {


    public BlockStateModelGeneratorExtends(Consumer<BlockStateSupplier> blockStateCollector, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<Item> simpleItemModelExemptionCollector) {
        super(blockStateCollector, modelCollector, simpleItemModelExemptionCollector);
    }

    public static BlockStateSupplier createCubeBlockState(Block block, Identifier modelId) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING)
                        .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
                        .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.Y, VariantSettings.Rotation.R90)));
    }

    public static BlockStateSupplier createWallTorchBlockState(Block block, Identifier modelId) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.HORIZONTAL_FACING)
                        .register(Direction.NORTH, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.Y, VariantSettings.Rotation.R270))
                        .register(Direction.WEST, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.Y, VariantSettings.Rotation.R180))
                        .register(Direction.EAST, BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
                        .register(Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.MODEL, modelId).put(VariantSettings.Y, VariantSettings.Rotation.R90)));
    }

    public static BlockStateSupplier createLampBlockState(Block block, Identifier modelId, Identifier modelId2) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.LIT)
                        .register(true, BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
                        .register(false, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                );
    }

    public static BlockStateSupplier createCropBlockState(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3, Identifier modelId4, Identifier modelId5, Identifier modelId6, Identifier modelId7) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_7)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                        .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, modelId4))
                        .register(5, BlockStateVariant.create().put(VariantSettings.MODEL, modelId5))
                        .register(6, BlockStateVariant.create().put(VariantSettings.MODEL, modelId6))
                        .register(7, BlockStateVariant.create().put(VariantSettings.MODEL, modelId7))
                );
    }
}