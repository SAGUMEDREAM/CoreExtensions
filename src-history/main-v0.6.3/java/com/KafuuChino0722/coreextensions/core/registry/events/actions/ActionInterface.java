package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public interface ActionInterface {

    static void run(World world, PlayerEntity user, Hand hand, Map<String, Object> Key) {

    }

    static void run(ItemUsageContext context, Map<String, Object> Key) {
    }
}
