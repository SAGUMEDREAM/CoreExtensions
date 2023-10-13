package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.util.Objects;

public class CraftingShapeless {
    public static void generate(String name, String namespace, String id, String types, String A, String B, String C, String D, String E, String F, String G, String H, String I ,String result,int maxCount, RecipeCategory category, CraftingRecipeCategory categoryTypeCrafting) {

        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_recipe"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            if (Objects.equals(B, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else if (Objects.equals(C, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else if (Objects.equals(D, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .input(Registries.ITEM.get(new Identifier(C)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else if (Objects.equals(E, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .input(Registries.ITEM.get(new Identifier(C)), 1)
                                .input(Registries.ITEM.get(new Identifier(D)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else if (Objects.equals(F, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .input(Registries.ITEM.get(new Identifier(C)), 1)
                                .input(Registries.ITEM.get(new Identifier(D)), 1)
                                .input(Registries.ITEM.get(new Identifier(E)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else if (Objects.equals(G, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .input(Registries.ITEM.get(new Identifier(C)), 1)
                                .input(Registries.ITEM.get(new Identifier(D)), 1)
                                .input(Registries.ITEM.get(new Identifier(E)), 1)
                                .input(Registries.ITEM.get(new Identifier(F)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else if (Objects.equals(H, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .input(Registries.ITEM.get(new Identifier(C)), 1)
                                .input(Registries.ITEM.get(new Identifier(D)), 1)
                                .input(Registries.ITEM.get(new Identifier(E)), 1)
                                .input(Registries.ITEM.get(new Identifier(F)), 1)
                                .input(Registries.ITEM.get(new Identifier(G)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else if (Objects.equals(I, "minecraft:air")) {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .input(Registries.ITEM.get(new Identifier(C)), 1)
                                .input(Registries.ITEM.get(new Identifier(D)), 1)
                                .input(Registries.ITEM.get(new Identifier(E)), 1)
                                .input(Registries.ITEM.get(new Identifier(F)), 1)
                                .input(Registries.ITEM.get(new Identifier(G)), 1)
                                .input(Registries.ITEM.get(new Identifier(H)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            } else {
                packs.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapelessRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .input(Registries.ITEM.get(new Identifier(A)), 1)
                                .input(Registries.ITEM.get(new Identifier(B)), 1)
                                .input(Registries.ITEM.get(new Identifier(C)), 1)
                                .input(Registries.ITEM.get(new Identifier(D)), 1)
                                .input(Registries.ITEM.get(new Identifier(E)), 1)
                                .input(Registries.ITEM.get(new Identifier(F)), 1)
                                .input(Registries.ITEM.get(new Identifier(G)), 1)
                                .input(Registries.ITEM.get(new Identifier(H)), 1)
                                .input(Registries.ITEM.get(new Identifier(I)), 1)
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));
            }
            b.add(packs);
        });
    }
}
