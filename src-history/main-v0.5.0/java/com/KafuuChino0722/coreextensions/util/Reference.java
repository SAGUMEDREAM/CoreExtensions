package com.KafuuChino0722.coreextensions.util;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.MinecraftVersion;
import net.minecraft.client.util.Session;

public class Reference {
    public static final String MODID = "coreextensions";
    public static final String NAME = "CoreExtensions";
    public static final String VERSION = "0.5.0";
    public static final String ACCEPTED_VERSIONS = "[1.20.1]";
    public static final String MINECRAFT_VERSIONS = MinecraftVersion.CURRENT.getName();
    public static final String CLIENT_PROXY_CLASS = "com.KafuuChino0722.coreextensions.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.KafuuChino0722.coreextensions.proxy.CommonProxy";
    public static final String PLAYER_NAME = "[username]";
    public static final String VANILLA = "minecraft";
    //public static final String File = "config/coreextensions/";
    public static final String File = "core/";
    public static final String Mods = "mods/";
    public static final String Config = "config/";
    public static final EnvType EnvType = FabricLoader.getInstance().getEnvironmentType();

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }
}
