package com.KafuuChino0722.coreextensions.core.oldapi_v1.item;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegItem {
    public static final String FILE = Reference.File;

    private static Item register(String namespace, String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name), item);
    }

    public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(FILE + "item.yml"));

            if (itemsData != null && itemsData.containsKey("items")) {
                Map<String, Object> items = itemsData.get("items");

                for (Map.Entry<String, Object> entry : items.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                        String name = (String) itemData.get("name");
                        String namespace = (String) itemData.get("namespace");
                        String id = (String) itemData.get("id");
                        int maxCount = (int) itemData.get("maxCount");

                        Main.LOGGER.info("Item " + name + "<->" + namespace + ":" + id + " registered!");
                        Item item = new Item(new Item.Settings().maxCount(maxCount));
                        register(namespace, id, item);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
