package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.block.FruitBushBlock;
import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.*;
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

public class Plants {
    public static class normal {
        public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, boolean generate){
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
                setRegistry.set(namespace, id, block);
                setRegistry.set(namespace, "potted_" + id, potblock);
                setRegistry.set(namespace, id, ItemBlock);
                setRegistry.set(namespace, "potted_" + id, ItemBlockPot);
            }

            if (Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);
                setupRenderLayer.set(potblock);// 设置渲染层
            }

            if (generate) {
                Models.generate(namespace, id, "PLANTS");
            }
            Tags.Block.generateTags(namespace, id, properties);
            Tags.generateSmallFlower(namespace, id);
            Loots.addPlant(namespace, id, blockData, properties);
            ReturnMessage.PlantsYMLRegister(name, namespace, id); //returnMessage
        }
    }
    public static class seeds {
        public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, boolean generate){
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
                setRegistry.set(namespace, id, block);
                setRegistry.set(namespace, "potted_" + id, potblock);
                setRegistry.set(namespace, id, ItemBlock);
                setRegistry.set(namespace, "potted_" + id, ItemBlockPot);
            }

            if (Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);
                setupRenderLayer.set(potblock);// 设置渲染层
            }

            if (generate) {
                Models.generate(namespace, id, "PLANTS");
            }
            Tags.Block.generateTags(namespace, id, properties);
            Tags.generateSeed(namespace, id);
            Loots.addSeed(namespace, id, blockData, properties, seed);
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
                RegItemGroupsContents.load(namespace,id,properties);
            }

            try {
                registerBlock(namespace, id, block);
                registerItem(namespace, id, foodItem);
            } catch (Exception e) {
                setRegistry.set(namespace,id,block);
                setRegistry.set(namespace,id,foodItem);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);
            }
            if(generate) {
                Models.generate(namespace, id, "FRUITBUSH");
            }
            Tags.Block.generateTags(namespace,id,properties);
            Tags.generateFruitBush(namespace,id);
            Loots.addFruitBush(namespace, id, blockData, properties);
            ReturnMessage.PlantsYMLRegister(name, namespace, id);




        }
    }

    public static class high {
        public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound, boolean generate){
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
                setRegistry.set(namespace,id,block);
                setRegistry.set(namespace,id,item);
            }

            if(Reference.EnvType == EnvType.CLIENT) {
                setupRenderLayer.set(block);// 设置渲染层
            }

            if(generate) {
                Models.generate(namespace, id, "HIGH_PLANTS");
            }

            Tags.Block.generateTags(namespace,id,properties);
            Tags.generateHighFlower(namespace,id);
            Loots.addHighPlant(namespace, id, blockData, properties);
            ReturnMessage.PlantsYMLRegister(name, namespace, id);
        }
    }

    //API-Lib
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