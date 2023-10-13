package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.Map;
import java.util.Objects;

public class Shears {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData, Map<String, Object> properties, String level, boolean generate){
        int durability = properties.containsKey("durability") ? (int) properties.get("durability") : 250;
        String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";

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

        ShearsItem Shears = registerPickaxe(namespace, id, durability, rarityType);

        if(FabricLoader.getInstance().isModLoaded("polymc")&&properties.containsKey("polyinfo")) {
            Map<String, Object> polyinfo = (Map<String, Object>) properties.get("polyinfo");
            Item vanillaItem = IdentifierManager.getItem((String) polyinfo.getOrDefault("vanilla","minecraft:stick"));
            PolyMcLoader.loadTool.shears(Shears,vanillaItem);
        }

        String type = "ITEM";
        if (generate) {
            Models.generate(namespace, id, type);
        }

        Tags.Item.generateTags(namespace,id,properties);
        ReturnMessage.ToolYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static ShearsItem registerPickaxe(String namespace, String id, int durability, Rarity rarityType) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new ShearsItem(new FabricItemSettings().maxDamage(durability)));
    }
}
