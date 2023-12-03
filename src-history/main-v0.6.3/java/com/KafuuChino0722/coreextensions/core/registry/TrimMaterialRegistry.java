package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.model.TrimMaterials;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;

import java.util.Map;

public class TrimMaterialRegistry {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("itemTrimMaterials.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("itemTrimMaterials.yml");
        load(itemsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("list")) {
            Map<String, Object> armors = itemsData.get("list");

            for (Map.Entry<String, Object> entry : armors.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String name = (String) itemData.getOrDefault("name",entry.getKey());
                    String lang_us = (String) itemData.getOrDefault("name",name);
                    String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                    String id = (String) itemData.get("id");

                    ReturnMessage.TrimMaterialsYMLRegister(name, namespace, id);
                    TrimMaterials.generate(namespace, id);
                }
            }
        }
    }
}