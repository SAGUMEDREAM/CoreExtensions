package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionClearInventory implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> K = (Map<String, Object>) Key.getOrDefault("clearInventory", null);
        if(K!=null) {
            boolean a = (boolean) K.getOrDefault("value",false);
            PlayerEntity player = user.getCommandSource().getPlayer();
            if (player != null && a) {
                player.getInventory().clear();
            }
        }
    }
}
