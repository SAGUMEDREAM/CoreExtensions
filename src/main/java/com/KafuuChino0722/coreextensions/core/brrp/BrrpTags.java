package com.KafuuChino0722.coreextensions.core.brrp;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.*;
import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class BrrpTags {
    public static void addTag() {
        respacks.addTag(TAG_AXE_MINEABLE);
        respacks.addTag(TAG_PICKAXE_MINEABLE);
        respacks.addTag(TAG_HOE_MINEABLE);
        respacks.addTag(TAG_SHOVEL_MINEABLE);
        respacks.addTag(TAG_SWORD_MINEABLE);
        respacks.addTag(TAG_SHEARS_MINEABLE);
        respacks.addTag(TAG_NEEDS_STONE_TOOL);
        respacks.addTag(TAG_NEEDS_IRON_TOOL);
        respacks.addTag(TAG_NEEDS_DIAMOND_TOOL);
        respacks.addTag(TAG_NEEDS_NETHERITE_TOOL);
        respacks.addTag(TAG_MUSIC_DISCS);
        respacks.addTag(TAG_WATER);
        respacks.addTag(TAG_BED_ITEM);
        respacks.addTag(TAG_BED_BLOCK);
        respacks.addTag(TAG_TALL_FLOWERS_ITEM);
        respacks.addTag(TAG_TALL_FLOWERS_BLOCK);
        respacks.addTag(TAG_SMALL_FLOWERS_ITEM);
        respacks.addTag(TAG_SMALL_FLOWERS_BLOCK);
        respacks.addTag(TAG_FLOWER_POTS_BLOCK);
        respacks.addTag(TAG_FOX_FOOD_ITEM);
        respacks.addTag(TAG_BEE_GROWABLES_BLOCK);
        respacks.addTag(TAG_BUTTONS_ITEM);
        respacks.addTag(TAG_BUTTONS_BLOCK);
        respacks.addTag(TAG_LOGS_ITEM);
        respacks.addTag(TAG_LOGS_BLOCK);
        respacks.addTag(TAG_DOORS_ITEM);
        respacks.addTag(TAG_DOORS_BLOCK);
        respacks.addTag(TAG_FENCES_ITEM);
        respacks.addTag(TAG_FENCES_BLOCK);
        respacks.addTag(TAG_FENCE_GATES_ITEM);
        respacks.addTag(TAG_FENCE_GATES_BLOCK);
        respacks.addTag(TAG_LEAVES_ITEM);
        respacks.addTag(TAG_LEAVES_BLOCK);
        respacks.addTag(TAG_PRESSURE_PLATES_ITEM);
        respacks.addTag(TAG_PRESSURE_PLATES_BLOCK);
        respacks.addTag(TAG_SAND_ITEM);
        respacks.addTag(TAG_SAND_BLOCK);
        respacks.addTag(TAG_SLABS_ITEM);
        respacks.addTag(TAG_SLABS_BLOCK);
        respacks.addTag(TAG_SOUL_SPEED_BLOCKS_BLOCK);
        respacks.addTag(TAG_SOUL_FIRE_BASE_BLOCKS_BLOCK);
        respacks.addTag(TAG_WITHER_SUMMON_BASE_BLOCKS_BLOCK);
        respacks.addTag(TAG_STAIRS_ITEM);
        respacks.addTag(TAG_STAIRS_BLOCK);
        respacks.addTag(TAG_TRAPDOORS_ITEM);
        respacks.addTag(TAG_TRAPDOORS_BLOCK);
        respacks.addTag(TAG_WALLS_ITEM);
        respacks.addTag(TAG_WALLS_BLOCK);
        respacks.addTag(TAG_FIRE_BLOCK);
        respacks.addTag(TAG_TRIM_MATERIALS);
    }
}
