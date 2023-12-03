package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.HungerManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionAddFeed implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> HKey = (Map<String, Object>) Key.getOrDefault("addFeed", null);
        if(HKey!=null) {
            PlayerEntity player = user.getCommandSource().getPlayer();
            HungerManager hungerM = player.getHungerManager();
            if (HKey != null && !player.getAbilities().creativeMode) {
                int hunger = (int) HKey.getOrDefault("hunger",0);
                double saturationModifier = (double) HKey.getOrDefault("saturationModifier",0.0);
                hungerM.add((int) hunger,(float) saturationModifier);
            }
        }
    }
}
