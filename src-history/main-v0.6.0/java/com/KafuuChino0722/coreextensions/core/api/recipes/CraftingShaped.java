package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class CraftingShaped {

    public static final String ItemVoidAir ="minecraft:air";

    public static void generate(String name, String namespace, String id, String types, String topRowPattern, String middleRowPattern, String bottomRowPattern, String result, int maxCount, RecipeCategory category, CraftingRecipeCategory categoryTypeCrafting, Map<String, Object> properties) {

        ShapedRecipeJsonBuilder Builder = ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                .setCustomCraftingCategory(categoryTypeCrafting)
                .setCustomRecipeCategory("event");

        if(!Objects.equals(topRowPattern, null)) {
            Builder = Builder.pattern(topRowPattern);
            if(!Objects.equals(middleRowPattern, null)) {
                Builder = Builder.pattern(middleRowPattern);
                if(!Objects.equals(bottomRowPattern, null)) {
                    Builder = Builder.pattern(bottomRowPattern);
                }
            }
        }


        Map<String, Object> KeyData = (Map<String, Object>) properties.getOrDefault("key",null);

        for (String key : KeyData.keySet()) {
            if(!Objects.equals(key, ItemVoidAir)) {
                char charKeys = key.charAt(0);
                Builder = Builder.input(charKeys, Registries.ITEM.get(new Identifier((String) KeyData.getOrDefault(key,"minecraft:air"))));
            }
        }

        respacks.addRecipeAndAdvancement(new Identifier(namespace, id),Builder);

    }
}
