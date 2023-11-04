package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandHat {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {

        serverCommandSourceCommandDispatcher.register(CommandManager.literal("hat")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(ctx -> {
                ServerPlayerEntity player = ctx.getSource().getPlayer();
                ItemStack handItem = player.getMainHandStack();
                int handItemCount = handItem.getCount();
                ItemStack hatItem = player.getEquippedStack(EquipmentSlot.HEAD).copy();
                int hatItemCount = hatItem.getCount();
                //logError(String.valueOf(hatItem.getEnchantments()));
                        if (handItem.isEmpty()) { // If there's no item in hands
                            if (!hatItem.isEmpty()) {
                                player.equipStack(EquipmentSlot.HEAD, handItem);
                                player.setStackInHand(Hand.MAIN_HAND, hatItem);
                            } else {
                                ctx.getSource().sendError(Text.translatable("commands.hat.noitem"));
                                return -1;
                            }
                        } else if (hatItem.isEmpty()) {    // If player doesn't have hat item
                            ItemStack oneHandItem = handItem.copy();
                            oneHandItem.setCount(1);
                            player.equipStack(EquipmentSlot.HEAD, oneHandItem);
                            handItem.setCount(handItem.getCount() - 1);
                        } else {
                            PlayerInventory inventory = player.getInventory();
                            int slot = inventory.getOccupiedSlotWithRoomForStack(hatItem);

                            player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                            if(handItem.getCount() == 1) {
                                player.equipStack(EquipmentSlot.HEAD, handItem);
                                player.setStackInHand(Hand.MAIN_HAND, hatItem);
                            } else if(slot != -1) {
                                ItemStack invItem = inventory.getStack(slot);
                                invItem.setCount(invItem.getCount() + hatItemCount);
                            } else if(inventory.getEmptySlot() != -1) {
                                inventory.setStack(inventory.getEmptySlot(), hatItem);
                            } else {
                                player.dropStack(hatItem, 1);
                                ctx.getSource().sendError(Text.translatable("commands.hat.droppeditem"));
                            }
                            player.sendMessage(Text.translatable("commands.hat.done"));
                        }
                        return 1;
                        }
        ).build().createBuilder());
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":hat")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(ctx -> {
                                ServerPlayerEntity player = ctx.getSource().getPlayer();
                                ItemStack handItem = player.getMainHandStack();
                                int handItemCount = handItem.getCount();
                                ItemStack hatItem = player.getEquippedStack(EquipmentSlot.HEAD).copy();
                                int hatItemCount = hatItem.getCount();
                                //logError(String.valueOf(hatItem.getEnchantments()));
                                if (handItem.isEmpty()) { // If there's no item in hands
                                    if (!hatItem.isEmpty()) {
                                        player.equipStack(EquipmentSlot.HEAD, handItem);
                                        player.setStackInHand(Hand.MAIN_HAND, hatItem);
                                    } else {
                                        ctx.getSource().sendError(Text.translatable("commands.hat.noitem"));
                                        return -1;
                                    }
                                } else if (hatItem.isEmpty()) {    // If player doesn't have hat item
                                    ItemStack oneHandItem = handItem.copy();
                                    oneHandItem.setCount(1);
                                    player.equipStack(EquipmentSlot.HEAD, oneHandItem);
                                    handItem.setCount(handItem.getCount() - 1);
                                } else {
                                    PlayerInventory inventory = player.getInventory();
                                    int slot = inventory.getOccupiedSlotWithRoomForStack(hatItem);

                                    player.equipStack(EquipmentSlot.HEAD, ItemStack.EMPTY);
                                    if(handItem.getCount() == 1) {
                                        player.equipStack(EquipmentSlot.HEAD, handItem);
                                        player.setStackInHand(Hand.MAIN_HAND, hatItem);
                                    } else if(slot != -1) {
                                        ItemStack invItem = inventory.getStack(slot);
                                        invItem.setCount(invItem.getCount() + hatItemCount);
                                    } else if(inventory.getEmptySlot() != -1) {
                                        inventory.setStack(inventory.getEmptySlot(), hatItem);
                                    } else {
                                        player.dropStack(hatItem, 1);
                                        ctx.getSource().sendError(Text.translatable("commands.hat.droppeditem"));
                                    }
                                    player.sendMessage(Text.translatable("commands.hat.done"));
                                }
                                return 1;
                            }
                    ).build().createBuilder());
        }

    }
}
