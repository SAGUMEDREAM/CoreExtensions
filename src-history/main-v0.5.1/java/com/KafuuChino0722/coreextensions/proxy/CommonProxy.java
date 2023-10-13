package com.KafuuChino0722.coreextensions.proxy;

import com.KafuuChino0722.coreextensions.CoreManager;
import com.KafuuChino0722.coreextensions.network.VersionChecker;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerWorldEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;


public class CommonProxy{

    public static boolean CORE_API_Enabled;

    public void onInitializeCommon(boolean CORE_API_Enabled) {
        this.CORE_API_Enabled = CORE_API_Enabled;
        if(this.CORE_API_Enabled) {
            ServerLifecycleEvents.SERVER_STARTING.register(client -> {

            });
        }
        ServerWorldEvents.LOAD.register((server, world) -> {

            world.getPlayers().forEach(player -> onPlayerEnterWorld(server, player, world));

        });
    }

    private static void onWorldLoad() {
        CoreManager.load(false);
    }

    private static void onPlayerEnterWorld(MinecraftServer server, ServerPlayerEntity player, ServerWorld world) {
        if(Reference.VERSION_ID < VersionChecker.getVersion()) {
            player.sendMessage(Text.literal("[CoreExtensions] 发现新版本"), false);
            player.sendMessage(Text.literal("[CoreExtensions] Found new version"), false);
        }
    }
}
