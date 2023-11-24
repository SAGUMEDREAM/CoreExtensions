package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

public class PlayerSneaking implements ConditionsInterface {

    public static boolean get(PlayerEntity user, Map<String, Object> Key){

        if(Key.containsKey("isSneaking")) {
            boolean a = (boolean) Key.getOrDefault("isSneaking",false);
            if(a) {
                if (user.isSneaking()) {
                    return false;
                } else return true;
            } else return false;
        }
        return false;
    }

}
