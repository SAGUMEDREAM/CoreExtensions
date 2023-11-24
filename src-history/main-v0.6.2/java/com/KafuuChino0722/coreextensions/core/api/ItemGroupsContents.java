package com.KafuuChino0722.coreextensions.core.api;

import com.KafuuChino0722.coreextensions.util.Reference;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class ItemGroupsContents {
    public static final String FILE = Reference.File;

    public static void load(@Nullable String namespace, String id, Map<String, Object> Data, Map<String, Object> properties) {
        String groupid = properties.containsKey("groups") ? (String) properties.get("groups") : "INGREDIENTS";
        if (properties.containsKey("groups")) {
            com.KafuuChino0722.coreextensions.core.api.itemgroups.itemGroupsContents.generate(namespace, id, groupid, properties);
        }
    }
    public static void load(@Nullable String namespace, String id, Map<String, Object> properties) {
        String groupid = properties.containsKey("groups") ? (String) properties.get("groups") : "INGREDIENTS";
        if (properties.containsKey("groups")) {
            com.KafuuChino0722.coreextensions.core.api.itemgroups.itemGroupsContents.generate(namespace, id, groupid, properties);
        }
    }
    public static void itemDataload(@Nullable String namespace, String id, Map<String, Object> itemData) {
        String groupid = itemData.containsKey("groups") ? (String) itemData.get("groups") : "INGREDIENTS";
        if (itemData.containsKey("groups")) {
            com.KafuuChino0722.coreextensions.core.api.itemgroups.itemGroupsContents.generate(namespace, id, groupid, itemData);
        }
    }
}