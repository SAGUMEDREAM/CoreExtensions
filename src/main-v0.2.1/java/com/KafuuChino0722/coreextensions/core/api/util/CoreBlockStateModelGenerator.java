package com.KafuuChino0722.coreextensions.core.api.util;

import com.google.gson.JsonElement;
import net.minecraft.block.Block;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.block.enums.StairShape;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.data.client.*;
import net.minecraft.item.Item;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class CoreBlockStateModelGenerator extends BlockStateModelGenerator {

    public CoreBlockStateModelGenerator(Consumer<BlockStateSupplier> blockStateCollector, BiConsumer<Identifier, Supplier<JsonElement>> modelCollector, Consumer<Item> simpleItemModelExemptionCollector) {
        super(blockStateCollector, modelCollector, simpleItemModelExemptionCollector);
    }

    public static VariantsBlockStateSupplier createSingletonBlockState(Block block, Identifier modelId) {
        return VariantsBlockStateSupplier
                .create(block, BlockStateVariant.create().put(VariantSettings.MODEL, modelId));
    }

    public static VariantsBlockStateSupplier createCorpBlockState(Block block, Identifier modelId) {
        return VariantsBlockStateSupplier
                .create(block, BlockStateVariant.create().put(VariantSettings.MODEL, modelId));
    }

    public static BlockStateSupplier createFireBlockState(Block fireBlock, Identifier TEMPLATE_FIRE_UP, Identifier TEMPLATE_FIRE_FLOOR, Identifier TEMPLATE_FIRE_UP_ALT, Identifier TEMPLATE_FIRE_SIDE_ALT, Identifier TEMPLATE_FIRE_SIDE) {
        //return MultipartBlockStateSupplier.create(fireBlock);

        return null;

    }
}