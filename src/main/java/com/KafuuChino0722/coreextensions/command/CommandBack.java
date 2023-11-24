package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;

import java.util.Collection;
import java.util.Optional;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandBack {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("back")
                .requires(source -> source.hasPermissionLevel(1))
                .executes(CommandBack::run)
                .then(CommandManager.argument("targets", EntityArgumentType.entities())
                        .executes(context -> execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets"))))
        );
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":back")
                    .requires(source -> source.hasPermissionLevel(1))
                    .executes(CommandBack::run)
                    .then(CommandManager.argument("targets", EntityArgumentType.entities())
                            .executes(context -> execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets"))))
            );
        }
    }

    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets) {
        for (ServerPlayerEntity serverPlayerEntity : targets) {
            ServerPlayerEntity player = source.getPlayer();
            Optional<GlobalPos> posL = player.getLastDeathPos();
            int x = posL.get().getPos().getX();
            int y = posL.get().getPos().getY();
            int z = posL.get().getPos().getZ();
            player.teleport(x,y,z);
            player.sendMessage(Text.translatable("commands.back.done"), false);
        }
        return targets.size();
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = source.getPlayer();
        Optional<GlobalPos> posL = player.getLastDeathPos();
        int x = posL.get().getPos().getX();
        int y = posL.get().getPos().getY();
        int z = posL.get().getPos().getZ();
        player.teleport(x,y,z);

        player.sendMessage(Text.translatable("commands.back.done"), false);
        return 1;
    }
}