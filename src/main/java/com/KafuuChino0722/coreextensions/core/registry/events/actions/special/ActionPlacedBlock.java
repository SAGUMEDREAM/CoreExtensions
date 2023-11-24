package com.KafuuChino0722.coreextensions.core.registry.events.actions.special;

import com.KafuuChino0722.coreextensions.core.registry.events.actions.ActionInterface;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Map;

public class ActionPlacedBlock implements ActionInterface {

    public static void run(ItemUsageContext context, Map<String, Object> Key) {
        Map<String, Object> placeKey = (Map<String, Object>) Key.getOrDefault("placed_block", null);
        PlayerEntity user = context.getPlayer();

        if(placeKey!=null) {
            String I = (String) placeKey.getOrDefault("block","minecraft:stone");
            ItemStack itemStack = user.getStackInHand(user.getActiveHand());
            int a = (int) placeKey.getOrDefault("move_x",0);
            int b = (int) placeKey.getOrDefault("move_y",0);
            int c = (int) placeKey.getOrDefault("move_z",0);

            if (I != null) {
                if(Registries.BLOCK.containsId(new Identifier(I))) {
                    Block block = Registries.BLOCK.get(new Identifier(I));
                    World world = context.getWorld();
                    ItemPlacementContext itemPlacementContext = new ItemPlacementContext(context);
                    BlockState blockState = block.getPlacementState(itemPlacementContext);
                    BlockPos blockPos = itemPlacementContext.getBlockPos();
                    a = blockPos.getX() + a;
                    b = blockPos.getY() + b;
                    c = blockPos.getZ() + c;
                    blockPos = new BlockPos(a,b,c);
                    itemPlacementContext.getWorld().setBlockState(context.getBlockPos(), blockState, Block.NOTIFY_ALL | Block.REDRAW_ON_MAIN_THREAD);
                    world.updateNeighbors(blockPos,block);
                }
            }
        }
    }
}
