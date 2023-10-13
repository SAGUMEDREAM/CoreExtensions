package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class VoidAPI {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData){


        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name), item);
    }

    public static ToolItem registerAxe(String namespace, String name, float attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new AxeItem(material, (float) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerPickaxe(String namespace, String name, int attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new PickaxeItem(material, attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerShovel(String namespace, String name, double attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new ShovelItem(material, (float) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerHoe(String namespace, String name, double attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new HoeItem(material, (int) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static ToolItem registerSword(String namespace, String name, double attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new SwordItem(material, (int) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static Item registerBall(String namespace, String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name), item);
    }
}
