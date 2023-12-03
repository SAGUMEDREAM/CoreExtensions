package com.KafuuChino0722.coreextensions.core.registry.events.eventsItem;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry.events.EventInterface;
import com.KafuuChino0722.coreextensions.core.registry.events.Events;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.special.ActionBreakBlock;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.special.ActionPlacedBlock;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.special.ActionSummonEntity;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.ContainsItem;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.ItemEnchanted;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.RayCastBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class EventUseOnBlockRegistry implements EventInterface {
    public static void register(String namespace, String id, ItemUsageContext context) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read(Events.EVENT);
        load(namespace, id, itemsData, context);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip(Events.EVENT);
        load(namespace, id, itemsDataZ, context);
    }

    public static boolean fail = false;

    public static void load(String namespace, String id, Map<String, Map<String, Object>> eventsData, ItemUsageContext context) {
        if (eventsData != null && eventsData.containsKey("item_events")) {
            Map<String, Object> events = eventsData.get("item_events");

            for (Map.Entry<String, Object> entry : events.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> eventData = (Map<String, Object>) entry.getValue();
                    String name = (String) eventData.getOrDefault("name", (String) entry.getKey());
                    String ItemIdentifier = namespace + ":" + id;

                    BlockPos blockPos;
                    World world = context.getWorld();
                    BlockState blockState = world.getBlockState(blockPos = context.getBlockPos());

                    LivingEntity user = context.getPlayer();
                    Hand hand = context.getPlayer().getActiveHand();

                    List<String> applyKey = (List<String>) eventData.getOrDefault("apply",null);
                    if (applyKey != null) {
                        for (String apply : applyKey) {
                            if (apply.equalsIgnoreCase(ItemIdentifier)) {
                                Map<String, Object> actionsKey = (Map<String, Object>) eventData.getOrDefault("actions", null);
                                if (actionsKey != null) {
                                    Map<String, Object> Key = (Map<String, Object>) actionsKey.getOrDefault("useOnBlock", null);
                                    if (Key != null) {
                                        Map<String, Object> condition = (Map<String, Object>) Key.getOrDefault("condition",null);

                                        fail = false;
                                        if(condition!=null) {

                                            List<String> blocks = (List<String>) condition.getOrDefault("blocks", null);
                                            List<String> contains = (List<String>) condition.getOrDefault("contains", null);
                                            List<String> enchants = (List<String>) condition.getOrDefault("enchants", null);

                                            if(blocks!=null) {
                                                fail = RayCastBlock.get(blockState,blocks);
                                                if(fail) return;
                                            }
                                            if(contains!=null) {
                                                fail = ContainsItem.get(user.getCommandSource().getPlayer(),contains);
                                                if(fail) return;
                                            }

                                            if(enchants!=null) {
                                                fail = ItemEnchanted.get(world,user,hand,enchants);
                                                if(fail) return;
                                            }

                                            if(!fail) {
                                                EventUseRegistry.isRight = true;
                                                user.swingHand(hand);
                                                ActionBreakBlock.run(context, Key);
                                                ActionPlacedBlock.run(context, Key);
                                                ActionSummonEntity.run(context, Key);

                                                Events.runActionItem(world, user.getCommandSource().getPlayer(), hand, Key);
                                            }
                                        } else {
                                            EventUseRegistry.isRight = true;
                                            user.swingHand(hand);
                                            ActionBreakBlock.run(context, Key);
                                            ActionPlacedBlock.run(context, Key);
                                            ActionSummonEntity.run(context, Key);

                                            Events.runActionItem(world, user.getCommandSource().getPlayer(), hand, Key);
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
