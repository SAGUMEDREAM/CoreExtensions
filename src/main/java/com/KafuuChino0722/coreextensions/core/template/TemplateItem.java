package com.KafuuChino0722.coreextensions.core.template;


import com.KafuuChino0722.coreextensions.core.api.MethodRarity;
import com.KafuuChino0722.coreextensions.core.api.MethodToolMaterial;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.item.ClickItem;
import com.KafuuChino0722.coreextensions.util.ModToolMaterials;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import com.github.crimsondawn45.fabricshieldlib.lib.object.FabricShieldItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
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
    public static final String FILE = Reference.File;

    public static void register(String namespace, String id, String TemplateIdentifier) {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/template.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("items")) {
                                Map<String, Object> items = itemsData.get("items");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                                        String name = (String) itemData.getOrDefault("name",(String) entry.getKey());

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

                                            String rarityStr = (String) properties.getOrDefault("rarity","common");
                                            ItemSettings = ItemSettings.rarity(MethodRarity.getRarity(rarityStr));
                                            /*
                                            if (rarity.equalsIgnoreCase("common")) {
                                                ItemSettings = ItemSettings.rarity(Rarity.COMMON);
                                            } else if (rarity.equalsIgnoreCase("uncommon")) {
                                                ItemSettings = ItemSettings.rarity(Rarity.UNCOMMON);
                                            } else if (rarity.equalsIgnoreCase("rare")) {
                                                ItemSettings = ItemSettings.rarity(Rarity.RARE);
                                            } else if (rarity.equalsIgnoreCase("epic")) {
                                                ItemSettings = ItemSettings.rarity(Rarity.EPIC);
                                            } else {
                                                ItemSettings = ItemSettings.rarity(Rarity.COMMON);
                                            }*/

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
                                                    /*switch (level.toLowerCase()) {
                                                case "0", "wood", "gold" -> ToolMaterials.WOOD;
                                                case "1", "stone" -> ToolMaterials.STONE;
                                                case "2", "iron" -> ToolMaterials.IRON;
                                                case "3", "diamond" -> ToolMaterials.DIAMOND;
                                                case "4", "netherite" -> ToolMaterials.NETHERITE;
                                                case "custom" -> ModToolMaterials.CUSTOM;
                                                default -> ToolMaterials.IRON;
                                            };*/

                                            Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("repairMaterial","minecraft:iron_ingot")));
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
                                            }
                                            if(ItemType!=null) {
                                                try {
                                                    Registry.register(Registries.ITEM, new Identifier(namespace, id), ItemType);
                                                    ReturnMessage.ItemYMLRegister(name, namespace, id);
                                                } catch (Exception e) {
                                                    setRegistry.set(namespace, id, ItemType);
                                                }
                                                if(FabricLoader.getInstance().getEnvironmentType()==CLIENT&&generate) {
                                                    ModelBuilder.Item.getModel(namespace,id,ModelTypes);
                                                }
                                            }
                                        }
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
