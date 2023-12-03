package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.registry.events.eventsItem.EventPlayerBreakingBlockRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {

    @Inject(method = "processBlockBreakingAction", at = @At("HEAD"))
    public void processBlockBreakingAction (BlockPos pos, PlayerActionC2SPacket.Action action, Direction direction, int worldHeight, int sequence, CallbackInfo ci) {
        ServerPlayerEntity player = ((ServerPlayerInteractionManager)(Object)this).player;
        ServerWorld world = ((ServerPlayerInteractionManager)(Object)this).world;
        if (((ServerPlayerInteractionManager)(Object)this).player.networkHandler == null) return;
        ItemStack heldItemStack = player.getStackInHand(player.getActiveHand());
        Hand hand = player.getActiveHand();
        Item item = heldItemStack.getItem().asItem();
        if(item != Items.AIR) {
            String namespace = Registries.ITEM.getId(item).getNamespace();
            String id = Registries.ITEM.getId(item).getPath();
            EventPlayerBreakingBlockRegistry.register(namespace,id,world,player,hand);
        }
    }
}
