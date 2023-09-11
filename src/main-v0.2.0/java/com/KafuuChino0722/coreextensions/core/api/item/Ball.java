package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Ball {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData){
        Item item = new SnowballItem(new Item.Settings().maxCount(maxCount));
        registerBall(namespace, id, item);

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static Item registerBall(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}
