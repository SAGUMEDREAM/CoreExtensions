package com.KafuuChino0722.coreextensions.outdate;

import com.KafuuChino0722.coreextensions.block.HalfTNT;
import com.KafuuChino0722.coreextensions.block.StonecutterBEBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.block.sapling.CherrySaplingGenerator;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

public class BlockManager {
    //1.19.4以下兼容修复
    public static final Block BAMBOO_MOSAIC = registerBlock("bamboo_mosaic",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW)
                    .instrument(Instrument.BASS).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block CHERRY_PLANKS = registerBlock("cherry_planks",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.TERRACOTTA_WHITE)
                    .instrument(Instrument.BASS).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block BAMBOO_PLANKS = registerBlock("bamboo_planks",
            new Block(AbstractBlock.Settings.create().mapColor(MapColor.YELLOW)
                    .instrument(Instrument.BASS).strength(2.0F, 3.0F)
                    .sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block CHERRY_LOG = registerBlock("cherry_log",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_LOG)));
    public static final Block STRIPPED_CHERRY_LOG = registerBlock("stripped_cherry_log",
            new Block(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_LOG)));
    public static final Block STRIPPED_BAMBOO_BLOCK = registerBlock("stripped_bamboo_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STRIPPED_OAK_WOOD)));
    public static final Block BAMBOO_BLOCK = registerBlock("bamboo_block",
            new Block(FabricBlockSettings.copyOf(Blocks.OAK_PLANKS)));
    public static final Block CHERRY_LEAVES = registerBlock("cherry_leaves",
            new LeavesBlock(AbstractBlock.Settings.create().mapColor(MapColor.PINK)
                    .strength(0.2F)
                    .ticksRandomly()
                    .sounds(BlockSoundGroup.CHERRY_LEAVES)
                    .nonOpaque()
                    .allowsSpawning(Blocks::canSpawnOnLeaves)
                    .suffocates(Blocks::never)
                    .blockVision(Blocks::never)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)));
    public static final Block CHERRY_SAPLING = registerBlock("cherry_sapling",
            new SaplingBlock(new OakSaplingGenerator(),
                    AbstractBlock.Settings.create()
                            .mapColor(MapColor.PINK)
                            .noCollision()
                            .ticksRandomly()
                            .breakInstantly()
                            .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block POTTED_CHERRY_SAPLING = registerBlock("potted_cherry_sapling",
            Blocks.createFlowerPotBlock(Blocks.OAK_SAPLING));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier("minecraft", name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier("minecraft", name ),
                new BlockItem(block, new FabricItemSettings()));
    }

    private static void addItemsToRedStoneGroup(FabricItemGroupEntries entries) {
        // 添加需要分类到红石类的物品

    }

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {

    }
    private static void addItemsToNaturalGroup(FabricItemGroupEntries entries) {

    }

    private static void addItemsToBuildGroup(FabricItemGroupEntries entries) {
        // 添加需要分类到建筑类的物品
        entries.add(BAMBOO_MOSAIC);
        entries.add(BAMBOO_BLOCK);
        entries.add(BAMBOO_PLANKS);
        entries.add(CHERRY_LEAVES);
        entries.add(CHERRY_LOG);
        entries.add(CHERRY_SAPLING);
        entries.add(CHERRY_PLANKS);
        entries.add(STRIPPED_CHERRY_LOG);
        entries.add(STRIPPED_BAMBOO_BLOCK);
    }

    public static void load() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(BlockManager::addItemsToRedStoneGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(BlockManager::addItemsToIngredientTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(BlockManager::addItemsToBuildGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(BlockManager::addItemsToNaturalGroup);
    }
}
