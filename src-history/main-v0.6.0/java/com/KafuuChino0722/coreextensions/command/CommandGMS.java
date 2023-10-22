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
import net.minecraft.world.GameMode;

import java.util.Collection;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandGMS {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("gms")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandGMS::run)
                .then(CommandManager.argument("targets", EntityArgumentType.entities())
                        .executes(context -> execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets"))))
        );
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":gms")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(CommandGMS::run)
                    .then(CommandManager.argument("targets", EntityArgumentType.entities())
                            .executes(context -> execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets")))));
        }
    }

    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets) {
        for (ServerPlayerEntity serverPlayerEntity : targets) {
            serverPlayerEntity.sendMessage(Text.translatable("commands.gm.0"), false);
            GameMode newGameMode = GameMode.SURVIVAL;
            serverPlayerEntity.changeGameMode(newGameMode);
        }
        return targets.size();
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerWorld world = source.getWorld();
        ServerPlayerEntity player =context.getSource().getPlayer();
        player.sendMessage(Text.translatable("commands.gm.0"), false);
        GameMode newGameMode = GameMode.SURVIVAL;
        player.changeGameMode(newGameMode);
        return 1;
    }
}
