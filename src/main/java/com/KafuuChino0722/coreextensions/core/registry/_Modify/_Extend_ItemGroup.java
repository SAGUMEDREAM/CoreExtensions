package com.KafuuChino0722.coreextensions.core.registry._Modify;

import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;

import java.util.Map;

public class _Extend_ItemGroup {

    public static void register() {
        Map<String, Map<String, Object>> itemData = IOFileManager.read("itemGroups@Extends.yml");
        load(itemData);
        Map<String, Map<String, Object>> itemDataZ = IOFileManager.readZip("itemGroups@Extends.yml");
        load(itemDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("items")) {
            Map<String, Object> items = itemsData.get("items");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {

                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String name = (String) itemData.get("name");
                    String lang_us = (String) itemData.get("name");
                    //String namespace = (String) itemData.get("namespace");
                    String namespace = itemData.containsKey("namespace") ? (String) itemData.get("namespace") : "minecraft";
                    String id = (String) itemData.get("id");

                    ItemGroupsContents.itemDataload(namespace,id,itemData);

                }
            }
        }
    }
}