package com.KafuuChino0722.coreextensions.core.api.itemgroups;

import com.KafuuChino0722.coreextensions.util.ReturnMessage;

import java.util.Map;

public class itemGroupsContents {
    public static void generate(
            String namespace, String id,
            String GroupId,
            Map<String, Object> properties
    ) {
        if (GroupId.equalsIgnoreCase("BUILDING_BLOCKS")) {
            itemGroupsManager.BUILDING_BLOCKS(namespace, id);

        } else if (GroupId.equalsIgnoreCase("COLORED_BLOCKS")) {
            itemGroupsManager.COLORED_BLOCKS(namespace, id);

        } else if (GroupId.equalsIgnoreCase("NATURAL")) {
            itemGroupsManager.NATURAL(namespace, id);

        } else if (GroupId.equalsIgnoreCase("FUNCTIONAL")) {
            itemGroupsManager.FUNCTIONAL(namespace, id);

        } else if (GroupId.equalsIgnoreCase("REDSTONE")) {
            itemGroupsManager.REDSTONE(namespace, id);

        } else if (GroupId.equalsIgnoreCase("HOTBAR")) {
            itemGroupsManager.HOTBAR(namespace, id);

        } else if (GroupId.equalsIgnoreCase("SEARCH")) {
            itemGroupsManager.SEARCH(namespace, id);

        } else if (GroupId.equalsIgnoreCase("TOOLS")) {
            itemGroupsManager.TOOLS(namespace, id);

        } else if (GroupId.equalsIgnoreCase("COMBAT")) {
            itemGroupsManager.COMBAT(namespace, id);

        } else if (GroupId.equalsIgnoreCase("FOOD_AND_DRINK")) {
            itemGroupsManager.FOOD_AND_DRINK(namespace, id);

        } else if (GroupId.equalsIgnoreCase("INGREDIENTS")) {
            itemGroupsManager.INGREDIENTS(namespace, id);

        } else if (GroupId.equalsIgnoreCase("SPAWN_EGGS")) {
            itemGroupsManager.SPAWN_EGGS(namespace, id);

        } else if (GroupId.equalsIgnoreCase("OPERATOR")) {
            itemGroupsManager.OPERATOR(namespace, id);

        } else if (GroupId.equalsIgnoreCase("INVENTORY")) {
            itemGroupsManager.INVENTORY(namespace, id);

        } else {
            itemGroupsManager.CUSTOM(namespace, id, GroupId);
        }

    }
}
