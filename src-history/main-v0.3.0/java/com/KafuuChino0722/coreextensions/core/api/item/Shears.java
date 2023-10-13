package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Objects;

public class Shears {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData, String level, boolean generate){
        int durability = (int) itemData.get("durability"); // Parse durability attribute

        //ToolItem pickaxe =
        registerPickaxe(namespace, id, durability);

        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        ReturnMessage.ToolYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static ShearsItem registerPickaxe(String namespace, String id, int durability) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new ShearsItem(new FabricItemSettings().maxDamage(durability)));
    }
}
