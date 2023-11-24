package com.KafuuChino0722.coreextensions.core.registry.events.eventsItem;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry.events.Events;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class EventHoldItemRegistry {
    public static void register(String namespace, String id, World world, PlayerEntity user, Hand hand) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read(Events.EVENT);
        load(namespace, id, itemsData, world, user, hand);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip(Events.EVENT);
        load(namespace, id, itemsDataZ, world, user, hand);
    }

    public static boolean fail = false;

    public static int delay = 0;

    public static void load(String namespace, String id, Map<String, Map<String, Object>> eventsData, World world, PlayerEntity user, Hand hand) {
        if (eventsData != null && eventsData.containsKey("item_events")) {
            Map<String, Object> events = eventsData.get("item_events");

            for (Map.Entry<String, Object> entry : events.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> eventData = (Map<String, Object>) entry.getValue();
                    String name = (String) eventData.getOrDefault("name", (String) entry.getKey());
                    String ItemIdentifier = namespace + ":" + id;

                    List<String> applyKey = (List<String>) eventData.getOrDefault("apply",null);
                    if (applyKey != null) {
                        for (String apply : applyKey) {
                            if (apply.equalsIgnoreCase(ItemIdentifier)) {
                                Map<String, Object> actionsKey = (Map<String, Object>) eventData.getOrDefault("actions", null);
                                if (actionsKey != null) {
                                    Map<String, Object> Key = (Map<String, Object>) actionsKey.getOrDefault("hold", null);
                                    if (Key != null) {
                                        int delayMax = (int) Key.getOrDefault("delay", 1);
                                        Map<String, Object> condition = (Map<String, Object>) Key.getOrDefault("condition",null);
                                        delay = delay + 1;
                                        fail = false;
                                        if(condition!=null) {
                                            Map<String, Object> playerKey = (Map<String, Object>) condition.getOrDefault("player", null);
                                            List<String> contains = (List<String>) condition.getOrDefault("contains", null);
                                            List<String> enchants = (List<String>) condition.getOrDefault("enchants", null);

                                            if(playerKey!=null) {
                                                fail = Conditions.getCheckPlayer(user,playerKey);
                                                if(fail) return;
                                            }

                                            if(contains!=null) {
                                                fail = ContainsItem.get(user,contains);
                                                if(fail) return;
                                            }

                                            if(enchants!=null) {
                                                fail = ItemEnchanted.get(world,user,hand,enchants);
                                                if(fail) return;
                                            }

                                            if(!fail && delayMax >= delay) {
                                                Events.runActionItem(world, user, hand, Key);
                                                delay = 0;
                                            }
                                        } else {
                                            if(delayMax >= delay) {
                                                Events.runActionItem(world, user, hand, Key);
                                                delay = 0;
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
}
