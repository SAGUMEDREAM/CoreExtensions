package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.client.BoatLayer;
import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.MethodRarity;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.core.template.Template;
import com.KafuuChino0722.coreextensions.item.ClickItem;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.*;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.registriesCount;
import static net.fabricmc.api.EnvType.CLIENT;

public class ItemRegistry {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("item.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("item.yml");
        load(itemsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("items")) {
            Map<String, Object> items = itemsData.get("items");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();

                    Item ItemType = null;
                    FabricItemSettings ItemSettings = new FabricItemSettings();
                    ModelBuilder.Item.Types ModelTypes = null;
                    boolean error = false;
                    boolean IsTemplate = false;

                    String name = (String) itemData.getOrDefault("name",(String) entry.getKey());

                    String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                    String id = (String) itemData.get("id");
                    String types = (String) itemData.getOrDefault("types","item");
                    int maxCount = (int) itemData.getOrDefault("maxCount",64);

                    Map<String, Object> properties = (Map<String, Object>) itemData.getOrDefault("properties", entry.getValue());

                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                    boolean hasGlint = (boolean) properties.getOrDefault("hasGlint",false);

                    ItemSettings = ItemSettings.maxCount(maxCount);

                    List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;
                    String rarityStr = (String) properties.getOrDefault("rarity","common");
                    ItemSettings = ItemSettings.rarity(MethodRarity.getRarity(rarityStr));

                    boolean fireproof = (boolean) properties.getOrDefault("fireproof", false);
                    if (fireproof) {
                        ItemSettings = ItemSettings.fireproof();
                    }

                    String recipeRemainder = (String) properties.getOrDefault("recipeRemainder", null);
                    if (recipeRemainder != null) {
                        ItemSettings = ItemSettings.recipeRemainder(Registries.ITEM.get(new Identifier(recipeRemainder)));
                    }

                    switch (types.toLowerCase()) {
                        case "item" -> {
                            ItemType = new Item(ItemSettings) {
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
                            ModelTypes = ModelBuilder.Item.Types.GENERATE;
                        }
                        case "clickitem" -> {
                            ItemType = new ClickItem(ItemSettings) {
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
                            ModelTypes = ModelBuilder.Item.Types.GENERATE;
                        }
                        case "ball" -> {
                            ItemType = new SnowballItem(ItemSettings) {
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
                            ModelTypes = ModelBuilder.Item.Types.GENERATE;
                        }
                        case "food" -> {
                            int hunger = (int) properties.getOrDefault("hunger", 0);
                            double saturationModifier = (double) properties.getOrDefault("saturationModifier", 0);
                            boolean isMeat = (boolean) properties.getOrDefault("isMeat",
                                    (boolean) properties.getOrDefault("meat", false));
                            boolean isFoxFood = (boolean) properties.getOrDefault("isFoxFood",
                                    (boolean) properties.getOrDefault("fox", false));

                            FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                                    .hunger((int) hunger)
                                    .saturationModifier((float) saturationModifier);

                            if (isMeat) {
                                foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
                            }

                            FoodComponent foodComponent = foodComponentBuilder.build();

                            ItemSettings = ItemSettings.food(foodComponent);

                            ItemType = new Item(ItemSettings) {
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

                            if (isFoxFood) {
                                TAG_FOX_FOOD.add(new Identifier(namespace, id));
                            }
                            ModelTypes = ModelBuilder.Item.Types.GENERATE;
                        }
                        case "stewitem", "bowl" -> {
                            int hunger = (int) properties.getOrDefault("hunger", 0);
                            double saturationModifier = (double) properties.getOrDefault("saturationModifier", 0);
                            boolean isMeat = (boolean) properties.getOrDefault("isMeat",
                                    (boolean) properties.getOrDefault("meat", false));
                            boolean isFoxFood = (boolean) properties.getOrDefault("isFoxFood",
                                    (boolean) properties.getOrDefault("fox", false));

                            FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                                    .hunger((int) hunger)
                                    .saturationModifier((float) saturationModifier);

                            if (isMeat) {
                                foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
                            }

                            FoodComponent foodComponent = foodComponentBuilder.build();

                            ItemSettings = ItemSettings.food(foodComponent);

                            ItemType = new StewItem(ItemSettings) {
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

                            if (isFoxFood) {
                                TAG_FOX_FOOD.add(new Identifier(namespace, id));
                            }
                            ModelTypes = ModelBuilder.Item.Types.GENERATE;
                        }
                        case "boat" -> {
                            if(registriesCount<1) {
                                Identifier BOAT_ID = new Identifier(namespace, id);
                                Identifier CHEST_BOAT_ID = new Identifier(namespace, "chest_" + id);

                                RegistryKey<TerraformBoatType> BOAT_KEY = TerraformBoatTypeRegistry.createKey(BOAT_ID);

                                Item CHESTNUT_BOAT = TerraformBoatItemHelper.registerBoatItem(BOAT_ID, BOAT_KEY, false);
                                Item CHESTNUT_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(CHEST_BOAT_ID, BOAT_KEY, true);

                                TerraformBoatType chestnutBoat = new TerraformBoatType.Builder()
                                        .item(CHESTNUT_BOAT)
                                        .chestItem(CHESTNUT_CHEST_BOAT)
                                        .planks(Blocks.OAK_PLANKS.asItem())
                                        .build();

                                Registry.register(TerraformBoatTypeRegistry.INSTANCE, BOAT_KEY, chestnutBoat);
                                if(FabricLoader.getInstance().getEnvironmentType() == CLIENT) BoatLayer.register(BOAT_ID);
                                provider.add("item." +namespace +"."+"chest_"+id, name);
                                ItemGroupsContents.load(namespace,id,properties);
                                ModelTypes = ModelBuilder.Item.Types.BOAT;
                            }
                        }
                        case "template" -> {
                            String TemplateIdentifier = properties.containsKey("template") ? (String) properties.get("template") : null;
                            Template.Item(namespace,id,TemplateIdentifier);
                            IsTemplate = true;
                        }
                        default -> error = true;
                    }


                    if(!error){

                        if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                            ItemGroupsContents.load(namespace,id,properties);
                        }

                        if(ItemType!=null) {
                            try {
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), ItemType);
                                ReturnMessage.ItemYMLRegister(name, namespace, id);
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Item oldItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, ItemType);
                                    WorldRegistryDataReloading.run(ItemType,oldItem);
                                    ReturnMessage.ItemYMLRegister(name, namespace, id);
                                }
                            }
                            if(types.equalsIgnoreCase("food")) VillagerInteractionRegistries.registerFood(ItemType,1);
                        }

                        boolean piglinLoved = (boolean) properties.getOrDefault("piglinLoved",false);
                        if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                            if (piglinLoved) {
                                TAG_PIGLIN_LOVED.add(new Identifier(namespace,id));
                            }
                        }
                        if(FabricLoader.getInstance().getEnvironmentType()==CLIENT&&generate) {
                            ModelBuilder.Item.getModel(namespace,id,ModelTypes);
                        }
                        provider.add("item." +namespace +"."+id, name);
                    } else {
                        if(!IsTemplate) {
                            ReturnMessage.ItemYMLTYPEERROR(name, namespace, id);
                        }
                    }
                }
            }
        }
    }
}