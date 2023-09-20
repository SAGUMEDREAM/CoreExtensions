package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

import java.util.function.Supplier;

public class CommandGMA {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("gma")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandGMA::run));
    }
    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerWorld world = source.getWorld();
        ServerPlayerEntity player =context.getSource().getPlayer();
        player.sendMessage(Text.translatable("commands.gm.2"), false);
        GameMode newGameMode = GameMode.SURVIVAL;
        player.changeGameMode(newGameMode);
        return 1;
    }
}
