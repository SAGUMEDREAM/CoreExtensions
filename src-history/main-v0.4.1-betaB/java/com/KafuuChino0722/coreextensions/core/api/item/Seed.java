package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Seed {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData){

        Item seedItem = new AliasedBlockItem(Registries.BLOCK.get(
                new Identifier(namespace, id+"_block")),
                new FabricItemSettings().maxCount(maxCount));

        Item item = new Item(new Item.Settings().maxCount(maxCount));

        registerSeedItem(namespace, id, seedItem);
        registerItem(namespace, id, item);


        String type = "ITEM";
        Models.generate(namespace, id+"_seeds", type);
        Models.generate(namespace, id, type);

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerSeedItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id+"_seeds"), item);
    }
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}