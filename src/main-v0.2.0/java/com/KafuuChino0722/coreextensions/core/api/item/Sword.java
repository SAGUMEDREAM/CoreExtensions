package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Sword {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData){
        double attackDamage = (double) itemData.get("attackDamage");
        double attackSpeed = (double) itemData.get("attackSpeed");
        int durability = (int) itemData.get("durability"); // Parse durability attribute
        ToolMaterial material = ToolMaterials.IRON; // Default material

        String repairMaterialId = (String) itemData.get("repairMaterial");
        Item repairMaterialItem = Registries.ITEM.get(new Identifier(repairMaterialId));
        if (repairMaterialItem instanceof ToolItem) {
            material = ((ToolItem) repairMaterialItem).getMaterial();
        }
        ToolItem sword = registerSword(namespace, id, (double) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static ToolItem registerSword(String namespace, String id, double attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new SwordItem(material, (int) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

}
