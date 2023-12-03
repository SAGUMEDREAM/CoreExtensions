package com.KafuuChino0722.coreextensions.util;

import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.intprovider.IntProvider;

public class Weather {
    public static int processDuration(ServerCommandSource source, int duration, IntProvider provider) {
        if (duration == -1) {
            return provider.get(source.getWorld().getRandom());
        }
        return duration;
    }
}
