package com.KafuuChino0722.coreextensions.util;

import net.minecraft.registry.CombinedDynamicRegistries;
import net.minecraft.registry.ServerDynamicRegistryType;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.world.WorldSaveHandler;

public abstract class PlayerManagerExtend extends PlayerManager {
    public PlayerManagerExtend(MinecraftServer server, CombinedDynamicRegistries<ServerDynamicRegistryType> registryManager, WorldSaveHandler saveHandler, int maxPlayers) {
        super(server, registryManager, saveHandler, maxPlayers);
    }
}
