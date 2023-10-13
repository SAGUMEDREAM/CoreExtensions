package com.KafuuChino0722.coreextensions.command;

import com.KafuuChino0722.coreextensions.core.*;
import com.KafuuChino0722.coreextensions.core.brrp.Export;
import com.KafuuChino0722.coreextensions.util.iZipManager;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.lang.reflect.Method;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class CoreRegReload {

    public static void load() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, server) -> {
            dispatcher.register(CommandManager.literal("cex-reload")
                    .requires(source -> source.hasPermissionLevel(0))
                    .executes(context -> {

                        return 1;
                    })
                    .build()
                    .createBuilder()
            );
        });
    }
}
