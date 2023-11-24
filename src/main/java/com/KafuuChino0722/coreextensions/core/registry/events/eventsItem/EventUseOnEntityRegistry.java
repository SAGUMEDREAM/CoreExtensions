package com.KafuuChino0722.coreextensions.core.registry.events.eventsItem;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry.events.EventInterface;
import com.KafuuChino0722.coreextensions.core.registry.events.Events;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.special.ActionDamage;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.ContainsItem;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.ItemEnchanted;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.RayCastEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class EventUseOnEntityRegistry implements EventInterface {
    public static void register(String namespace, String id, PlayerEntity user, LivingEntity entity, Hand hand) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read(Events.EVENT);
        load(namespace, id, itemsData, user, entity, hand);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip(Events.EVENT);
        load(namespace, id, itemsDataZ, user, entity, hand);
    }

    public static boolean fail = false;

    public static void load(String namespace, String id, Map<String, Map<String, Object>> eventsData, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (eventsData != null && eventsData.containsKey("item_events")) {
            Map<String, Object> events = eventsData.get("item_events");
            
            for (Map.Entry<String, Object> entry : events.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> eventData = (Map<String, Object>) entry.getValue();
                    String name = (String) eventData.getOrDefault("name", (String) entry.getKey());
                    String ItemIdentifier = namespace + ":" + id;

                    World world = user.getEntityWorld();
                    
                    List<String> applyKey = (List<String>) eventData.getOrDefault("apply",null);
                    if (applyKey != null) {
                        for (String apply : applyKey) {
                            if (apply.equalsIgnoreCase(ItemIdentifier)) {
                                Map<String, Object> actionsKey = (Map<String, Object>) eventData.getOrDefault("actions", null);
                                if (actionsKey != null) {
                                    Map<String, Object> Key = (Map<String, Object>) actionsKey.getOrDefault("useOnEntity", null);
                                    if (Key != null) {
                                        Map<String, Object> condition = (Map<String, Object>) Key.getOrDefault("condition",null);
                                        fail = false;
                                        if(condition!=null) {

                                            Map<String,Object> entitiesKey = (Map<String, Object>) condition.getOrDefault("entities",null);

                                            List<String> entities = null;
                                            List<String> entityNames = null;
                                            if(entitiesKey!=null) entityNames = (List<String>) entitiesKey.getOrDefault("name", null);
                                            if(entitiesKey!=null) entities = (List<String>) entitiesKey.getOrDefault("list", null);

                                            List<String> contains = (List<String>) condition.getOrDefault("contains", null);
                                            List<String> enchants = (List<String>) condition.getOrDefault("enchants", null);

                                            if(entities!=null) {
                                                fail = RayCastEntity.get(user,entity,entities);
                                                if(fail) return;
                                            }

                                            if(entityNames!=null) {
                                                fail = RayCastEntity.CustomName.get(user,entity,entities);
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

                                            if(!fail) {
                                                EventUseRegistry.isRight = true;
                                                user.swingHand(hand);
                                                ActionDamage.run(user,entity,hand,Key);
                                                Events.runActionItem(world, user, hand, Key);
                                            }
                                        } else {
                                            EventUseRegistry.isRight = true;
                                            user.swingHand(hand);
                                            ActionDamage.run(user,entity,hand,Key);
                                            Events.runActionItem(world, user, hand, Key);
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
