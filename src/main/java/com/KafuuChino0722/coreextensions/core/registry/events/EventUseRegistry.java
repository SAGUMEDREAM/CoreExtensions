package com.KafuuChino0722.coreextensions.core.registry.events;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading.server;

public class EventUseRegistry {
    public static void register(String namespace, String id, World world, PlayerEntity user, Hand hand) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("event.yml");
        load(namespace, id, itemsData, world, user, hand);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("event.yml");
        load(namespace, id, itemsDataZ, world, user, hand);
    }

    public static void load(String namespace, String id, Map<String, Map<String, Object>> eventsData, World world, PlayerEntity user, Hand hand) {
        if (eventsData != null && eventsData.containsKey("events")) {
            Map<String, Object> events = eventsData.get("events");

            for (Map.Entry<String, Object> entry : events.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> eventData = (Map<String, Object>) entry.getValue();
                    String name = (String) eventData.getOrDefault("name", (String) entry.getKey());
                    String ItemIdentifier = namespace + ":" + id;

                    List<String> applyKey = (List<String>) eventData.getOrDefault("apply",null);
                    for (String apply : applyKey) {
                        if(apply.equalsIgnoreCase(ItemIdentifier)) {

                            Map<String, Object> actionsKey = (Map<String, Object>) eventData.getOrDefault("actions",null);
                            if(actionsKey == null) {
                                //左
                                Map<String, Object> leftKey = (Map<String, Object>) eventData.getOrDefault("left",null);

                                if(leftKey!=null) {

                                }

                                //右
                                Map<String, Object> rightKey = (Map<String, Object>) eventData.getOrDefault("right",null);
                                if(rightKey!=null) {
                                    List<String> run_commandsKey = (List<String>) rightKey.getOrDefault("run_commands",null);

                                    if(run_commandsKey!=null) {
                                        for (String run_command : run_commandsKey) {
                                            server.getCommandManager().executeWithPrefix(server.getCommandSource(), run_command);
                                        }
                                    }
                                }
                            }

                        }
                    }
                }

            }
        }
    }
}
