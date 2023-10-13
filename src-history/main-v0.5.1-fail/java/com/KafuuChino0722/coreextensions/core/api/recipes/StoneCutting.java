package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SingleItemRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class StoneCutting {
    public static void generate(String name, String namespace, String id, String input, String result,int maxCount, RecipeCategory category) {

        respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                    SingleItemRecipeJsonBuilder
                            .createStonecutting(Ingredient.ofItems(Registries.ITEM.get(new Identifier(input))),
                                    category,
                                    Registries.ITEM.get(new Identifier(result)),maxCount)
                            .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                            .setCustomRecipeCategory("event")
                    );

    }
}
