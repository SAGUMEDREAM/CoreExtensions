package com.KafuuChino0722.coreextensions.command;

import com.KafuuChino0722.coreextensions.core.brrp.Export;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.SaveProperties;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class CommandReloadNoReport {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("coreextensions:reloaddp")
                .requires(source -> source.hasPermissionLevel(0))
                .executes(CommandReloadNoReport::run));
    }

    private static Collection<String> findNewDataPacks(ResourcePackManager dataPackManager, SaveProperties saveProperties, Collection<String> enabledDataPacks) {
        dataPackManager.scanPacks();
        ArrayList<String> collection = Lists.newArrayList(enabledDataPacks);
        List<String> collection2 = saveProperties.getDataConfiguration().dataPacks().getDisabled();
        for (String string : dataPackManager.getNames()) {
            if (collection2.contains(string) || collection.contains(string)) continue;
            collection.add(string);
        }
        return collection;
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        ServerCommandSource serverCommandSource = (ServerCommandSource)context.getSource();
        MinecraftServer minecraftServer = serverCommandSource.getServer();
        ResourcePackManager resourcePackManager = minecraftServer.getDataPackManager();
        SaveProperties saveProperties = minecraftServer.getSaveProperties();
        Collection<String> collection = resourcePackManager.getEnabledNames();
        Collection<String> collection2 = CommandReloadNoReport.findNewDataPacks(resourcePackManager, saveProperties, collection);

        return 1;
    }


}