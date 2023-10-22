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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Ball {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> itemData, Map<String, Object> properties, boolean generate){
        String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";
        List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;

        Rarity rarityType = null;
        if (Objects.equals(rarity, "COMMON") || Objects.equals(rarity, "common")) {
            rarityType = Rarity.COMMON;
        } else if (Objects.equals(rarity, "UNCOMMON") || Objects.equals(rarity, "uncommon")) {
            rarityType = Rarity.UNCOMMON;
        } else if (Objects.equals(rarity, "RARE") || Objects.equals(rarity, "rare")) {
            rarityType = Rarity.RARE;
        } else if (Objects.equals(rarity, "EPIC") || Objects.equals(rarity, "epic")) {
            rarityType = Rarity.EPIC;
        } else {
            rarityType = Rarity.COMMON;
        }

        FabricItemSettings ItemSettings = new FabricItemSettings().maxCount(maxCount).rarity(rarityType);

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

        Item item = new SnowballItem(ItemSettings){
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
            registerItem(namespace, id, item);
        } catch (Exception e) {
            setRegistry.set(namespace,id,item);
        }

        if(FabricLoader.getInstance().isModLoaded("polymc")) {
            Map<String, Object> polyinfo = properties.containsKey("polyinfo")?(Map<String, Object>) properties.get("polyinfo"):(Map<String, Object>) itemData.get("properties");
            Item vanillaItem = IdentifierManager.getItem((String) polyinfo.getOrDefault("vanilla","minecraft:stick"));
            PolyMcLoader.loadItem.snowball(item,vanillaItem);
        }

        if (generate) {
            Models.generate(namespace, id, "ITEM");
        }

        Tags.Item.generateTags(namespace,id,properties);
        ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}
