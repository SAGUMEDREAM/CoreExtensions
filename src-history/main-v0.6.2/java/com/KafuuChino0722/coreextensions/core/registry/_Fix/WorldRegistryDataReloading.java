package com.KafuuChino0722.coreextensions.core.registry._Fix;

import com.KafuuChino0722.coreextensions.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipData;
import net.minecraft.command.argument.BlockStateArgument;
import net.minecraft.command.argument.BlockStateArgumentType;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.SetBlockCommand;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ChunkHolder;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class WorldRegistryDataReloading {

    public static MinecraftServer server;

    public static void run(Block newBlock, Block oldBlock) {
        int replaceRange = 8;

        List<ServerPlayerEntity> playerList = server.getPlayerManager().getPlayerList();
        for (ServerPlayerEntity player : playerList) {
            Vec3d playerPos = player.getPos();
            int x = (int) playerPos.getX() - replaceRange;
            int y = (int) playerPos.getY() - replaceRange;
            int z = (int) playerPos.getZ() - replaceRange;
            int dx = (int) playerPos.getX() + replaceRange;
            int dy = (int) playerPos.getY() + replaceRange;
            int dz = (int) playerPos.getZ() + replaceRange;

            String Namespace = Registries.BLOCK.getId(newBlock).getNamespace();
            String Id = Registries.BLOCK.getId(newBlock).getPath();

            String zero = " ";
            String rangeToCommand = x + zero + y + zero + z + zero + dx + zero + dy + zero + dz;
            String CommandFill = "execute at @a as @s run fill "+rangeToCommand+" "+Namespace+":"+Id+" replace "+Namespace+":"+Id;

            if(Config.getConfigBoolean("AUTO-REPLACE_RELOADING_REGISTRY_BLOCKS")) {
                if(player.hasPermissionLevel(2)) {
                    server.getCommandManager().executeWithPrefix(server.getCommandSource(), "gamerule-noshow commandModificationBlockLimit 8192");
                    server.getCommandManager().executeWithPrefix(server.getCommandSource(), CommandFill);
                }
            }

        }
    }

    public static void run(Item newItem, Item oldItem) {
        List<ServerPlayerEntity> playerEntityList = server.getPlayerManager().getPlayerList();
        for(ServerPlayerEntity player : playerEntityList) {
            Inventory playerInventory = player.getInventory();
            for (int i = 0; i < playerInventory.size(); i++) {
                ItemStack itemStack = playerInventory.getStack(i);
                if (itemStack.getItem() == oldItem) {
                    Text name = null;
                    Optional<TooltipData> tooltipData = null;
                    int Count = itemStack.getCount();
                    Integer Damage = itemStack.getDamage();
                    NbtCompound nbt = itemStack.getNbt();
                    if (itemStack.hasCustomName()) name = itemStack.getName();
                    if (itemStack.getTooltipData() != null) tooltipData = itemStack.getTooltipData();
                    itemStack = new ItemStack(newItem);
                    itemStack.setCount(Count);
                    if (Damage != null) itemStack.setDamage(Damage);
                    if (nbt != null && itemStack.hasNbt()) itemStack.setNbt(nbt);
                    if (name != null) itemStack.setCustomName(name);
                    if (tooltipData != null);
                    playerInventory.setStack(i,itemStack);
                }
            }
        }
    }

    protected static final BlockStateArgument AIR_BLOCK_ARGUMENT = new BlockStateArgument(Blocks.AIR.getDefaultState(), Collections.emptySet(), null);
}

