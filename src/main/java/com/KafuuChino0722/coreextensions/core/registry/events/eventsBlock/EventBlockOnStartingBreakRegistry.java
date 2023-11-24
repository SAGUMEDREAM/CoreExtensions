package com.KafuuChino0722.coreextensions.core.registry.events.eventsBlock;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry.events.Events;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.special.ActionBreakBlock;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.special.ActionPlacedBlock;
import com.KafuuChino0722.coreextensions.core.registry.events.actions.special.ActionSummonEntity;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.Conditions;
import com.KafuuChino0722.coreextensions.core.registry.events.conditions.ContainsItem;
import com.KafuuChino0722.coreextensions.core.registry.events.eventsItem.EventUseRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class EventBlockOnStartingBreakRegistry {
    public static void register(String namespace, String id, BlockState state, World world, BlockPos pos, PlayerEntity player) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read(Events.EVENT);
        load(namespace, id, itemsData, state, world, pos, player);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip(Events.EVENT);
        load(namespace, id, itemsDataZ, state, world, pos, player);
    }

    public static boolean fail = false;

    public static void load(String namespace, String id, Map<String, Map<String, Object>> eventsData, BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (eventsData != null && eventsData.containsKey("block_events")) {
            Map<String, Object> events = eventsData.get("block_events");

            for (Map.Entry<String, Object> entry : events.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> eventData = (Map<String, Object>) entry.getValue();
                    String name = (String) eventData.getOrDefault("name", (String) entry.getKey());
                    String ItemIdentifier = namespace + ":" + id;

                    Hand hand = player.getActiveHand();
;
                    List<String> applyKey = (List<String>) eventData.getOrDefault("apply_block",null);
                    if (applyKey != null) {
                        for (String apply : applyKey) {
                            if (apply.equalsIgnoreCase(ItemIdentifier)) {
                                Map<String, Object> actionsKey = (Map<String, Object>) eventData.getOrDefault("actions", null);
                                if (actionsKey != null) {
                                    Map<String, Object> Key = (Map<String, Object>) actionsKey.getOrDefault("OnBlockStartingBreak", null);
                                    if (Key != null) {
                                        Map<String, Object> condition = (Map<String, Object>) Key.getOrDefault("condition",null);
                                        fail = false;
                                        if(condition!=null) {
                                            Map<String, Object> playerKey = (Map<String, Object>) condition.getOrDefault("player", null);
                                            Map<String, Object> blockKey = (Map<String, Object>) condition.getOrDefault("block", null);
                                            List<String> contains = (List<String>) condition.getOrDefault("contains", null);
                                            List<String> NbtKey = (List<String>) blockKey.getOrDefault("nbt", null);

                                            if(blockKey!=null) {
                                                fail = Conditions.getCheckBlock(state, world, pos, player, hand, blockKey);
                                                if(fail) return;
                                            }

                                            if(NbtKey!=null) {
                                                if(fail) return;
                                            }

                                            if(playerKey!=null) {
                                                fail = Conditions.getCheckPlayer(player,playerKey);
                                                if(fail) return;
                                            }

                                            if(contains!=null) {
                                                fail = ContainsItem.get(player,contains);
                                                if(fail) return;
                                            }

                                            if(!fail) {
                                                EventUseRegistry.isRight = true;
                                                player.swingHand(hand);

                                                Events.runActionItem(world, player, hand, Key);
                                            }
                                        } else {
                                            EventUseRegistry.isRight = true;
                                            player.swingHand(hand);

                                            Events.runActionItem(world, player, hand, Key);
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
