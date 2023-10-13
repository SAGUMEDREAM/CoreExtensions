package com.LoneDev.itemsadder.api;

import com.KafuuChino0722.coreextensions.block.CropBlocks7;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import com.LoneDev.itemsadder.item.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static net.minecraft.item.ArmorItem.Type.*;

public class CustomItems {
    public static final String[] strArrayFood = {"apple","golden_apple","enchanted_golden_apple","melon_slice","sweet_berries","glow_berries","chorus_fruit","carrot","golden_carrot","potato","baked_potato","poisonous_potato","beetroot","dried_kelp","beef","cooked_beef","porkchop","cooked_porkchop","mutton","cooked_mutton","chicken","cooked_chicken","rabbit","cooked_rabbit","cod","cooked_cod","salmon","cooked_salmon","tropical_fish","pufferfish","bread","cookie","cake","pumpkin_pie","rotten_flesh","spider_eye","mushroom_stew","beetroot_soup","rabbit_stew","suspicious_stew"};

    public static final String[] strArrayCropBlocks = {"bamboo","beetroot","carrots","cocoa","kelp_plant","nether_wart","potatoes","sea_pickle","sugar_cane","sweet_berries","wheat"};

    public static final String[] strArrayStew = {"mushroom_stew","beetroot_stew","rabbit_stew","suspicious_stew"};

    public static final String[] strArrayBlock = {"REAL_NOTE","NOTE","TILE"};

    public static final String[] strArrayTransParentBlock = {"REAL_TRANSPARENT","REAL_WIRE"};

    public static final String[] strArrayFire = {"FIRE"};

    public static String types;
    public static String cropTypes = "only";
    public static Object crop_block;

    public static String display_name;
    public static String id;
    public static boolean enabled;
    public static List<String> lore = null;
    public static String[] loreArray;
    public static int maxCount = 64;
    public static boolean generate;
    public static String texture;
    public static String material;
    public static String model_path;
    public static Object attackDamage;
    public static Object attackSpeed;
    public static ToolMaterial toolMaterial;
    public static Object amount;
    public static Object saturation;

    public static Object armor = 0;
    public static Object armorToughness = 0;
    public static Object knockbackResistance = 0;
    public static Item repairMaterialItem;

    public static String blockType;

    public static ArmorItem.Type typeArmor;


    public static FoodComponent foodComponent;
    public static FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder();

    public static int durability = 200;

    public static FabricItemSettings itemSetting = new FabricItemSettings();
    public static Item Item = new Item(itemSetting);

    public void load(String namespace, Map<String, Object> DataAll) {

        if(DataAll.containsKey("items")) {
            try {
                Map<String, Object> items = (Map<String, Object>) DataAll.get("items");

                if (items != null) {
                    for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                        Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();

                        if(itemData.containsKey("display_name")) {
                            display_name = (String) itemData.get("display_name");
                        } else {
                            display_name = itemEntry.getKey().toLowerCase();
                        }

                        if(itemData.containsKey("id")) {
                            id = (String) itemData.get("id");
                        } else {
                            id = itemEntry.getKey().toLowerCase().replaceAll(" ", "_").replaceAll("[^a-zA-Z0-9 ]", "");

                        }

                        if(itemData.containsKey("maxCount")) {
                            maxCount = (int) itemData.get("maxCount");
                        } else {
                            maxCount = 64;
                        }
                        itemSetting = itemSetting.maxCount(maxCount);

                        if(itemData.containsKey("enabled")) {
                            enabled = (boolean) itemData.get("enabled");
                        } else {
                            enabled = true;
                        }

                        if(itemData.containsKey("lore")) {
                            List<String> lore = (List<String>) itemData.get("lore");
                            loreArray = lore.toArray(new String[lore.size()]);
                        } else {
                            lore = null;
                        }


                        if(itemData.containsKey("resource")) {
                            Map<String, Object> resourcekey = (Map<String, Object>) itemData.get("resource");

                            if(resourcekey.containsKey("generate")) {
                                generate = (boolean) resourcekey.get("generate");
                            } else {
                                generate = true;
                            }

                            if(resourcekey.containsKey("material")) {
                                material = (String) resourcekey.get("material");

                                if(material.toLowerCase().contains("wood".toLowerCase())) {
                                    toolMaterial = ToolMaterials.WOOD;
                                }
                                if(material.toLowerCase().contains("stone".toLowerCase())) {
                                    toolMaterial = ToolMaterials.STONE;
                                }
                                if(material.toLowerCase().contains("iron".toLowerCase())) {
                                    toolMaterial = ToolMaterials.IRON;
                                }
                                if(material.toLowerCase().contains("gold".toLowerCase())) {
                                    toolMaterial = ToolMaterials.GOLD;
                                }
                                if(material.toLowerCase().contains("diamond".toLowerCase())) {
                                    toolMaterial = ToolMaterials.DIAMOND;
                                }
                                if(material.toLowerCase().contains("netherite".toLowerCase())) {
                                    toolMaterial = ToolMaterials.NETHERITE;
                                }

                                String finalMaterial = material;
                                if(Arrays.stream(strArrayFood).anyMatch(s -> s.toLowerCase().contains(finalMaterial.toLowerCase()))) {
                                    types = "food";
                                }

                                if(Arrays.stream(strArrayStew).anyMatch(s -> s.toLowerCase().contains(finalMaterial.toLowerCase()))) {
                                    types = "stew";
                                }

                                if(material.toLowerCase().contains("snowball".toLowerCase())) {
                                    types = "snowball";
                                }

                                if(material.toLowerCase().contains("sword".toLowerCase())) {
                                    types = "sword";
                                }

                                if(material.toLowerCase().contains("pickaxe".toLowerCase())) {
                                    types = "pickaxe";
                                }

                                if(material.toLowerCase().contains("axe".toLowerCase())) {
                                    types = "axe";
                                }

                                if(material.toLowerCase().contains("shovel".toLowerCase())) {
                                    types = "shovel";
                                }

                                if(material.toLowerCase().contains("hoe".toLowerCase())) {
                                    types = "hoe";
                                }

                                if(material.toLowerCase().contains("seeds")||
                                   material.toLowerCase().contains("carrot")||
                                   material.toLowerCase().contains("potato")) {
                                    types = "seeds";
                                }

                            } else {
                                material = "item";
                            }

                            if(resourcekey.containsKey("model_path")) {
                                model_path = (String) resourcekey.get("model_path");
                            }

                            if(resourcekey.containsKey("texture")) {
                                texture = null;
                            }

                        }

                        if(itemData.containsKey("durability")) {
                            Map<String, Object> durabilitykey = (Map<String, Object>) itemData.get("durability");
                            if(durabilitykey.containsKey("durability")) {
                                durability = (int) durabilitykey.get("max_custom_durability");
                            } else {
                                durability = 200;
                            }
                            itemSetting = itemSetting.maxDamage(durability);
                        }

                        if(itemData.containsKey("attribute_modifiers")) {
                            Map<String, Object> attributekey = (Map<String, Object>) itemData.get("attribute_modifiers");
                            if(attributekey.containsKey("mainhand")) {
                                Map<String, Object> mainhandKey = (Map<String, Object>) attributekey.get("mainhand");
                                if(mainhandKey.containsKey("attackDamage")) {
                                    attackDamage = mainhandKey.get("attackDamage");
                                    attackDamage = (float) attackDamage;
                                }

                                if(mainhandKey.containsKey("attackSpeed")) {
                                    attackSpeed = mainhandKey.get("attackSpeed");
                                    attackSpeed = (float) attackSpeed;
                                }

                            }
                            if(attributekey.containsKey("head")) {
                                Map<String, Object> headKey = (Map<String, Object>) attributekey.get("head");
                                if(headKey.containsKey("armor")) {
                                    armor = headKey.get("armor");
                                    armor = (int) armor;
                                }
                                if(headKey.containsKey("armorToughness")) {
                                    armorToughness = headKey.get("armorToughness");
                                    armorToughness = (int) armorToughness;
                                }
                                if(headKey.containsKey("ingredient")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) headKey.get("ingredient")));
                                } else if(headKey.containsKey("repairMaterialItem")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) headKey.get("repairMaterialItem")));
                                } else {
                                    repairMaterialItem = Items.IRON_INGOT;
                                }
                            }
                            if(attributekey.containsKey("chest")) {
                                Map<String, Object> chestKey = (Map<String, Object>) attributekey.get("chest");
                                if(chestKey.containsKey("armor")) {
                                    armor = chestKey.get("armor");
                                    armor = (int) armor;
                                }
                                if(chestKey.containsKey("armorToughness")) {
                                    armorToughness = chestKey.get("armorToughness");
                                    armorToughness = (int) armorToughness;
                                }
                                if(chestKey.containsKey("ingredient")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) chestKey.get("ingredient")));
                                } else if(chestKey.containsKey("repairMaterialItem")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) chestKey.get("repairMaterialItem")));
                                } else {
                                    repairMaterialItem = Items.IRON_INGOT;
                                }
                            }
                            if(attributekey.containsKey("legs")) {
                                Map<String, Object> legKey = (Map<String, Object>) attributekey.get("legs");
                                if(legKey.containsKey("armor")) {
                                    armor = legKey.get("armor");
                                    armor = (int) armor;
                                }
                                if(legKey.containsKey("armorToughness")) {
                                    armorToughness = legKey.get("armorToughness");
                                    armorToughness = (int) armorToughness;
                                }
                                if(legKey.containsKey("ingredient")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) legKey.get("ingredient")));
                                } else if(legKey.containsKey("repairMaterialItem")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) legKey.get("repairMaterialItem")));
                                } else {
                                    repairMaterialItem = Items.IRON_INGOT;
                                }
                            }
                            if(attributekey.containsKey("feet")) {
                                Map<String, Object> feetKey = (Map<String, Object>) attributekey.get("feet");
                                if(feetKey.containsKey("armor")) {
                                    armor = feetKey.get("armor");
                                    armor = (int) armor;
                                }
                                if(feetKey.containsKey("armorToughness")) {
                                    armorToughness = feetKey.get("armorToughness");
                                    armorToughness = (int) armorToughness;
                                }
                                if(feetKey.containsKey("ingredient")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) feetKey.get("ingredient")));
                                } else if(feetKey.containsKey("repairMaterialItem")) {
                                    repairMaterialItem = Registries.ITEM.get(new Identifier((String) feetKey.get("repairMaterialItem")));
                                } else {
                                    repairMaterialItem = Items.IRON_INGOT;
                                }
                            }
                        }

                        if(itemData.containsKey("events")) {
                            Map<String, Object> eventskey = (Map<String, Object>) itemData.get("events");
                            if(eventskey.containsKey("eat")) {
                                Map<String, Object> eatkey = (Map<String, Object>) eventskey.get("eat");
                                if(eatkey.containsKey("feed")) {
                                    Map<String, Object> feedkey = (Map<String, Object>) eatkey.get("feed");
                                    if(feedkey.containsKey("amount")) {
                                        amount = feedkey.get("amount");
                                        amount = (int) amount;
                                        foodComponentBuilder = foodComponentBuilder.hunger((int) amount);
                                    }
                                    if(feedkey.containsKey("saturation")) {
                                        saturation = feedkey.get("saturation");
                                        saturation = (float) saturation;
                                        foodComponentBuilder = foodComponentBuilder.saturationModifier((float) saturation);

                                    }
                                    if(feedkey.containsKey("isMeat")) {
                                        boolean isMeat = (boolean) feedkey.get("meat");
                                        foodComponentBuilder = foodComponentBuilder.meat();
                                    }

                                }
                            }
                        }

                        if(itemData.containsKey("specific_properties")) {
                            Map<String, Object> specifickey = (Map<String, Object>) itemData.get("specific_properties");
                            if (specifickey.containsKey("seed")) {
                                Map<String, Object> seedkey = (Map<String, Object>) specifickey.get("seed");
                                if (seedkey.containsKey("crop_block")) {
                                    String cropBlock = (String) seedkey.get("crop_block");
                                    if (Registries.BLOCK.containsId(new Identifier(cropBlock))) {
                                        crop_block = Registries.BLOCK.get(new Identifier(cropBlock.toLowerCase()));
                                    }

                                    if (Arrays.stream(strArrayCropBlocks).anyMatch(s -> s.toLowerCase().contains(cropBlock.toLowerCase()))) {
                                        cropTypes = "crop";
                                    } else {
                                        cropTypes = "only";
                                    }

                                }
                            }

                            if(specifickey.containsKey("block")) {
                                    Map<String, Object> blockKey = (Map<String, Object>) specifickey.get("block");
                                    if (blockKey.containsKey("placed_model")) {
                                       Map<String, Object> placedModelKey = (Map<String, Object>) blockKey.get("placed_model");
                                       if(placedModelKey.containsKey("type")) {
                                           String blockTypeStr = (String) placedModelKey.get("type");
                                           if(Arrays.stream(strArrayBlock).anyMatch(s -> s.toLowerCase().contains(blockTypeStr.toLowerCase()))) {
                                               types = "block";
                                               blockType = "cubeAllBlock";
                                           }
                                           if(Arrays.stream(strArrayTransParentBlock).anyMatch(s -> s.toLowerCase().contains(blockTypeStr.toLowerCase()))) {
                                               types = "block";
                                               blockType = "cubeTransParentBlock";
                                           }
                                       }
                                    }
                            }

                            if(specifickey.containsKey("armor")) {
                                Map<String, Object> armorKey = (Map<String, Object>) specifickey.get("armor");
                                if(armorKey.containsKey("slot")) {
                                    String slotStr = (String) armorKey.get("slot");
                                    if(slotStr.equalsIgnoreCase("helmet")||slotStr.equalsIgnoreCase("head")) {
                                        typeArmor = HELMET;
                                        types = "armor";
                                    }
                                    if(slotStr.equalsIgnoreCase("chestplate")||slotStr.equalsIgnoreCase("chest")) {
                                        typeArmor = CHESTPLATE;
                                        types = "armor";
                                    }
                                    if(slotStr.equalsIgnoreCase("leggings")||slotStr.equalsIgnoreCase("legs")) {
                                        typeArmor = LEGGINGS;
                                        types = "armor";
                                    }
                                    if(slotStr.equalsIgnoreCase("boots")||slotStr.equalsIgnoreCase("feet")) {
                                        typeArmor = BOOTS;
                                        types = "armor";
                                    }

                                }
                            }
                        }

                        if(enabled) {
                            if(types.equalsIgnoreCase("item")) {
                                Item RegistryItem = new CustomItem(itemSetting, loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                String type = "ITEM";
                                if (generate) {
                                    Models.generate(namespace, id, type);
                                }
                            }
                            if(types.equalsIgnoreCase("snowball")) {
                                Item RegistryItem = new CustomSnowballItem(itemSetting, loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "ITEM");
                                }
                            }
                            if(types.equalsIgnoreCase("sword")) {
                                Item RegistryItem = new CustomSwordItem(toolMaterial, (int) attackDamage,(float) attackSpeed, itemSetting, loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "HANDHELD");
                                }
                            }
                            if(types.equalsIgnoreCase("pickaxe")) {
                                Item RegistryItem = new CustomPickaxeItem(toolMaterial, (int) attackDamage,(float) attackSpeed, itemSetting, loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "HANDHELD");
                                }
                            }
                            if(types.equalsIgnoreCase("axe")) {
                                Item RegistryItem = new CustomAxeItem(toolMaterial, (float) attackDamage,(float) attackSpeed, itemSetting ,loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "HANDHELD");
                                }
                            }
                            if(types.equalsIgnoreCase("shovel")) {
                                Item RegistryItem = new CustomShovelItem(toolMaterial, (float) attackDamage,(float) attackSpeed, itemSetting, loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "HANDHELD");
                                }
                            }
                            if(types.equalsIgnoreCase("hoe")) {
                                Item RegistryItem = new CustomHoeItem(toolMaterial, (int) attackDamage,(float) attackSpeed, itemSetting, loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "HANDHELD");
                                }
                            }
                            if(types.equalsIgnoreCase("food")) {
                                Item RegistryItem = new CustomItem(itemSetting.food(foodComponentBuilder.build()), loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "ITEM");
                                }
                            }
                            if(types.equalsIgnoreCase("stew")) {
                                Item RegistryItem = new CustomStewItem(itemSetting.food(foodComponentBuilder.build()).maxCount(1), loreArray);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                if (generate) {
                                    Models.generate(namespace, id, "ITEM");
                                }
                            }
                            if(types.equalsIgnoreCase("seeds")) {
                                if(cropTypes.equalsIgnoreCase("crop")){
                                    Item RegistryItem = new AliasedBlockItem(Registries.BLOCK.get(
                                            new Identifier(namespace, id+"_block")),
                                            itemSetting);
                                    FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.WHEAT);
                                    Block block = new CropBlocks7(blockSettings, namespace ,id);
                                    Registry.register(Registries.BLOCK, new Identifier(namespace, id+"_block"), block);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id+"_age"), new BlockItem(block, new FabricItemSettings()));
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id+"_seeds"), RegistryItem);
                                }
                                if(cropTypes.equalsIgnoreCase("item")){
                                    Item RegistryItem = new CustomItem(itemSetting, loreArray);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id), RegistryItem);
                                    if (generate) {
                                        Models.generate(namespace, id, "ITEM");
                                    }
                                }
                            }

                            if(types.equalsIgnoreCase("block")) {
                                new CustomBlocks().register(types,namespace,id,blockType,itemSetting,enabled,itemData);
                            }

                            if(types.equalsIgnoreCase("armor")) {
                                ArmorMaterial armorMaterial = new ArmorMaterial() {
                                    @Override
                                    public int getDurability(ArmorItem.Type type) {
                                        return (int) durability;
                                    }

                                    @Override
                                    public int getProtection(ArmorItem.Type type) {
                                        return (int) armor;
                                    }

                                    @Override
                                    public int getEnchantability() {
                                        return 10;
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
                                        return id;
                                    }

                                    @Override
                                    public float getToughness() {
                                        return (float) armorToughness;
                                    }

                                    @Override
                                    public float getKnockbackResistance() {
                                        return (float) knockbackResistance;
                                    }
                                };
                                ArmorItem armorItem = new ArmorItem(armorMaterial, typeArmor, itemSetting);
                            }

                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
