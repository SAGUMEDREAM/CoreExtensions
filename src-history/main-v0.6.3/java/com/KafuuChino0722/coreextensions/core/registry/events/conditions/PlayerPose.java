package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

import java.util.Map;

public class PlayerPose implements ConditionsInterface{

    public static boolean get(PlayerEntity user, Map<String, Object> Key) {
        if(Key.containsKey("EntityPose")) {
            Map<String, Object> EPKey = (Map<String, Object>) Key.getOrDefault("EntityPose", null);
            boolean zone = false;
            if ((EPKey.containsKey("dX") || EPKey.containsKey("dY") || EPKey.containsKey("dZ"))) {
                zone = true;
                Vec3d playerPos = user.getPos();
                int x = (int) EPKey.getOrDefault("X", 1);
                int y = (int) EPKey.getOrDefault("Y", 1);
                int z = (int) EPKey.getOrDefault("Z", 1);
                int dx = (int) EPKey.getOrDefault("dX", 1);
                int dy = (int) EPKey.getOrDefault("dY", 1);
                int dz = (int) EPKey.getOrDefault("dZ", 1);
                if ((playerPos.getX() >= Math.min(x, dx) && playerPos.getX() <= Math.max(x, dx)) &&
                        (playerPos.getY() >= Math.min(y, dy) && playerPos.getY() <= Math.max(y, dy)) &&
                        (playerPos.getZ() >= Math.min(z, dz) && playerPos.getZ() <= Math.max(z, dz))) {
                    return false;
                } else {
                    return true;
                }

            } else {
                zone = false;
                if (EPKey.containsKey("X") && EPKey.containsKey("Y") && EPKey.containsKey("Z")) {
                    int a1 = (int) user.getX();
                    int b1 = (int) EPKey.getOrDefault("X", 20);
                    int a2 = (int) user.getY();
                    int b2 = (int) EPKey.getOrDefault("Y", 20);
                    int a3 = (int) user.getZ();
                    int b3 = (int) EPKey.getOrDefault("Z", 20);
                    if(a1 == b1 && a2 == b2 && a3 == b3) {
                    } else {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
