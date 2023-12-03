package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.registry.events.eventsItem.EventAttackingRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "attack", at = @At("HEAD"))
    private void attack(Entity target, CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        World world = player.getWorld();
        Hand hand = player.getActiveHand();
        ItemStack itemStack = player.getStackInHand(hand);
        String namespace = Registries.ITEM.getId(itemStack.getItem()).getNamespace();
        String id = Registries.ITEM.getId(itemStack.getItem()).getPath();
        EventAttackingRegistry.register(namespace,id,world,player,hand);
    }
    @Inject(method = "interact", at = @At("HEAD"))
    private void onPlayerInteract(Entity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {

    }


}
