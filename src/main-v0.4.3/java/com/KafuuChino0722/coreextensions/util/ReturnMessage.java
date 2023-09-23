package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;

import java.util.Map;

public class ReturnMessage {
    public static void ItemGroupsYMLTYPEERROR(String namespace, String id) {
        Info.custom("item " + namespace + ":" + id + " fail to add ItemGroups!maybe its groups is error","ItemGroups");
    }

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

    public static void PlantsYMLTYPEERROR(String name, String namespace, String id) {
        Info.createBlock("plants.yml " + name + "<->" + namespace + ":" + id + " fail to register!please check its types.");
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

    public static void TrimMaterialsYMLRegister(String name, String namespace, String id) {
        Info.createItem("TrimMaterials " + name + "<->" + namespace + ":" + id + " registered!");
    }

    public static void ItemYMLRegister(String name, String namespace, String id) {
        Info.createItem("Item " + name + "<->" + namespace + ":" + id + " registered!");
    }

    public static void BlockYMLRegister(String name, String namespace, String id) {
        Info.createBlock("block " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void PlantsYMLRegister(String name, String namespace, String id) {
        Info.createBlock("plant " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void RecipesYMLRegister(String name, String namespace, String id) {
        Info.createItem("Recipes " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void LootsYMLRegister(String name, String namespace, String id, String item) {
        Info.createItem("LootTables " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void ArmorYMLRegister(String name, String namespace, String id) {
        Info.createItem("Item " + name + "<->" + namespace + ":" + id + " registered!");
        Info.createArmor("Armor " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void FuelYMLRegister(String name, String namespace, String id) {
        Info.create("Fuel " + name + "<->" + namespace + ":" + id + " registered!");
    }
    public static void CompostingYMLRegister(String name, String namespace, String id) {
        Info.create("Composting " + name + "<->" + namespace + ":" + id + " registered!");
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
    public static void RewriteYMLRegister(String name, String namespace, String id) {
        Info.createItem("Rewrite " + name + "<->" + namespace + ":" + id + " registered!");
    }
}
