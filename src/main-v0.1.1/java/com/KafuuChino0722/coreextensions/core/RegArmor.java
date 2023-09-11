package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegArmor {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> armorData = yaml.load(new FileReader(FILE + "armor.yml"));

            if (armorData != null && armorData.containsKey("armors")) {
                Map<String, Object> armors = armorData.get("armors");

                for (Map.Entry<String, Object> entry : armors.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> armorEntry = (Map<String, Object>) entry.getValue();
                        String name = (String) armorEntry.get("name");
                        String namespace = (String) armorEntry.get("namespace");
                        String id = (String) armorEntry.get("id");
                        ArmorItem.Type type = ArmorItem.Type.valueOf((String) armorEntry.get("type"));
                        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) armorEntry.get("RepairIngredient")));
                        int durability = (int) armorEntry.get("Durability");
                        int protection = (int) armorEntry.get("Protection");
                        double toughness = (Double) armorEntry.get("toughness");
                        double knockbackResistance = (double) armorEntry.get("KnockbackResistance");

                        ArmorMaterial material = new ArmorMaterial() {
                            @Override
                            public int getDurability(ArmorItem.Type type) {
                                return durability;
                            }

                            @Override
                            public int getProtection(ArmorItem.Type type) {
                                return protection;
                            }

                            @Override
                            public int getEnchantability() {
                                return 10; // 适当的附魔值
                            }

                            @Override
                            public SoundEvent getEquipSound() {
                                return SoundEvents.ITEM_ARMOR_EQUIP_GENERIC;
                            }

                            @Override
                            public Ingredient getRepairIngredient() {
                                return Ingredient.ofItems(repairMaterialItem);
                            }

                            @Override
                            public String getName() {
                                return namespace + ":" + id;
                            }

                            @Override
                            public float getToughness() {
                                return (float) toughness;
                            }

                            @Override
                            public float getKnockbackResistance() {
                                return (float) knockbackResistance;
                            }
                        };

                        ArmorItem armorItem = new ArmorItem(material, type, new FabricItemSettings());
                        Main.LOGGER.info("ItemArmor " + name + "<->" + namespace + ":" + id + " registered!");
                        Registry.register(Registries.ITEM, new Identifier(namespace, id), armorItem);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
