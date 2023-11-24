package com.KafuuChino0722.coreextensions.core.registry.events.eventsWorld;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry.events.Events;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.Conditions;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.ContainsItem;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.ItemEnchanted;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading.server;

public class EventTickRegistry {
    public static void register() {
        ServerTickEvents.START_WORLD_TICK.register(server->{
            Map<String, Map<String, Object>> wData = IOFileManager.read(Events.EVENT);
            load(wData);
            Map<String, Map<String, Object>> wDataZ = IOFileManager.readZip(Events.EVENT);
            load(wDataZ);
        });
    }

    public static boolean fail = false;


    public static void load(Map<String, Map<String, Object>> eventsData) {
        if (eventsData != null && eventsData.containsKey("world_events")) {
            Map<String, Object> events = eventsData.get("world_events");

            for (Map.Entry<String, Object> entry : events.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> eventData = (Map<String, Object>) entry.getValue();
                    String name = (String) eventData.getOrDefault("name", (String) entry.getKey());
                    Map<String, Object> actionsKey = (Map<String, Object>) eventData.getOrDefault("actions", null);
                    if (actionsKey != null) {
                        Map<String, Object> Key = (Map<String, Object>) actionsKey.getOrDefault("tick", null);
                        if (Key != null) {
                            try {
                                List<String> run_commandsKey = (List<String>) Key.getOrDefault("run_commands", null);

                                if (run_commandsKey != null) {
                                    for (String run_command : run_commandsKey) {
                                        if (server != null) {
                                            if (server.getProfiler() != null) {
                                                if (server != null && server.getCommandSource() != null && server.getCommandSource().getServer() != null) {
                                                    server.getCommandManager().executeWithPrefix(server.getCommandSource(), run_command);
                                                }
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                }
            }
        }
    }
}
