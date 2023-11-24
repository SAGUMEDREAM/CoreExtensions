package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionAddUpItemCount implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> addupKey = (Map<String, Object>) Key.getOrDefault("addCount", null);
        if (addupKey != null) {
            ServerPlayerEntity player = user.getCommandSource().getPlayer();
            int Int = (int) addupKey.getOrDefault("count", 0);
            if (player != null) {
                user.getActiveItem().setCount(Int + user.getActiveItem().getCount());
            }
        }
    }
}
