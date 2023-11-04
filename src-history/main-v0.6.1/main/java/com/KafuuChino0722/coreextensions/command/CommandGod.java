package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Collection;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandGod {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("god")
                .requires(source -> source.hasPermissionLevel(CommandPermissions.VISITOR))
                .executes(CommandGod::run)
                .then(CommandManager.argument("targets", EntityArgumentType.entities())
                        .executes(context -> CommandGod.execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets"))))
        );
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":god")
                    .requires(source -> source.hasPermissionLevel(CommandPermissions.VISITOR))
                    .executes(CommandGod::run)
                    .then(CommandManager.argument("targets", EntityArgumentType.entities())
                            .executes(context -> CommandGod.execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets"))))
            );
        }
    }

    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets) {
        for (ServerPlayerEntity serverPlayerEntity : targets) {
            boolean isInvulnerable = serverPlayerEntity.isInvulnerable();
            serverPlayerEntity.setInvulnerable(!isInvulnerable);
            ServerPlayerEntity player = serverPlayerEntity;
            player.sendMessage(Text.translatable("commands.god.done"), false);
            String message = isInvulnerable ? "Disabled invulnerability." : "Enabled invulnerability.";
        }
        return targets.size();
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        boolean isInvulnerable = source.getPlayer().isInvulnerable();
        source.getPlayer().setInvulnerable(!isInvulnerable);
        ServerPlayerEntity player =context.getSource().getPlayer();
        player.sendMessage(Text.translatable("commands.god.done"), false);
        String message = isInvulnerable ? "Disabled invulnerability." : "Enabled invulnerability.";
        return 1;
    }


}