package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.Identifier;

import java.util.List;

public class RayCastBlock implements ConditionsInterface{

    public static boolean get(BlockState blockState, List<String> blocks) {
        if (blocks.contains("*")) return false;
        for (String block : blocks) {
            Block I = Registries.BLOCK.get(new Identifier(block));
            if (Registries.BLOCK.containsId(new Identifier(block))) {
                if (blockState.isOf(I)) {
                    return false;
                }
            }
        }
        return true;
    }
}
