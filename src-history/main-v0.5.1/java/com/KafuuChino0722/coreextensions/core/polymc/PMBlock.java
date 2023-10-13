package com.KafuuChino0722.coreextensions.core.polymc;

import io.github.theepicblock.polymc.api.PolyRegistry;
import io.github.theepicblock.polymc.api.item.CustomModelDataManager;
import io.github.theepicblock.polymc.impl.poly.block.SimpleReplacementPoly;
import io.github.theepicblock.polymc.impl.poly.item.DamageableItemPoly;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolItem;

public class PMBlock {
    public static void registerBlock(Block block, Block vanillaBlock) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerBlockPoly(block, new SimpleReplacementPoly(vanillaBlock.getDefaultState()));
        //registry.registerGlobalItemPoly(block, new SimpleReplacementPoly(vanillaBlock.getDefaultState()));
    }

}
