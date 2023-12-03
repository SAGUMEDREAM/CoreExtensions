package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.item.ElytraItem;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Item.Types.GENERATE;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;
import static net.fabricmc.api.EnvType.CLIENT;

public class ArmorElytraRegistry {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("armorElytra.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("armorElytra.yml");
        load(itemsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> armorsData) {
        if (armorsData != null && armorsData.containsKey("armors")) {
            Map<String, Object> armors = armorsData.get("armors");

            for (Map.Entry<String, Object> entry : armors.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> armorData = (Map<String, Object>) entry.getValue();
                    String name = (String) armorData.get("name");
                    String lang_us = (String) armorData.get("lang_us");
                    String namespace = (String) armorData.get("namespace");
                    String id = (String) armorData.get("id");

                    Map<String, Object> properties = (Map<String, Object>) armorData.get("properties");

                    int durability = properties.containsKey("Durability") ? (int) properties.get("Durability") : 250;
                    Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("RepairIngredient","minecraft:iron_ingot")));
                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                        ItemGroupsContents.load(namespace,id,properties);
                    }

                    Item item = new ElytraItem(new FabricItemSettings().maxDamage(durability).rarity(Rarity.UNCOMMON), repairMaterialItem);
                    try {
                        Registry.register(Registries.ITEM,new Identifier(namespace,id),item);
                    } catch (Exception e) {
                        if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                            setRegistry.set(namespace, id, item);
                        }
                    }

                    if (generate && FabricLoader.getInstance().getEnvironmentType()==CLIENT) {
                        ModelBuilder.Item.getModel(namespace,id,GENERATE);
                    }

                    provider.add("item."+namespace+"."+id, name);

                    if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                        StorageRegistry.add(StorageRegistry.ARMOR,new Identifier(namespace,id));
                        StorageRegistry.add(StorageRegistry.ITEMS,new Identifier(namespace,id));
                    }

                    ReturnMessage.ArmorYMLRegister(name,namespace,id);
                }
            }
        }
    }
}