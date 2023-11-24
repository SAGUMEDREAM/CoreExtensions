package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.block.HighCrops;
import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.data.client.*;
import net.minecraft.data.client.Models;
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

    public static BlockStateSupplier createHangingSignBlockState(Block block, Identifier modelId) {
        return VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL, modelId));
    }

    public static BlockStateSupplier createHighPlantBlockState(Block block, Identifier modelId, Identifier modelId2) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.DOUBLE_BLOCK_HALF)
                        .register(DoubleBlockHalf.LOWER, BlockStateVariant.create().put(VariantSettings.MODEL, modelId))
                        .register(DoubleBlockHalf.UPPER, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                );}

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

    public static BlockStateSupplier createCropBlockState1(Block block, Identifier modelId0, Identifier modelId1) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_1)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                );
    }

    public static BlockStateSupplier createCropBlockState2(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_2)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                );
    }

    public static BlockStateSupplier createCropBlockState3(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_3)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                );
    }

    public static BlockStateSupplier createCropBlockState4(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3, Identifier modelId4) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_4)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                        .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, modelId4))
                );
    }

    public static BlockStateSupplier createCropBlockState5(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3, Identifier modelId4, Identifier modelId5) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_5)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                        .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, modelId4))
                        .register(5, BlockStateVariant.create().put(VariantSettings.MODEL, modelId5))
                );
    }

    public static BlockStateSupplier createCropBlockState6(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3, Identifier modelId4, Identifier modelId5, Identifier modelId6) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Int.AGE_6)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                        .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, modelId4))
                        .register(5, BlockStateVariant.create().put(VariantSettings.MODEL, modelId5))
                        .register(6, BlockStateVariant.create().put(VariantSettings.MODEL, modelId6))
                );
    }


    public static BlockStateSupplier createCropBlockState7(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3, Identifier modelId4, Identifier modelId5, Identifier modelId6, Identifier modelId7) {
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

    public static BlockStateSupplier createCropBlockState8(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3, Identifier modelId4, Identifier modelId5, Identifier modelId6, Identifier modelId7, Identifier modelId8) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(HighCrops.AGE)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                        .register(4, BlockStateVariant.create().put(VariantSettings.MODEL, modelId4))
                        .register(5, BlockStateVariant.create().put(VariantSettings.MODEL, modelId5))
                        .register(6, BlockStateVariant.create().put(VariantSettings.MODEL, modelId6))
                        .register(7, BlockStateVariant.create().put(VariantSettings.MODEL, modelId7))
                        .register(8, BlockStateVariant.create().put(VariantSettings.MODEL, modelId8))
                );
    }

    public static BlockStateSupplier createFruitBushBlockState(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(BlockStateVariantMap.create(Properties.AGE_3)
                        .register(0, BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                        .register(1, BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                        .register(2, BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                        .register(3, BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                );
    }

    public static BlockStateSupplier createGlassPaneBlockState(Block block, Identifier modelId0, Identifier modelId1, Identifier modelId2, Identifier modelId3, Identifier modelId4) {
        return MultipartBlockStateSupplier.create(block)
                .with(BlockStateVariant.create().put(VariantSettings.MODEL, modelId0))
                .with((When)When.create().set(Properties.NORTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId1))
                .with((When)When.create().set(Properties.EAST, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId1).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with((When)When.create().set(Properties.SOUTH, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId2))
                .with((When)When.create().set(Properties.WEST, true), BlockStateVariant.create().put(VariantSettings.MODEL, modelId2).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with((When)When.create().set(Properties.NORTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, modelId3))
                .with((When)When.create().set(Properties.EAST, false), BlockStateVariant.create().put(VariantSettings.MODEL, modelId4))
                .with((When)When.create().set(Properties.SOUTH, false), BlockStateVariant.create().put(VariantSettings.MODEL, modelId4).put(VariantSettings.Y, VariantSettings.Rotation.R90))
                .with((When)When.create().set(Properties.WEST, false), BlockStateVariant.create().put(VariantSettings.MODEL, modelId3).put(VariantSettings.Y, VariantSettings.Rotation.R270));

    }
}