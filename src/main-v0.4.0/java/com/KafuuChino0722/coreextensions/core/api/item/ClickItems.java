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
import net.minecraft.util.Rarity;

import java.util.Map;
import java.util.Objects;

public class ClickItems {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData, Map<String, Object> properties, boolean generate){

        String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";

        Rarity rarityType = null;
        if (Objects.equals(rarity, "COMMON") || Objects.equals(rarity, "common")) {
            rarityType = Rarity.COMMON;
        } else if (Objects.equals(rarity, "UNCOMMON") || Objects.equals(rarity, "uncommon")) {
            rarityType = Rarity.UNCOMMON;
        } else if (Objects.equals(rarity, "RARE") || Objects.equals(rarity, "rare")) {
            rarityType = Rarity.RARE;
        } else if (Objects.equals(rarity, "EPIC") || Objects.equals(rarity, "epic")) {
            rarityType = Rarity.EPIC;
        } else {
            rarityType = Rarity.COMMON;
        }

        int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 64;
        //int maxDamage = itemData.containsKey("attackDamage") ? (int) itemData.get("attackDamage") : 0;
        //int attackDamage = (int) itemData.get("attackDamage");
        //double attackSpeed = (double) itemData.get("attackSpeed");

        Item item = new ClickItem(new Item.Settings().maxCount(maxCount).rarity(rarityType)//.maxDamage(attackDamage)
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
