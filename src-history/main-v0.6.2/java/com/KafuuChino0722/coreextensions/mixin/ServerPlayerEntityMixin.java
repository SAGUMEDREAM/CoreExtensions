package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.registry.events.eventsItem.EventHoldItemRegistry;
import com.KafuuChino0722.coreextensions.core.registry.events.eventsWorld.EventOnDeath;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "worldChanged", at = @At("HEAD"))
    private void worldChanged(CallbackInfo ci) {

    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        World world = player.getWorld();
        Hand hand = player.getActiveHand();
        ItemStack itemStack = player.getStackInHand(hand);
        String namespace = Registries.ITEM.getId(itemStack.getItem()).getNamespace();
        String id = Registries.ITEM.getId(itemStack.getItem()).getPath();
        EventOnDeath.register(namespace,id,world,player,hand);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void tick(CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        World world = player.getWorld();
        Hand hand = player.getActiveHand();
        ItemStack itemStack = player.getStackInHand(hand);
        String namespace = Registries.ITEM.getId(itemStack.getItem()).getNamespace();
        String id = Registries.ITEM.getId(itemStack.getItem()).getPath();
        EventHoldItemRegistry.register(namespace,id,world,player,hand);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    private void onDeath(DamageSource damageSource, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        ItemStack heldItemStack = player.getMainHandStack();

    }
}
