package com.LoneDev.itemsadder.api;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.core.api.itemgroups.itemGroupsManager.getID;

public class CustomCategory {
    public boolean enabled;
    public String name;
    public String id;
    public Item icon;

    public void load(String namespace, Map<String, Object> DataAll) {

        if (DataAll.containsKey("categories")) {
            try {
                Map<String, Object> categories = (Map<String, Object>) DataAll.get("categories");

                if (categories != null) {
                    for (Map.Entry<String, Object> cateEntry : categories.entrySet()) {
                        Map<String, Object> cateData = (Map<String, Object>) cateEntry.getValue();

                        if(cateData.containsKey("enabled")) {
                            enabled = (boolean) cateData.get("enabled");
                        } else {
                            enabled = true;
                        }

                        if(cateData.containsKey("name")) {
                            name = (String) cateData.get("name");
                        } else {
                            name = cateEntry.getKey().toLowerCase();
                        }

                        if(cateData.containsKey("icon")) {
                            icon = Registries.ITEM.get(new Identifier((String) cateData.get("icon")));
                        } else {
                            icon = Items.GRASS_BLOCK;
                        }
                        id = cateEntry.getKey().toLowerCase().replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9 ]", "");

                        try {
                            ItemGroup GroupsId = Registry.register(Registries.ITEM_GROUP,
                                    new Identifier(namespace, id),
                                    FabricItemGroup.builder()
                                            .icon(() -> new ItemStack(icon))
                                            .displayName(Text.literal(name))
                                            .build()
                            );
                        } catch (Exception e) {

                        }


                        if(cateData.containsKey("items")) {
                            List<String> itemsKey = (List<String>) cateData.get("items");
                            for (String itemId : itemsKey) {
                                ItemGroupEvents.modifyEntriesEvent(RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(namespace,id))).register(content -> {
                                    if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                        content.add(getID(itemId));
                                    }
                                });
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}