package com.KafuuChino0722.coreextensions.core.registry._Fix;

import io.github.theepicblock.polymc.PolyMc;
import io.github.theepicblock.polymc.api.misc.PolyMapProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.server.network.ServerPlayerEntity;

import static com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading.server;

public class PolyReloading {
    public static void reload() {
        if (FabricLoader.getInstance().isModLoaded("polymc")) {
            try {
                PolyMc.onRegistryClosed();
                for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
                    ((PolyMapProvider) (player.networkHandler)).refreshUsedPolyMap();
                }
            } catch (Exception e) {

            }
        }
    }
}
