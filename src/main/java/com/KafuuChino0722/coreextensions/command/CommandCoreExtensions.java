package com.KafuuChino0722.coreextensions.command;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.CoreManager;
import com.KafuuChino0722.coreextensions.network.VersionChecker;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.fabricmc.api.EnvType;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.ArgumentHelper;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.ClickEvent;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.util.Info.UPDATER;

public class CommandCoreExtensions {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        String[] NAMESPACE = {"coreextensions","coreextensions:cex","cex"};

        for (String COMMAND : NAMESPACE) {
            serverCommandSourceCommandDispatcher.register(CommandManager.literal(COMMAND)
                    .executes(Command::run)
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("version")
                            .executes(Command::version))
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("check")
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(Command::check))
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("getNbt")
                            .executes(Command::getNbt))
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("registry")
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(Command::registry))
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("reload")
                            .requires(source -> source.hasPermissionLevel(2))
                            .executes(Command::registry))
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("help")
                            .executes(Command::help))
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("about")
                            .executes(Command::about))
                    .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("dump")
                            .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("resource").executes(CommandDataGen::run))
                            .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("events").executes(Command::dumpEvents))
                            .then((ArgumentBuilder<ServerCommandSource, ?>)CommandManager.literal("registry").executes(Command::dumpRegistry))
            ));
        }
    }

    public static class Command {

        public static int about(CommandContext<ServerCommandSource> context) {
            context.getSource().sendFeedback(() -> Text.translatable("--------"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.about"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.about.author", Reference.AUTHOR), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.about.url", Reference.AUTHOR_URL), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.about.source", Reference.AUTHOR_SOURCE_URL), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.about.report", Reference.AUTHOR_REPORT_URL), false);
            return 1;
        }
        public static int run(CommandContext<ServerCommandSource> context) {
            context.getSource().sendFeedback(() -> Text.translatable("help.text.help.command").formatted(Formatting.RED), false);
            return 1;
        }

        public static int dumpEvents(CommandContext<ServerCommandSource> context) {
            return 0;
        }

        public static int dumpRegistry(CommandContext<ServerCommandSource> context) {
            CommandManager commands = context.getSource().getServer().getCommandManager();
            CommandDispatcher<ServerCommandSource> disp = commands.getDispatcher();

            String path = java.nio.file.Path.of("./core/dump/commands.json", "").toAbsolutePath().normalize().toString();
            String path2 = "./core/dump/";
            new File("dump").mkdir();
            try (FileWriter writer = new FileWriter(path)){
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject obj = ArgumentHelper.toJson(disp, disp.getRoot());

                gson.toJson(obj, gson.newJsonWriter(writer));
            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
            try {
/*
                List<String> registriesList = new ArrayList<>();
                for (Registry registry : Registries.REGISTRIES) {
                    String registries = registry.getIds().toString();
                    registriesList.add(registries);
                }

                String registriesListStr = String.join(",", registriesList);
*/

                BufferedWriter writer = new BufferedWriter(new FileWriter(path2 + "registries.yml", false));
                writer.write("DUMPING:");
                writer.newLine();
                {
                    String registriesStr = Registries.REGISTRIES.getIds().toString();
                    writer.write("  REGISTRIES: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.ITEM.getIds().toString();
                    writer.write("  ITEM: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.ITEM_GROUP.getIds().toString();
                    writer.write("  ITEM_GROUPS: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.BLOCK.getIds().toString();
                    writer.write("  BLOCK: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.BLOCK_ENTITY_TYPE.getIds().toString();
                    writer.write("  BLOCK_ENTITY_TYPE: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.FLUID.getIds().toString();
                    writer.write("  FLUID: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.ENCHANTMENT.getIds().toString();
                    writer.write("  ENCHANTMENT: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.POTION.getIds().toString();
                    writer.write("  POTION: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.STATUS_EFFECT.getIds().toString();
                    writer.write("  STATUS_EFFECT: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.SOUND_EVENT.getIds().toString();
                    writer.write("  SOUND_EVENT: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.VILLAGER_TYPE.getIds().toString();
                    writer.write("  VILLAGER_TYPE: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.VILLAGER_PROFESSION.getIds().toString();
                    writer.write("  VILLAGER_PROFESSION: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.POINT_OF_INTEREST_TYPE.getIds().toString();
                    writer.write("  POINT_OF_INTEREST_TYPE: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.ENTITY_TYPE.getIds().toString();
                    writer.write("  ENTITY_TYPE: "+registriesStr);
                    writer.newLine();
                }
                {
                    String registriesStr = Registries.RECIPE_TYPE.getIds().toString();
                    writer.write("  RECIPE_TYPE: "+registriesStr);
                    writer.newLine();
                }
                writer.close();
                

            } catch (IOException e) {
                e.printStackTrace();
                return 0;
            }
            context.getSource().sendFeedback(() -> Text.translatable("commands.cex.dumped"), false);

            return 1;
        }
        public static int help(CommandContext<ServerCommandSource> context) {
            context.getSource().sendFeedback(() -> Text.translatable("--------"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.help.command"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.registry.command"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.check.command"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.version.command"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.about.command"), false);

            return 1;
        }
        public static int getNbt(CommandContext<ServerCommandSource> context) {
            ServerPlayerEntity player = context.getSource().getPlayer();
            ItemStack heldItem = player.getMainHandStack();
            NbtCompound itemNbt = heldItem.getNbt();
            try {
                context.getSource().sendFeedback(() -> Text.literal("NBT: "+ itemNbt.toString()), false);
                context.getSource().sendFeedback(() -> Text.translatable("text.copy")
                        .setStyle(Style.EMPTY.withClickEvent(new ClickEvent(ClickEvent.Action.COPY_TO_CLIPBOARD, itemNbt.toString()))
                                .withHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, Text.translatable("Copy NBT Data")))
                        ),false);
            } catch (Exception e) {
                context.getSource().sendFeedback(() -> Text.literal("NBT: "+ "{}").formatted(Formatting.RED), false);
            }
            return 1;
        }
        public static int version(CommandContext<ServerCommandSource> context) {
            context.getSource().sendFeedback(() -> Text.translatable("--------"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.version.0"), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.version.1", Reference.VERSION), false);
            context.getSource().sendFeedback(() -> Text.translatable("help.text.version.2", Reference.VERSION_ID), false);
            context.getSource().sendFeedback(() -> Text.translatable("--------"), false);
            return 1;
        }
        public static int check(CommandContext<ServerCommandSource> context) {
            ServerPlayerEntity player = context.getSource().getPlayer();
            context.getSource().sendFeedback(() -> Text.translatable("Checking for updates！"), false);
            int remoteVersion = VersionChecker.getVersion();
            if (remoteVersion > Reference.VERSION_ID) {
                UPDATER.info("A new version is available！");
                context.getSource().sendFeedback(() -> Text.translatable("A new version is available！"), false);
            } else if (remoteVersion == -100) {
                context.getSource().sendFeedback(() -> Text.translatable("Could not connect to server.").formatted(Formatting.DARK_RED), false);
            } else {
                context.getSource().sendFeedback(() -> Text.translatable("Your CoreExtensions are already the latest version.").formatted(Formatting.GREEN), false);
            }
            return 1;
        }
        public static int registry(CommandContext<ServerCommandSource> context) {
            ServerCommandSource source = context.getSource();
            ServerPlayerEntity player = context.getSource().getPlayer();
            MinecraftServer server = player.getServer();
            if(Config.getConfigBoolean("ALLOW_RELOADING_REGISTRY")) {
                if(Reference.EnvType == EnvType.CLIENT) {
                    try {
                        CoreManager.bootstrap();
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
                    CoreManager.bootstrap();
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
