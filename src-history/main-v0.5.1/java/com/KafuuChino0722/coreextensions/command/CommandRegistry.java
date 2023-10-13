package com.KafuuChino0722.coreextensions.command;

import com.KafuuChino0722.coreextensions.CoreManager;
import com.KafuuChino0722.coreextensions.extensions.PolyReload;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.theepicblock.polymc.PolyMc;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class CommandRegistry {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("registry")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandRegistry::run));
    }
    public static int run(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player =context.getSource().getPlayer();
        MinecraftServer server = player.getServer();
        if(Reference.EnvType == EnvType.CLIENT) {
            try {
                player.sendMessage(Text.translatable("commands.registry.done"), false);
                CoreManager.load(false);
                try {
                    server.getCommandManager().executeWithPrefix(player.getCommandSource(), "coreextensions:reloaddp");
                } catch (Exception e) {
                    player.sendMessage(Text.literal("重新加载数据包失败").formatted(Formatting.DARK_RED), false);
                }
            } catch (Exception e) {
                player.sendMessage(Text.translatable("commands.registry.failed").formatted(Formatting.DARK_RED), false);
            }
        } else if(Reference.EnvType == EnvType.SERVER && Reference.isModLoaded("polymc")) {
            player.sendMessage(Text.translatable("commands.registry.server").formatted(Formatting.DARK_RED), false);

        } else if(Reference.EnvType == EnvType.SERVER) {
            player.sendMessage(Text.translatable("commands.registry.done"), false);
            CoreManager.load(false);
            try {
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), "coreextensions:reloaddp");
            } catch (Exception e) {
                player.sendMessage(Text.literal("重新加载数据包失败").formatted(Formatting.DARK_RED), false);
            }
        } else {

        }

        return 1;
    }
}
