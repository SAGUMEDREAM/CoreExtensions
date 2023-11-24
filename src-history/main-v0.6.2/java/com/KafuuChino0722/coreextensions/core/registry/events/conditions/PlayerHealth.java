package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import com.KafuuChino0722.coreextensions.core.api.MethodMath;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

public class PlayerHealth implements ConditionsInterface{

    public static boolean get(PlayerEntity user, Map<String, Object> Key){
        if(Key.containsKey("health")) {
            int a = (int) user.getHealth();
            String b = (String) Key.getOrDefault("health",null);

            if(b!=null) {
                return MethodMath.getMathInt(b, a);
            }
        }
        return false;
    }
}
