package com.KafuuChino0722.coreextensions.core.registry.events;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

public interface EventInterface {

    static boolean fail = false;

    static boolean getConditions() {
        return false;
    }

    static void register(String namespace, String id, PlayerEntity user, LivingEntity entity, Hand hand) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("event.yml");
        load(namespace, id, itemsData, user, entity, hand);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("event.yml");
        load(namespace, id, itemsDataZ, user, entity, hand);
    }

    static void load(String namespace, String id, Map<String, Map<String, Object>> eventsData, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (eventsData != null && eventsData.containsKey("events")) {
            Map<String, Object> events = eventsData.get("events");

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
                                    Map<String, Object> Key = (Map<String, Object>) actionsKey.getOrDefault("useOnEntity", null);
                                    if (Key != null) {
                                        Map<String, Object> condition = (Map<String, Object>) Key.getOrDefault("condition",null);
                                        if(condition!=null) {

                                        } else {

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
