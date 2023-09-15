package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.block.CropBlocks;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.fabricmc.fabric.impl.mininglevel.MiningLevelManagerImpl;
import net.minecraft.block.BeetrootsBlock;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.data.server.loottable.vanilla.VanillaBlockLootTableGenerator;
import net.minecraft.item.Items;
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

public class Loots {

    protected void generate(String namespace, String id) {
        //BlockStatePropertyLootCondition.Builder builder2 = BlockStatePropertyLootCondition.builder(Blocks.WHEAT).properties(StatePredicate.Builder.create().exactMatch(CropBlock.AGE, 7));
        //this.addDrop(Registries.BLOCK.get(new Identifier(namespace, id)), this.cropDrops(Blocks.WHEAT, Items.WHEAT, Items.WHEAT_SEEDS, builder2));
    }

    public static void CROP(String namespace, String id, Map<String, Object> blockData) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_seed_item"));
        String blockID = id+"_block";
        String seedID = id+"_seeds";

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            /*packs.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(),
                    new VanillaBlockLootTableGenerator().drops(Registries.ITEM.get(new Identifier(namespace, id+"_seeds")), ConstantLootNumberProvider.create(1)));
*/
            packs.addLootTable(Registries.BLOCK.get(new Identifier(namespace, blockID)).getLootTableId(),
                    new VanillaBlockLootTableGenerator().cropDrops(
                            Registries.BLOCK.get(new Identifier(namespace, blockID)),
                            Registries.ITEM.get(new Identifier(namespace, id)),
                            Registries.ITEM.get(new Identifier(namespace, seedID)),
                            BlockStatePropertyLootCondition.builder(Registries.BLOCK.get(new Identifier(namespace, blockID)))
                                    .properties(StatePredicate.Builder.create().exactMatch(CropBlocks.AGE, 7))
                            ));
            packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.CROPS).add(new Identifier(namespace,blockID)));

            b.add(packs);
        });

        NEED(namespace, id, blockData);
        LEVEL(namespace, id, blockData);
    }

    public static void ONLY(String namespace, String id, Map<String, Object> blockData) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_loots"));
        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addLootTable(Registries.BLOCK.get(new Identifier(namespace, id)).getLootTableId(), new VanillaBlockLootTableGenerator().drops(Registries.BLOCK.get(new Identifier(namespace, id)), ConstantLootNumberProvider.create(1)));
            b.add(packs);
        });

        NEED(namespace, id, blockData);
        LEVEL(namespace, id, blockData);
    }
    public static void LEVEL(String namespace, String id, Map<String, Object> blockData) {
        int LEVEL = blockData.containsKey("level") ? (int) blockData.get("level") : 0;
        RuntimeResourcePack packs_level = RuntimeResourcePack.create(new Identifier(namespace, id+"_levelloots"));
        if (LEVEL == 1) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_level.clearResources();
                packs_level.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_STONE_TOOL).add(new Identifier(namespace,id)));
                b.add(packs_level);
            });
        } else if (LEVEL == 2) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_level.clearResources();
                packs_level.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_IRON_TOOL).add(new Identifier(namespace,id)));
                b.add(packs_level);
            });
        } else if (LEVEL == 3) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_level.clearResources();
                packs_level.addTag(IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_DIAMOND_TOOL).add(new Identifier(namespace,id)));
                b.add(packs_level);
            });
        } else if (LEVEL == 4) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_level.clearResources();
                packs_level.addTag(IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(4)).add(new Identifier(namespace,id)));
                b.add(packs_level);
            });
        } else if (LEVEL > 4) {
            int finalLEVEL = LEVEL;
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_level.clearResources();
                packs_level.addTag(IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(finalLEVEL)).add(new Identifier(namespace,id)));
                b.add(packs_level);
            });
        } else {
            LEVEL = 0;
            }
    }
        public static void NEED(String namespace, String id, Map<String, Object> blockData) {
            String TOOL = blockData.containsKey("require") ? (String) blockData.get("require") : "EMPTY";
            RuntimeResourcePack packs_only = RuntimeResourcePack.create(new Identifier(namespace, id+"_breakonly"));

            if (Objects.equals(TOOL, "AXE") || Objects.equals(TOOL, "axe")) {
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs_only.clearResources();
                    packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE).add(new Identifier(namespace,id)));
                    b.add(packs_only);
                });
            } else if (Objects.equals(TOOL, "PICKAXE") || Objects.equals(TOOL, "pickaxe")) {
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs_only.clearResources();
                    packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE).add(new Identifier(namespace,id)));
                    b.add(packs_only);
                });

            } else if (Objects.equals(TOOL, "SHOVEL") || Objects.equals(TOOL, "shovel")) {
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs_only.clearResources();
                    packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE).add(new Identifier(namespace,id)));
                    b.add(packs_only);
                });

            } else if (Objects.equals(TOOL, "HOE") || Objects.equals(TOOL, "hoe")) {
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs_only.clearResources();
                    packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE).add(new Identifier(namespace,id)));
                    b.add(packs_only);
                });
            } else if (Objects.equals(TOOL, "SWORD") || Objects.equals(TOOL, "sword")) {
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs_only.clearResources();
                    packs_only.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SWORD_MINEABLE).add(new Identifier(namespace,id)));
                    b.add(packs_only);
                });

            } else if (Objects.equals(TOOL, "SHEARS") || Objects.equals(TOOL, "shears")) {
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs_only.clearResources();
                    packs_only.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE).add(new Identifier(namespace,id)));
                    b.add(packs_only);
                });

            } else if (Objects.equals(TOOL, "NULL") || Objects.equals(TOOL, "null") || Objects.equals(TOOL, "EMPTY") || Objects.equals(TOOL, "empty")) {

            } else {

        }
    }

    public static void NEED_GET(String namespace, String id, Map<String, Object> blockData, String TOOL) {
        RuntimeResourcePack packs_only = RuntimeResourcePack.create(new Identifier(namespace, id+"_breakonly"));

        if (Objects.equals(TOOL, "AXE") || Objects.equals(TOOL, "axe")) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_only.clearResources();
                packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE).add(new Identifier(namespace,id)));
                b.add(packs_only);
            });
        } else if (Objects.equals(TOOL, "PICKAXE") || Objects.equals(TOOL, "pickaxe")) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_only.clearResources();
                packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE).add(new Identifier(namespace,id)));
                b.add(packs_only);
            });

        } else if (Objects.equals(TOOL, "SHOVEL") || Objects.equals(TOOL, "shovel")) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_only.clearResources();
                packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE).add(new Identifier(namespace,id)));
                b.add(packs_only);
            });

        } else if (Objects.equals(TOOL, "HOE") || Objects.equals(TOOL, "hoe")) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_only.clearResources();
                packs_only.addTag(IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE).add(new Identifier(namespace,id)));
                b.add(packs_only);
            });
        } else if (Objects.equals(TOOL, "SWORD") || Objects.equals(TOOL, "sword")) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_only.clearResources();
                packs_only.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SWORD_MINEABLE).add(new Identifier(namespace,id)));
                b.add(packs_only);
            });

        } else if (Objects.equals(TOOL, "SHEARS") || Objects.equals(TOOL, "shears")) {
            RRPCallback.BEFORE_VANILLA.register(b -> {
                packs_only.clearResources();
                packs_only.addTag(IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE).add(new Identifier(namespace,id)));
                b.add(packs_only);
            });

        } else if (Objects.equals(TOOL, "NULL") || Objects.equals(TOOL, "null") || Objects.equals(TOOL, "EMPTY") || Objects.equals(TOOL, "empty")) {

        } else {

        }
    }
}