package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.entity.player.PlayerEntity;

import java.util.List;
import java.util.Map;

public interface ConditionsInterface {

    static boolean get(PlayerEntity user, Map<String, Object> Key) {
        return false;
    }

    static boolean get(PlayerEntity user, List<String> Key) {
        return false;
    }

}
