package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandRepair {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("repair")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandRepair::run));
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":repair")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(CommandRepair::run));
        }
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ItemStack heldItem = source.getPlayer().getStackInHand(Hand.MAIN_HAND);
        if (!heldItem.isEmpty()) {
            ServerPlayerEntity player = context.getSource().getPlayer();
            NbtCompound tag = heldItem.getOrCreateNbt();
            tag.putInt("RepairCost", 0);
            heldItem.setNbt(tag);
            //source.sendFeedback(new LiteralText("Item repaired."), false);
            player.sendMessage(Text.translatable("commands.repair.done"), false);

            return 1;
        } else {
            ServerPlayerEntity player = context.getSource().getPlayer();
            //source.sendFeedback(new LiteralText("You must hold an item in your main hand to repair."), false);
            player.sendMessage(Text.translatable("commands.repair.fail"), false);
            return 0;
        }
    }
}