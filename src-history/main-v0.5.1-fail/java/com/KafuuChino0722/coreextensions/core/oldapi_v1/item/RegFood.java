package com.KafuuChino0722.coreextensions.core.oldapi_v1.item;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegFood {
    public static final String FILE = Reference.File;
    private static Item register(String namespace, String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name), item);
    }

    public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> foodsData = yaml.load(new FileReader(FILE + "foods.yml"));

            if (foodsData != null && foodsData.containsKey("foods")) {
                Map<String, Object> foods = foodsData.get("foods");

                for (Map.Entry<String, Object> entry : foods.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> foodData = (Map<String, Object>) entry.getValue();
                        String name = (String) foodData.get("name");
                        String namespace = (String) foodData.get("namespace");
                        String id = (String) foodData.get("id");
                        int hunger = (int) foodData.get("hunger");
                        double saturationModifier = (double) foodData.get("saturationModifier");
                        boolean isMeat = (boolean) foodData.get("meat"); // 获取之前的属性

                        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                                .hunger((int) hunger)
                                .saturationModifier((float) saturationModifier);

                        if (isMeat) {
                            foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
                        }

                        FoodComponent foodComponent = foodComponentBuilder.build();
                        Item foodItem = new Item(new Item.Settings().food(foodComponent));
                        Main.LOGGER.info("ItemFood " + name + "<->" + namespace + ":" + id + " registered!");
                        register(namespace, id, foodItem);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
