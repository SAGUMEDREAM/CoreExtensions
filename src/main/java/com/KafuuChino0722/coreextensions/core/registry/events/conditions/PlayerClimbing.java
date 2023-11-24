package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

public class PlayerClimbing implements ConditionsInterface {

    public static boolean get(PlayerEntity user, Map<String, Object> Key){

        if(Key.containsKey("isClimbing")) {
            boolean a = (boolean) Key.getOrDefault("isClimbing",false);
            if(a) {
                if (user.isClimbing()) {
                    return false;
                } else return true;
            } else return false;
        }
        return false;
    }

}
