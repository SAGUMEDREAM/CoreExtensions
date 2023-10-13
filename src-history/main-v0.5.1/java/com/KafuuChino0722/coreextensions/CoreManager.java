package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.command.CoreRegReload;
import com.KafuuChino0722.coreextensions.core.block.*;
import com.KafuuChino0722.coreextensions.core.brrp.BrrpTags;
import com.KafuuChino0722.coreextensions.core.brrp.Export;
import com.KafuuChino0722.coreextensions.core.entity.RegModifyVillager;
import com.KafuuChino0722.coreextensions.core.entity.RegVillager;
import com.KafuuChino0722.coreextensions.core.item.*;
import com.KafuuChino0722.coreextensions.util.*;

import com.KafuuChino0722.coreextensions.core.*;
import com.KafuuChino0722.coreextensions.core.RegPortal;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class CoreManager {

    public static final RuntimeResourcePack respacks = RuntimeResourcePack.create(new Identifier("mc", "packs"));
    public static final LanguageProvider.Impl<HashMap<String, String>> provider = LanguageProvider.create();
    public static final IdentifiedTagBuilder<Block> TAG_AXE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_PICKAXE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_SHOVEL_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_HOE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_SWORD_MINEABLE = IdentifiedTagBuilder.createBlock(FabricMineableTags.SWORD_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_SHEARS_MINEABLE = IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE);

    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_STONE_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_STONE_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_IRON_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_IRON_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_DIAMOND_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_DIAMOND_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_NETHERITE_TOOL = IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(4));

    public static final IdentifiedTagBuilder<Item> TAG_MUSIC_DISCS = IdentifiedTagBuilder.createItem(ItemTags.MUSIC_DISCS);
    public static final IdentifiedTagBuilder<Fluid> TAG_WATER = IdentifiedTagBuilder.createFluid(FluidTags.WATER);
    public static final IdentifiedTagBuilder<Item> TAG_BED_ITEM = IdentifiedTagBuilder.createItem(ItemTags.BEDS);
    public static final IdentifiedTagBuilder<Block> TAG_BED_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.BEDS);
    public static final IdentifiedTagBuilder<Item> TAG_TALL_FLOWERS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.TALL_FLOWERS);
    public static final IdentifiedTagBuilder<Block> TAG_TALL_FLOWERS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.TALL_FLOWERS);
    public static final IdentifiedTagBuilder<Item> TAG_SMALL_FLOWERS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.SMALL_FLOWERS);
    public static final IdentifiedTagBuilder<Block> TAG_SMALL_FLOWERS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.SMALL_FLOWERS);
    public static final IdentifiedTagBuilder<Block> TAG_FLOWER_POTS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.FLOWER_POTS);
    public static final IdentifiedTagBuilder<Item> TAG_FOX_FOOD_ITEM = IdentifiedTagBuilder.createItem(ItemTags.FOX_FOOD);
    public static final IdentifiedTagBuilder<Block> TAG_BEE_GROWABLES_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.BEE_GROWABLES);

    public static final IdentifiedTagBuilder<Item> TAG_BUTTONS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.BUTTONS);
    public static final IdentifiedTagBuilder<Block> TAG_BUTTONS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.BUTTONS);
    public static final IdentifiedTagBuilder<Item> TAG_LOGS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.LOGS);
    public static final IdentifiedTagBuilder<Block> TAG_LOGS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.LOGS);
    public static final IdentifiedTagBuilder<Item> TAG_DOORS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.DOORS);
    public static final IdentifiedTagBuilder<Block> TAG_DOORS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.DOORS);
    public static final IdentifiedTagBuilder<Item> TAG_FENCES_ITEM = IdentifiedTagBuilder.createItem(ItemTags.FENCES);
    public static final IdentifiedTagBuilder<Block> TAG_FENCES_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.FENCES);
    public static final IdentifiedTagBuilder<Item> TAG_FENCE_GATES_ITEM = IdentifiedTagBuilder.createItem(ItemTags.FENCE_GATES);
    public static final IdentifiedTagBuilder<Block> TAG_FENCE_GATES_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.FENCE_GATES);
    public static final IdentifiedTagBuilder<Item> TAG_LEAVES_ITEM = IdentifiedTagBuilder.createItem(ItemTags.LEAVES);
    public static final IdentifiedTagBuilder<Block> TAG_LEAVES_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.LEAVES);
    public static final IdentifiedTagBuilder<Item> TAG_PRESSURE_PLATES_ITEM = IdentifiedTagBuilder.createItem(ItemTags.WOODEN_PRESSURE_PLATES);
    public static final IdentifiedTagBuilder<Block> TAG_PRESSURE_PLATES_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.PRESSURE_PLATES);
    public static final IdentifiedTagBuilder<Item> TAG_SAND_ITEM = IdentifiedTagBuilder.createItem(ItemTags.SAND);
    public static final IdentifiedTagBuilder<Block> TAG_SAND_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.SAND);
    public static final IdentifiedTagBuilder<Item> TAG_SLABS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.SLABS);
    public static final IdentifiedTagBuilder<Block> TAG_SLABS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.SLABS);
    public static final IdentifiedTagBuilder<Block> TAG_SOUL_SPEED_BLOCKS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.SOUL_SPEED_BLOCKS);
    public static final IdentifiedTagBuilder<Block> TAG_SOUL_FIRE_BASE_BLOCKS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.SOUL_FIRE_BASE_BLOCKS);
    public static final IdentifiedTagBuilder<Block> TAG_WITHER_SUMMON_BASE_BLOCKS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.WITHER_SUMMON_BASE_BLOCKS);
    public static final IdentifiedTagBuilder<Item> TAG_STAIRS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.STAIRS);
    public static final IdentifiedTagBuilder<Block> TAG_STAIRS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.STAIRS);
    public static final IdentifiedTagBuilder<Item> TAG_TRAPDOORS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.TRAPDOORS);
    public static final IdentifiedTagBuilder<Block> TAG_TRAPDOORS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.TRAPDOORS);
    public static final IdentifiedTagBuilder<Item> TAG_WALLS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.WALLS);
    public static final IdentifiedTagBuilder<Block> TAG_WALLS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.WALLS);
    public static final IdentifiedTagBuilder<Block> TAG_FIRE_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.FIRE);
    public static final IdentifiedTagBuilder<Item> TAG_TRIM_MATERIALS = IdentifiedTagBuilder.createItem(ItemTags.TRIM_MATERIALS);



    public static void load(boolean BOOLEAN) {
        Yaml yaml = new Yaml();
        boolean CORE_API_Enabled = true;
        boolean OLD_CORE_API_Enabled = false;
        boolean DEBUG_Enabled = false;
        boolean DataGenEnabled = true;
        try {
            File configFile = new File("config/coreconfig.yml");

            if (configFile.exists()) {
                Map<String, Map<String, Object>> configData = yaml.load(new FileReader(configFile));

                if (configData != null && configData.containsKey("settings")) {
                    Map<String, Object> settings = configData.get("settings");

                    if (settings.containsKey("CORE_API")) {
                        Object CORE_API_Enabled_Value = settings.get("CORE_API");
                        if (CORE_API_Enabled_Value instanceof Boolean) {
                            CORE_API_Enabled = (boolean) CORE_API_Enabled_Value;
                        }
                    }
                    if (settings.containsKey("DEBUG")) {
                        Object DEBUG_Enabled_Value = settings.get("DEBUG");
                        if (DEBUG_Enabled_Value instanceof Boolean) {
                            DEBUG_Enabled = (boolean) DEBUG_Enabled_Value;
                        }
                    }
                    if (settings.containsKey("DATAGEN_EXPORT")) {
                        Object DATAGEN_Value = settings.get("DATAGEN_EXPORT");
                        if (DATAGEN_Value instanceof Boolean) {
                            DataGenEnabled = (boolean) DATAGEN_Value;
                        }
                    }

                    if (settings.containsKey("OLD")) {
                        Object OLD_CORE_API_Value = settings.get("OLD");
                        if (OLD_CORE_API_Value instanceof Boolean) {
                            OLD_CORE_API_Enabled = (boolean) OLD_CORE_API_Value;
                        }
                    }
                }
            } else {
                System.out.println("配置文件不存在！");
            }

            // 根据 CORE_API_Enabled 的值执行操作
            if (CORE_API_Enabled) {
                if (DEBUG_Enabled) {
                    Info.create("CoreManager Debug Loaded!");
                }
                Info.create("CoreManager Loaded!");

                respacks.clearResources();

                RegCommand.load();
                RegTags.load();
                RegBlocks.load();
                RegItems.load();
                RegMusicCD.load();
                RegWeapon.load();
                RegTool.load();
                RegArmors.load();
                RegElytra.load();
                RegTrimMaterials.load();
                RegItemGroups.load();
                RegFuels.load();
                RegComposting.load();
                RegCrop.load();
                RegPlants.load();
                RegBlockPainting.load();
                RegVillager.load();
                RegGameRule.load();
                RegPortal.load();
                RegRecipes.load();
                RegModifyItem.load();
                RegModifyTool.load();
                RegModifyWeapon.load();
                RegModifyBlock.load();
                if(BOOLEAN) { RegModifyGroups.load(); }
                RegModifyRecipes.load();
                RegModifyLootTable.load();
                RegModifyVillager.load();
                RegRefect.load();
                new RegFluids().load();
                iZipManager.setup(CORE_API_Enabled, BOOLEAN);

                BrrpTags.addTag();

                respacks.addLang(new Identifier("mc", "zh_cn"), provider);
                respacks.addLang(new Identifier("mc", "en_us"), provider);

                RRPCallback.BEFORE_VANILLA.register(b -> {
                    b.add(respacks);
                });

                if(DataGenEnabled) {
                    respacks.dump(Export.getPath());
                }

                if (OLD_CORE_API_Enabled) {
                    Info.create("CoreManager OLD-API Will Be Loaded!");
                }
            } else {
                Info.create("CoreManager Disabled!if the setting is not you changing,please check your config/coreconfig.yml");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}