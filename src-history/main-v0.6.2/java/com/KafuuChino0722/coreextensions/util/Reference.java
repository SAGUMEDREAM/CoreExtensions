package com.KafuuChino0722.coreextensions.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.MinecraftVersion;

import java.net.InetAddress;
import java.net.UnknownHostException;

import java.net.NetworkInterface;
import java.net.SocketException;

public class Reference {
    public static final String MODID = "coreextensions";
    public static final String NAME = "CoreExtensions";
    public static final String VERSION = "0.6.2";
    public static final int VERSION_ID = 25;
    public static final String ACCEPTED_VERSIONS = "1.20.1";
    public static final String MINECRAFT_VERSIONS = MinecraftVersion.CURRENT.getName();
    public static final String CLIENT_PROXY_CLASS = "com.KafuuChino0722.coreextensions.proxy.ClientProxy";
    public static final String COMMON_PROXY_CLASS = "com.KafuuChino0722.coreextensions.proxy.CommonProxy";
    public static final String PLAYER_NAME = "[username]";
    public static final String AUTHOR = "稀神灵梦";
    public static final String AUTHOR_URL = "https://space.bilibili.com/158597454";
    public static final String AUTHOR_SOURCE_URL = "https://github.com/SAGUMEDREAM/CoreExtensions";
    public static final String AUTHOR_REPORT_URL = "https://github.com/SAGUMEDREAM/CoreExtensions/issues";
    public static final String VANILLA = "minecraft";
    //public static final String File = "config/coreextensions/";
    public static final String File = "core/";
    public static final String Mods = "mods/";
    public static final String Config = "config/";
    public static final EnvType EnvType = FabricLoader.getInstance().getEnvironmentType();

    public static boolean isModLoaded(String id) {
        return FabricLoader.getInstance().isModLoaded(id);
    }

    public static String getUserName() {
        try {
            String computerName = InetAddress.getLocalHost().getHostName();
            return computerName;
        } catch (UnknownHostException e) {
            return PLAYER_NAME;
        }
    }

    public static String getUID() {
        try {
            InetAddress localhost = InetAddress.getLocalHost();
            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localhost);
            byte[] macAddressBytes = networkInterface.getHardwareAddress();

            if (macAddressBytes != null) {
                StringBuilder macAddress = new StringBuilder();
                for (byte b : macAddressBytes) {
                    macAddress.append(String.format("%02X", b));
                    macAddress.append(":");
                }
                macAddress.deleteCharAt(macAddress.length() - 1);

                return macAddress.toString().replace(":", "");
            } else {
                return "000000000000";
            }
        } catch (UnknownHostException | SocketException e) {
            return "000000000000";
        }
    }
}
