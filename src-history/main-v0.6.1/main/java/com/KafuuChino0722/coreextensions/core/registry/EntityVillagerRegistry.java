package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.villager.Villager;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_VILlAGER_JOB_SITE;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;


public class EntityVillagerRegistry {

    public static void register() {
        Map<String, Map<String, Object>> enchantData = IOFileManager.read("villager.yml");
        load(enchantData);
        Map<String, Map<String, Object>> enchantDataZ = IOFileManager.readZip("villager.yml");
        load(enchantDataZ);
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
                    String block = (String) properties.getOrDefault("block","command_block");

                    Villager.registerVillager(namespace,id,block);
                    Villager.registerTrades(namespace,id,villagerData);

                    TAG_VILlAGER_JOB_SITE.add(new Identifier(namespace,id+"_poi"));

                    provider.add("entity.minecraft.villager."+id+"_master", name);

                }
            }
        }
    }
}