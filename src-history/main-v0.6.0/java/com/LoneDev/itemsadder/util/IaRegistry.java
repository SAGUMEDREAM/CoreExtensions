package com.LoneDev.itemsadder.util;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public class IaRegistry {
    public static void registerCommand(CommandRegistrationCallback Method) {
        CommandRegistrationCallback.EVENT.register(Method);
    }
}
