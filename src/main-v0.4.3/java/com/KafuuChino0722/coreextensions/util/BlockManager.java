package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.block.*;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

import static net.minecraft.block.Blocks.*;

public class BlockManager {
    //Minecraft Bedrock Edition
    public static final Block NETHERREACTOR = registerBlock("netherreactor",
            new Block(FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK).lightLevel(2).sounds(BlockSoundGroup.STONE).strength(4.0F, 6.0F)));
    public static final Block POWERED_NETHERREACTOR = registerBlock("powered_netherreactor",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).sounds(BlockSoundGroup.STONE).lightLevel(10).strength(4.0F, 6.0F)));
    public static final Block GLOWINGOBSIDIAN = registerBlock("glowing_obsidian",
            new Block(FabricBlockSettings.copyOf(Blocks.OBSIDIAN).lightLevel(15).strength(10.0F, 1200.0F)));
    public static final Block CLIENT_REQUEST_PLACEHOLDER_BLOCK = registerBlock("client_request_placeholder_block",
            new Block(FabricBlockSettings.copyOf(Blocks.GLOWSTONE).strength(50.0F, 10.0F)));
    public static final Block STONE_CUTTER = registerBlock("stone_cutter",
            new StonecutterBEBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.STONE_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.5F)));
    //RV-Pre1
    public static final Block USB_CHARGER = registerBlock("usb_charger",
            new RedstoneBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_BLOCK).strength(4.0F, 6.0F).requires()));
    //Minecraft 2.0
    public static final Block ETHO_TNT = registerBlock("etho_tnt",
            new HalfTNT(FabricBlockSettings.copyOf(Blocks.TNT).strength(0.0F, 0.0F).breakInstantly().sounds(BlockSoundGroup.GRASS).burnable().solidBlock(Blocks::never)));
    public static final Block FOOL_COAL_BLOCK = registerBlock("fool_coal_block",
            new Block(FabricBlockSettings.copyOf(Blocks.COAL_BLOCK).strength(4.0F, 6.0F).instrument(Instrument.BASEDRUM).requires()));
    //20w14infinite
    public static final Block CURSOR = registerBlock("cursor",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    public static final Block ANTBLOCK = registerBlock("ant",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));

    public static final Block NETHERITE_STAIRS = registerBlock("netherite_stairs",
            new StairsBlock(Blocks.NETHERITE_BLOCK.getDefaultState(),
                    AbstractBlock.Settings
                            .copy(Blocks.NETHERITE_BLOCK)
                            .requires()
                            .sounds(BlockSoundGroup.NETHERITE)));
    //Missing
    public static final Block MISSING_BLOCK = registerBlock("missing_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE)));
    //23w13a_or_b
    public static final Block CHEESE = registerBlock("cheese",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE)));

    public static final Block LUNARSTONEBRICK = registerBlock("lunar_stone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.END_STONE_BRICKS)));
    public static final Block ADLUNARSTONEBRICK = registerBlock("ad_lunar_stone_bricks",
            new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK)));
    public static final Block COPPER_SINK = registerBlock("copper_sink",
            new CauldronBlock(FabricBlockSettings.copyOf(Blocks.CAULDRON)));
    /*public static final Block GOLD_CHEST = registerBlock("gold_chest",
            new ChestBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.GOLD)
                    .instrument(Instrument.BASS)
                    .strength(2.5F)
                    .sounds(BlockSoundGroup.WOOD)
                    .burnable(), () -> {
                return BlockEntityType.CHEST;
            }));*/

    public static final Block COPPER_SPLEAVES = registerBlock("copper_spleaves",
            new GlassBlock(AbstractBlock
                    .Settings.create()
                    .instrument(Instrument.HAT)
                    .strength(1.0F).sounds(BlockSoundGroup.COPPER)
                    .nonOpaque()
                    .allowsSpawning(Blocks::never)
                    .solidBlock(Blocks::never)
                    .suffocates(Blocks::never).blockVision(Blocks::never)));
    //There is not to be use
    public static final Block DIRT_SLAB = registerBlock("dirt_slab",
            new SlabBlock(FabricBlockSettings.copyOf(Blocks.DIRT)));

    public static final Block CHEST_LOCKED = registerBlock("chest_locked",
                    new Block(FabricBlockSettings.copyOf(CHEST)));

    public static final Block FLOWER_PEAONIA = register("flower_paeonia",
            new TallFlowerBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.DARK_GREEN)
                    .noCollision()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .offset(AbstractBlock.OffsetType.XZ)
                    .burnable()
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block CLOUD = registerBlock("cloud",
            new Block(FabricBlockSettings.copyOf(WHITE_WOOL)));
    public static final Block FIRE_PLACEGODER_HEX = registerBlock("fire_placegolder_hex",
            new Block(FabricBlockSettings.copyOf(FIRE)));

    //EDUCATAL EDITION
    public static final Block ELEMENT_CONSTRUTOR = registerBlock("element_constructor",
            new StonecutterBEBlock(AbstractBlock.Settings.create()
                    .mapColor(MapColor.IRON_GRAY)
                    .instrument(Instrument.BASEDRUM)
                    .requiresTool()
                    .strength(3.5F)));

    public static final Block MATERIAL_REDUCER = registerBlock("material_reducer",
            new CubeBlock(FabricBlockSettings.copyOf(CRAFTING_TABLE)));

    public static final Block UNDERWATER_TNT = registerBlock("underwater_tnt",
            new UnderWaterTNT(FabricBlockSettings.copyOf(Blocks.TNT).strength(0.0F, 0.0F).breakInstantly().sounds(BlockSoundGroup.GRASS).burnable().solidBlock(Blocks::never)));

    public static final Block COMPOUND_CREATOR = registerBlock("compound_creator",
            new CubeBlock(FabricBlockSettings.copyOf(CAULDRON)));
    public static final Block LAB_TABLE = registerBlock("lab_table",
            new CubeBlock(FabricBlockSettings.copyOf(CRAFTING_TABLE)));

    public static final Block CHEMISTRY_TABLE = registerBlock("chemistry_table",
            new CubeBlock(FabricBlockSettings.copyOf(CRAFTING_TABLE)));
    public static final Block BORDER = register("border",
            new BarrierBlock(AbstractBlock.Settings.create()
                    .strength(-1.0F, 3600000.8F)
                    .dropsNothing().nonOpaque()
                    .allowsSpawning(Blocks::never)
                    .noBlockBreakParticles()
                    .pistonBehavior(PistonBehavior.BLOCK)));
    public static final Block CHEMICAL_HEAT = registerBlock("chemical_heat",
            new Block(FabricBlockSettings.copyOf(COAL_BLOCK)));

    public static final Block ELEMENTS = registerBlock("elements_unknow",
            new Block(FabricBlockSettings.copyOf(STONE)));

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
        entries.add(USB_CHARGER);
        entries.add(ETHO_TNT);
        //entries.add(GOLD_CHEST);
        entries.add(UNDERWATER_TNT);
    }

    private static void addItemsToIngredientTabItemGroup(FabricItemGroupEntries entries) {
        entries.add(CURSOR);
        entries.add(ANTBLOCK);
        entries.add(CHEMICAL_HEAT);
        entries.add(ELEMENTS);
        //
        entries.add(MISSING_BLOCK);
    }
    private static void addItemsToNaturalGroup(FabricItemGroupEntries entries) {
        entries.add(CHEESE);
        entries.add(LUNARSTONEBRICK);
        entries.add(ADLUNARSTONEBRICK);
    }

    private static void addItemsToBuildGroup(FabricItemGroupEntries entries) {
        // 添加需要分类到建筑类的物品
        entries.add(FOOL_COAL_BLOCK);
        entries.add(COPPER_SINK);
        entries.add(COPPER_SPLEAVES);
        entries.add(CHEST_LOCKED);
        entries.add(CLOUD);
    }

    public static void load() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(BlockManager::addItemsToRedStoneGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(BlockManager::addItemsToIngredientTabItemGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(BlockManager::addItemsToBuildGroup);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(BlockManager::addItemsToNaturalGroup);

        if(Reference.EnvType == EnvType.CLIENT) {
            Map<String, Object> blockData;
            setupRenderLayer.set(COPPER_SPLEAVES);
            setupRenderLayer.set(CHEMISTRY_TABLE);
            setupRenderLayer.set(CLOUD);
        }
    }
}
