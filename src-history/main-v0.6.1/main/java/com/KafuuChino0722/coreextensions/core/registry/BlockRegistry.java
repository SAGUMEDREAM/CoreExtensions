package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.block.ChestBlockExtends;
import com.KafuuChino0722.coreextensions.block.CubeBlock;
import com.KafuuChino0722.coreextensions.block.PowerBlock;
import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.*;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.core.template.Template;
import com.KafuuChino0722.coreextensions.util.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.*;
import static com.KafuuChino0722.coreextensions.CoreManager.TAG_GLASS_ITEM;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Block.Types.*;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;
import static net.fabricmc.api.EnvType.CLIENT;
import static net.minecraft.block.Blocks.OAK_PLANKS;


public class BlockRegistry {

    public static void register() {
        Map<String, Map<String, Object>> blocksData = IOFileManager.read("block.yml");
        load(blocksData);
        Map<String, Map<String, Object>> blocksDataZ = IOFileManager.readZip("block.yml");
        load(blocksDataZ);
    }

    public static void load(Map<String, Map<String, Object>> blocksData) {
        if (blocksData != null && blocksData.containsKey("blocks")) {
            Map<String, Object> blocks = blocksData.get("blocks");

            for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                    String name = (String) blockData.getOrDefault("name",(String) entry.getKey());

                    String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                    String id = (String) blockData.get("id");
                    String types = (String) blockData.getOrDefault("types","CUBE_ALL");
                    int maxCount = (int) blockData.getOrDefault("maxCount",64);

                    Block block = null;
                    Block stripped_block = null;
                    Block wallTorch = null;
                    Block chest_block = null;
                    Item chest_blockItem = null;
                    Item BlockItem = null;
                    ModelBuilder.Block.Types ModelTypes = null;
                    boolean IsTemplate = false;

                    FabricBlockSettings blockSettings = FabricBlockSettings.create();
                    FabricItemSettings itemSettings = new FabricItemSettings();

                    itemSettings = itemSettings.maxCount(maxCount);

                    Map<String, Object> properties = (Map<String, Object>) blockData.getOrDefault("properties", entry.getValue());

                    double hardness = (double) properties.getOrDefault("hardness",1.0);
                    blockSettings = blockSettings.hardness((float) hardness);;
                    double resistance = (double) properties.getOrDefault("resistance",3.0);
                    blockSettings = blockSettings.strength((float) resistance);
                    boolean dropsNothing = (boolean) properties.getOrDefault("dropsNothing",false);
                    if(dropsNothing) blockSettings = blockSettings.dropsNothing();
                    boolean generate = (boolean) properties.getOrDefault("generate",true);

                    boolean canFire = (boolean) properties.getOrDefault("canFire",false);

                    String soundStr = (String) properties.getOrDefault("sound","STONE");

                    BlockSoundGroup customSound = MethodSound.getSound(soundStr);
                    blockSettings = blockSettings.sounds(customSound);

                    if(properties.containsKey("require")) {
                        blockSettings = blockSettings.requiresTool();
                    }

                    if(properties.containsKey("MapColor")) {
                        String MapColorKey = (String) properties.get("MethodMapColor");
                        blockSettings = blockSettings.mapColor(MethodMapColor.get(MapColorKey));
                    }

                    String rarityStr = (String) properties.getOrDefault("rarity","common");
                    itemSettings = itemSettings.rarity(MethodRarity.getRarity(rarityStr));

                    boolean hasGlint = (boolean) properties.getOrDefault("hasGlint",false);

                    boolean fireproof = (boolean) properties.getOrDefault("fireproof", false);
                    if (fireproof) {
                        itemSettings = itemSettings.fireproof();
                    }

                    String recipeRemainder = (String) properties.getOrDefault("recipeRemainder", null);
                    if (recipeRemainder != null) {
                        itemSettings = itemSettings.recipeRemainder(Registries.ITEM.get(new Identifier(recipeRemainder)));
                    }

                    int hunger = (int) properties.getOrDefault("hunger", 0);
                    double saturationModifier = (double) properties.getOrDefault("saturationModifier", 0.0);
                    boolean isMeat = (boolean) properties.getOrDefault("isMeat",
                            (boolean) properties.getOrDefault("meat", false));
                    if(properties.containsKey("hunger")||properties.containsKey("saturationModifier")) {
                        FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                                .hunger((int) hunger)
                                .saturationModifier((float) saturationModifier);
                        if (isMeat) {
                            foodComponentBuilder.meat();
                        }
                        FoodComponent foodComponent = foodComponentBuilder.build();

                        itemSettings = itemSettings.food(foodComponent);;
                    }

                    int lightLevel = (int) properties.getOrDefault("lightLevel", 0);
                    blockSettings = blockSettings.luminance(lightLevel);

                    boolean error = false;

                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                        ItemGroupsContents.load(namespace,id,properties);
                    }

                    List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;

                    switch (types.toLowerCase()) {
                        case "cube_all", "cubeall" -> {
                            block = new Block(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            ModelTypes = CUBE_ALL;
                        }
                        case "cube" -> {
                            block = new CubeBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            ModelTypes = CUBE;
                        }
                        case "button" -> {
                            boolean wooden = (boolean) properties.getOrDefault("wooden",true);
                            int pressTicks = (int) properties.getOrDefault("pressTicks",20);
                            block = new ButtonBlock(blockSettings, BlockSetType.OAK, pressTicks, wooden);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_BUTTONS_ITEM.add(new Identifier(namespace,id));
                            TAG_BUTTONS_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = BUTTON;
                        }
                        case "door" -> {
                            boolean canOpenByHand = (boolean) properties.getOrDefault("canOpenByHand",true);
                            BlockSetType blockType;
                            if (canOpenByHand) {
                                blockType = BlockSetType.OAK;
                            } else {
                                blockType = BlockSetType.IRON;
                            }
                            block = new DoorBlock(blockSettings, blockType);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_DOORS_ITEM.add(new Identifier(namespace,id));
                            TAG_DOORS_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = DOOR;
                        }
                        case "fence" -> {
                            block = new FenceBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace,id,block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block,itemSettings) {
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

                            };;
                                    setRegistry.set(namespace,id,BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_FENCES_BLOCK.add(new Identifier(namespace,id));
                            TAG_FENCES_ITEM.add(new Identifier(namespace,id));
                            ModelTypes = FENCE;
                        }
                        case "fence_gate", "fencegate" -> {
                            block = new FenceGateBlock(blockSettings, WoodType.ACACIA);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_FENCE_GATES_BLOCK.add(new Identifier(namespace,id));
                            TAG_FENCE_GATES_ITEM.add(new Identifier(namespace,id));
                            ModelTypes = FENCEGATE;

                        }
                        case "pressure_plate", "pressureplate" -> {
                            boolean SetType = (boolean) properties.getOrDefault("stone",false);
                            BlockSetType blockType;
                            if (SetType) {
                                blockType = BlockSetType.STONE;
                            } else {
                                blockType = BlockSetType.OAK;
                            }

                            PressurePlateBlock.ActivationRule ActivationRule = PressurePlateBlock.ActivationRule.EVERYTHING;
                            block = new PressurePlateBlock(ActivationRule, blockSettings, blockType);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_PRESSURE_PLATES_BLOCK.add(new Identifier(namespace,id));
                            TAG_PRESSURE_PLATES_ITEM.add(new Identifier(namespace,id));
                            ModelTypes = PRESSUREPLATE;
                        }
                        case "slab" -> {
                            block = new SlabBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_SLABS_ITEM.add(new Identifier(namespace,id));
                            TAG_SLABS_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = SLAB;
                        }
                        case "uprightslab", "upright_slab" -> {
                            block = new SlabBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_SLABS_ITEM.add(new Identifier(namespace,id));
                            TAG_SLABS_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = SLAB;
                        }
                        case "stairs" -> {
                            block = new StairsBlock(OAK_PLANKS.getDefaultState(), blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_STAIRS_ITEM.add(new Identifier(namespace,id));
                            TAG_STAIRS_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = STAIRS;
                        }
                        case "trapdoor" -> {
                            boolean canOpenByHand = (boolean) properties.getOrDefault("canOpenByHand",true);
                            BlockSetType blockType;
                            if (canOpenByHand) {
                                blockType = BlockSetType.OAK;
                            } else {
                                blockType = BlockSetType.IRON;
                            }
                            block = new TrapdoorBlock(blockSettings, blockType);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_TRAPDOORS_ITEM.add(new Identifier(namespace, id));
                            TAG_TRAPDOORS_BLOCK.add(new Identifier(namespace, id));
                            ModelTypes = TRAPDOOR;
                        }
                        case "wall" -> {
                            block = new WallBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_WALLS_ITEM.add(new Identifier(namespace,id));
                            TAG_WALLS_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = WALL;
                        }
                        case "sand" -> {
                            block = new SandBlock(11098145, blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_SAND_ITEM.add(new Identifier(namespace,id));
                            TAG_SAND_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = CUBE_ALL;
                        }
                        case "soulsand", "soul_sand" -> {
                            block = new SoulSandBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_SOUL_SPEED_BLOCKS_BLOCK.add(new Identifier(namespace,id));
                            TAG_SOUL_FIRE_BASE_BLOCKS_BLOCK.add(new Identifier(namespace,id));
                            TAG_WITHER_SUMMON_BASE_BLOCKS_BLOCK.add(new Identifier(namespace,id));
                            ModelTypes = CUBE_ALL;
                        }
                        case "fire" -> {}
                        //Fire.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                        case "log" -> {
                            block = new PillarBlock(blockSettings);
                            BlockItem = new BlockItem(block,itemSettings) {
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

                            };;
                            stripped_block = new PillarBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                                Registry.register(Registries.BLOCK, new Identifier(namespace, "stripped_"+id), stripped_block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, "stripped_"+id), new BlockItem(stripped_block,itemSettings));
                                StrippableBlockRegistry.register(block,stripped_block);
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock1 = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);

                                    Item oldBlockItem1 = Registries.ITEM.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem1);

                                    Block oldBlock2 = Registries.BLOCK.get(new Identifier(namespace,"stripped_"+id));
                                    setRegistry.set(namespace, "stripped_" + id, stripped_block);


                                    Item oldBlockItem2 = Registries.ITEM.get(new Identifier(namespace,"stripped_" + id));
                                    Item BlockItemStripped = new BlockItem(stripped_block, new FabricItemSettings().maxCount(maxCount));
                                    setRegistry.set(namespace, "stripped_" + id, BlockItemStripped);

                                    WorldRegistryDataReloading.run(BlockItemStripped,oldBlockItem2);

                                    StrippableBlockRegistry.register(block, stripped_block);
                                }
                            }
                            TAG_LOGS_ITEM.add(new Identifier(namespace, id));
                            TAG_LOGS_ITEM.add(new Identifier(namespace, "stripped_"+id));
                            TAG_LOGS_BLOCK.add(new Identifier(namespace, id));
                            TAG_LOGS_BLOCK.add(new Identifier(namespace, "stripped_"+id));
                            provider.add("block." +namespace +"."+"stripped_"+id, name);
                            ModelTypes = CUBECOLUMN;
                        }
                        case "leaves" -> {
                            block = new LeavesBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_LEAVES_ITEM.add(new Identifier(namespace, id));
                            TAG_LEAVES_BLOCK.add(new Identifier(namespace, id));
                            ModelTypes = CUBE_ALL;
                        }
                        case "torch" -> {
                            block = new TorchBlock(blockSettings, ParticleTypes.FLAME);
                            wallTorch = new WallTorchBlock(blockSettings, ParticleTypes.FLAME);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.BLOCK, new Identifier(namespace, "wall_"+id), wallTorch);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id),
                                        new VerticallyAttachableBlockItem(
                                                block,
                                                wallTorch,
                                                itemSettings, Direction.DOWN));
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock1 = Registries.BLOCK.get(new Identifier(namespace,id));
                                    Block oldBlock2 = Registries.BLOCK.get(new Identifier(namespace,"wall_"+id));
                                    setRegistry.set(namespace, id, block);
                                    setRegistry.set(namespace, "wall_"+id, wallTorch);
                                    WorldRegistryDataReloading.run(block,oldBlock1);
                                    WorldRegistryDataReloading.run(wallTorch,oldBlock2);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id,
                                            new VerticallyAttachableBlockItem(
                                                    block,
                                                    wallTorch,
                                                    itemSettings, Direction.DOWN));
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            ModelTypes = TORCH;
                        }
                        case "lamp", "redstone_lamp", "redstonelamp" -> {
                            blockSettings = blockSettings.allowsSpawning(Blocks::always).luminance(Blocks.createLightLevelFromLitBlockState((int) properties.getOrDefault("lightLevel", 0)));
                            block = new RedstoneLampBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            ModelTypes = REDSTONE_LAMP;
                        }
                        case "power","powerblock","power_block" -> {
                            int power = (int) properties.getOrDefault("power",15);
                            block = new PowerBlock(blockSettings,power);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            ModelTypes = CUBE_ALL;
                        }
                        case "bed" -> {
                            Block bedBlock;
                            Item bedItem;
                            bedBlock = new BedBlock(DyeColor.WHITE, blockSettings);
                            bedItem = new BedItem(bedBlock ,new FabricItemSettings());
                            ModelTypes = BED;
                            error = true;
                        }
                        case "chest" -> {
                            chest_block = new ChestBlockExtends(blockSettings, () -> BlockEntityType.CHEST);
                            chest_blockItem = new BlockItem(block, itemSettings) {
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
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), chest_block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(chest_block,itemSettings));
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, chest_block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(chest_block, itemSettings){
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            ModelTypes = CHEST;
                        }
                        case "glass" -> {
                            blockSettings = blockSettings.instrument(Instrument.HAT).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never);
                            block = new GlassBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_GLASS_BLOCK.add(new Identifier(namespace, id));
                            TAG_GLASS_ITEM.add(new Identifier(namespace, id));
                            ModelTypes = CUBE_ALL;
                        }
                        case "glass_pane","glasspane","pane" -> {
                            blockSettings = blockSettings.instrument(Instrument.HAT).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never);
                            block = new PaneBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);
                                }
                            }
                            TAG_GLASS_BLOCK.add(new Identifier(namespace, id));
                            TAG_GLASS_ITEM.add(new Identifier(namespace, id));
                            ModelTypes = GLASSPANE;
                        }
                        case "carpet" -> {
                            block = new CarpetBlock(blockSettings);
                            try {
                                Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block,itemSettings) {
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

                            });
                            } catch (Exception e) {
                                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                                    Block oldBlock = Registries.BLOCK.get(new Identifier(namespace,id));
                                    setRegistry.set(namespace, id, block);
                                    WorldRegistryDataReloading.run(block,oldBlock);

                                    Item oldBlockItem = Registries.ITEM.get(new Identifier(namespace,id));
                                    BlockItem = new BlockItem(block, itemSettings) {
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
                                    setRegistry.set(namespace, id, BlockItem);
                                    WorldRegistryDataReloading.run(BlockItem,oldBlockItem);

                                }
                            }
                            TAG_WOOL_CARPETS_BLOCK.add(new Identifier(namespace, id));
                            TAG_WOOL_CARPETS_ITEM.add(new Identifier(namespace, id));
                            ModelTypes = CARPETS;
                        }
                        case "template" -> {
                            String TemplateIdentifier = (String) properties.getOrDefault("template",null);
                            Template.Block(namespace,id,TemplateIdentifier);
                            IsTemplate = true;
                        }
                        default -> {
                            ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                            error = true;
                        }
                    }

                    if(!error) {
                        if(Reference.EnvType == EnvType.CLIENT) {
                            if(block!=null) setupRenderLayer.set(block, properties);
                            if(stripped_block!=null) setupRenderLayer.set(stripped_block, properties);
                            if(wallTorch!=null) setupRenderLayer.set(block);
                            if(wallTorch!=null) setupRenderLayer.set(wallTorch);
                            if(types.equalsIgnoreCase("glass")) setupRenderLayer.set(block);;
                        }

                        if (canFire) {
                            FlammableBlockRegistry.getDefaultInstance().add(Registries.BLOCK.get(new Identifier(namespace,id)),5 , 20);
                            if(stripped_block!=null) FlammableBlockRegistry.getDefaultInstance().add(Registries.BLOCK.get(new Identifier(namespace,"stripped_"+id)),5 , 20);
                        }

                        if(FabricLoader.getInstance().getEnvironmentType()==CLIENT&&generate) {
                            ModelBuilder.Block.getModel(namespace,id,ModelTypes);
                        }

                        LootTableBuilder.Build(namespace,id,types,blockData,properties);
                        ReturnMessage.BlockYMLRegister(name, namespace, id);
                        provider.add("block." +namespace +"."+id, name);
                    }
                }
            }}
    }
}