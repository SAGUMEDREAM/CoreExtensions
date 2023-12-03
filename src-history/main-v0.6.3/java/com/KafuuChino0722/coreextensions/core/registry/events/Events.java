package com.KafuuChino0722.coreextensions.core.registry.events;

import com.KafuuChino0722.coreextensions.core.registry.events.actions.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class Events {
    public static final String EVENT = "event.yml";


    //You can inject Mixin there to add your custom event
    public static void Inject(World world, @Nullable PlayerEntity user, Hand hand, Map<String, Object> Key) {
    }

    public static void runActionItem(World world, @Nullable PlayerEntity user, Hand hand, Map<String, Object> Key) {
        if(user!=null && user.isPlayer()) {
            ActionAddEffect.run(world,user,hand,Key);
            ActionRemoveEffect.run(world,user,hand,Key);
            ActionSetHealth.run(world, user, hand, Key);
            ActionAddHealth.run(world, user, hand, Key);
            ActionRemoveHealth.run(world, user, hand, Key);
            ActionSetFeed.run(world, user, hand, Key);
            ActionAddFeed.run(world, user, hand, Key);
            ActionRemoveFeed.run(world, user, hand, Key);
            ActionSetAir.run(world, user, hand, Key);
            ActionShotArrow.run(world, user, hand, Key);
            ActionDropItem.run(world, user, hand, Key);
            ActionShotItem.run(world, user, hand, Key);
            ActionSetFireTick.run(world, user, hand, Key);
            ActionDamageItem.run(world, user, hand, Key);
            ActionGiveItem.run(world, user, hand, Key);
            ActionClearInventory.run(world, user, hand, Key);
            ActionReplaceItem.run(world, user, hand, Key);
            ActionSetItemCount.run(world, user, hand, Key);
            ActionAddUpItemCount.run(world, user, hand, Key);
            ActionPlaySound.run(world, user, hand, Key);
            ActionRemoveItem.run(world, user, hand, Key);
            ActionSetSpawnPoint.run(world, user, hand, Key);
            ActionTeleport.run(world, user, hand, Key);
            ActionRunCommands.run(world, user, hand, Key);
            Inject(world, user, hand, Key);
        }
    }
}
