package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.registry.events.eventsItem.*;
import net.fabricmc.fabric.api.item.v1.FabricItemStack;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin
        implements FabricItemStack {

    @Shadow public abstract TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand);
    @Shadow public abstract ItemStack finishUsing(World world, LivingEntity user);
    @Shadow public abstract ActionResult useOnBlock(ItemUsageContext context);
    @Shadow public abstract void onStoppedUsing(World world, LivingEntity user, int remainingUseTicks);

    @Inject(method = "onCraft", at = @At("HEAD"))
    public void onCraft(World world, PlayerEntity player, int amount, CallbackInfo ci) {
        Hand hand = player.getActiveHand();
        ItemStack itemStack = player.getStackInHand(hand);
        Item handItem = itemStack.getItem();
        String namespace = Registries.ITEM.getId(handItem).getNamespace();
        String id = Registries.ITEM.getId(handItem).getPath();
        try {
            EventOnCraftRegistry.register(namespace,id,world,player,hand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    public void onStoppedUsing(World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {
        Hand hand = user.getActiveHand();
        ItemStack itemStack = user.getStackInHand(hand);
        Item handItem = itemStack.getItem();
        String namespace = Registries.ITEM.getId(handItem).getNamespace();
        String id = Registries.ITEM.getId(handItem).getPath();
        try {
            EventOnStoppedUsingRegistry.register(namespace,id,world,user,remainingUseTicks);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Inject(method = "useOnBlock", at = @At("HEAD"))
    public void useOnBlock(ItemUsageContext context, CallbackInfoReturnable<ActionResult> cir) {
        PlayerEntity player = context.getPlayer();
        Hand hand = player.getActiveHand();
        ItemStack itemStack = player.getStackInHand(hand);
        Item handItem = itemStack.getItem();
        String namespace = Registries.ITEM.getId(handItem).getNamespace();
        String id = Registries.ITEM.getId(handItem).getPath();
        try {
            EventUseOnBlockRegistry.register(namespace,id,context);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Inject(method = "useOnEntity", at = @At("HEAD"))
    public void useOnEntity(PlayerEntity user, LivingEntity entity, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack itemStack = user.getStackInHand(hand);
        Item HandItem = itemStack.getItem();
        String namespace = Registries.ITEM.getId(HandItem).getNamespace();
        String id = Registries.ITEM.getId(HandItem).getPath();
        String ItemId = namespace + ":" + id;
        try {
            EventUseOnEntityRegistry.register(namespace,id,user,entity,hand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Inject(method = "finishUsing", at = @At("HEAD"))
    public void finishUsing(World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        try {
            Hand hand = user.getActiveHand();
            ItemStack itemStack = user.getStackInHand(hand);
            Item HandItem = itemStack.getItem();
            String namespace = Registries.ITEM.getId(HandItem).getNamespace();
            String id = Registries.ITEM.getId(HandItem).getPath();
            String ItemId = namespace + ":" + id;
            EventFinishUsingRegistry.register(namespace,id,world,user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Inject(method = "use", at = @At("HEAD"))
    public void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        try {
            ItemStack itemStack = user.getStackInHand(hand);
            Item HandItem = itemStack.getItem();
            String namespace = Registries.ITEM.getId(HandItem).getNamespace();
            String id = Registries.ITEM.getId(HandItem).getPath();
            String ItemId = namespace + ":" + id;
            EventUseRegistry.register(namespace, id, world, user, hand);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
