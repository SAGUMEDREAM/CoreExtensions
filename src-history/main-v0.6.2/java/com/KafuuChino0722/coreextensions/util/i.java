package com.KafuuChino0722.coreextensions.util;

public class i {
    private static final Thread shutdownHook = new Thread(() -> {
    });
    public static Object i() {
        if(!Reference.AUTHOR.equals("稀神灵梦")) {
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            Runtime.getRuntime().halt(0);
        }
        if(!Reference.isModLoaded("coreextensions")) {
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            Runtime.getRuntime().halt(0);
        }
        return null;
    }
}
