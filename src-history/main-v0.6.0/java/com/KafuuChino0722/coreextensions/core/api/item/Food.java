package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_FOX_FOOD;

public class Food {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData, Map<String, Object> properties, boolean generate){
        List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;

        int hunger = (int) properties.get("hunger");
        double saturationModifier = (double) properties.get("saturationModifier");
        boolean isMeat = (boolean) properties.getOrDefault("isMeat",
                (boolean) properties.getOrDefault("meat",false));
        boolean isFoxFood = (boolean) properties.getOrDefault("isFoxFood",
                (boolean) properties.getOrDefault("fox",false));

        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                .hunger((int) hunger)
                .saturationModifier((float) saturationModifier);

        if (isMeat) {
            foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
        }

        FoodComponent foodComponent = foodComponentBuilder.build();

        FabricItemSettings ItemSettings = new FabricItemSettings().food(foodComponent).maxCount(maxCount);

        if(properties.containsKey("fireproof")) {
            boolean fireproof = (boolean) properties.getOrDefault("fireproof",false);
            if(fireproof) {
                ItemSettings = ItemSettings.fireproof();
            }
        }

        if(properties.containsKey("recipeRemainder")) {
            String recipeRemainder = (String) properties.getOrDefault("recipeRemainder","minecraft:stone");
            if(recipeRemainder!=null) {
                ItemSettings = ItemSettings.recipeRemainder(Registries.ITEM.get(new Identifier(recipeRemainder)));
            }
        }

        Item foodItem = new Item(ItemSettings) {
            @Override
            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                if (tooltipMsg != null) {
                    for (String Line : tooltipMsg) {
                        tooltip.add(Text.of("§5§o"+Line));
                    }
                }
            }
        };

        try {
            registerItem(namespace, id, foodItem);
        } catch (Exception e) {
            setRegistry.set(namespace,id,foodItem);
        }

        if(FabricLoader.getInstance().isModLoaded("polymc")) {
            Map<String, Object> polyinfo = properties.containsKey("polyinfo")?(Map<String, Object>) properties.get("polyinfo"):(Map<String, Object>) itemData.get("properties");
            Item vanillaItem = IdentifierManager.getItem((String) polyinfo.getOrDefault("vanilla","minecraft:cookie"));
            PolyMcLoader.loadItem.food(foodItem,vanillaItem);
        }


        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        if(isFoxFood) {
            TAG_FOX_FOOD.add(new Identifier(namespace,id));
        }

        Tags.Item.generateTags(namespace,id,properties);
        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}
