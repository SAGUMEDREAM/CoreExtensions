package com.KafuuChino0722.coreextensions.events;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.server.world.ServerWorld;

public interface WorldLoadEvent {
    Event<WorldLoadEvent> WORLD_LOAD = EventFactory.createArrayBacked(WorldLoadEvent.class,
            (listeners) -> (world) -> {
                for (WorldLoadEvent event : listeners) {
                    event.onWorldLoad(world);
                }
            });

    void onWorldLoad(ServerWorld world);
}
