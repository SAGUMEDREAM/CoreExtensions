package com.LoneDev.itemsadder.api;

import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;
import static com.LoneDev.itemsadder.Main.IaPacks;

public class CustomRecipes {

    public Item ingredient;
    public int exp;
    public int cook_time;
    public Item resultItem;
    public int amount;

    public boolean enabled;
    public String line1 = null;
    public String line2 = null;
    public String line3 = null;
    public ShapedRecipeJsonBuilder recipeBuilder;
    public int maxCount;

    public void load(String namespace, Map<String, Object> DataAll) {

        if(DataAll.containsKey("recipes")) {
            try {
                Map<String, Map<String, Object>> recipes = (Map<String, Map<String, Object>>) DataAll.get("recipes");

                if (recipes.containsKey("cooking")) {
                    Map<String, Object> cookingRecipes = recipes.get("cooking");

                    for (Map.Entry<String, Object> entry : cookingRecipes.entrySet()) {
                        Map<String, Object> recipeData = (Map<String, Object>) entry.getValue();
                        String recipeId = entry.getKey();
                        if(recipeData.containsKey("ingredient")) {
                            String ingredientStr = (String) recipeData.get("ingredient");
                            if(Registries.ITEM.containsId(new Identifier(ingredientStr.toLowerCase()))) {
                                ingredient = Registries.ITEM.get(new Identifier(ingredientStr.toLowerCase()));
                            } else {
                                ingredient = Items.COBBLESTONE;
                            }
                        }

                        if(recipeData.containsKey("exp")) {
                            exp = (int) recipeData.get("exp");
                        } else {
                            exp = 0;
                        }

                        if(recipeData.containsKey("cook_time")) {
                            cook_time = (int) recipeData.get("cook_time");
                        } else {
                            cook_time = 200;
                        }

                        if(recipeData.containsKey("result")) {
                            Map<String, Object> resultKey = (Map<String, Object>) recipeData.get("result");

                            if(resultKey.containsKey("item")) {
                                String resultItemStr = (String) resultKey.get("item");
                                if(Registries.ITEM.containsId(new Identifier(resultItemStr.toLowerCase()))) {
                                    resultItem = Registries.ITEM.get(new Identifier(resultItemStr.toLowerCase()));
                                } else {
                                    resultItem = Items.STONE;
                                }
                            }

                            if(resultKey.containsKey("amount")) {
                                amount = (int) resultKey.get("amount");
                            } else {
                                amount = 1;
                            }

                        } else {
                            resultItem = Items.STONE;
                            amount = 1;
                        }

                        if(recipeData.containsKey("machines")) {
                            List<String> machinesKey = (List<String>) recipeData.get("machines");
                            if (machinesKey.contains("FURNACE")) {
                                IaPacks.addRecipeAndAdvancement(new Identifier(namespace, recipeId+"_furnace"),
                                        CookingRecipeJsonBuilder
                                                .createSmelting(Ingredient.ofItems(ingredient),
                                                        RecipeCategory.MISC,
                                                        resultItem,
                                                        (float) exp,
                                                        cook_time)
                                                .setCustomRecipeCategory("event")
                                                .criterionFromItem(resultItem));
                            }
                            if (machinesKey.contains("BLAST_FURNACE")) {
                                IaPacks.addRecipeAndAdvancement(new Identifier(namespace, recipeId+"_blast"),
                                        CookingRecipeJsonBuilder
                                                .createBlasting(Ingredient.ofItems(ingredient),
                                                        RecipeCategory.MISC,
                                                        resultItem,
                                                        (float) exp,
                                                        cook_time)
                                                .setCustomRecipeCategory("event")
                                                .criterionFromItem(resultItem));
                            }

                            if (machinesKey.contains("SMOKER")) {
                                IaPacks.addRecipeAndAdvancement(new Identifier(namespace, recipeId+"_smoker"),
                                        CookingRecipeJsonBuilder
                                                .createSmoking(Ingredient.ofItems(ingredient),
                                                        RecipeCategory.MISC,
                                                        resultItem,
                                                        (float) exp,
                                                        cook_time)
                                                .setCustomRecipeCategory("event")
                                                .criterionFromItem(resultItem));
                            }
                        }

                    }
                }
                if (recipes.containsKey("crafting_table")) {
                    Map<String, Object> craftingTableRecipes = recipes.get("crafting_table");

                    for (Map.Entry<String, Object> entry : craftingTableRecipes.entrySet()) {
                        Map<String, Object> recipeData = (Map<String, Object>) entry.getValue();
                        String recipeId = entry.getKey();
                        if(recipeData.containsKey("enabled")) {
                            boolean enabledValue = (boolean) recipeData.get("enabled");
                            if(enabledValue) {
                                enabled = true;
                            } else {
                                enabled = false;
                            }
                        } else {
                            enabled = true;
                        }

                        if(recipeData.containsKey("result")) {
                            Map<String, Object> resultKey = (Map<String, Object>) recipeData.get("result");

                            if(resultKey.containsKey("item")) {
                                String resultItemStr = (String) resultKey.get("item");
                                if(Registries.ITEM.containsId(new Identifier(resultItemStr.toLowerCase()))) {
                                    resultItem = Registries.ITEM.get(new Identifier(resultItemStr.toLowerCase()));
                                } else {
                                    resultItem = Items.STONE;
                                }
                            }

                            if(resultKey.containsKey("amount")) {
                                amount = (int) resultKey.get("amount");
                            } else {
                                amount = 1;
                            }

                        } else {
                            resultItem = Items.STONE;
                            amount = 1;
                        }

                        recipeBuilder = ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, resultItem, amount)
                                .criterionFromItem(resultItem)
                                .setCustomCraftingCategory(CraftingRecipeCategory.MISC)
                                .setCustomRecipeCategory("event");

                        if (recipeData != null && recipeData.containsKey("ingredients")) {
                            Map<Character, String> ingredients = (Map<Character, String>) recipeData.get("ingredients");
                            for (Map.Entry<Character, String> entryIng : ingredients.entrySet()) {
                                char key = entryIng.getKey();
                                String value = entryIng.getValue();
                                if(Registries.ITEM.containsId(new Identifier(value))) {
                                    recipeBuilder.input(key, Registries.ITEM.get(new Identifier(value)));
                                }
                            }
                        }

                        if (recipeData != null && recipeData.containsKey("pattern")) {
                            List<String> pattern = (List<String>) recipeData.get("pattern");

                            if (pattern.size() >= 1) {
                                line1 = pattern.get(0);
                            }
                            if (pattern.size() >= 2) {
                                line2 = pattern.get(1);
                            }
                            if (pattern.size() >= 3) {
                                line3 = pattern.get(2);
                            }
                            if(enabled) {
                                if(pattern.size() == 1) {
                                    respacks.addRecipeAndAdvancement(new Identifier(namespace, recipeId), recipeBuilder.patterns(line1));
                                }
                                if(pattern.size() == 2) {
                                    respacks.addRecipeAndAdvancement(new Identifier(namespace, recipeId), recipeBuilder.patterns(line1,line2));
                                }
                                if(pattern.size() >= 3) {
                                    respacks.addRecipeAndAdvancement(new Identifier(namespace, recipeId), recipeBuilder.patterns(line1,line2,line3));
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

}
