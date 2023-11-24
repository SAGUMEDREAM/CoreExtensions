package com.KafuuChino0722.coreextensions.core.template;

import com.KafuuChino0722.coreextensions.block.ChestBlockExtends;
import com.KafuuChino0722.coreextensions.block.CubeBlock;
import com.KafuuChino0722.coreextensions.block.PowerBlock;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.api.LootTableBuilder;
import com.KafuuChino0722.coreextensions.core.api.MethodMapColor;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.util.*;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
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
import static com.KafuuChino0722.coreextensions.CoreManager.TAG_DOORS_BLOCK;
import static net.fabricmc.api.EnvType.CLIENT;
import static net.minecraft.block.Blocks.OAK_PLANKS;

import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Block.Types.*;

public class TemplateBlock {

    public static void register(String namespace, String id, String TemplateIdentifier) {
        Map<String, Map<String, Object>> blocksData = IOFileManager.read("template.yml");
        load(namespace, id, TemplateIdentifier, blocksData);
        Map<String, Map<String, Object>> blocksDataZ = IOFileManager.readZip("template.yml");
        load(namespace, id, TemplateIdentifier, blocksDataZ);
    }

    public static void load(String namespace, String id, String TemplateIdentifier, Map<String, Map<String, Object>> blocksData) {
        if (blocksData != null && blocksData.containsKey("blocks")) {
            Map<String, Object> blocks = blocksData.get("blocks");

            for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                    String name = (String) blockData.getOrDefault("name",(String) entry.getKey());

                    String Identifier = blockData.containsKey("identifier") ? (String) blockData.get("identifier") : null;

                    if (TemplateIdentifier.equalsIgnoreCase(Identifier)) {
                        Block block = null;
                        Block stripped_block = null;
                        Block wallTorch = null;
                        Block chest_block = null;
                        Item chest_blockItem = null;
                        Item BlockItem = null;
                        ModelBuilder.Block.Types ModelTypes = null;

                        boolean break_action = false;

                        FabricBlockSettings blockSettings = FabricBlockSettings.create();
                        FabricItemSettings itemSettings = new FabricItemSettings();

                        String types = (String) blockData.getOrDefault("types","CUBE_ALL");

                        int maxCount = (int) blockData.getOrDefault("maxCount",64);
                        itemSettings = itemSettings.maxCount(maxCount);

                        Map<String, Object> properties = blockData.containsKey("properties") ? (Map<String, Object>) blockData.get("properties") : (Map<String, Object>) entry.getValue() ;

                        double hardness = (double) properties.getOrDefault("hardness",1.0);
                        blockSettings = blockSettings.hardness((float) hardness);;
                        double resistance = (double) properties.getOrDefault("resistance",3.0);
                        blockSettings = blockSettings.strength((float) resistance);
                        boolean dropsNothing = (boolean) properties.getOrDefault("dropsNothing",false);
                        if(dropsNothing) blockSettings = blockSettings.dropsNothing();
                        boolean generate = (boolean) properties.getOrDefault("generate",true);

                        boolean canFire = (boolean) properties.getOrDefault("canFire",false);

                        String sound = (String) properties.getOrDefault("sound","STONE");

                        String soundStr = sound.toUpperCase();
                        BlockSoundGroup customSound = switch (soundStr) {
                            case "STONE" -> BlockSoundGroup.STONE;
                            case "WOOD" -> BlockSoundGroup.WOOD;
                            case "GRAVEL" -> BlockSoundGroup.GRAVEL;
                            case "METAL" -> BlockSoundGroup.METAL;
                            case "GRASS" -> BlockSoundGroup.GRASS;
                            case "WOOL" -> BlockSoundGroup.WOOL;
                            case "SAND" -> BlockSoundGroup.SAND;
                            case "CROP" -> BlockSoundGroup.CROP;
                            case "GLASS" -> BlockSoundGroup.GLASS;
                            default -> BlockSoundGroup.STONE;
                        };
                        blockSettings = blockSettings.sounds(customSound);

                        if(properties.containsKey("require")) {
                            blockSettings = blockSettings.requiresTool();
                        }

                        if(properties.containsKey("MapColor")) {
                            String MapColorKey = (String) properties.get("MethodMapColor");
                            blockSettings = blockSettings.mapColor(MethodMapColor.get(MapColorKey));
                        }

                        int lightLevel = (int) properties.getOrDefault("lightLevel", 0);
                        blockSettings = blockSettings.luminance(lightLevel);

                        switch (types.toLowerCase()) {
                            case "cube_all", "cubeall" -> {
                                block = new Block(blockSettings);
                                ModelTypes = CUBE_ALL;
                            }
                            case "cube" -> {
                                block = new CubeBlock(blockSettings);
                                ModelTypes = CUBE;
                            }
                            case "button" -> {
                                boolean wooden = (boolean) properties.getOrDefault("wooden",true);
                                int pressTicks = (int) properties.getOrDefault("pressTicks",20);
                                block = new ButtonBlock(blockSettings, BlockSetType.OAK, pressTicks, wooden);
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
                                TAG_DOORS_ITEM.add(new Identifier(namespace,id));
                                TAG_DOORS_BLOCK.add(new Identifier(namespace,id));
                                ModelTypes = DOOR;
                            }
                            case "fence" -> {
                                block = new FenceBlock(blockSettings);
                                TAG_FENCES_BLOCK.add(new Identifier(namespace,id));
                                TAG_FENCES_ITEM.add(new Identifier(namespace,id));
                                ModelTypes = FENCE;
                            }
                            case "fence_gate", "fencegate" -> {
                                block = new FenceGateBlock(blockSettings, WoodType.ACACIA);
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
                                TAG_PRESSURE_PLATES_BLOCK.add(new Identifier(namespace,id));
                                TAG_PRESSURE_PLATES_ITEM.add(new Identifier(namespace,id));
                                ModelTypes = PRESSUREPLATE;
                            }
                            case "slab" -> {
                                block = new SlabBlock(blockSettings);
                                TAG_SLABS_ITEM.add(new Identifier(namespace,id));
                                TAG_SLABS_BLOCK.add(new Identifier(namespace,id));
                                ModelTypes = SLAB;
                            }
                            case "uprightslab", "upright_slab" -> {
                                block = new SlabBlock(blockSettings);
                                TAG_SLABS_ITEM.add(new Identifier(namespace,id));
                                TAG_SLABS_BLOCK.add(new Identifier(namespace,id));
                                ModelTypes = SLAB;
                            }
                            case "stairs" -> {
                                block = new StairsBlock(OAK_PLANKS.getDefaultState(), blockSettings);
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
                                TAG_TRAPDOORS_ITEM.add(new Identifier(namespace, id));
                                TAG_TRAPDOORS_BLOCK.add(new Identifier(namespace, id));
                                ModelTypes = TRAPDOOR;
                            }
                            case "wall" -> {
                                block = new WallBlock(blockSettings);
                                TAG_WALLS_ITEM.add(new Identifier(namespace,id));
                                TAG_WALLS_BLOCK.add(new Identifier(namespace,id));
                                ModelTypes = WALL;
                            }
                            case "sand" -> {
                                block = new SandBlock(11098145, blockSettings);
                                TAG_SAND_ITEM.add(new Identifier(namespace,id));
                                TAG_SAND_BLOCK.add(new Identifier(namespace,id));
                                ModelTypes = CUBE_ALL;
                            }
                            case "soulsand", "soul_sand" -> {
                                block = new SoulSandBlock(blockSettings);
                                TAG_SOUL_SPEED_BLOCKS_BLOCK.add(new Identifier(namespace,id));
                                TAG_SOUL_FIRE_BASE_BLOCKS_BLOCK.add(new Identifier(namespace,id));
                                TAG_WITHER_SUMMON_BASE_BLOCKS_BLOCK.add(new Identifier(namespace,id));
                                ModelTypes = CUBE_ALL;
                            }
                            case "fire" -> {}
                            //Fire.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                            case "log" -> {
                                block = new PillarBlock(blockSettings);
                                stripped_block = new PillarBlock(blockSettings);
                                TAG_LOGS_ITEM.add(new Identifier(namespace, id));
                                TAG_LOGS_ITEM.add(new Identifier(namespace, "stripped_"+id));
                                TAG_LOGS_BLOCK.add(new Identifier(namespace, id));
                                TAG_LOGS_BLOCK.add(new Identifier(namespace, "stripped_"+id));
                                provider.add("block." +namespace +"."+"stripped_"+id, name);
                                ModelTypes = CUBE_COLUMN;
                            }
                            case "leaves" -> {
                                block = new LeavesBlock(blockSettings);
                                TAG_LEAVES_ITEM.add(new Identifier(namespace, id));
                                TAG_LEAVES_BLOCK.add(new Identifier(namespace, id));
                                ModelTypes = CUBE_ALL;
                            }
                            case "torch" -> {
                                block = new TorchBlock(blockSettings, ParticleTypes.FLAME);
                                wallTorch = new WallTorchBlock(blockSettings, ParticleTypes.FLAME);
                                ModelTypes = TORCH;
                            }
                            case "lamp", "redstone_lamp", "redstonelamp" -> {
                                block = new RedstoneLampBlock(blockSettings);
                                blockSettings = blockSettings.allowsSpawning(Blocks::always).luminance(Blocks.createLightLevelFromLitBlockState((int) properties.getOrDefault("lightLevel", 0)));
                                ModelTypes = REDSTONE_LAMP;
                            }
                            case "power","powerblock","power_block" -> {
                                int power = (int) properties.getOrDefault("power",15);
                                block = new PowerBlock(blockSettings,power);
                                ModelTypes = CUBE_ALL;
                            }
                            case "bed" -> {
                                Block bedBlock;
                                Item bedItem;
                                bedBlock = new BedBlock(DyeColor.WHITE, blockSettings);
                                bedItem = new BedItem(bedBlock ,new FabricItemSettings());
                                ModelTypes = BED;
                            }
                            case "chest" -> {
                                chest_block = new ChestBlockExtends(blockSettings, () -> BlockEntityType.CHEST);
                                chest_blockItem = new BlockItem(block, itemSettings);
                                ModelTypes = CHEST;
                            }
                            case "glass" -> {
                                block = new GlassBlock(blockSettings);
                                blockSettings = blockSettings.instrument(Instrument.HAT).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never);
                                TAG_GLASS_BLOCK.add(new Identifier(namespace, id));
                                TAG_GLASS_ITEM.add(new Identifier(namespace, id));
                                ModelTypes = CUBE_ALL;
                            }
                            case "glass_pane","glasspane" -> {
                                block = new PaneBlock(blockSettings);
                                blockSettings = blockSettings.instrument(Instrument.HAT).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never);
                                TAG_GLASS_BLOCK.add(new Identifier(namespace, id));
                                TAG_GLASS_ITEM.add(new Identifier(namespace, id));
                                ModelTypes = GLASS_PANE;
                            }
                            case "carpet" -> {
                                block = new CarpetBlock(blockSettings);
                                TAG_WOOL_CARPETS_BLOCK.add(new Identifier(namespace, id));
                                TAG_WOOL_CARPETS_ITEM.add(new Identifier(namespace, id));
                                ModelTypes = CARPETS;
                            }
                            case "sign" -> {
                                break_action = true;
                                Identifier SIGN_TEXTURE = new Identifier(namespace,"entity/signs/"+id);
                                Identifier HANGING_SIGN_TEXTURE = new Identifier(namespace,"entity/signs/hanging/"+id);
                                Identifier HANGING_GUI_SIGN_TEXTURE = new Identifier(namespace,"textures/gui/hanging_signs/"+id);

                                Block STANDING_SIGN = new TerraformSignBlock(SIGN_TEXTURE, blockSettings);
                                Block WALL_SIGN = new TerraformWallSignBlock(SIGN_TEXTURE, blockSettings);
                                Block HANGING_SIGN = new TerraformHangingSignBlock(HANGING_SIGN_TEXTURE, HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN));
                                Block WALL_HANGING_SIGN = new TerraformWallHangingSignBlock(HANGING_SIGN_TEXTURE, HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN));
                                Item SIGN_ITEM = new SignItem(itemSettings, STANDING_SIGN, WALL_SIGN) {};
                                Item HANGING_SIGN_ITEM = new HangingSignItem(HANGING_SIGN, WALL_HANGING_SIGN, itemSettings) {};

                                try {
                                    Registry.register(Registries.BLOCK, new Identifier(namespace,id+"_"+"standing"),
                                            STANDING_SIGN);
                                    Registry.register(Registries.BLOCK, new Identifier(namespace,id+"_"+"wall"),
                                            WALL_SIGN);
                                    Registry.register(Registries.BLOCK, new Identifier(namespace, id+"_"+"hanging"),
                                            HANGING_SIGN);
                                    Registry.register(Registries.BLOCK, new Identifier(namespace, id+"_"+"wall_hanging"),
                                            WALL_HANGING_SIGN);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id), SIGN_ITEM);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id+"_"+"hanging"), HANGING_SIGN_ITEM);
                                } catch (Exception e) {
                                    Item oldBlockItem1 = Registries.ITEM.get(new Identifier(namespace,id));
                                    Item oldBlockItem2 = Registries.ITEM.get(new Identifier(namespace,id+"_"+"hanging"));
                                    setRegistry.set(namespace,id+"_"+"standing",STANDING_SIGN);
                                    setRegistry.set(namespace,id+"_"+"wall",WALL_SIGN);
                                    setRegistry.set(namespace,id+"_"+"hanging",HANGING_SIGN);
                                    setRegistry.set(namespace,id+"_"+"wall_hanging",WALL_HANGING_SIGN);
                                    setRegistry.set(namespace,id+"_"+"sign",SIGN_ITEM);
                                    setRegistry.set(namespace,id+"_"+"hanging",HANGING_SIGN_ITEM);
                                    WorldRegistryDataReloading.run(SIGN_ITEM,oldBlockItem1);
                                    WorldRegistryDataReloading.run(HANGING_SIGN_ITEM,oldBlockItem2);
                                }

                                TAG_SIGNS_ITEM.add(SIGN_ITEM);
                                TAG_HANGING_SIGNS_ITEM.add(HANGING_SIGN_ITEM);
                                TAG_STANDING_SIGNS_BLOCK.add(HANGING_SIGN);
                                TAG_WALL_HANGING_SIGNS_BLOCK.add(WALL_HANGING_SIGN);
                                provider.add("block." +namespace +"."+id+"_standing", name);
                                provider.add("block." +namespace +"."+id+"_hanging", name);
                                ModelTypes = SIGN;
                            }
                            default -> ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                        }

                        if(!break_action) {
                            try {
                                if (chest_block == null)
                                    Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
                                if (wallTorch == null && chest_block == null)
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, itemSettings));
                                if (stripped_block != null) {
                                    Registry.register(Registries.BLOCK, new Identifier(namespace, "stripped_" + id), stripped_block);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, "stripped_" + id), new BlockItem(stripped_block, itemSettings));
                                    StrippableBlockRegistry.register(block, stripped_block);
                                }
                                if (wallTorch != null) {
                                    Registry.register(Registries.BLOCK, new Identifier(namespace, "wall_" + id), wallTorch);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id),
                                            new VerticallyAttachableBlockItem(
                                                    Registries.BLOCK.get(new Identifier(namespace, id)),
                                                    Registries.BLOCK.get(new Identifier(namespace, "wall_" + id)),
                                                    new Item.Settings().maxCount(maxCount), Direction.DOWN));
                                }
                                if (chest_block != null) {
                                    Registry.register(Registries.BLOCK, new Identifier(namespace, id), chest_block);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id), chest_blockItem);
                                    Item finalChest_blockItem = chest_blockItem;
                                    ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> entries.add(finalChest_blockItem));
                                    ItemGroupEvents.modifyEntriesEvent(ItemGroups.REDSTONE).register(entries -> entries.add(finalChest_blockItem));
                                }
                            } catch (Exception e) {
                                if (chest_block == null) setRegistry.set(namespace, id, block);
                                if (chest_block == null) BlockItem = new BlockItem(block, itemSettings);
                                if (wallTorch == null && chest_block == null) setRegistry.set(namespace, id, BlockItem);
                                if (stripped_block != null) {
                                    setRegistry.set(namespace, "stripped_" + id, stripped_block);
                                    Item BlockItemStripped = new BlockItem(stripped_block, itemSettings);
                                    setRegistry.set(namespace, "stripped_" + id, BlockItemStripped);
                                    StrippableBlockRegistry.register(block, stripped_block);
                                }
                                if (wallTorch != null) {
                                    setRegistry.set(namespace, id, wallTorch);
                                    Registry.register(Registries.ITEM, new Identifier(namespace, id),
                                            new VerticallyAttachableBlockItem(
                                                    Registries.BLOCK.get(new Identifier(namespace, id)),
                                                    Registries.BLOCK.get(new Identifier(namespace, "wall_" + id)),
                                                    new Item.Settings().maxCount(maxCount), Direction.DOWN));
                                }
                                if (chest_block != null) {
                                    setRegistry.set(namespace, id, chest_block);
                                    setRegistry.set(namespace, id, chest_blockItem);
                                }
                            }
                        }

                        if(Reference.EnvType == EnvType.CLIENT) {
                            setupRenderLayer.set(block, properties);
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
                    } else {
                        Info.ERROR.info("ERROR?TEMPLATE");
                    }
                }
            }
        }
    }
}
