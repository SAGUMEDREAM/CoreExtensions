package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.block.*;
import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.MethodRarity;
import com.KafuuChino0722.coreextensions.core.api.MethodSound;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.core.api.MethodLootBuilder;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Block.Types.FRUIT_BUSH;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Crop.Types.CROP;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Crop.Types.HIGHCROP;
import static com.KafuuChino0722.coreextensions.core.api.ModelBuilder.Item.Types.GENERATE;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;

public class BlockCropRegistry {

    public static void register() {
        Map<String, Map<String, Object>> blocksData = IOFileManager.read("crop.yml");
        load(blocksData);
        Map<String, Map<String, Object>> blocksDataZ = IOFileManager.readZip("crop.yml");
        load(blocksDataZ);
    }

    public static void load(Map<String, Map<String, Object>> Data) {
        if (Data != null && Data.containsKey("crops")) {
            Map<String, Object> blocks = Data.get("crops");

            for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                    String name = (String) blockData.get("name");
                    String namespace = (String) blockData.getOrDefault("namespace", "minecraft");
                    String id = (String) blockData.get("id");
                    String types = (String) blockData.getOrDefault("types", "normal");

                    FabricBlockSettings BlockSettings = FabricBlockSettings.create();
                    FabricItemSettings ItemSettings = new FabricItemSettings();

                    int maxCount = (int) blockData.getOrDefault("maxCount",64);
                    ItemSettings = ItemSettings.maxCount(maxCount);

                    Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");

                    double hardness = (double) properties.getOrDefault("hardness",0.0);
                    BlockSettings = BlockSettings.hardness((float) hardness);;
                    double resistance = (double) properties.getOrDefault("resistance",0.0);
                    BlockSettings = BlockSettings.strength((float) resistance);

                    boolean dropsNothing = (boolean) properties.getOrDefault("dropsNothing",false);
                    boolean generate =(boolean) properties.getOrDefault("generate", true);
                    int AGE = properties.containsKey("max_age") ? (int) properties.get("max_age") : 7;

                    int lightLevel = (int) properties.getOrDefault("lightLevel", 0);
                    BlockSettings = BlockSettings.luminance(lightLevel);

                    String soundStr = (String) properties.getOrDefault("sound","stone");
                    BlockSoundGroup customSound = MethodSound.getSound(soundStr);
                    BlockSettings = BlockSettings.sounds(customSound);

                    boolean fireproof = (boolean) properties.getOrDefault("fireproof", false);
                    if (fireproof) {
                        ItemSettings = ItemSettings.fireproof();
                    }

                    String recipeRemainder = (String) properties.getOrDefault("recipeRemainder", null);
                    if (recipeRemainder != null) {
                        ItemSettings = ItemSettings.recipeRemainder(Registries.ITEM.get(new Identifier(recipeRemainder)));
                    }

                    String rarityStr = (String) properties.getOrDefault("rarity","common");
                    ItemSettings = ItemSettings.rarity(MethodRarity.getRarity(rarityStr));

                    if (types.equalsIgnoreCase("normal")) {
                        CropRegistry.add(name, namespace, id, blockData, properties, dropsNothing, customSound, BlockSettings, ItemSettings, generate);
                    } else if (types.equalsIgnoreCase("high")) {
                        CropHighRegistry.add(name, namespace, id, blockData, properties, dropsNothing, customSound, BlockSettings, ItemSettings, generate);
                    } else if (types.equalsIgnoreCase("fruitbush") || types.equalsIgnoreCase("bush") || types.equalsIgnoreCase("fruit")) {
                        CropFruitBushRegistry.add(name, namespace, id, blockData, properties, dropsNothing, customSound, BlockSettings, ItemSettings, generate);
                    }

                    provider.add("item." +namespace +"."+id, name)
                            .add("block." +namespace +"."+id, name)
                            .add("item." +namespace +"."+id+"_seeds", name)
                            .add("block." +namespace +"."+id+"_block", name);

                    if(Registries.BLOCK.containsId(new Identifier(namespace,id))) {
                        StorageRegistry.add(StorageRegistry.BLOCKS,new Identifier(namespace,id));
                        StorageRegistry.add(StorageRegistry.BLOCKS,new Identifier(namespace,id+"_block"));
                        StorageRegistry.add(StorageRegistry.ITEMS,new Identifier(namespace,id));
                        StorageRegistry.add(StorageRegistry.ITEMS,new Identifier(namespace,id+"_seeds"));
                    }
                }
            }
        }
    }

    public static class CropFruitBushRegistry {
        public static void add(String name, String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, FabricBlockSettings BlockSettings, FabricItemSettings ItemSettings, boolean generate){

            // 读取更多属性并设置到 blockSettings 中
            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
            FabricBlockSettings blockSettings = BlockSettings
                    .copyOf(Blocks.WHEAT)
                    .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                    .sounds(customSound)
                    .ticksRandomly()
                    .noCollision()
                    .pistonBehavior(PistonBehavior.DESTROY);

            Map<String, Object> setting = properties.containsKey("settings") ? (Map<String, Object>) properties.get("settings"):null;
            int hunger = (int) setting.get("hunger");
            double saturationModifier = (double) setting.get("saturationModifier");
            FoodComponent.Builder foodComponentBuilder = new FoodComponent.Builder()
                    .hunger((int) hunger)
                    .saturationModifier((float) saturationModifier);
            if (dropsNothing) {
                blockSettings.dropsNothing();
            }

            Block block = new FruitBushBlock(namespace, id, blockSettings);
            FoodComponent foodComponent = foodComponentBuilder.build();
            Item foodItem = new AliasedBlockItem(block ,ItemSettings.food(foodComponent));

            if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                ItemGroupsContents.load(namespace,id,properties);
            }

            try {
                registerBlock(namespace, id, block);
                registerItem(namespace, id, foodItem);
            } catch (Exception e) {
                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                    setRegistry.set(namespace, id, block);
                    setRegistry.set(namespace, id, foodItem);
                }
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);
            }
            if(generate) {
                ModelBuilder.Block.getModel(namespace,id, FRUIT_BUSH);
            }

            Tags.generateFruitBush(namespace,id);
            MethodLootBuilder.addFruitBush(namespace, id, blockData, properties);
            ReturnMessage.PlantsYMLRegister(name, namespace, id);

        }
    }
    public static class CropRegistry {
        public static void add(String name, String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, FabricBlockSettings BlockSettings, FabricItemSettings ItemSettings, boolean generate){
            int AGE = properties.containsKey("max_age") ? (int) properties.get("max_age") : 7;
            FabricBlockSettings blockSettings = BlockSettings.copyOf(Blocks.WHEAT)
                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                    .sounds(customSound);

            if (dropsNothing) {
                blockSettings.dropsNothing();
            }

            if(!Registries.ITEM.containsId(new Identifier(namespace,id+"_age"))) {
                ItemGroupsContents.load(namespace,id,properties);
                ItemGroupsContents.load(namespace,id+"_seeds",properties);
            }

            Block crop = new CropBlocks7(blockSettings, namespace ,id);
            // 读取更多属性并设置到 blockSettings 中
            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
            try {
                if(AGE>=7){
                    crop = new CropBlocks7(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                } else if(AGE==6) {
                    crop = new CropBlocks6(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                } else if(AGE==5) {
                    crop = new CropBlocks5(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                } else if(AGE==4) {
                    crop = new CropBlocks4(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                } else if(AGE==3) {
                    crop = new CropBlocks3(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                } else if(AGE==2) {
                    crop = new CropBlocks2(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                } else if(AGE==1) {
                    crop = new CropBlocks1(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                } else {
                    crop = new CropBlocks(blockSettings, namespace ,id);
                    registerBlock(namespace, id+"_block", crop);
                    registerBlockItem(namespace, id+"_age", crop);

                    if(Reference.EnvType == EnvType.CLIENT) {
                        setupRenderLayer.set(crop); // 设置渲染层
                    }
                }
            } catch (Exception e) {

            }

            Item seedItem = new AliasedBlockItem(crop,
                    ItemSettings);

            Item item = new Item(ItemSettings);

            try {
                registerSeedItem(namespace, id, seedItem);
                registerItem(namespace, id, item);
            } catch (Exception e) {

            }
            if (generate) {
                ModelBuilder.Crop.getModel(namespace,id,CROP,AGE);
                ModelBuilder.Item.getModel(namespace,id+"_seeds",GENERATE);
                ModelBuilder.Item.getModel(namespace,id,GENERATE);
            }

            ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage

            MethodLootBuilder.addCrop(crop,item,seedItem,AGE);

            ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
        }
    }

    public static class CropHighRegistry {
        public static void add(String name, String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, FabricBlockSettings BlockSettings, FabricItemSettings ItemSettings, boolean generate){
            int AGE = properties.containsKey("max_age") ? (int) properties.get("max_age") : 7;
            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.WHEAT)
                    .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                    .sounds(customSound);

            if (dropsNothing) {
                blockSettings.dropsNothing();
            }

            if(!Registries.ITEM.containsId(new Identifier(namespace,id+"_age"))) {
                ItemGroupsContents.load(namespace,id,properties);
                ItemGroupsContents.load(namespace,id+"_seeds",properties);
            }

            // 读取更多属性并设置到 blockSettings 中
            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
            Block crops = new HighCrops(blockSettings, namespace ,id);

            try {
                registerBlock(namespace, id+"_block", crops);
                registerBlockItem(namespace, id+"_age", crops);
            } catch (Exception e) {
                if(Registries.BLOCK.containsId(new Identifier(namespace,id + "_block"))&&AllowExistingReloading) {
                    setRegistry.set(namespace, id + "_block", crops);
                    Item BlockItem = new BlockItem(crops, ItemSettings);
                    setRegistry.set(namespace, id + "_age", BlockItem);
                }
            }

            Item seeds = new AliasedBlockItem(Registries.BLOCK.get(
                    new Identifier(namespace, id+"_block")),
                    ItemSettings);

            Item product = new Item(ItemSettings);

            try {
                registerSeedItem(namespace, id, seeds);
                registerItem(namespace, id, product);
            } catch (Exception e) {
                if(Registries.ITEM.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                    setRegistry.set(namespace, id + "_seeds", seeds);
                    setRegistry.set(namespace, id, product);
                }
            }
            if (generate) {
                ModelBuilder.Crop.getModel(namespace,id,HIGHCROP,AGE);
                ModelBuilder.Item.getModel(namespace,id+"_seeds",GENERATE);
                ModelBuilder.Item.getModel(namespace,id,GENERATE);
            }


            ReturnMessage.ItemYMLRegister(name, namespace, id); //returnMessage

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(crops); // 设置渲染层
            }

            MethodLootBuilder.addHighCrop(crops,product,seeds);

            ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
        }
    }


    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings()));
    }
    public static Item registerSeedItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id+"_seeds"), item);
    }
    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}
