package com.KafuuChino0722.coreextensions.core.registry.events.actions.special;

import com.KafuuChino0722.coreextensions.core.registry.events.actions.ActionInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class ActionSummonEntity implements ActionInterface {

    public static void run(ItemUsageContext context, Map<String, Object> Key) {
        Map<String, Object> placeKey = (Map<String, Object>) Key.getOrDefault("summon", null);
        PlayerEntity user = context.getPlayer();

        if(placeKey!=null) {
            String II = (String) placeKey.getOrDefault("entity","minecraft:pig");
            ItemStack itemStack = user.getStackInHand(user.getActiveHand());
            ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
            BlockPos blockPos = itemPlacementContext.getBlockPos();
            if (II != null) {
                if(Registries.ENTITY_TYPE.containsId(new Identifier(II))) {
                    EntityType<?> entityType = Registries.ENTITY_TYPE.get(new Identifier(II));
                    World world = context.getWorld();
                    entityType.spawn((ServerWorld) world,blockPos,SpawnReason.EVENT);
                    //world.spawnEntity();
                }
            }
        }
    }
}
