package com.KafuuChino0722.coreextensions.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;

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
