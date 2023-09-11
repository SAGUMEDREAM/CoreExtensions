package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Shield {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData){
        int durability = (int) itemData.get("durability"); // Parse durability attribute
        ToolMaterial material = ToolMaterials.IRON; // Default material

        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) itemData.get("repairMaterial")));

        //Item shield =
        registerShield(namespace, id , durability, repairMaterialItem);

        String type = "SHIELD";
        Models.generate(namespace, id, type);

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static Item registerShield(String namespace, String id, int durability, Item repairMaterialItem) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new FabricShieldItem(new FabricItemSettings().maxDamage(durability), 10, 13, repairMaterialItem));
    }
}
