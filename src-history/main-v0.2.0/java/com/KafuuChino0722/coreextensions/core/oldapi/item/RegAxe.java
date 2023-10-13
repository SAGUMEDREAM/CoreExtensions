package com.KafuuChino0722.coreextensions.core.oldapi_v1.item;

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

public class RegAxe {
    public static final String FILE = Reference.File;

    private static ToolItem register(String namespace, String name, float attackDamage, float attackSpeed, int durability, ToolMaterials material) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, name),
                new AxeItem(material, (float) attackDamage, attackSpeed, new FabricItemSettings().maxDamage(durability)));
    }

    public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> ValueData = yaml.load(new FileReader(FILE + "axe.yml"));

            if (ValueData != null && ValueData.containsKey("axes")) {
                Map<String, Object> swords = ValueData.get("axes");

                for (Map.Entry<String, Object> entry : swords.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> Data = (Map<String, Object>) entry.getValue();
                        String name = (String) Data.get("name");
                        String namespace = (String) Data.get("namespace");
                        String id = (String) Data.get("id");
                        double attackDamage = (double) Data.get("attackDamage");
                        double attackSpeed = (double) Data.get("attackSpeed");
                        int durability = (int) Data.get("durability"); // Parse durability attribute


                        ToolMaterial material = ToolMaterials.IRON; // Default material

                        String repairMaterialId = (String) Data.get("repairMaterial");
                        Item repairMaterialItem = Registries.ITEM.get(new Identifier(repairMaterialId));
                        if (repairMaterialItem instanceof ToolItem) {
                            material = ((ToolItem) repairMaterialItem).getMaterial();
                        }
                        Main.LOGGER.info("Item " + name + "<->" + namespace + ":" + id + " registered!");
                        ToolItem axe = register(namespace, id, (float) attackDamage, (float) attackSpeed, durability, (ToolMaterials) material);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
