package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.registry.events.eventsItem.EventLeftUseRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.packet.c2s.play.HandSwingC2SPacket;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayNetworkHandler.class)
public final class ServerPlayNetworkHandlerMixin {
    @Shadow public ServerPlayerEntity player;

    @Unique
    private static int success = 0;

    @Inject(method = "onHandSwing", at = @At("HEAD"))
    public void onHandSwing(HandSwingC2SPacket packet, CallbackInfo ci) {
        success = success+1;
        if(success>=2) {
            PlayerEntity player = (PlayerEntity) (Object) this.player;
            World world = player.getWorld();
            Hand hand = player.getActiveHand();
            ItemStack itemStack = player.getStackInHand(hand);
            String namespace = Registries.ITEM.getId(itemStack.getItem()).getNamespace();
            String id = Registries.ITEM.getId(itemStack.getItem()).getPath();
            try {
                EventLeftUseRegistry.register(namespace,id,world,player,hand);
            } catch (Exception e) {
                e.printStackTrace();
            }
            success = 0;
        }
    }
}