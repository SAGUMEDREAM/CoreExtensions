package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.item.BowItem;
import com.KafuuChino0722.coreextensions.item.CustomBowItem;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;

public class Bow {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData, Map<String, Object> properties, boolean generate) throws NoSuchFieldException, IllegalAccessException {
        String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";
        int durability = properties.containsKey("durability") ? (int) properties.get("durability") : 384;
        int RANGE = properties.containsKey("range") ? (int) properties.get("range") : 15;
        String tooltipMsg = properties.containsKey("tooltipMsg") ? (String) properties.get("tooltipMsg") : null;

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

        Item item = new CustomBowItem(new Item.Settings().maxCount(maxCount).rarity(rarityType).maxDamage(durability), tooltipMsg);

        if(!Registries.ITEM.containsId(new Identifier(namespace, id))) {
            registerItem(namespace, id, item, rarityType);
        } else {
            Info.custom(namespace+":"+id+" has been registered twice and has automatically prevented the game from crashing","ItemManager");
        }


        Field field;
        try {
            field = BowItem.class.getDeclaredField("RANGE");
        } catch (NoSuchFieldException e) {
            field = BowItem.class.getDeclaredField("field_30856");
        }
        field.setAccessible(true);
        field.set(item, RANGE);

        String type = "BOW";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        Tags.Item.generateTags(namespace,id,properties);
        ReturnMessage.ToolYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static net.minecraft.item.Item registerItem(String namespace, String id, net.minecraft.item.Item item, Rarity rarityType) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

}
