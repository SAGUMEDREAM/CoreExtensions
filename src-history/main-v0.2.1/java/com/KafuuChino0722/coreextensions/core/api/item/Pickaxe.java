package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Objects;

public class Pickaxe {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData, String level, boolean generate){
        double attackDamage = (double) itemData.get("attackDamage");
        double attackSpeed = (double) itemData.get("attackSpeed");
        int durability = (int) itemData.get("durability"); // Parse durability attribute

        ToolMaterial material;
        if (Objects.equals(level, "0") || Objects.equals(level, "WOOD") || Objects.equals(level, "GOLD") || Objects.equals(level, "wood") || Objects.equals(level, "gold")) {
            material = ToolMaterials.WOOD;
        } else if (Objects.equals(level, "1") || Objects.equals(level, "stone") || Objects.equals(level, "STONE")) {
            material = ToolMaterials.STONE;
        } else if (Objects.equals(level, "2") || Objects.equals(level, "iron") || Objects.equals(level, "IRON")) {
            material = ToolMaterials.IRON;
        } else if (Objects.equals(level, "3") || Objects.equals(level, "diamond") || Objects.equals(level, "DIAMOND")) {
            material = ToolMaterials.DIAMOND;
        } else if (Objects.equals(level, "4") || Objects.equals(level, "netherite") || Objects.equals(level, "NETHERITE")) {
            material = ToolMaterials.NETHERITE;
        } else {
            material = ToolMaterials.IRON;
        }

        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) itemData.get("repairMaterial")));
        if (repairMaterialItem instanceof ToolItem) {
            material = ((ToolItem) repairMaterialItem).getMaterial();
        }

        //ToolItem pickaxe =
        registerPickaxe(namespace, id, (int) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);

        String type = "HANDHELD";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        ReturnMessage.ToolYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static ToolItem registerPickaxe(String namespace, String id, int attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new PickaxeItem(material, attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }
}
