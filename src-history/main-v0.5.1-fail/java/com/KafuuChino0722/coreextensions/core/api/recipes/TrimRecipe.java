package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTrimRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class TrimRecipe {
    public static void generate(String name, String namespace, String id, String types, String template,String base,String addition, String result, int maxCount, RecipeCategory category, CraftingRecipeCategory categoryTypeCrafting) {

            respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                    SmithingTrimRecipeJsonBuilder
                            .create(Ingredient.ofItems(Registries.ITEM.get(new Identifier(template))),
                                    Ingredient.ofItems(Registries.ITEM.get(new Identifier(base))),
                                    Ingredient.ofItems(Registries.ITEM.get(new Identifier(addition))),
                                    category)
                            .setCustomRecipeCategory("event").setBypassesValidation(true));

    }
}
