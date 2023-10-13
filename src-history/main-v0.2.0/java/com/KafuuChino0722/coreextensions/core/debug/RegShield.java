package com.KafuuChino0722.coreextensions.core.debug;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegShield {
    public static final String FILE = Reference.File;
    private static Item register(String namespace, String name, int durability) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new ShieldItem(new FabricItemSettings().maxDamage(durability)));
    }

    public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> ValueData = yaml.load(new FileReader(FILE +"sword.yml"));

            if (ValueData != null && ValueData.containsKey("shield")) {
                Map<String, Object> swords = ValueData.get("shield");

                for (Map.Entry<String, Object> entry : swords.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> Data = (Map<String, Object>) entry.getValue();
                        String name = (String) Data.get("name");
                        String namespace = (String) Data.get("namespace");
                        String id = (String) Data.get("id");
                        int durability = (int) Data.get("durability"); // Parse durability attribute

                        String repairMaterialId = (String) Data.get("repairMaterial");
                        Main.LOGGER.info("Item " + name + "<->" + namespace + ":" + id + " registered!");
                        ToolItem sword = (ToolItem) register(namespace, id, durability);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
