package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;

import java.util.Map;

public class ReturnMessage {
    public static void ItemYMLTYPEERROR(String name, String namespace, String id) {
        Main.LOGGER.info("item.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }
    public static void BlockYMLTYPEERROR(String name, String namespace, String id) {
        Main.LOGGER.info("block.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }
    public static void ArmorYMLTYPEERROR(String name, String namespace, String id) {
        Main.LOGGER.info("armor.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }

    public static void PortalYMLTYPEERROR(String name,String types, Map<String, Object> portals) {
        Main.LOGGER.info("portal.yml " + name + "<->" + portals + " fail to register!please check its types.");
    }

    public static void PortalYMLlightWithFluidERROR(String name,String types, Map<String, Object> portals) {
        Main.LOGGER.info("portal.yml " + name + "<->" + portals + " fail to register!please check its lightWithFluid.");
    }

    public static void ItemYMLRegister(String name, String namespace, String id) {
        Main.LOGGER.info("Item " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void BlockYMLRegister(String name, String namespace, String id) {
        Main.LOGGER.info("block " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void ArmorYMLRegister(String name, String namespace, String id) {
        Main.LOGGER.info("Armor " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void FuelYMLRegister(String name, String namespace, String id) {
        Main.LOGGER.info("Fuel " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void PortalYMLRegister(String name,String types, Map<String, Object> portals) {
        Main.LOGGER.info("Portal " + name + "<->" + portals + " registered!");
    }
}
