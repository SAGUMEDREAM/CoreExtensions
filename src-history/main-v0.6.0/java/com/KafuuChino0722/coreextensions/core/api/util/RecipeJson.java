package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.core.api.recipes.*;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.Map;
import java.util.Objects;

public class RecipeJson {
    public static void generate(String name,
                                String namespace,
                                String id,
                                String types,
                                String template, String base, String addition,
                                String INPUT, String result,
                                int maxCount,
                                double experience, int cookingTime,
                                String category,
                                Map<String, Object> recipesData
                                ) {

        RecipeCategory categoryType = null;
        CraftingRecipeCategory categoryTypeCrafting = null;
        if (Objects.equals(category, "BUILDING_BLOCKS")) {
            categoryType = RecipeCategory.BUILDING_BLOCKS;
            categoryTypeCrafting = CraftingRecipeCategory.BUILDING;
        } else if (Objects.equals(category, "DECORATIONS")) {
            categoryType = RecipeCategory.DECORATIONS;
            categoryTypeCrafting = CraftingRecipeCategory.BUILDING;
        } else if (Objects.equals(category, "REDSTONE")) {
            categoryType = RecipeCategory.REDSTONE;
            categoryTypeCrafting = CraftingRecipeCategory.REDSTONE;
        } else if (Objects.equals(category, "TRANSPORTATION")) {
            categoryType = RecipeCategory.TRANSPORTATION;
            categoryTypeCrafting = CraftingRecipeCategory.MISC;
        } else if (Objects.equals(category, "TOOLS")) {
            categoryType = RecipeCategory.TOOLS;
            categoryTypeCrafting = CraftingRecipeCategory.EQUIPMENT;
        } else if (Objects.equals(category, "COMBAT")) {
            categoryType = RecipeCategory.COMBAT;
            categoryTypeCrafting = CraftingRecipeCategory.EQUIPMENT;
        } else if (Objects.equals(category, "FOOD")) {
            categoryType = RecipeCategory.FOOD;
            categoryTypeCrafting = CraftingRecipeCategory.MISC;
        } else if (Objects.equals(category, "BREWING")) {
            categoryType = RecipeCategory.BREWING;
            categoryTypeCrafting = CraftingRecipeCategory.MISC;
        } else if (Objects.equals(category, "MISC")) {
            categoryType = RecipeCategory.MISC;
            categoryTypeCrafting = CraftingRecipeCategory.MISC;
        } else {
            categoryType = RecipeCategory.MISC;
            categoryTypeCrafting = CraftingRecipeCategory.MISC;
        }


        if (Objects.equals(types, "crafting_shaped") || Objects.equals(types, "CRAFTING_SHAPED") || Objects.equals(types, "craftingshaped") || Objects.equals(types, "CRAFTINGSHAPED")) {
            Map<String, Object> properties = (Map<String, Object>) recipesData.get("properties");
            Map<String, Object> pattern = (Map<String, Object>) properties.get("pattern");
            //int SizeX = pattern.containsKey("SizeX") ? (int) pattern.get("SizeX") : 3;
            //int SizeY = pattern.containsKey("SizeY") ? (int) pattern.get("SizeY") : 3;
            String topRowPattern = pattern.containsKey("topRowPattern") ? (String) pattern.get("topRowPattern") : "   ";
            String middleRowPattern = pattern.containsKey("middleRowPattern") ? (String) pattern.get("middleRowPattern") : " A ";
            String bottomRowPattern = pattern.containsKey("bottomRowPattern") ? (String) pattern.get("bottomRowPattern") : "   ";
            //Info.create(topRowPattern);Info.create(middleRowPattern);Info.create(bottomRowPattern);

            CraftingShaped.generate(name,namespace,id,types,topRowPattern,middleRowPattern,bottomRowPattern,result,maxCount,categoryType,categoryTypeCrafting,properties);
            //CraftingShapedOld.generate(name,namespace,id,types,SizeX,SizeY,topRowPattern,middleRowPattern,bottomRowPattern,A,B,C,D,E,F,G,H,I,result,maxCount,categoryType,categoryTypeCrafting);
        } else if (Objects.equals(types, "crafting_shapeless") || Objects.equals(types, "CRAFTING_SHAPELESS") || Objects.equals(types, "craftingshapeless") || Objects.equals(types, "CRAFTINGSHAPELESS")) {
            Map<String, Object> properties = (Map<String, Object>) recipesData.getOrDefault("properties",null);
            CraftingShapeless.generate(name,namespace,id,types,result,maxCount,categoryType,categoryTypeCrafting,properties);
            //CraftingShapelessOld.generate(name,namespace,id,types,A,B,C,D,E,F,G,H,I,result,maxCount,categoryType,categoryTypeCrafting);
        } else if (Objects.equals(types, "smelting") || Objects.equals(types, "SMELTING")) {
            Smelting.generate(name,namespace,id,INPUT,result,categoryType,experience,cookingTime);
        } else if (Objects.equals(types, "smoking") || Objects.equals(types, "SMOKING")) {
            Smoking.generate(name,namespace,id,INPUT,result,categoryType,experience,cookingTime);
        } else if (Objects.equals(types, "blasting") || Objects.equals(types, "BLASTING")) {
            Blasting.generate(name,namespace,id,INPUT,result,categoryType,experience,cookingTime);
        } else if (Objects.equals(types, "campfire_cooking") || Objects.equals(types, "CAMPFIRE_COOKING")) {
            CampfireCooking.generate(name,namespace,id,INPUT,result,categoryType,experience,cookingTime);
        } else if (Objects.equals(types, "stonecutting") || Objects.equals(types, "STONECUTTING") || Objects.equals(types, "stone_cutting") || Objects.equals(types, "STONE_CUTTING")) {
            StoneCutting.generate(name,namespace,id,INPUT,result,maxCount,categoryType);
        } else if (Objects.equals(types, "transform") || Objects.equals(types, "TRANSFORM")) {
            TransformRecipe.generate(name, namespace, id, types, template, base, addition,  result, maxCount, categoryType, categoryTypeCrafting);
        } else if (Objects.equals(types, "trim") || Objects.equals(types, "TRIM")) {
            TrimRecipe.generate(name, namespace, id, types, template, base, addition, result, maxCount, categoryType, categoryTypeCrafting);
        }
    }
}
