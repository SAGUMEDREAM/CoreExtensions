package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;

import java.util.concurrent.TimeUnit;

public class CommandWait_600 {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("wait-600")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandWait_600::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {

        try {
            TimeUnit.SECONDS.sleep(600);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return 1;
    }
}
