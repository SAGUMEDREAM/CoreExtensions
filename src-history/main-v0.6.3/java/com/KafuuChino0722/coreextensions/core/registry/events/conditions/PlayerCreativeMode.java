package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

public class PlayerCreativeMode implements ConditionsInterface {

    public static boolean get(PlayerEntity user, Map<String, Object> Key){

        if(Key.containsKey("creativeMode")) {
            boolean a = (boolean) Key.getOrDefault("creativeMode",false);
            if(a) {
                if (!user.isCreative()) {
                    return true;
                }
            } else return false;
        }
        return false;
    }

}
