package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import com.KafuuChino0722.coreextensions.core.api.MethodMath;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

public class PlayerAir implements ConditionsInterface{

    public static boolean get(PlayerEntity user, Map<String, Object> Key){
        if(Key.containsKey("air")) {
            int a = (int) user.getAir();
            String b = (String) Key.getOrDefault("air",null);

            if(b!=null) {
                return MethodMath.getMathInt(b, a);
            }
        }
        return false;
    }
}
