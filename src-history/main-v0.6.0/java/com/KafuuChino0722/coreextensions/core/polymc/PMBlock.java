package com.KafuuChino0722.coreextensions.core.polymc;

import io.github.theepicblock.polymc.api.PolyRegistry;
import io.github.theepicblock.polymc.impl.poly.block.SimpleReplacementPoly;
import net.minecraft.block.Block;

public class PMBlock {
    public static void registerBlock(Block block, Block vanillaBlock) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerBlockPoly(block, new SimpleReplacementPoly(vanillaBlock.getDefaultState()));
        //registry.registerGlobalItemPoly(block, new SimpleReplacementPoly(vanillaBlock.getDefaultState()));
    }

}
