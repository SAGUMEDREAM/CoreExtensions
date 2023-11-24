package com.KafuuChino0722.coreextensions.core.api;

import com.KafuuChino0722.coreextensions.block.CropBlocks;
import com.KafuuChino0722.coreextensions.block.CropBlocks7;
import com.KafuuChino0722.coreextensions.block.HighCrops;
import com.KafuuChino0722.coreextensions.core.api.util.ModBlockLootTableGenerator;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.item.Item;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.*;

public class MethodLootBuilder {

    protected void generate(String namespace, String id) {
        //BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(Blocks.WHEAT).properties(StatePredicate.Builder.create().exactMatch(CropBlock.AGE, 7));
        //this.addDrop(Registries.BLOCK.get(new Identifier(namespace, id)), this.cropDrops(Blocks.WHEAT, Items.WHEAT, Items.WHEAT_SEEDS, builder2));
    }
    public static void addCrop(Block crop, Item product, Item seeds, int AGE) {
        respacks.addLootTable(crop.getLootTableId(),
                new VanillaBlockLootTableGenerator().cropDrops(
                        crop,
                        product,
                        seeds,
                        BlockStatePropertyLootCondition.builder(crop)
                                .properties(StatePredicate.Builder.create().exactMatch(CropBlocks7.AGE, AGE))
                ));
    }

    public static void addHighCrop(Block crop, Item product, Item seeds) {
        respacks.addLootTable(crop.getLootTableId(),
                new VanillaBlockLootTableGenerator().cropDrops(
                        crop,
                        product,
                        seeds,
                        BlockStatePropertyLootCondition.builder(crop)
                                .properties(StatePredicate.Builder.create().exactMatch(HighCrops.AGE, 8))
                ));
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


        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id)), ConstantLootNumberProvider.create(1)));
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

        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id)), Registries.ITEM.get(new Identifier(namespace, id)), ConstantLootNumberProvider.create(1)));
        //respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, "potted_"+id)).getLootTableId(), new VanillaBlockLootTableGenerator().pottedPlantDrops(Registries.BLOCK.get(new Identifier(namespace, "potted"+id))));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addDoor(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().doorDrops(Registries.BLOCK.get(new Identifier(namespace, id))));

        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addSign(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        Identifier[] z = {
                new Identifier(namespace, id+"_"+"standing"),
                new Identifier(namespace, id+"_"+"wall"),
                new Identifier(namespace, id+"_"+"hanging"),
                new Identifier(namespace, id+"_"+"wall_hanging")
        };

        for (Identifier e : z) {
            respacks.addLootTable(Registries.BLOCK.get(e).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.ITEM.get(new Identifier(namespace, id))).type(LootContextTypes.BLOCK).randomSequenceId(new Identifier(namespace,"blocks/"+id)));
            Require(e.getNamespace(), e.getPath(), blockData, properties);
            RequireLevel(e.getNamespace(), e.getPath(), blockData, properties);
        }
    }

    public static void addDrop(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id))).type(LootContextTypes.BLOCK).randomSequenceId(new Identifier(namespace,"blocks/"+id)));

        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addDropWithSilkTouch(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {

        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), VanillaBlockLootTableGenerator.dropsWithSilkTouch(Registries.ITEM.get(new Identifier(namespace, id))));

        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addDropPath(String namespace, String id, String path, Map<String, Object> blockData, Map<String, Object> properties) {

        respacks.addLootTable(new Identifier(namespace, path+id), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id))).type(LootContextTypes.BLOCK).randomSequenceId(new Identifier(namespace,"blocks/"+id)));

        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addDrop(Block block, Map<String, Object> blockData, Map<String, Object> properties) {

        Identifier identifier = block.getLootTableId();
        //respacks.addLootTable(block.getLootTableId(), new VanillaBlockLootTableGenerator().drops(block));
        respacks.addLootTable(block.getLootTableId(), new VanillaBlockLootTableGenerator().drops(block));

        Require(block, blockData, properties);
        RequireLevel(block, blockData, properties);
    }

    public static void addLogDrop(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {


        respacks.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id)), Registries.ITEM.get(new Identifier(namespace, id)), ConstantLootNumberProvider.create(1)));


        Require(namespace, id, blockData, properties);
        RequireLevel(namespace, id, blockData, properties);
    }

    public static void addDrop(String namespace, String Key, Block block, Item item) {
        respacks.addLootTable(block.getLootTableId(),
                new VanillaBlockLootTableGenerator()
                        .drops(item));
    }

    public static void RequireLevel(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {
        int LEVEL = properties.containsKey("level") ? (int) properties.get("level") : 0;
        if (LEVEL == 1) {

            TAG_NEEDS_STONE_TOOL.add(new Identifier(namespace,id));

        } else if (LEVEL == 2) {

            TAG_NEEDS_IRON_TOOL.add(new Identifier(namespace,id));

        } else if (LEVEL == 3) {

            TAG_NEEDS_DIAMOND_TOOL.add(new Identifier(namespace,id));

        } else if (LEVEL == 4) {

            TAG_NEEDS_NETHERITE_TOOL.add(new Identifier(namespace,id));

        } else if (LEVEL > 4) {
            int finalLEVEL = LEVEL;


                //respacks.addTag(IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(finalLEVEL)).add(new Identifier(namespace,id)));


        } else {
            LEVEL = 0;
        }
    }

    public static void RequireLevel(Block block, Map<String, Object> blockData, Map<String, Object> properties) {
        int LEVEL = properties.containsKey("level") ? (int) properties.get("level") : 0;
        if (LEVEL == 1) {

            TAG_NEEDS_STONE_TOOL.add(block);

        } else if (LEVEL == 2) {

            TAG_NEEDS_IRON_TOOL.add(block);

        } else if (LEVEL == 3) {

            TAG_NEEDS_DIAMOND_TOOL.add(block);

        } else if (LEVEL == 4) {

            TAG_NEEDS_NETHERITE_TOOL.add(block);

        } else if (LEVEL > 4) {
            int finalLEVEL = LEVEL;


            //respacks.addTag(IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(finalLEVEL)).add(new Identifier(namespace,id)));


        } else {
            LEVEL = 0;
        }
    }
        public static void Require(String namespace, String id, Map<String, Object> blockData, Map<String, Object> properties) {
            String TOOL = properties.containsKey("require") ? (String) properties.get("require") : "EMPTY";

            if (Objects.equals(TOOL, "AXE") || Objects.equals(TOOL, "axe")) {

                TAG_AXE_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "PICKAXE") || Objects.equals(TOOL, "pickaxe")) {

                TAG_PICKAXE_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "SHOVEL") || Objects.equals(TOOL, "shovel")) {

                TAG_SHOVEL_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "HOE") || Objects.equals(TOOL, "hoe")) {

                TAG_HOE_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "SWORD") || Objects.equals(TOOL, "sword")) {

                TAG_SWORD_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "SHEARS") || Objects.equals(TOOL, "shears")) {

                TAG_SHEARS_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "NULL") || Objects.equals(TOOL, "null") || Objects.equals(TOOL, "EMPTY") || Objects.equals(TOOL, "empty")) {

            } else {

            }
    }

    public static void Require(Block block, Map<String, Object> blockData, Map<String, Object> properties) {
        String TOOL = properties.containsKey("require") ? (String) properties.get("require") : "EMPTY";

        if (Objects.equals(TOOL, "AXE") || Objects.equals(TOOL, "axe")) {


            TAG_AXE_MINEABLE.add(block);


        } else if (Objects.equals(TOOL, "PICKAXE") || Objects.equals(TOOL, "pickaxe")) {

            TAG_PICKAXE_MINEABLE.add(block);

        } else if (Objects.equals(TOOL, "SHOVEL") || Objects.equals(TOOL, "shovel")) {

            TAG_SHOVEL_MINEABLE.add(block);

        } else if (Objects.equals(TOOL, "HOE") || Objects.equals(TOOL, "hoe")) {

            TAG_HOE_MINEABLE.add(block);

        } else if (Objects.equals(TOOL, "SWORD") || Objects.equals(TOOL, "sword")) {

            TAG_SWORD_MINEABLE.add(block);


        } else if (Objects.equals(TOOL, "SHEARS") || Objects.equals(TOOL, "shears")) {

            TAG_SHEARS_MINEABLE.add(block);

        } else if (Objects.equals(TOOL, "NULL") || Objects.equals(TOOL, "null") || Objects.equals(TOOL, "EMPTY") || Objects.equals(TOOL, "empty")) {

        } else {

        }
    }

        public static void Require(String namespace, String id, Map<String, Object> blockData, String TOOL) {
            Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");

            if (Objects.equals(TOOL, "AXE") || Objects.equals(TOOL, "axe")) {


                TAG_AXE_MINEABLE.add(new Identifier(namespace,id));


            } else if (Objects.equals(TOOL, "PICKAXE") || Objects.equals(TOOL, "pickaxe")) {

                TAG_PICKAXE_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "SHOVEL") || Objects.equals(TOOL, "shovel")) {

                TAG_SHOVEL_MINEABLE.add(new Identifier(namespace,id));

            } else if (Objects.equals(TOOL, "HOE") || Objects.equals(TOOL, "hoe")) {


                TAG_HOE_MINEABLE.add(new Identifier(namespace,id));


            } else if (Objects.equals(TOOL, "SWORD") || Objects.equals(TOOL, "sword")) {


                TAG_SWORD_MINEABLE.add(new Identifier(namespace,id));



            } else if (Objects.equals(TOOL, "SHEARS") || Objects.equals(TOOL, "shears")) {


                TAG_SHEARS_MINEABLE.add(new Identifier(namespace,id));


            } else if (Objects.equals(TOOL, "NULL") || Objects.equals(TOOL, "null") || Objects.equals(TOOL, "EMPTY") || Objects.equals(TOOL, "empty")) {

            } else {

            }
    }
}