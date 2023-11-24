package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionSetItemCount implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> decrementKey = (Map<String, Object>) Key.getOrDefault("setCount", null);
        if(decrementKey!=null) {
            ServerPlayerEntity player = user.getCommandSource().getPlayer();
            int Int = (int) decrementKey.getOrDefault("count",0);
            if (player != null) {
                user.getActiveItem().setCount(Int);
            }
        }
    }
}
