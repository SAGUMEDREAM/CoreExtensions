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

public class Items {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData, boolean generate){
        Item item = new Item(new Item.Settings().maxCount(maxCount));

        registerItem(namespace, id, item);

        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        ReturnMessage.ToolYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static net.minecraft.item.Item registerItem(String namespace, String id, net.minecraft.item.Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}
