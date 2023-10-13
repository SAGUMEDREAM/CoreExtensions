package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

public class Smoking {
    public static void generate(String name, String namespace, String id, String input, String result, RecipeCategory category,double experience,int cookingTime) {

        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_recipe"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                    CookingRecipeJsonBuilder
                            .createSmoking(Ingredient.ofItems(Registries.ITEM.get(new Identifier(input))),
                                    category,
                                    Registries.ITEM.get(new Identifier(result)),
                                    (float) experience,
                                    cookingTime)
                            .setCustomRecipeCategory("event")
                            .criterionFromItem(Registries.ITEM.get(new Identifier(result))));

            b.add(packs);
        });
    }
}
