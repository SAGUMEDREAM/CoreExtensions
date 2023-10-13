package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

import java.util.UUID;

public class CommandFakePlayer {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("player")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandFakePlayer::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerWorld world = player.getServerWorld();

        // Create a fake player entity with random UUID
        PlayerEntity fakePlayer = EntityType.PLAYER.create(world);
        fakePlayer.setCustomName(player.getName());
        fakePlayer.setCustomNameVisible(true);
        world.spawnEntity(fakePlayer);

        player.sendMessage(Text.translatable("commands.player.done"), false);

        return 1;
    }
}