package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.level.ServerWorldProperties;

public class CommandThundering {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("thundering")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandThundering::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerWorldProperties properties = (ServerWorldProperties) context.getSource().getWorld().getLevelProperties();

        properties.setClearWeatherTime(0);
        properties.setRainTime(6000);
        properties.setRaining(true);
        properties.setThundering(true);
        player.sendMessage(Text.translatable("commands.thundering.done"), false);
        return 1;
    }
}
