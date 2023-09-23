package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.block.CropBlocks;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class Loots {

    protected void generate(String namespace, String id) {
        //BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(Blocks.WHEAT).properties(StatePredicate.Builder.create().exactMatch(CropBlock.AGE, 7));
        //this.addDrop(Registries.BLOCK.get(new Identifier(namespace, id)), this.cropDrops(Blocks.WHEAT, Items.WHEAT, Items.WHEAT_SEEDS, builder2));
    }

    public static void addCrop(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties, int AGE) {
        String blockID = id+"_block";
        String seedID = id+"_seeds";

            /*packs.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(),
                    new VanillaBlockLootTableGenerator().drops(Registries.ITEM.get(new Identifier(namespace, id+"_seeds")), ConstantLootNumberProvider.create(1)));
*/
            respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, blockID)).getLootTableId(),
                    new VanillaBlockLootTableGenerator().cropDrops(
                            Registries.BLOCK.get(new Identifier(namespace, blockID)),
                            Registries.ITEM.get(new Identifier(namespace, id)),
                            Registries.ITEM.get(new Identifier(namespace, seedID)),
                            BlockStatePropertyLootCondition.builder(Registries.BLOCK.get(new Identifier(namespace, blockID)))
                                    .properties(StatePredicate.Builder.create().exactMatch(CropBlocks.AGE, AGE))
                            ));
            //respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.CROPS).add(new Identifier(namespace,blockID)));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addHighPlant(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        
        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().flowerbedDrops(Registries.BLOCK.get(new Identifier(namespace, id))));

        //respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, "potted_"+id)).getLootTableId(), new VanillaBlockLootTableGenerator().pottedPlantDrops(Registries.BLOCK.get(new Identifier(namespace, "potted_"+id))));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addFruitBush(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        
        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.ITEM.get(new Identifier(namespace, id)), ConstantLootNumberProvider.create(1)));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addSeed(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties,String seed) {

        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new ModBlockLootTableGenerator(seed).seedDrops(Registries.BLOCK.get(new Identifier(namespace, id))));
        //respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, "potted_"+id)).getLootTableId(), new VanillaBlockLootTableGenerator().pottedPlantDrops(Registries.BLOCK.get(new Identifier(namespace, "potted"+id))));

        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addPlant(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id)), ConstantLootNumberProvider.create(1)));
        //respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, "potted_"+id)).getLootTableId(), new VanillaBlockLootTableGenerator().pottedPlantDrops(Registries.BLOCK.get(new Identifier(namespace, "potted"+id))));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addDoor(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        
        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().doorDrops(Registries.BLOCK.get(new Identifier(namespace, id))));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addDrop(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        
        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id)), ConstantLootNumberProvider.create(1)));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }
    public static void RequireLevel(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {
        int LEVEL = properties.containsKey("level") ? (int) properties.get("level") : 0;
        if (LEVEL == 1) {

                
                respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_STONE_TOOL).add(new Identifier(namespace,id)));
                

        } else if (LEVEL == 2) {

                
                respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_IRON_TOOL).add(new Identifier(namespace,id)));
                

        } else if (LEVEL == 3) {

                
                respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_DIAMOND_TOOL).add(new Identifier(namespace,id)));
                

        } else if (LEVEL == 4) {

                
                respacks.addTag(IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(4)).add(new Identifier(namespace,id)));
                

        } else if (LEVEL > 4) {
            int finalLEVEL = LEVEL;

                
                respacks.addTag(IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(finalLEVEL)).add(new Identifier(namespace,id)));
                

        } else {
            LEVEL = 0;
            }
    }
        public static void Require(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {
            String TOOL = properties.containsKey("require") ? (String) properties.get("require") : "EMPTY";

            if (Objects.equals(TOOL, "AXE") || Objects.equals(TOOL, "axe")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE).add(new Identifier(namespace,id)));
                    
    
            } else if (Objects.equals(TOOL, "PICKAXE") || Objects.equals(TOOL, "pickaxe")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE).add(new Identifier(namespace,id)));
                    
    

            } else if (Objects.equals(TOOL, "SHOVEL") || Objects.equals(TOOL, "shovel")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE).add(new Identifier(namespace,id)));
                    
    

            } else if (Objects.equals(TOOL, "HOE") || Objects.equals(TOOL, "hoe")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE).add(new Identifier(namespace,id)));
                    
    
            } else if (Objects.equals(TOOL, "SWORD") || Objects.equals(TOOL, "sword")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SWORD_MINEABLE).add(new Identifier(namespace,id)));
                    
    

            } else if (Objects.equals(TOOL, "SHEARS") || Objects.equals(TOOL, "shears")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE).add(new Identifier(namespace,id)));
                    

            } else if (Objects.equals(TOOL, "NULL") || Objects.equals(TOOL, "null") || Objects.equals(TOOL, "EMPTY") || Objects.equals(TOOL, "empty")) {

            } else {

        }
    }

        public static void Require(String namespace, String id, Map<String, Object> blockData, String TOOL) {
            Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
            RuntimeResourcePack respacks = RuntimeResourcePack.create(new Identifier(namespace, id+"_breakonly"));

            if (Objects.equals(TOOL, "AXE") || Objects.equals(TOOL, "axe")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE).add(new Identifier(namespace,id)));
                    
    
            } else if (Objects.equals(TOOL, "PICKAXE") || Objects.equals(TOOL, "pickaxe")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE).add(new Identifier(namespace,id)));
                    
    

            } else if (Objects.equals(TOOL, "SHOVEL") || Objects.equals(TOOL, "shovel")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE).add(new Identifier(namespace,id)));
                    
    

            } else if (Objects.equals(TOOL, "HOE") || Objects.equals(TOOL, "hoe")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE).add(new Identifier(namespace,id)));
                    
    
            } else if (Objects.equals(TOOL, "SWORD") || Objects.equals(TOOL, "sword")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SWORD_MINEABLE).add(new Identifier(namespace,id)));
                    
    

            } else if (Objects.equals(TOOL, "SHEARS") || Objects.equals(TOOL, "shears")) {
    
                    
                    respacks.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE).add(new Identifier(namespace,id)));
                    
    

            } else if (Objects.equals(TOOL, "NULL") || Objects.equals(TOOL, "null") || Objects.equals(TOOL, "EMPTY") || Objects.equals(TOOL, "empty")) {

            } else {

            }
    }
}