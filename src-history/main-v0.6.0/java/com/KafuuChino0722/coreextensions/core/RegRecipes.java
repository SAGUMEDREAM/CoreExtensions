package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.util.RecipeJson;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegRecipes {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/recipe.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("recipes")) {
                                Map<String, Object> items = itemsData.get("recipes");

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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}