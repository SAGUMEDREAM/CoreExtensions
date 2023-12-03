package com.KafuuChino0722.coreextensions.command;

import com.KafuuChino0722.coreextensions.player.FakeConnection;
import com.KafuuChino0722.coreextensions.player.FakePlayer;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.NetworkSide;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.test.TestContext;
import net.minecraft.text.Text;
import net.minecraft.util.Uuids;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;

import java.util.UUID;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandFakePlayer {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {

        serverCommandSourceCommandDispatcher.register(CommandManager.literal("fakeplayer")
                .then(CommandManager.literal("spawn")
                        .then(CommandManager.argument("name", StringArgumentType.string())
                                .executes(CommandFakePlayer::run)
                        )));
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":fakeplayer")
                    .then(CommandManager.literal("spawn")
                            .then(CommandManager.argument("name", StringArgumentType.string())
                                    .executes(CommandFakePlayer::run)
                            )));
        }
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        String player_name = StringArgumentType.getString(context, "name");
        //int isSpectator = IntegerArgumentType.getInteger(context, "isSpectator");
        //int isCreative = IntegerArgumentType.getInteger(context, "isCreative");

        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = context.getSource().getPlayer();

        ServerPlayerEntity serverPlayerEntity = new ServerPlayerEntity(source.getPlayer().getWorld().getServer(), (ServerWorld) source.getPlayer().getWorld(), new GameProfile(UUID.randomUUID(), player_name)) {

            @Override
            public boolean isSpectator() {
                return false;
            }

            @Override
            public boolean isCreative() {
                return false;
            }

            @Override
            public void onSpawn() {
                changeGameMode(GameMode.SURVIVAL);
                teleport((ServerWorld) player.getWorld(),player.getX(),player.getY(),player.getZ(),player.getYaw(),player.getPitch());
            }

            @Override
            public void onDeath(DamageSource damageSource) {
                networkHandler.disconnect(Text.of("Fake Player Was Dead"));
            }
        };

        source.getWorld().getServer().getPlayerManager().onPlayerConnect(new ClientConnection(NetworkSide.SERVERBOUND), serverPlayerEntity);
        return 1;
    }
}