package com.KafuuChino0722.coreextensions.core.template;


import com.KafuuChino0722.coreextensions.core.api.*;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.item.ClickItem;
import com.KafuuChino0722.coreextensions.util.ModToolMaterials;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.*;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Item.Types.*;
import static net.fabricmc.api.EnvType.CLIENT;

public class TemplateItem {

    public static void register(String namespace, String id, String TemplateIdentifier) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("template.yml");
        load(namespace, id, TemplateIdentifier, itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("template.yml");
        load(namespace, id, TemplateIdentifier, itemsDataZ);
    }

    public static void load(String namespace, String id, String TemplateIdentifier, Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("items")) {
            Map<String, Object> items = itemsData.get("items");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String name = (String) itemData.getOrDefault("name", (String) entry.getKey());

                    String Identifier = itemData.containsKey("identifier") ? (String) itemData.get("identifier") : null;

                    if (Identifier != null && Identifier.equalsIgnoreCase(TemplateIdentifier)) {
                        Item ItemType = null;
                        FabricItemSettings ItemSettings = new FabricItemSettings();
                        ModelBuilder.Item.Types ModelTypes = null;

                        String types = itemData.containsKey("types") ? (String) itemData.get("types") : "item";

                        int maxCount = itemData.containsKey("maxCount") ? (int) itemData.get("maxCount") : 64;
                        ItemSettings = ItemSettings.maxCount(maxCount);

                        Map<String, Object> properties = itemData.containsKey("properties") ? (Map<String, Object>) itemData.get("properties") : null;

                        List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;

                        boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                        String rarityStr = (String) properties.getOrDefault("rarity", "common");
                        ItemSettings = ItemSettings.rarity(MethodRarity.getRarity(rarityStr));

                        boolean fireproof = (boolean) properties.getOrDefault("fireproof", false);
                        if (fireproof) {
                            ItemSettings = ItemSettings.fireproof();
                        }

                        String recipeRemainder = (String) properties.getOrDefault("recipeRemainder", null);
                        if (recipeRemainder != null) {
                            ItemSettings = ItemSettings.recipeRemainder(Registries.ITEM.get(new Identifier(recipeRemainder)));
                        }

                        String level = properties.containsKey("level") ? (String) properties.get("level") : "IRON";
                        ToolMaterial material = MethodToolMaterial.getToolMaterial(level);

                        Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("repairMaterial", "minecraft:iron_ingot")));
                        if (repairMaterialItem instanceof ToolItem) {
                            material = ((ToolItem) repairMaterialItem).getMaterial();
                        }

                        int durability = properties.containsKey("durability") ? (int) properties.get("durability") : 250;

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
                                };
                                ModelTypes = GENERATE;
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
                                };
                                ModelTypes = GENERATE;
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
                                };
                                ModelTypes = GENERATE;
                            }
                            case "food" -> {
                                int hunger = (int) properties.getOrDefault("hunger", 0);
                                double saturationModifier = (double) properties.getOrDefault("saturationModifier", 0);
                                boolean isMeat = (boolean) properties.getOrDefault("isMeat",
                                        (boolean) properties.getOrDefault("meat", false));
                                boolean isAlwaysEdible = (boolean) properties.getOrDefault("isAlwaysEdible", false);
                                boolean isSnack = (boolean) properties.getOrDefault("isSnack",
                                        (boolean) properties.getOrDefault("snack", false));
                                boolean isFoxFood = (boolean) properties.getOrDefault("isFoxFood",
                                        (boolean) properties.getOrDefault("fox", false));

                                FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                                        .hunger((int) hunger)
                                        .saturationModifier((float) saturationModifier);

                                if (isMeat) {
                                    foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
                                }
                                if (isSnack) {
                                    foodComponentBuilder.snack();
                                }
                                if(isAlwaysEdible) {
                                    foodComponentBuilder.alwaysEdible();
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
                                };

                                if (isFoxFood) {
                                    TAG_FOX_FOOD.add(new Identifier(namespace, id));
                                }
                                ModelTypes = GENERATE;
                            }
                            case "stewitem", "bowl" -> {
                                int hunger = (int) properties.getOrDefault("hunger", 0);
                                double saturationModifier = (double) properties.getOrDefault("saturationModifier", 0);
                                boolean isMeat = (boolean) properties.getOrDefault("isMeat",
                                        (boolean) properties.getOrDefault("meat", false));
                                boolean isFoxFood = (boolean) properties.getOrDefault("isFoxFood",
                                        (boolean) properties.getOrDefault("fox", false));
                                boolean isAlwaysEdible = (boolean) properties.getOrDefault("isAlwaysEdible", false);
                                boolean isSnack = (boolean) properties.getOrDefault("isSnack",
                                        (boolean) properties.getOrDefault("snack", false));

                                FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                                        .hunger((int) hunger)
                                        .saturationModifier((float) saturationModifier);

                                if (isMeat) {
                                    foodComponentBuilder.meat(); // 如果是肉类，设置为肉类
                                }
                                if (isSnack) {
                                    foodComponentBuilder.snack();
                                }
                                if (isAlwaysEdible) {
                                    foodComponentBuilder.alwaysEdible();
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
                                };

                                if (isFoxFood) {
                                    TAG_FOX_FOOD.add(new Identifier(namespace, id));
                                }
                                ModelTypes = GENERATE;
                            }

                            case "pickaxe" -> {
                                double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 1.0;
                                double attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : -2.8;
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new PickaxeItem(material, (int) attackDamage, (float) attackSpeed, ItemSettings) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                TAG_TOOL_PICKAXE.add(new Identifier(namespace, id));
                                ModelTypes = HANDHELD;
                            }

                            case "axe" -> {
                                double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 6.0;
                                double attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : -3.1;
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new AxeItem(material, (float) attackDamage, (float) attackSpeed, ItemSettings) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                TAG_TOOL_AXE.add(new Identifier(namespace, id));
                                ModelTypes = HANDHELD;
                            }

                            case "shovel" -> {
                                double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 1.5;
                                double attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : -3.0;
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new ShovelItem(material, (int) attackDamage, (float) attackSpeed, ItemSettings) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                TAG_TOOL_SHOVEL.add(new Identifier(namespace, id));
                                ModelTypes = HANDHELD;
                            }

                            case "hoe" -> {
                                double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 0.0;
                                double attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : -3.0;
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new HoeItem(material, (int) attackDamage, (float) attackSpeed, ItemSettings) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                TAG_TOOL_HOE.add(new Identifier(namespace, id));
                                ModelTypes = HANDHELD;
                            }

                            case "shears" -> {
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new ShearsItem(ItemSettings) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                ModelTypes = HANDHELD;
                            }

                            case "sword" -> {
                                double attackDamage = properties.containsKey("attackDamage") ? (double) properties.get("attackDamage") : 3.0;
                                double attackSpeed = properties.containsKey("attackSpeed") ? (double) properties.get("attackSpeed") : -2.4;
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new SwordItem(material, (int) attackDamage, (float) attackSpeed, ItemSettings) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                TAG_TOOL_SWORD.add(new Identifier(namespace, id));
                                ModelTypes = HANDHELD;
                            }

                            case "bow" -> {
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new com.KafuuChino0722.coreextensions.item.BowItem(ItemSettings) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                ModelTypes = BOW;
                            }

                            case "shield" -> {
                                ItemSettings = ItemSettings.maxDamage(durability);
                                ItemType = new FabricShieldItem(ItemSettings, 10, 13, repairMaterialItem) {
                                    @Override
                                    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                        if (tooltipMsg != null) {
                                            for (String Line : tooltipMsg) {
                                                tooltip.add(Text.of("§5§o" + Line));
                                            }
                                        }
                                    }
                                };
                                ModelTypes = SHIELD;
                            }
                            case "sapling" -> {
                                try {
                                    RegistryKey<ConfiguredFeature<?, ?>> CUSTOM_TREE_KEY = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(namespace, id));
                                    SaplingGenerator TREE_GENERATION = new SaplingGenerator() {
                                        @Nullable
                                        @Override
                                        protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
                                            return CUSTOM_TREE_KEY;
                                        }
                                    };
                                    Block BLOCK_SAPLING = Registry.register(Registries.BLOCK, new Identifier(namespace, id), new SaplingBlock(TREE_GENERATION, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
                                    Item ITEM_SAPLING = Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(BLOCK_SAPLING, new FabricItemSettings()));
                                    setupRenderLayer.set(Registries.BLOCK.get(new Identifier(namespace, id)));
                                } catch (Exception e) {

                                }
                                if (Reference.EnvType == EnvType.CLIENT) {
                                    ModelBuilder.Block.getModel(namespace, id, ModelBuilder.Block.Types.PLANTS);
                                }
                                provider.add("item." +namespace +"."+id, name);
                                provider.add("block." +namespace +"."+id, name);
                                ItemGroupsContents.load(namespace,id,properties);
                                ModelTypes = ModelBuilder.Item.Types.BOAT;
                            }
                        }
                        if (ItemType != null) {
                            try {
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), ItemType);
                                ReturnMessage.ItemYMLRegister(name, namespace, id);
                            } catch (Exception e) {
                                setRegistry.set(namespace, id, ItemType);
                            }
                            if (FabricLoader.getInstance().getEnvironmentType() == CLIENT && generate) {
                                ModelBuilder.Item.getModel(namespace, id, ModelTypes);
                            }
                        }
                    }
                }
            }
        }
    }
}