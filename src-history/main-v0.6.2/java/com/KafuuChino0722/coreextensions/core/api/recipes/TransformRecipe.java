package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class TransformRecipe {
    public static void generate(String name, String namespace, String id, String types ,String template,String base,String addition, String result, int maxCount, RecipeCategory category, CraftingRecipeCategory categoryTypeCrafting) {

        respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                    SmithingTransformRecipeJsonBuilder
                            .create(Ingredient.ofItems(Registries.ITEM.get(new Identifier(template))),
                                    Ingredient.ofItems(Registries.ITEM.get(new Identifier(base))),
                                    Ingredient.ofItems(Registries.ITEM.get(new Identifier(addition))),
                                    category,
                                    Registries.ITEM.get(new Identifier(result)))
                            .setCustomRecipeCategory("event").setBypassesValidation(true));

    }
}
