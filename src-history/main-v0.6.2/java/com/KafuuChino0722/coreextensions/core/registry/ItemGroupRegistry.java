package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class ItemGroupRegistry {
    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("itemGroups.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("itemGroups.yml");
        load(itemsDataZ);
    }
    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("groups")) {
            Map<String, Object> items = itemsData.get("groups");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {

                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String name = (String) itemData.get("name");
                    String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                    String id = (String) itemData.get("id");
                    String icon = (String) itemData.get("icon");
                    try {
                        Registry.register(Registries.ITEM_GROUP,
                                new Identifier(namespace, id),
                                FabricItemGroup.builder()
                                        .icon(() -> new ItemStack(Registries.ITEM.get(new Identifier(icon))))
                                        .displayName(Text.translatable("itemGroup."+namespace+"."+id))
                                        .build()
                        );
                        ReturnMessage.FuelYMLRegister(name, namespace, id); //returnMessage
                    } catch (Exception e) {

                    }

                    provider.add("itemGroup." +namespace +"."+id, name);

                }
            }
        }
    }
}