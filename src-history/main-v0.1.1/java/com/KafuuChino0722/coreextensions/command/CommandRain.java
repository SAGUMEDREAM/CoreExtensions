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

public class CommandRain {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("rain")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandRain::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerWorldProperties properties = (ServerWorldProperties) context.getSource().getWorld().getLevelProperties();

        properties.setClearWeatherTime(0);
        properties.setRainTime(10000);
        properties.setRaining(true);
        properties.setThundering(false);
        player.sendMessage(Text.translatable("commands.rain.done"), false);
        return 1;
    }
}
