package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.*;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.Map;

public class ActionShotArrow implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> shotKey = (Map<String, Object>) Key.getOrDefault("shot", null);
        if(shotKey!=null) {
            String shotType = (String) shotKey.getOrDefault("type",null);
            if(shotType!=null) {
                if(shotType.equalsIgnoreCase("arrow")||shotType.equalsIgnoreCase("minecraft:arrow")) {
                    PlayerEntity player = user.getCommandSource().getPlayer();
                    double damage = (double) shotKey.getOrDefault("damage",0.0);
                    double speed = (double) shotKey.getOrDefault("speed",1.5);
                    int punch = (int) shotKey.getOrDefault("punch",0);
                    boolean flame = (boolean) shotKey.getOrDefault("isFire",false);
                    boolean canPickup = (boolean) shotKey.getOrDefault("canPickup",false);
                    if (player != null) {
                        ItemStack itemStack = new ItemStack(Items.ARROW);
                        ArrowItem arrowItem = new ArrowItem(new Item.Settings());
                        PersistentProjectileEntity persistentProjectileEntity = arrowItem.createArrow(world,itemStack,user);
                        persistentProjectileEntity.setVelocity((net.minecraft.entity.Entity) user, user.getPitch(), user.getYaw(), 0.0f, (float) (speed * 3.0f), 1.0f);
                        persistentProjectileEntity.setDamage(persistentProjectileEntity.getDamage() + (double) damage * 0.5 + 0.5);
                        persistentProjectileEntity.setPunch(punch);
                        if (flame) persistentProjectileEntity.setOnFireFor(100);
                        if (player.getAbilities().creativeMode && (itemStack.isOf(Items.SPECTRAL_ARROW) || itemStack.isOf(Items.TIPPED_ARROW))) {
                            if (!canPickup) {
                                persistentProjectileEntity.pickupType = PersistentProjectileEntity.PickupPermission.CREATIVE_ONLY;
                            }
                        }
                        world.spawnEntity(persistentProjectileEntity);
                    }
                }
            }
        }
    }
}
