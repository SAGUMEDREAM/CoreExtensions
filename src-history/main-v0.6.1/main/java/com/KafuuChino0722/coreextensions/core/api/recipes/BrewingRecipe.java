package com.KafuuChino0722.coreextensions.core.api.recipes;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.Map;

public class BrewingRecipe {
    public static void generate(String name, String namespace, String id, Map<String, Object> recipesData) {
        Map<String, Object> properties = (Map<String, Object>) recipesData.get("properties");
        String inputStr = (String) properties.getOrDefault("input", null);
        String materialStr = (String) properties.getOrDefault("material", null);
        String resultStr = (String) properties.getOrDefault("result", null);
        Potion input = Registries.POTION.get(new Identifier(inputStr));
        Item material = Registries.ITEM.get(new Identifier(materialStr));
        Potion result = Registries.POTION.get(new Identifier(resultStr));

        try {
            BrewingRecipeRegistry.registerPotionRecipe(input, material, result);
        } catch (Exception e) {
            Info.error("There is a problem in the recipe of the pharmaceutical material!");
        }

    }
}
