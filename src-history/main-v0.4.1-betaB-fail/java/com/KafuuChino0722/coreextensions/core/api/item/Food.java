package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class Food {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData, Map<String, Object> properties, boolean generate){

        int hunger = (int) properties.get("hunger");
        double saturationModifier = (double) properties.get("saturationModifier");
        boolean isMeat = (boolean) properties.get("meat"); // 获取之前的属性

        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                .hunger((int) hunger)
                .saturationModifier((float) saturationModifier);

        if (isMeat) {
            foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
        }

        FoodComponent foodComponent = foodComponentBuilder.build();
        Item foodItem = new Item(new Item.Settings().food(foodComponent).maxCount(maxCount));
        registerItem(namespace, id, foodItem);

        if(FabricLoader.getInstance().isModLoaded("polymc")) {
            Map<String, Object> polyinfo = properties.containsKey("polyinfo")?(Map<String, Object>) properties.get("polyinfo"):(Map<String, Object>) itemData.get("properties");
            Item vanillaItem = IdentifierManager.getItem((String) polyinfo.getOrDefault("vanilla","minecraft:cookie"));
            PolyMcLoader.loadItem.food(foodItem,vanillaItem);
        }


        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        Tags.Item.generateTags(namespace,id,properties);
        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}
