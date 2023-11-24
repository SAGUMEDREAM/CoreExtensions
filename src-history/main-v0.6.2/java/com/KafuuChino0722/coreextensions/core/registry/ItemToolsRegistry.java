package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.MethodRarity;
import com.KafuuChino0722.coreextensions.core.api.MethodToolMaterial;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.core.template.Template;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.*;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Item.Types.*;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;
import static net.fabricmc.api.EnvType.CLIENT;

public class ItemToolsRegistry {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("itemTool.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("itemTool.yml");
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

                    int durability = properties.containsKey("durability") ? (int) properties.get("durability") : 250;

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

                    Item repairMaterialItem = Registries.ITEM.get(new Identifier((String) properties.getOrDefault("repairMaterial","minecraft:iron_ingot")));
                    if (repairMaterialItem instanceof ToolItem) {
                        material = ((ToolItem) repairMaterialItem).getMaterial();
                    }

                    switch (types.toLowerCase()) {
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

                                @Override
                                public boolean hasGlint(ItemStack stack) {
                                    if(hasGlint) return true;
                                    return false;
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

                                @Override
                                public boolean hasGlint(ItemStack stack) {
                                    if(hasGlint) return true;
                                    return false;
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

                                @Override
                                public boolean hasGlint(ItemStack stack) {
                                    if(hasGlint) return true;
                                    return false;
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

                                @Override
                                public boolean hasGlint(ItemStack stack) {
                                    if(hasGlint) return true;
                                    return false;
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

                                @Override
                                public boolean hasGlint(ItemStack stack) {
                                    if(hasGlint) return true;
                                    return false;
                                }
                            };
                            ModelTypes = HANDHELD;
                        }
                        case "template" -> {
                            String TemplateIdentifier = properties.containsKey("template") ? (String) properties.get("template") : null;
                            Template.Item(namespace,id,TemplateIdentifier);
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