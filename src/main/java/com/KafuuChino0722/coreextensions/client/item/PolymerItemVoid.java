package com.KafuuChino0722.coreextensions.client.item;

import eu.pb4.polymer.core.api.item.SimplePolymerItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PolymerItemVoid {

    public Item clientItem;
    public Item.Settings settings;

    public PolymerItemVoid(Identifier identifier, Item clientItem, Item.Settings settings) {
        this.clientItem = clientItem;
        this.settings = settings;
        try {
            Registry.register(Registries.ITEM, identifier,
                    new SimplePolymerItem(settings, this.clientItem));
        } catch (Exception e) {

        }
    }

}
