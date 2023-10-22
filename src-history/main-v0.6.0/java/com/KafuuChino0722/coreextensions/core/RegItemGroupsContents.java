package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.itemgroups.itemGroupsContents;
import com.KafuuChino0722.coreextensions.util.Reference;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class RegItemGroupsContents {
    public static final String FILE = Reference.File;

    public static void load(@Nullable String namespace, String id, Map<String, Object> Data, Map<String, Object> properties) {
        String groupid = properties.containsKey("groups") ? (String) properties.get("groups") : "INGREDIENTS";
        if (properties.containsKey("groups")) {
            itemGroupsContents.generate(namespace, id, groupid, properties);
        }
    }
    public static void load(@Nullable String namespace, String id, Map<String, Object> properties) {
        String groupid = properties.containsKey("groups") ? (String) properties.get("groups") : "INGREDIENTS";
        if (properties.containsKey("groups")) {
            itemGroupsContents.generate(namespace, id, groupid, properties);
        }
    }
    public static void itemDataload(@Nullable String namespace, String id, Map<String, Object> itemData) {
        String groupid = itemData.containsKey("groups") ? (String) itemData.get("groups") : "INGREDIENTS";
        if (itemData.containsKey("groups")) {
            itemGroupsContents.generate(namespace, id, groupid, itemData);
        }
    }
}