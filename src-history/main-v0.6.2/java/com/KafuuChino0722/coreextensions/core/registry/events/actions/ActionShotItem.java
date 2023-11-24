package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

public class ActionShotItem implements ActionInterface {

    public static int tempDamage = 0;

    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> shotKey = (Map<String, Object>) Key.getOrDefault("shot", null);
        if(shotKey!=null) {
            String shotType = (String) shotKey.getOrDefault("type",null);
            if(shotType!=null) {
                if(shotType.equalsIgnoreCase("item")||shotType.equalsIgnoreCase("minecraft:item")) {
                    PlayerEntity player = user.getCommandSource().getPlayer();
                    String identifier = (String) shotKey.getOrDefault("identifier",null);
                    double damage = (double) shotKey.getOrDefault("damage",0.0);
                    double speed = (double) shotKey.getOrDefault("speed",1.5);
                    if (player != null) {
                        Item entityIdentifier = Registries.ITEM.get(new Identifier(identifier));
                        ItemStack itemStack = new ItemStack(entityIdentifier);
                        SnowballEntity Entity = new SnowballEntity(world, user);
                        Entity.damage(user.getDamageSources().thrown(Entity, user.getAttacker()),(float) damage);
                        Entity.setItem(itemStack);
                        Entity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0f, (float) speed, 1.0f);
                        world.spawnEntity(Entity);
                        tempDamage = (int) damage;
                    }
                }
            }
        }
    }
}
