package com.KafuuChino0722.coreextensions.core.registry._Modify;

import com.KafuuChino0722.coreextensions.core.api.villager.Villager;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;

import java.util.Map;


public class _Extend_Villager {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("villager@Extends.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("villager@Extends.yml");
        load(itemsDataZ);
    }
    public static void load(Map<String, Map<String, Object>> entitiesData) {
        if (entitiesData != null && entitiesData.containsKey("villagers")) {
            Map<String, Object> info = entitiesData.get("villagers");

            for (Map.Entry<String, Object> entry : info.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> villagerData = (Map<String, Object>) entry.getValue();
                    String name = (String) villagerData.get("name");
                    String namespace = (String) villagerData.getOrDefault("namespace","minecraft");
                    String id = (String) villagerData.get("id");
                    //int maxLevel = 1;
                    Map<String, Object> properties = (Map<String, Object>) villagerData.get("properties");
                    //String block = (String) properties.get("block");

                    //Villager.registerVillager(namespace,id,block);
                    Villager.modifyTrades(namespace,id,villagerData);

                }
            }
        }
    }
}