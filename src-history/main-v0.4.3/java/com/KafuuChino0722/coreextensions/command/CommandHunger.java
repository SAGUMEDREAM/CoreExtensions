package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class CommandHunger {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("hunger")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandHunger::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        source.getPlayer().setHealth(source.getPlayer().getMaxHealth());
        ServerPlayerEntity player = context.getSource().getPlayer();
        player.getHungerManager().setFoodLevel(0);
        player.getHungerManager().setSaturationLevel(0f);

        player.sendMessage(Text.translatable("commands.hunger.done"), false);
        return 1;
    }
}