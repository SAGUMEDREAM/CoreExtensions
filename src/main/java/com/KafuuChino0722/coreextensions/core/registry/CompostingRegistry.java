package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

public class CompostingRegistry {
    public static final String FILE = Reference.File;

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("composting.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("composting.yml");
        load(itemsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("items")) {
            Map<String, Object> items = itemsData.get("items");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String name = (String) itemData.get("name");
                    String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                    String id = (String) itemData.get("id");
                    double chance = (double) itemData.get("chance");

                    if(Registries.ITEM.containsId(new Identifier(namespace, id))) {
                        Item ItemResources = Registries.ITEM.get(new Identifier(namespace, id));
                        CompostingChanceRegistry.INSTANCE.add(ItemResources,(float) chance);
                        ReturnMessage.CompostingYMLRegister(name, namespace, id); //returnMessage
                    } else {
                        Info.ERROR.info("Can't find item for"+namespace+":"+id+"in data/composting.yml");
                    }

                }
            }
        }
    }
}
