package com.KafuuChino0722.coreextensions.core.api.item;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.item.CustomSwordItem;
import com.KafuuChino0722.coreextensions.util.ModToolMaterials;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.Map;
import java.util.Objects;

public class Sword {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> itemData, Map<String, Object> properties, String level, boolean generate){
        double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 3.0;
        double attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : -2.4;
        int durability = properties.containsKey("durability") ? (int) properties.get("durability") : 250;
        String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";
        String tooltipMsg = properties.containsKey("tooltipMsg") ? (String) properties.get("tooltipMsg") : null;

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

        ToolMaterial material;
        if (Objects.equals(level, "0") || Objects.equals(level, "WOOD") || Objects.equals(level, "GOLD") || Objects.equals(level, "wood") || Objects.equals(level, "gold")) {
            material = ToolMaterials.WOOD;
        } else if (Objects.equals(level, "1") || Objects.equals(level, "stone") || Objects.equals(level, "STONE")) {
            material = ToolMaterials.STONE;
        } else if (Objects.equals(level, "2") || Objects.equals(level, "iron") || Objects.equals(level, "IRON")) {
            material = ToolMaterials.IRON;
        } else if (Objects.equals(level, "3") || Objects.equals(level, "diamond") || Objects.equals(level, "DIAMOND")) {
            material = ToolMaterials.DIAMOND;
        } else if (Objects.equals(level, "4") || Objects.equals(level, "netherite") || Objects.equals(level, "NETHERITE")) {
            material = ToolMaterials.NETHERITE;
        } else if(level.equalsIgnoreCase("CUSTOM")) {
            material = ModToolMaterials.CUSTOM;
        } else {
            material = ToolMaterials.IRON;
        }

        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.get("repairMaterial")));
        if (repairMaterialItem instanceof ToolItem) {
            material = ((ToolItem) repairMaterialItem).getMaterial();
        }

        ToolItem Sword = new CustomSwordItem(material, (int) attackDamage, (float) attackSpeed, new FabricItemSettings().maxDamage(durability).rarity(rarityType), tooltipMsg);

        try {
            registerItem(namespace, id, Sword);
        } catch (Exception e) {
            setRegistry.set(namespace, id, Sword);
        }

        if(FabricLoader.getInstance().isModLoaded("polymc")) {
            Map<String, Object> polyinfo = properties.containsKey("polyinfo") ? (Map<String, Object>) properties.get("polyinfo"):(Map<String, Object>) itemData.get("properties");
            Item vanillaItem = IdentifierManager.getItem((String) polyinfo.getOrDefault("vanilla","minecraft:iron_sword"));
            PolyMcLoader.loadTool.sword(Sword,vanillaItem);
        }

        String type = "HANDHELD";
        if(generate) {
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
