package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.Map;

public class FoodStewItem {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData, Map<String, Object> properties, boolean generate){

        int hunger = (int) properties.get("hunger");
        double saturationModifier = (double) properties.get("saturationModifier");
        boolean isMeat = (boolean) properties.get("meat");


        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                .hunger((int) hunger)
                .saturationModifier((float) saturationModifier);

        if (isMeat) {
            foodComponentBuilder.meat();
        }

        FoodComponent foodComponent = foodComponentBuilder.build();
        Item foodItem = new StewItem(new Item.Settings().food(foodComponent).maxCount(maxCount));
        registerItem(namespace, id, foodItem);

        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        if(FabricLoader.getInstance().isModLoaded("polymc")) {
            Map<String, Object> polyinfo = properties.containsKey("polyinfo")?(Map<String, Object>) properties.get("polyinfo"):(Map<String, Object>) itemData.get("properties");
            Item vanillaItem = IdentifierManager.getItem((String) polyinfo.getOrDefault("vanilla","minecraft:mushroom_stew"));
            PolyMcLoader.loadItem.stew(foodItem,vanillaItem);
        }

        Tags.Item.generateTags(namespace,id,properties);
        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name), item);
    }

    public static Item registerStewItem(String namespace, String name, int maxCount) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new StewItem(new Item.Settings().maxCount(1).food(FoodComponents.RABBIT_STEW)));
    }

}
