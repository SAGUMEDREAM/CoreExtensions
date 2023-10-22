package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandNight {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("night")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandNight::run));
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":night")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(CommandNight::run));
        }
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = context.getSource().getPlayer();
        for (ServerWorld serverWorld : source.getServer().getWorlds()) {
            serverWorld.setTimeOfDay(18000);
        }
        player.sendMessage(Text.translatable("commands.night.done"), false);
        return 1;
    }
}
