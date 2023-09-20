package com.KafuuChino0722.coreextensions.core.api.recipes;

import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class CraftingShaped {

    public static final String ItemVoidAir ="minecraft:air";

    public static void generate(String name, String namespace, String id, String types, int SizeX, int SizeY, String topRowPattern, String middleRowPattern, String bottomRowPattern, String A, String B, String C, String D, String E, String F, String G, String H, String I , String result, int maxCount, RecipeCategory category, CraftingRecipeCategory categoryTypeCrafting) {

        if (SizeX==3 && SizeY==2 && //头盔
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "D F")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //胸甲
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A C",
                                        "DEF",
                                        "GHI")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //护腿
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "D F",
                                        "G I")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==2 && //靴子
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A C",
                                        "D F")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==3 && //斧头
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB",
                                        "DE",
                                        " H")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //镐子
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        " E ",
                                        " H ")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==1 && SizeY==3 && //剑或竖直1或铲子
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A",
                                        "D",
                                        "G")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==1 && //水平
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==1 && SizeY==2 && //竖直2
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A",
                                        "D")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==1 && SizeY==2 && //水平2
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //楼梯1
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A  ",
                                        "DE ",
                                        "GHI")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //楼梯2
                Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("  C",
                                        " EF",
                                        "GHI")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==2 && //2x2缺角1
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" B",
                                        "DE")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==2 && //2x2缺角2
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A ",
                                        "DE")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==2 && //2x2缺角3
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB",
                                        " E")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==2 && //2x2缺角4
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB",
                                        "D ")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==2 && //2x2斜角1
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A ",
                                        " E")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==2 && //2x2斜角2
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" B",
                                        "D ")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==2 && //2x2
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB",
                                        "DE")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==2 && //3x2
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "DEF")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==2 && SizeY==3 && //2x3
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB",
                                        "DE",
                                        "GH")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3斜角1
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A  ",
                                        " E ",
                                        "   I")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3斜角2
                Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("  C",
                                        " E ",
                                        "G  ")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==1 && SizeY==1 && //1x1
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3十字
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" B ",
                                        "DEF",
                                        " H ")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3斜十字
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A C",
                                        " E ",
                                        "G I")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3十字
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" B ",
                                        "DEF",
                                        " H ")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3盾牌或告示牌
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "DEF",
                                        " H ")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3反盾牌或告示牌
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" B ",
                                        "DEF",
                                        "GHI")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3弓
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" BC",
                                        "D F",
                                        " HI")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3反弓
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB ",
                                        "D F",
                                        "GH ")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3盾或告示牌左
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB ",
                                        "DEF",
                                        "GH ")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3盾或告示牌右
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" BC",
                                        "DEF",
                                        " HI")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==2 && //3x2桶
                !Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("A C",
                                        " E ")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                //.input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==2 && //3x3中空
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "D F",
                                        "GHI")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                //.input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3缺角1
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" BC",
                                        "DEF",
                                        "GHI")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3缺角2
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB ",
                                        "DEF",
                                        "GHI")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3缺角3
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "DEF",
                                        " HI")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3缺角4
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "DEF",
                                        "GH ")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3缺角5
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("AB ",
                                        "DEF",
                                        " HI")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                //.input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                //.input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3缺角6
                Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns(" BC",
                                        "DEF",
                                        "GH ")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                //.input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //钓竿
                Objects.equals(A, ItemVoidAir)&&Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("  C",
                                        " EF",
                                        "G I")
                                //.input('A',Registries.ITEM.get(new Identifier(A)))
                                //.input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                //.input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                //.input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

                

        } else
        if (SizeX==3 && SizeY==3 && //3x3
                !Objects.equals(A, ItemVoidAir)&&!Objects.equals(B, ItemVoidAir)&&!Objects.equals(C, ItemVoidAir) &&
                !Objects.equals(D, ItemVoidAir)&&!Objects.equals(E, ItemVoidAir)&&!Objects.equals(F, ItemVoidAir) &&
                !Objects.equals(G, ItemVoidAir)&&!Objects.equals(H, ItemVoidAir)&&!Objects.equals(I, ItemVoidAir)
        ) {
            
                
                respacks.addRecipeAndAdvancement(new Identifier(namespace, id),
                        ShapedRecipeJsonBuilder.create(category, Registries.ITEM.get(new Identifier(result)),maxCount)
                                .patterns("ABC",
                                        "DEF",
                                        "GHI")
                                .input('A',Registries.ITEM.get(new Identifier(A)))
                                .input('B', Registries.ITEM.get(new Identifier(B)))
                                .input('C', Registries.ITEM.get(new Identifier(C)))
                                .input('D', Registries.ITEM.get(new Identifier(D)))
                                .input('E', Registries.ITEM.get(new Identifier(E)))
                                .input('F', Registries.ITEM.get(new Identifier(F)))
                                .input('G', Registries.ITEM.get(new Identifier(G)))
                                .input('H', Registries.ITEM.get(new Identifier(H)))
                                .input('I', Registries.ITEM.get(new Identifier(I)))
                                .criterionFromItem(Registries.ITEM.get(new Identifier(result)))
                                .setCustomCraftingCategory(categoryTypeCrafting)
                                .setCustomRecipeCategory("event"));

        }
    }
}
