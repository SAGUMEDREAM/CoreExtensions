package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;

public class RayCastEntity implements ConditionsInterface {

    public static boolean get(PlayerEntity user, LivingEntity entity, List<String> Key) {
        for (String entityStr : Key) {
            EntityType<?> targetEntity = Registries.ENTITY_TYPE.get(new Identifier(entityStr));
            EntityType<?> getEntity = entity.getType();
            if (targetEntity == getEntity) {
                return false;
            }
        }
        return true;
    }

    public static class CustomName {
        public static boolean get(PlayerEntity user, LivingEntity entity, List<String> Key) {
            for (String Str : Key) {
                String targetEntityName = entity.getCustomName().getString();
                if (targetEntityName == Str) {
                    return false;
                }
            }
            return true;
        }
    }
}
