package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.RecipeJson;
import com.KafuuChino0722.coreextensions.item.ElytraItem;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static net.minecraft.item.ArmorItem.Type.*;

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
            }
        }
    }
}