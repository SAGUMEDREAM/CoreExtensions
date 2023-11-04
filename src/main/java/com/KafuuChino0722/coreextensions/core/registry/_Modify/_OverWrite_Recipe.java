package com.KafuuChino0722.coreextensions.core.registry._Modify;

import com.KafuuChino0722.coreextensions.core.api.util.RecipeJson;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;

import java.util.Map;

public class _OverWrite_Recipe {

    public static void register() {
        Map<String, Map<String, Object>> EventData = IOFileManager.read("recipe@Overwrite.yml");
        load(EventData);
        Map<String, Map<String, Object>> EventDataZ = IOFileManager.readZip("recipe@Overwrite.yml");
        load(EventDataZ);
    }

    public static void load(Map<String, Map<String, Object>> EventsData) {
        if (EventsData != null && EventsData.containsKey("recipes")) {
            Map<String, Object> items = EventsData.get("recipes");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> recipesData = (Map<String, Object>) entry.getValue();
                    String name = (String) recipesData.get("name");
                    String namespace = (String) recipesData.getOrDefault("namespace","minecraft");
                    String id = (String) recipesData.get("id");
                    String types = (String) recipesData.get("types");
                    Map<String, Object> properties = (Map<String, Object>) recipesData.get("properties");
                    String category = properties.containsKey("category") ? (String) properties.get("category") : "MISC";
                    String result = (String) properties.getOrDefault("result","minecraft:stone");
                    int maxCount = properties.containsKey("count") ? (int) properties.get("count") : 1;
                    double experience = properties.containsKey("experience") ? (double) properties.get("experience") : (double) 1.0;
                    int cookingTime = properties.containsKey("cookingTime") ? (int) properties.get("cookingTime") : 100;
                    Map<String, Object> KeyData = (Map<String, Object>) properties.getOrDefault("key",null);
                    String template = KeyData.containsKey("template") ? (String) KeyData.get("template") : "minecraft:air";
                    String base = KeyData.containsKey("base") ? (String) KeyData.get("base") : "minecraft:air";
                    String addition = KeyData.containsKey("addition") ? (String) KeyData.get("addition") : "minecraft:air";
                    String INPUT = properties.containsKey("input") ? (String) properties.get("input") : "minecraft:iron_ingot";


                    RecipeJson.generate(name,namespace,id,types,template,base,addition,INPUT,result,maxCount,experience,cookingTime,category,recipesData);

                    ReturnMessage.RecipesYMLRegister(name,namespace,id);

                }
            }
        }
    }
}