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

public class CommandFly  {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("fly")
                .requires(source -> source.hasPermissionLevel(CommandPermissions.VISITOR))
                .executes(CommandFly::run)
                .then(CommandManager.argument("targets", EntityArgumentType.entities())
                        .executes(context -> CommandFly.execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets"))))
        );
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":fly")
                    .requires(source -> source.hasPermissionLevel(CommandPermissions.VISITOR))
                    .executes(CommandFly::run)
                    .then(CommandManager.argument("targets", EntityArgumentType.entities())
                            .executes(context -> CommandFly.execute((ServerCommandSource)context.getSource(), EntityArgumentType.getPlayers(context, "targets")))));
        }
    }

    public static int execute(ServerCommandSource source, Collection<ServerPlayerEntity> targets) {
        for (ServerPlayerEntity serverPlayerEntity : targets) {
            boolean isFlying = serverPlayerEntity.getAbilities().allowFlying;
            serverPlayerEntity.getAbilities().allowFlying = !isFlying;
            serverPlayerEntity.sendAbilitiesUpdate();
            ServerPlayerEntity player = serverPlayerEntity;
            player.sendMessage(Text.translatable("commands.fly.done"), false);
            String message = isFlying ? "Disabled flight mode." : "Enabled flight mode.";
        }
        return targets.size();
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        boolean isFlying = source.getPlayer().getAbilities().allowFlying;
        source.getPlayer().getAbilities().allowFlying = !isFlying;
        source.getPlayer().sendAbilitiesUpdate();
        ServerPlayerEntity player = context.getSource().getPlayer();
        player.sendMessage(Text.translatable("commands.fly.done"), false);
        String message = isFlying ? "Disabled flight mode." : "Enabled flight mode.";
        return 1;
    }


}