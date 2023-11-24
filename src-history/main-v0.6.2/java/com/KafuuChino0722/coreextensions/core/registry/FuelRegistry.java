package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class FuelRegistry {

    public static void register() {
        Map<String, Map<String, Object>> fuelData = IOFileManager.read("fuel.yml");
        load(fuelData);
        Map<String, Map<String, Object>> fuelDataZ = IOFileManager.readZip("fuel.yml");
        load(fuelDataZ);
    }

    public static void load(Map<String, Map<String, Object>> FuData) {
        if (FuData != null && FuData.containsKey("items")) {
            Map<String, Object> items = FuData.get("items");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String name = (String) itemData.get("name");
                    String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                    String id = (String) itemData.get("id");
                    int value = (int) itemData.get("value");

                    if(Registries.ITEM.containsId(new Identifier(namespace, id))) {
                        Item ItemResources = Registries.ITEM.get(new Identifier(namespace, id));
                        net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.remove(ItemResources);
                        net.fabricmc.fabric.api.registry.FuelRegistry.INSTANCE.add(ItemResources, value);

                        ReturnMessage.ItemGroupYMLRegister(name, namespace, id); //returnMessage
                    } else {
                        Info.ERROR.info("Can't find item for"+namespace+":"+id+"in data/fuel.yml");
                    }
                }
            }
        }
    }
}
