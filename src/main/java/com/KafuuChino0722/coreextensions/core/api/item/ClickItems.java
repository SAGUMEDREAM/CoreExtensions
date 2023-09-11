package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.item.ClickItem;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class ClickItems {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData, boolean generate){
        int maxCount = (int) itemData.get("maxCount");
        //int attackDamage = (int) itemData.get("attackDamage");
        //double attackSpeed = (double) itemData.get("attackSpeed");

        Item item = new ClickItem(new Item.Settings().maxCount(maxCount)
                //.maxDamage(attackDamage)
        );
        registerItem(namespace, id, item);

        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib

    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static final String type = "CUBEALL";

}
