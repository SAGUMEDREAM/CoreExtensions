package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Flint_And_Steel {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData){
        int durability = (int) itemData.get("durability"); // Parse durability attribute

        ToolItem flint_and_steel = (ToolItem) registerItem(namespace, id, durability);

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, int durability) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                (Item) new FlintAndSteelItem(new Item.Settings().maxDamage(durability)));
    }

}
