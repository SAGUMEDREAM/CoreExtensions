package com.KafuuChino0722.coreextensions.command;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.CoreManager;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.EnvType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import static com.KafuuChino0722.coreextensions.util.CommandManager.NAMESPACE;

public class CommandRegistry {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("registry")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandRegistry::run));
        for(String CommandLine: NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(CommandLine+":registry")
                    .requires(source -> source.hasPermissionLevel(2))
                    .executes(CommandRegistry::run));
        }
    }
    public static int run(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player =context.getSource().getPlayer();
        MinecraftServer server = player.getServer();
        if(Config.getConfigBoolean("ALLOW_RELOADING_REGISTRY")) {
            if(Reference.EnvType == EnvType.CLIENT) {
                try {
                    player.sendMessage(Text.translatable("commands.registry.done"), false);
                    CoreManager.bootstrap(false);
                    try {
                        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "coreextensions:reloaddp");
                    } catch (Exception e) {
                        player.sendMessage(Text.literal("ERROR?DATA PACKS").formatted(Formatting.RED), false);
                    }
                } catch (Exception e) {
                    player.sendMessage(Text.translatable("commands.registry.failed").formatted(Formatting.RED), false);
                }
            } else if(Reference.EnvType == EnvType.SERVER && Reference.isModLoaded("polymc")) {
                player.sendMessage(Text.translatable("commands.registry.server").formatted(Formatting.RED), false);

            } else if(Reference.EnvType == EnvType.SERVER) {
                player.sendMessage(Text.translatable("commands.registry.done"), false);
                CoreManager.bootstrap(false);
                try {
                    server.getCommandManager().executeWithPrefix(player.getCommandSource(), "coreextensions:reloaddp");
                } catch (Exception e) {
                    player.sendMessage(Text.literal("ERROR?DATA PACKS").formatted(Formatting.RED), false);
                }
            }
        } else {
            player.sendMessage(Text.translatable("commands.registry.off").formatted(Formatting.RED), false);
        }
        return 1;
    }
}
