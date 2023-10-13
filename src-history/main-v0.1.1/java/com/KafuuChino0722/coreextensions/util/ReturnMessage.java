package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;

public class ReturnMessage {
    public static void ItemYMLTYPEERROR(String name, String namespace, String id) {
        Main.LOGGER.info("item.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check it.");
    }
    public static void BlockYMLTYPEERROR(String name, String namespace, String id) {
        Main.LOGGER.info("block.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check it.");
    }
    public static void ItemYMLRegister(String name, String namespace, String id) {
        Main.LOGGER.info("Item " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void BlockYMLRegister(String name, String namespace, String id) {
        Main.LOGGER.info("block " + name + "<->" + namespace + ":" + id + " registered!");
    }
}
