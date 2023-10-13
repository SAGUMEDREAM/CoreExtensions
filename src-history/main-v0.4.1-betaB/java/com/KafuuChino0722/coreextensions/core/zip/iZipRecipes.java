package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.api.util.RecipeJson;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class iZipRecipes {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

            if (zipFiles != null) {
                for (File zipFile : zipFiles) {
                    try (ZipFile zip = new ZipFile(zipFile)) {
                        Enumeration<? extends ZipEntry> entries = zip.entries();

                        while (entries.hasMoreElements()) {
                            ZipEntry entry = entries.nextElement();

                            if (!entry.isDirectory() && entry.getName().equals("data/recipe.yml")) {
                                try (InputStream inputStream = zip.getInputStream(entry)) {
                                    Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                    if (Data != null && Data.containsKey("recipes")) {
                                        Map<String, Object> blocks = Data.get("recipes");

                                        for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                            if (DataEntry.getValue() instanceof Map) {
                                                Map<String, Object> recipesData = (Map<String, Object>) DataEntry.getValue();

                                                String name = (String) recipesData.get("name");
                                                String namespace = (String) recipesData.getOrDefault("namespace","minecraft");
                                                String id = (String) recipesData.get("id");
                                                String types = (String) recipesData.get("types");
                                                Map<String, Object> properties = (Map<String, Object>) recipesData.get("properties");
                                                String category = properties.containsKey("category") ? (String) properties.get("category") : "MISC";
                                                String result = (String) properties.get("result");
                                                int maxCount = properties.containsKey("count") ? (int) properties.get("count") : 1;
                                                double experience = properties.containsKey("experience") ? (double) properties.get("experience") : (double) 1.0;
                                                int cookingTime = properties.containsKey("cookingTime") ? (int) properties.get("cookingTime") : 100;
                                                Map<String, Object> KeyData = (Map<String, Object>) properties.get("key");
                                                String A = KeyData.containsKey("A") ? (String) KeyData.get("A") : "minecraft:air";
                                                String B = KeyData.containsKey("B") ? (String) KeyData.get("B") : "minecraft:air";
                                                String C = KeyData.containsKey("C") ? (String) KeyData.get("C") : "minecraft:air";
                                                String D = KeyData.containsKey("D") ? (String) KeyData.get("D") : "minecraft:air";
                                                String E = KeyData.containsKey("E") ? (String) KeyData.get("E") : "minecraft:air";
                                                String F = KeyData.containsKey("F") ? (String) KeyData.get("F") : "minecraft:air";
                                                String G = KeyData.containsKey("G") ? (String) KeyData.get("G") : "minecraft:air";
                                                String H = KeyData.containsKey("H") ? (String) KeyData.get("H") : "minecraft:air";
                                                String I = KeyData.containsKey("I") ? (String) KeyData.get("I") : "minecraft:air";
                                                String template = KeyData.containsKey("template") ? (String) KeyData.get("template") : "minecraft:air";
                                                String base = KeyData.containsKey("base") ? (String) KeyData.get("base") : "minecraft:air";
                                                String addition = KeyData.containsKey("addition") ? (String) KeyData.get("addition") : "minecraft:air";
                                                String INPUT = properties.containsKey("input") ? (String) properties.get("input") : "minecraft:iron_ingot";


                                                RecipeJson.generate(name,namespace,id,types,A,B,C,D,E,F,G,H,I,template,base,addition,INPUT,result,maxCount,experience,cookingTime,category,recipesData);

                                                ReturnMessage.RecipesYMLRegister(name,namespace,id);

                                            }
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
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
