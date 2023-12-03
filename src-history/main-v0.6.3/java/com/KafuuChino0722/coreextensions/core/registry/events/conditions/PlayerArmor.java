package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import com.KafuuChino0722.coreextensions.core.api.MethodMath;
import net.minecraft.entity.player.PlayerEntity;

import java.util.Map;

public class PlayerArmor implements ConditionsInterface {

    public static boolean get(PlayerEntity user, Map<String, Object> Key){
        if(Key.containsKey("armor")) {
            int a = (int) user.getArmor();
            String b = (String) Key.getOrDefault("armor",null);

            if(b!=null) {
                return MethodMath.getMathInt(b, a);
            }
        }
        return false;
    }
}
