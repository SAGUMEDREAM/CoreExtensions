package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static net.minecraft.item.ArmorItem.Type.*;

public class RegArmorsSRC {
    public static final String FILE = Reference.File;

    public static Item registerElytraItem(String namespace, String id, int durability) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new ElytraItem(new FabricItemSettings().maxDamage(durability).rarity(Rarity.UNCOMMON)));
    }

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE);

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/armor.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> armorData = yaml.load(new FileReader(FILE + "armor.yml"));

                            if (armorData != null && armorData.containsKey("armors")) {
                                Map<String, Object> armors = armorData.get("armors");

                                for (Map.Entry<String, Object> entry : armors.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> armorEntry = (Map<String, Object>) entry.getValue();
                                        String name = (String) armorEntry.get("name");
                                        String lang_us = (String) armorEntry.get("lang_us");
                                        String namespace = (String) armorEntry.get("namespace");
                                        String id = (String) armorEntry.get("id");
                                        ArmorItem.Type type = ArmorItem.Type.valueOf((String) armorEntry.get("types"));
                                        String typeString = (String) armorEntry.get("types");
                                        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) armorEntry.get("RepairIngredient")));
                                        int durability = (int) armorEntry.get("Durability");
                                        int protection = (int) armorEntry.get("Protection");
                                        double toughness = (Double) armorEntry.get("toughness");
                                        double knockbackResistance = (double) armorEntry.get("KnockbackResistance");

                                        boolean generate = armorEntry.containsKey("generate") ? (boolean) armorEntry.get("generate") : true;

                                        if (typeString == "ELYTRA") {

                                            registerElytraItem(namespace, id, durability);
                                            String typee = "ELYTRA";
                                            if (generate) {
                                                Models.generate(namespace, id, typee);
                                            }

                                        }
                                        if (type == HELMET || type == CHESTPLATE || type == LEGGINGS || type == BOOTS) {

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
                                                    return 10; //适当的附魔值
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

                                            Registry.register(Registries.ITEM, new Identifier(namespace, id), armorItem);

                                            String typee = "ITEM";
                                            if (generate) {
                                                Models.generate(namespace, id, typee);
                                            }

                                        }

                                        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_itemLang"));

                                        RRPCallback.BEFORE_VANILLA.register(b -> {
                                            packs.clearResources();
                                            packs.addLang(new Identifier(namespace, "en_us"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, lang_us));
                                            packs.addLang(new Identifier(namespace, "zh_cn"), LanguageProvider.create()
                                                    .add("item."+namespace+"."+id, name));
                                            b.add(packs);
                                        });

                                        ReturnMessage.ArmorYMLRegister(name,namespace,id);
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
