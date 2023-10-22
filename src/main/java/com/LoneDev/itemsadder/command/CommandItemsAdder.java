package com.LoneDev.itemsadder.command;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.command.CommandCoreExtensions;
import com.KafuuChino0722.coreextensions.core.brrp.Export;
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

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipOutputStream;

import static com.LoneDev.itemsadder.Main.IaPacks;

public class CommandItemsAdder {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("coreextensions:iareload")
                .executes(CommandItemsAdder.command::IaRegistry)
        );
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("coreextensions:iazip")
                .executes(CommandItemsAdder.command::IaZip)
        );
    }
    public static class command {
        public static int IaZip(CommandContext<ServerCommandSource> context) {
            IaPacks.dump(Export.getIaPath());
            try {
                IaPacks.dump(new ZipOutputStream(new FileOutputStream(Export.getIaPath()+"/ItemsAdder+ResourcePack+DataPacks.zip")));
            } catch (IOException ignored) {
            }
            context.getSource().sendFeedback(() -> Text.translatable("commands.datagen.done"), false);
            context.getSource().sendFeedback(() -> Text.literal("File: itemsadder/datagen/ItemsAdder+ResourcePack+DataPacks.zip"), false);
            return 1;
        }
        public static int IaRegistry(CommandContext<ServerCommandSource> context) {
            ServerCommandSource source = context.getSource();
            ServerPlayerEntity player = context.getSource().getPlayer();
            MinecraftServer server = player.getServer();
            if(Config.getConfigBoolean("ALLOW_RELOADING_REGISTRY")) {
                if(Reference.EnvType == EnvType.CLIENT) {
                    try {
                        com.LoneDev.itemsadder.Main.load();
                        context.getSource().sendFeedback(() -> Text.translatable("commands.registry.done"), false);
                        try {
                            server.getCommandManager().executeWithPrefix(player.getCommandSource(), "coreextensions:reloaddp");
                        } catch (Exception e) {
                            context.getSource().sendFeedback(() -> Text.literal("ERROR?DATA PACKS").formatted(Formatting.RED), false);
                        }
                    } catch (Exception e) {
                        context.getSource().sendFeedback(() -> Text.translatable("commands.registry.failed").formatted(Formatting.RED), false);
                    }
                } else if(Reference.EnvType == EnvType.SERVER && Reference.isModLoaded("polymc")) {
                    context.getSource().sendFeedback(() -> Text.translatable("commands.registry.server").formatted(Formatting.RED), false);

                } else if(Reference.EnvType == EnvType.SERVER) {
                    context.getSource().sendFeedback(() -> Text.translatable("commands.registry.done"), false);
                    com.LoneDev.itemsadder.Main.load();
                    try {
                        server.getCommandManager().executeWithPrefix(player.getCommandSource(), "coreextensions:reloaddp");
                    } catch (Exception e) {
                        context.getSource().sendFeedback(() -> Text.literal("ERROR?DATA PACKS").formatted(Formatting.RED), false);
                    }
                }
            } else {
                player.sendMessage(Text.translatable("commands.registry.off").formatted(Formatting.RED), false);
            }
            return 1;
        }
    }
}
