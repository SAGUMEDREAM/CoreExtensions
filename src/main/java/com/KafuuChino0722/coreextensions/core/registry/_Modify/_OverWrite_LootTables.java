package com.KafuuChino0722.coreextensions.core.registry._Modify;

import com.KafuuChino0722.coreextensions.core.api.util.MethodModifyLootTables;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;

import java.util.Map;

public class _OverWrite_LootTables {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("lootTables@Extend.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("lootTables@Extend.yml");
        load(itemsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("loots")) {
            Map<String, Object> items = itemsData.get("loots");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> LootTablesData = (Map<String, Object>) entry.getValue();
                    String name = (String) LootTablesData.get("name");
                    String namespace = (String) LootTablesData.getOrDefault("namespace","minecraft");
                    String path = (String) LootTablesData.get("path");
                    String item = (String) LootTablesData.get("item");
                    double chance = (double) LootTablesData.getOrDefault("chance",1.0);
                    int count = (int) LootTablesData.getOrDefault("count",1.0);

                    MethodModifyLootTables.create(namespace,path,item,chance,count);

                    ReturnMessage.LootsYMLRegister(name,namespace,path,item);

                }
            }
        }
    }
}