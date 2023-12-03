package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class Blasting {
    public static void generate(String name, String namespace, String id, String input, String result, RecipeCategory category,double experience,int cookingTime) {

        respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                    CookingRecipeJsonBuilder
                            .createBlasting(Ingredient.ofItems(Registries.ITEM.get(new Identifier(input))),
                                    category,
                                    Registries.ITEM.get(new Identifier(result)),
                                    (float) experience,
                                    cookingTime)
                            .setCustomRecipeCategory("event")
                            .criterionFromItem(Registries.ITEM.get(new Identifier(result))));

    }
}
