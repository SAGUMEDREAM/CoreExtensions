package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Food {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData, boolean generate){
        int hunger = (int) itemData.get("hunger");
        double saturationModifier = (double) itemData.get("saturationModifier");
        boolean isMeat = (boolean) itemData.get("meat"); // 获取之前的属性

        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                .hunger((int) hunger)
                .saturationModifier((float) saturationModifier);

        if (isMeat) {
            foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
        }

        FoodComponent foodComponent = foodComponentBuilder.build();
        Item foodItem = new Item(new Item.Settings().food(foodComponent));
        registerItem(namespace, id, foodItem);

        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}
