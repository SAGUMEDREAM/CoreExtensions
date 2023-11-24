package com.KafuuChino0722.coreextensions.network;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;

public class MCAuth {

    public static final MinecraftClient Client = MinecraftClient.getInstance();

    public String getUserName() {
        if(FabricLoader.getInstance().getEnvironmentType()== EnvType.CLIENT) {
            //return ((MinecraftClient)(Object)this).session.getUsername();
            return Client.getSession().getUsername();
        } else {
            return "Minecraft Server";
        }

    }
}
