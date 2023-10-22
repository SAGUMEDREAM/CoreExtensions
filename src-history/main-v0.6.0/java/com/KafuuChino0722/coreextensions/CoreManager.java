package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.core.*;
import com.KafuuChino0722.coreextensions.core.block.*;
import com.KafuuChino0722.coreextensions.core.brrp.BrrpTags;
import com.KafuuChino0722.coreextensions.core.brrp.Export;
import com.KafuuChino0722.coreextensions.core.brrp.IdentifiedTagBuilderExtends;
import com.KafuuChino0722.coreextensions.core.entity.RegModifyVillager;
import com.KafuuChino0722.coreextensions.core.entity.RegVillager;
import com.KafuuChino0722.coreextensions.core.item.*;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.iZipManager;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.MinecraftVersion;
import net.minecraft.block.Block;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.*;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.io.*;
import java.util.HashMap;
import java.util.zip.ZipOutputStream;

public class CoreManager {
    public static final RuntimeResourcePack respacks = RuntimeResourcePack.create(new Identifier("mc", "packs"));
    public static final LanguageProvider.Impl<HashMap<String, String>> provider = LanguageProvider.create();
    public static final IdentifiedTagBuilder<Block> TAG_AXE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_PICKAXE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_SHOVEL_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_HOE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_SWORD_MINEABLE = IdentifiedTagBuilder.createBlock(FabricMineableTags.SWORD_MINEABLE);
    public static final IdentifiedTagBuilder<Block> TAG_SHEARS_MINEABLE = IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE);

    public static final IdentifiedTagBuilder<Item> TAG_TOOL = IdentifiedTagBuilder.createItem(ItemTags.TOOLS);
    public static final IdentifiedTagBuilder<Item> TAG_TOOL_SWORD = IdentifiedTagBuilder.createItem(ItemTags.SWORDS);
    public static final IdentifiedTagBuilder<Item> TAG_TOOL_AXE = IdentifiedTagBuilder.createItem(ItemTags.AXES);
    public static final IdentifiedTagBuilder<Item> TAG_TOOL_PICKAXE = IdentifiedTagBuilder.createItem(ItemTags.PICKAXES);
    public static final IdentifiedTagBuilder<Item> TAG_TOOL_SHOVEL = IdentifiedTagBuilder.createItem(ItemTags.SHOVELS);
    public static final IdentifiedTagBuilder<Item> TAG_TOOL_HOE = IdentifiedTagBuilder.createItem(ItemTags.HOES);

    public static final IdentifiedTagBuilder<Item> TAG_PIGLIN_LOVED = IdentifiedTagBuilder.createItem(ItemTags.PIGLIN_LOVED);

    public static final IdentifiedTagBuilder<Item> TAG_ARROWS = IdentifiedTagBuilder.createItem(ItemTags.ARROWS);
    public static final IdentifiedTagBuilder<Item> TAG_FOX_FOOD = IdentifiedTagBuilder.createItem(ItemTags.FOX_FOOD);
    public static final IdentifiedTagBuilder<Item> TAG_PIGLIN_FOOD = IdentifiedTagBuilder.createItem(ItemTags.PIGLIN_FOOD);
    public static final IdentifiedTagBuilder<Item> TAG_SNIFFER_FOOD = IdentifiedTagBuilder.createItem(ItemTags.SNIFFER_FOOD);

    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_STONE_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_STONE_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_IRON_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_IRON_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_DIAMOND_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_DIAMOND_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_NETHERITE_TOOL = IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(4));

    public static final IdentifiedTagBuilder<Item> TAG_MUSIC_DISCS = IdentifiedTagBuilder.createItem(ItemTags.MUSIC_DISCS);
    public static final IdentifiedTagBuilder<Fluid> TAG_WATER = IdentifiedTagBuilder.createFluid(FluidTags.WATER);
    public static final IdentifiedTagBuilder<Fluid> TAG_LAVA = IdentifiedTagBuilder.createFluid(FluidTags.LAVA);
    public static final IdentifiedTagBuilder<Item> TAG_BED_ITEM = IdentifiedTagBuilder.createItem(ItemTags.BEDS);
    public static final IdentifiedTagBuilder<Block> TAG_BED_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.BEDS);
    public static final IdentifiedTagBuilder<Item> TAG_TALL_FLOWERS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.TALL_FLOWERS);
    public static final IdentifiedTagBuilder<Block> TAG_TALL_FLOWERS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.TALL_FLOWERS);
    public static final IdentifiedTagBuilder<Item> TAG_SMALL_FLOWERS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.SMALL_FLOWERS);
    public static final IdentifiedTagBuilder<Block> TAG_SMALL_FLOWERS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.SMALL_FLOWERS);
    public static final IdentifiedTagBuilder<Block> TAG_FLOWER_POTS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.FLOWER_POTS);
    public static final IdentifiedTagBuilder<Block> TAG_BEE_GROWABLES_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.BEE_GROWABLES);

    public static final IdentifiedTagBuilder<Item> TAG_GLASS_ITEM = IdentifiedTagBuilder.createItem(ItemTags.SMELTS_TO_GLASS);
    public static final IdentifiedTagBuilder<Block> TAG_GLASS_BLOCK = IdentifiedTagBuilder.createBlock(BlockTags.SMELTS_TO_GLASS);
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

    public static final IdentifiedTagBuilder<PaintingVariant> TAG_PAINTINGS = IdentifiedTagBuilderExtends.createPainting(PaintingVariantTags.PLACEABLE);
    public static final IdentifiedTagBuilder<PointOfInterestType> TAG_VILlAGER_JOB_SITE = IdentifiedTagBuilderExtends.createVillagerJobSite(PointOfInterestTypeTags.ACQUIRABLE_JOB_SITE);

    public static final boolean isDev = false;

    public static void bootstrap(boolean bootstrap) {
        boolean CORE_API_Enabled = Config.getConfigBoolean("ENABLED_CORE_API");
        boolean OLD_CORE_API_Enabled = Config.getConfigBoolean("ENABLED_OLD_CORE_API");
        boolean DEBUG_Enabled = Config.getConfigBoolean("ENABLED_DEBUG");
        boolean DataGenEnabled = Config.getConfigBoolean("ENABLED_DATAGEN_EXPORT");

        // 根据 CORE_API_Enabled 的值执行操作
        if (CORE_API_Enabled) {
            Info.create("CoreManager Loading!");
            Info.custom("Registering!","Registry");

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
            if(bootstrap) { RegItemGroups.load(); }
            RegFuels.load();
            RegComposting.load();
            RegCrop.load();
            RegPlants.load();
            RegBlockPainting.load();
            RegEnchantment.load();
            RegVillager.load();
            RegGameRule.load();
            RegPortal.load();
            RegRecipes.load();
            RegModifyItem.load();
            RegModifyTool.load();
            RegModifyWeapon.load();
            RegModifyBlock.load();
            if(bootstrap) { RegModifyGroups.load(); }
            RegModifyRecipes.load();
            RegModifyLootTable.load();
            RegModifyVillager.load();
            RegRefect.load();
            new RegFluids().load();
            iZipManager.setup(CORE_API_Enabled, bootstrap);

            if (DEBUG_Enabled) {
                Info.create("CoreManager Debug Loading!");
            }

            BrrpTags.addTag();

            respacks.setDisplayName(Text.translatable("brrp.configScreen.title"));
            respacks.setDescription(Text.translatable("brrp.pack.defaultName","[mc:packs] CoreExtensions"));
            respacks.setPackVersion(MinecraftVersion.create().getResourceVersion(ResourceType.SERVER_DATA));

            String mcmeta = "{\n" +
                          "  \"pack\": {\n" +
                          "    \"pack_format\": 15,\n" +
                          "    \"description\": \"[mc:packs] CoreExtensions\"\n" +
                          "  }\n" +
                          "}";

            respacks.addRootResource("pack.mcmeta", mcmeta.getBytes());

            respacks.addLang(new Identifier("mc", "zh_cn"), provider);
            respacks.addLang(new Identifier("mc", "en_us"), provider);

            try {
                respacks.regenerate();
            } catch (InterruptedException e) {
                Info.ERROR.info("DATAGEN?!ERROR");
            }


            RRPCallback.BEFORE_VANILLA.register(b -> {
                b.remove(respacks);
                b.add(respacks);
            });
            RRPCallback.BEFORE_USER.register(b -> {
                b.remove(respacks);
                b.add(respacks);
            });
            /*RRPCallback.AFTER_VANILLA.register(b -> {
                  b.remove(respacks);
                  b.add(respacks);
            });*/

            if(DataGenEnabled) {
                respacks.dump(Export.getPath());
                try {
                    respacks.dump(new ZipOutputStream(new FileOutputStream(Export.getPath()+"/ResourcePack+DataPacks.zip")));
                } catch (IOException e) {

                }
            }

            Info.create("Loading completed");

            if (OLD_CORE_API_Enabled) {
                Info.create("CoreManager OLD-API Will Be Loaded!");
            }
        } else {
            Info.create("CoreManager Disabled!if the setting is not you changing,please check your config/coreconfig.yml");
        }
    }
}