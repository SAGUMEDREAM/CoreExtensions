package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class CraftingShapeless {

    public static final String ItemVoidAir ="minecraft:air";

    public static void generate(String name, String namespace, String id, String types, String result,int maxCount, RecipeCategory category, CraftingRecipeCategory categoryTypeCrafting, Map<String, Object> properties) {

        ShapelessRecipeJsonBuilder Builder = ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                .setCustomCraftingCategory(categoryTypeCrafting)
                .setCustomRecipeCategory("event");


        Map<String, Object> KeyData = (Map<String, Object>) properties.getOrDefault("key",null);

        for (String key : KeyData.keySet()) {
            if(!key.equalsIgnoreCase(ItemVoidAir)) {
                char charKeys = key.charAt(0);
                Builder = Builder.input(Registries.ITEM.get(new Identifier((String) KeyData.getOrDefault(key,"minecraft:air"))), 1);
            }
        }

        respacks.addRecipeAndAdvancement(new Identifier(namespace, id),Builder);
    }
}
