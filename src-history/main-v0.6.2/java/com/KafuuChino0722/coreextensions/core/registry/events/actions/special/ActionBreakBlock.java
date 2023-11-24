package com.KafuuChino0722.coreextensions.core.registry.events.actions.special;

import com.KafuuChino0722.coreextensions.core.registry.events.actions.ActionInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.World;

import java.util.Map;

public class ActionBreakBlock implements ActionInterface {
    public static void run(ItemUsageContext context, Map<String, Object> Key) {
        if (Key != null) {
            Map<String, Object> placeKey = (Map<String, Object>) Key.getOrDefault("block_break", null);
            if (placeKey != null) {
                PlayerEntity user = context.getPlayer();
                boolean enable = (boolean) placeKey.getOrDefault("enable", false);
                boolean drop = (boolean) placeKey.getOrDefault("drop", true);
                int a = (int) placeKey.getOrDefault("move_x", 0);
                int b = (int) placeKey.getOrDefault("move_y", 0);
                int c = (int) placeKey.getOrDefault("move_z", 0);

                if (placeKey != null) {
                    if (enable) {
                        Block block = Blocks.AIR;
                        World world = context.getWorld();
                        ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
                        BlockState blockState = block.getPlacementState(itemPlacementContext);
                        BlockPos blockPos = itemPlacementContext.getBlockPos();
                        a = blockPos.getX() + a;
                        b = blockPos.getY() + b;
                        c = blockPos.getZ() + c;
                        blockPos = new BlockPos(a, b, c);
                        itemPlacementContext.getWorld().setBlockState(context.getBlockPos(), blockState, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                        if (drop) {
                            world.breakBlock(blockPos, drop);
                        } else {
                            world.updateNeighbors(blockPos, block);
                        }
                    }
                }
            }
        }
    }
}
