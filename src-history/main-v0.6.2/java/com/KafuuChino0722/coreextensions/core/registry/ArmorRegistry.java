package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PIGLIN_LOVED;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Item.Types.GENERATE;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;
import static net.minecraft.item.ArmorItem.Type.*;

public class ArmorRegistry {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("armor.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("armor.yml");
        load(itemsDataZ);
    }
    public static void load(Map<String, Map<String, Object>> armorsData) {
        if (armorsData != null && armorsData.containsKey("armors")) {
            Map<String, Object> armors = armorsData.get("armors");

            for (Map.Entry<String, Object> entry : armors.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> armorData = (Map<String, Object>) entry.getValue();
                    String name = (String) armorData.getOrDefault("name",(String) entry.getKey());
                    String lang_us = (String) armorData.getOrDefault("lang_us",name);
                    String namespace = (String) armorData.get("namespace");
                    String id = (String) armorData.get("id");
                    String type = (String) armorData.get("types");
                    ArmorItem.Type typeArmor = null;

                    ModelBuilder.Item.Types ModelTypes = null;
                    ModelTypes = GENERATE;

                    String typeArmorStr = type.toUpperCase();
                    typeArmor = switch (typeArmorStr) {
                        case "HELMET" -> HELMET;
                        case "CHESTPLATE" -> CHESTPLATE;
                        case "LEGGINGS" -> LEGGINGS;
                        case "BOOTS" -> BOOTS;
                        default -> HELMET;
                    };

                    Map<String, Object> properties = (Map<String, Object>) armorData.getOrDefault("properties", entry.getValue());

                    String materialType = (String) properties.getOrDefault("material",id);

                    boolean hasGlint = (boolean) properties.getOrDefault("hasGlint",false);

                    Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("RepairIngredient","minecraft:iron_ingot")));
                    int durability = (int) properties.getOrDefault("Durability", 250);
                    int protection = (int) properties.getOrDefault("Protection", 1);
                    double toughness = (double) properties.getOrDefault("toughness", 1.0);
                    double knockbackResistance = (double) properties.getOrDefault("KnockbackResistance", 0.0);

                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                    List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;

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
                            return materialType;
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

                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                        ItemGroupsContents.load(namespace,id,properties);
                    }

                    ArmorItem armorItem = new ArmorItem(material, typeArmor, new FabricItemSettings()) {
                        @Override
                        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                            if (tooltipMsg != null) {
                                for (String Line : tooltipMsg) {
                                    tooltip.add(Text.of("§5§o" + Line));
                                }
                            }
                        }
                        @Override
                        public boolean hasGlint(ItemStack stack) {
                            if(hasGlint) return true;
                            return false;
                        }
                    };

                    try {
                        Registry.register(Registries.ITEM, new Identifier(namespace, id), armorItem);
                    } catch (Exception e) {
                        if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                            Item oldItem = Registries.ITEM.get(new Identifier(namespace,id));
                            setRegistry.set(namespace, id, armorItem);
                            WorldRegistryDataReloading.run(armorItem,oldItem);
                        }
                    }

                    boolean piglinLoved = (boolean) properties.getOrDefault("piglinLoved", false);
                    if (Registries.ITEM.containsId(new Identifier(namespace, id)) && piglinLoved) {
                        TAG_PIGLIN_LOVED.add(new Identifier(namespace, id));
                    }

                    if (generate) {
                        ModelBuilder.Item.getModel(namespace,id,ModelTypes);
                    }

                    provider.add("item."+namespace+"."+id, name);

                    ReturnMessage.ArmorYMLRegister(name,namespace,id);
                }
            }
        }
    }
}