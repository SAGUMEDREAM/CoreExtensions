package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.block.FruitBushBlock;
import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.MethodRarity;
import com.KafuuChino0722.coreextensions.core.api.MethodSound;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.core.api.MethodLootBuilder;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.effect.StatusEffect;
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
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;


public class PlantRegistry {

    public static void register() {
        Map<String, Map<String, Object>> blocksData = IOFileManager.read("plant.yml");
        load(blocksData);
        Map<String, Map<String, Object>> blocksDataZ = IOFileManager.readZip("plant.yml");
        load(blocksDataZ);
    }

    public static void load(Map<String, Map<String, Object>> blocksData) {
        if (blocksData != null && blocksData.containsKey("blocks")) {
            Map<String, Object> blocks = blocksData.get("blocks");

            for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                    String name = (String) blockData.get("name");
                    String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                    String id = (String) blockData.get("id");
                    String types = blockData.containsKey("types") ? (String) blockData.get("types") : "normal";

                    FabricBlockSettings BlockSettings = FabricBlockSettings.create();
                    FabricItemSettings ItemSettings = new FabricItemSettings();

                    int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;
                    ItemSettings = ItemSettings.maxCount(maxCount);

                    Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
                    boolean dropsNothing = properties.containsKey("dropsNothing") && (boolean) properties.get("dropsNothing");

                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                    double hardness = (double) properties.getOrDefault("hardness",0.0);
                    BlockSettings = BlockSettings.hardness((float) hardness);;
                    double resistance = (double) properties.getOrDefault("resistance",0.0);
                    BlockSettings = BlockSettings.strength((float) resistance);

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

                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                        ItemGroupsContents.load(namespace,id,properties);
                    }

                    if(types.equalsIgnoreCase("normal")){
                        Plant.add(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, BlockSettings, ItemSettings, generate);
                    } else if(types.equalsIgnoreCase("high")||types.equalsIgnoreCase("highblock")||types.equalsIgnoreCase("high_block")) {
                        HighPlant.add(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, BlockSettings, ItemSettings, generate);
                    } else if(types.equalsIgnoreCase("seed")) {
                        SeedBlock.add(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, BlockSettings, ItemSettings, generate);
                    }

                    provider.add("item." +namespace +"."+id, name)
                            .add("block." +namespace +"."+id, name)
                            .add("block." +namespace +".potted_"+id, name);

                }
            }
        }
    }

    public static class Plant {
        public static void add(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, FabricBlockSettings BlockSettings, FabricItemSettings ItemSettings, boolean generate){
            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.OXEYE_DAISY)
                    .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                    .sounds(customSound)
                    .nonOpaque()
                    .noCollision()
                    .pistonBehavior(PistonBehavior.DESTROY);
            FabricBlockSettings potblockSettings = FabricBlockSettings.copyOf(Blocks.POTTED_OXEYE_DAISY)
                    .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                    .nonOpaque();

            if (dropsNothing) {
                blockSettings.dropsNothing();
            }

            if(properties.containsKey("require")) {
                blockSettings.requiresTool();
            }

            Map<String, Object> setting = properties.containsKey("settings") ? (Map<String, Object>) properties.get("settings") : null;
            String effectId = setting.containsKey("effect") ? (String) setting.get("effect") : "speed";
            StatusEffect EFFECT = Registries.STATUS_EFFECT.get(new Identifier(effectId));
            int effectDuration = setting.containsKey("effectDuration") ? (int) setting.get("effectDuration") : 8;
            // 读取更多属性并设置到 blockSettings 中
            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
            Block block = new FlowerBlock(EFFECT, effectDuration, blockSettings);
            Block potblock = new FlowerPotBlock(block, potblockSettings);
            Item ItemBlock = new BlockItem(block, new FabricItemSettings().maxCount(maxCount));
            Item ItemBlockPot = new BlockItem(potblock, new FabricItemSettings().maxCount(maxCount));
            try {
                registerBlock(namespace, id, block);
                registerBlock(namespace, "potted_" + id, potblock);
                registerBlockItem(namespace, id, block, maxCount);
                registerBlockItem(namespace, "potted_" + id, potblock, maxCount);
            } catch (Exception e) {
                if(Registries.BLOCK.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                    setRegistry.set(namespace, id, block);
                    Item oldItem1 = Registries.ITEM.get(new Identifier(namespace,"potted_" + id));
                    setRegistry.set(namespace, "potted_" + id, potblock);
                    WorldRegistryDataReloading.run(ItemBlockPot,oldItem1);
                    setRegistry.set(namespace, id, ItemBlock);
                    Item oldItem2 = Registries.ITEM.get(new Identifier(namespace,"potted_" + id));
                    setRegistry.set(namespace, "potted_" + id, ItemBlockPot);
                    WorldRegistryDataReloading.run(ItemBlockPot,oldItem2);
                }
            }

            if (Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);
                setupRenderLayer.set(potblock);// 设置渲染层
            }

            if (generate) {
                ModelBuilder.Block.getModel(namespace,id, ModelBuilder.Block.Types.PLANTS);
            }

            Tags.generateSmallFlower(namespace, id);
            MethodLootBuilder.addPlant(namespace, id, blockData, properties);
            ReturnMessage.PlantsYMLRegister(name, namespace, id); //returnMessage
        }
    }
    public static class SeedBlock {
        public static void add(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, FabricBlockSettings BlockSettings, FabricItemSettings ItemSettings, boolean generate){
            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.GRASS)
                    .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                    .sounds(customSound)
                    .nonOpaque()
                    .noCollision()
                    .pistonBehavior(PistonBehavior.DESTROY);

            FabricBlockSettings potblockSettings = FabricBlockSettings.copyOf(Blocks.GRASS)
                    .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                    .nonOpaque();
            if (dropsNothing) {
                blockSettings.dropsNothing();
            }
            Map<String, Object> setting = properties.containsKey("settings") ? (Map<String, Object>) properties.get("settings") : null;
            String seed = setting.containsKey("seed") ? (String) setting.get("seed") : "minecraft:wheat_seeds";
            String effectId = setting.containsKey("effect") ? (String) setting.get("effect") : "speed";
            StatusEffect EFFECT = Registries.STATUS_EFFECT.get(new Identifier(effectId));
            int effectDuration = setting.containsKey("effectDuration") ? (int) setting.get("effectDuration") : 8;
            // 读取更多属性并设置到 blockSettings 中
            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
            Block block = new FlowerBlock(EFFECT, effectDuration, blockSettings);
            Block potblock = new FlowerPotBlock(block, potblockSettings);
            Item ItemBlock = new BlockItem(block, new FabricItemSettings().maxCount(maxCount));
            Item ItemBlockPot = new BlockItem(potblock, new FabricItemSettings().maxCount(maxCount));
            try {
                registerBlock(namespace, id, block);
                registerBlock(namespace, "potted_" + id, potblock);
                registerBlockItem(namespace, id, block, maxCount);
                registerBlockItem(namespace, "potted_" + id, potblock, maxCount);
            } catch (Exception e) {
                if(Registries.BLOCK.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                    setRegistry.set(namespace, id, block);
                    Item oldItem1 = Registries.ITEM.get(new Identifier(namespace,id));
                    setRegistry.set(namespace, "potted_" + id, potblock);
                    WorldRegistryDataReloading.run(ItemBlock,oldItem1);
                    setRegistry.set(namespace, id, ItemBlock);
                    Item oldItem2 = Registries.ITEM.get(new Identifier(namespace,"potted_"+id));
                    setRegistry.set(namespace, "potted_" + id, ItemBlockPot);
                    WorldRegistryDataReloading.run(ItemBlockPot,oldItem2);
                }
            }

            if (Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);
                setupRenderLayer.set(potblock);// 设置渲染层
            }

            if (generate) {
                ModelBuilder.Block.getModel(namespace,id, ModelBuilder.Block.Types.PLANTS);
            }

            Tags.generateSeed(namespace, id);
            MethodLootBuilder.addSeed(namespace, id, blockData, properties, seed);
            ReturnMessage.PlantsYMLRegister(name, namespace, id); //returnMessage
        }
    }

    public static class fruitbush {
        public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, boolean generate){

            // 读取更多属性并设置到 blockSettings 中
            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.GRASS)
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
            Item foodItem = new AliasedBlockItem(block ,new Item.Settings().food(foodComponent).maxCount(maxCount));;

            if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                ItemGroupsContents.load(namespace,id,properties);
            }

            try {
                registerBlock(namespace, id, block);
                registerItem(namespace, id, foodItem);
            } catch (Exception e) {
                if(Registries.BLOCK.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                    setRegistry.set(namespace, id, block);
                    Item oldItem1 = Registries.ITEM.get(new Identifier(namespace,id));
                    setRegistry.set(namespace, id, foodItem);
                    WorldRegistryDataReloading.run(foodItem,oldItem1);
                }
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);
            }
            if(generate) {
                ModelBuilder.Block.getModel(namespace,id, ModelBuilder.Block.Types.FRUIT_BUSH);
            }

            Tags.generateFruitBush(namespace,id);
            MethodLootBuilder.addFruitBush(namespace, id, blockData, properties);
            ReturnMessage.PlantsYMLRegister(name, namespace, id);

        }
    }

    public static class HighPlant {
        public static void add(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, FabricBlockSettings BlockSettings, FabricItemSettings ItemSettings, boolean generate){
            FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.OXEYE_DAISY)
                    .lightLevel((int) properties.getOrDefault("lightLevel", 0))
                    .sounds(customSound)
                    .nonOpaque()
                    .noCollision()
                    .pistonBehavior(PistonBehavior.DESTROY);
            if (dropsNothing) {
                blockSettings.dropsNothing();
            }
            // 读取更多属性并设置到 blockSettings 中
            // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
            Block block = new TallFlowerBlock(blockSettings);
            Item item = new BlockItem(block, new FabricItemSettings().maxCount(maxCount));
            try {
                registerBlock(namespace, id, block);
                registerBlockItem(namespace, id, block, maxCount);
            } catch (Exception e) {
                if(Registries.BLOCK.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                    setRegistry.set(namespace, id, block);
                    Item oldItem1 = Registries.ITEM.get(new Identifier(namespace,id));
                    setRegistry.set(namespace, id, item);
                    WorldRegistryDataReloading.run(item,oldItem1);
                }
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);// 设置渲染层
            }

            if(generate) {
                ModelBuilder.Block.getModel(namespace,id, ModelBuilder.Block.Types.HIGH_PLANTS);
            }


            Tags.generateHighFlower(namespace,id);
            MethodLootBuilder.addHighPlant(namespace, id, blockData, properties);
            ReturnMessage.PlantsYMLRegister(name, namespace, id);
        }
    }
    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block, int maxCount) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings().maxCount(maxCount)));
    }

    public static Item registerItem(String namespace, String id, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }
}