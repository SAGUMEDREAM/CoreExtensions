package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;

import java.util.Map;

public class ReturnMessage {
    public static void ItemYMLTYPEERROR(String name, String namespace, String id) {
        Info.createItem("item.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }

    public static void WeaponYMLTYPEERROR(String name, String namespace, String id) {
        Info.createItem("itemWeapon.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }

    public static void ToolYMLTYPEERROR(String name, String namespace, String id) {
        Info.createItem("itemTool.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }

    public static void BlockYMLTYPEERROR(String name, String namespace, String id) {
        Info.createBlock("block.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }

    public static void FluidsYMLTYPEERROR(String name, String namespace, String id) {
        Info.createFluids("fluids.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }

    public static void ArmorYMLTYPEERROR(String name, String namespace, String id) {
        Info.createArmor("armor.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
    }

    public static void PortalYMLTYPEERROR(String name,String types, Map<String, Object> portals) {
        Info.create("portal.yml " + name + "<->" + portals + " fail to register!please check its types.");
    }

    public static void PortalYMLlightWithFluidERROR(String name,String types, Map<String, Object> portals) {
        Info.create("portal.yml " + name + "<->" + portals + " fail to register!please check its lightWithFluid.");
    }

    public static void ItemYMLRegister(String name, String namespace, String id) {
        Info.createItem("Item " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void BlockYMLRegister(String name, String namespace, String id) {
        Info.createBlock("block " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void ArmorYMLRegister(String name, String namespace, String id) {
        Info.createItem("Item " + name + "<->" + namespace + ":" + id + " registered!");
        Info.createArmor("Armor " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void FuelYMLRegister(String name, String namespace, String id) {
        Info.create("Fuel " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void PortalYMLRegister(String name,String types, Map<String, Object> portals) {
        Info.create("Portal " + name + "<->" + portals + " registered!");
    }
    public static void WeaponYMLRegister(String name, String namespace, String id) {
        Info.createItem("Item " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void ToolYMLRegister(String name, String namespace, String id) {
        Info.createItem("Item " + name + "<->" + namespace + ":" + id + " registered!");
    }
}
