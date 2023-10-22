package com.KafuuChino0722.coreextensions.command;

import com.KafuuChino0722.coreextensions.player.FakeConnection;
import com.KafuuChino0722.coreextensions.player.FakePlayer;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Uuids;
import net.minecraft.world.World;

import java.util.UUID;

public class CommandFakePlayer {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {

        serverCommandSourceCommandDispatcher.register(CommandManager.literal("fakeplayer")
                .then(CommandManager.literal("spawn")
                        .then(CommandManager.argument("name", StringArgumentType.word())
                                .executes(CommandFakePlayer::run)
                        )));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        /*String name = StringArgumentType.getString(context, "name");
        UUID uuid = Uuids.getOfflinePlayerUuid(name);
        FakePlayer fakePlayer = FakePlayer.create(
                context.getSource().getServer(),
                new GameProfile(uuid, name),
                true
        );
        FakeConnection fakeConnection = new FakeConnection();
        fakeConnection.register(context.getSource().getServer().getNetworkIo());
        context.getSource().getServer().getPlayerManager().onPlayerConnect(
                fakeConnection,
                fakePlayer
        );*/
        return 1;
    }
}