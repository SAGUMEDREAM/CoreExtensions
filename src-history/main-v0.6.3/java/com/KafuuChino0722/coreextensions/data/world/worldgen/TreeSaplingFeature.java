package com.KafuuChino0722.coreextensions.data.world.worldgen;

import com.KafuuChino0722.coreextensions.data.DatapackRegistry;
import com.KafuuChino0722.coreextensions.data.Provider;

import java.util.Map;

public class TreeSaplingFeature {
    public static void spawnTree(String name, String namespace, String id, Map<String, Object> Key) {
        String path = DatapackRegistry.CORE_PATH + "worldGenerator/data/" + namespace + "/worldgen";
        String block_log = (String) Key.getOrDefault("block_log", "minecraft:oak_log");
        String block_leaves = (String) Key.getOrDefault("block_leaves", "minecraft:oak_leaves");

        Map<String, Object> ConfigKey = (Map<String, Object>) Key.getOrDefault("config", null);
        if (ConfigKey != null) {
            Map<String, Object> configured_feature = (Map<String, Object>) ConfigKey.getOrDefault("configured_feature", null);
            if (configured_feature != null) {
                {
                    Integer trunk_base_height = (Integer) configured_feature.getOrDefault("trunk_base_height", 4);
                    Integer trunk_height_random = (Integer) configured_feature.getOrDefault("trunk_height_random", 2);
                    Integer leaves_reach = (Integer) configured_feature.getOrDefault("leaves_reach", 2);
                    Integer leaves_height = (Integer) configured_feature.getOrDefault("leaves_height", 3);
                    {
                        String content = "{\n" +
                                "  \"type\": \"minecraft:tree\",\n" +
                                "  \"config\": {\n" +
                                "    \"ignore_vines\": true,\n" +
                                "    \"force_dirt\": false,\n" +
                                "    \"minimum_size\": {\n" +
                                "      \"type\": \"minecraft:two_layers_feature_size\",\n" +
                                "      \"limit\": 1,\n" +
                                "      \"lower_size\": 0,\n" +
                                "      \"upper_size\": 1\n" +
                                "    },\n" +
                                "    \"dirt_provider\": {\n" +
                                "      \"type\": \"minecraft:simple_state_provider\",\n" +
                                "      \"state\": {\n" +
                                "        \"Name\": \"minecraft:dirt\"\n" +
                                "      }\n" +
                                "    },\n" +
                                "    \"trunk_provider\": {\n" +
                                "      \"type\": \"minecraft:simple_state_provider\",\n" +
                                "      \"state\": {\n" +
                                "        \"Name\": \"" + block_log + "\",\n" +
                                "        \"Properties\": {\n" +
                                "          \"axis\": \"y\"\n" +
                                "        }\n" +
                                "      }\n" +
                                "    },\n" +
                                "    \"foliage_provider\": {\n" +
                                "      \"type\": \"minecraft:simple_state_provider\",\n" +
                                "      \"state\": {\n" +
                                "        \"Name\": \"" + block_leaves + "\",\n" +
                                "        \"Properties\": {\n" +
                                "          \"distance\": \"7\",\n" +
                                "          \"persistent\": \"false\",\n" +
                                "          \"waterlogged\": \"false\"\n" +
                                "        }\n" +
                                "      }\n" +
                                "    },\n" +
                                "    \"trunk_placer\": {\n" +
                                "      \"type\": \"minecraft:straight_trunk_placer\",\n" +
                                "      \"base_height\": " + trunk_base_height.toString() + ",\n" +
                                "      \"height_rand_a\": " + trunk_height_random.toString() + ",\n" +
                                "      \"height_rand_b\": 0\n" +
                                "    },\n" +
                                "    \"foliage_placer\": {\n" +
                                "      \"type\": \"minecraft:blob_foliage_placer\",\n" +
                                "      \"radius\": " + leaves_reach.toString() + ",\n" +
                                "      \"offset\": 0,\n" +
                                "      \"height\": " + leaves_height.toString() + "\n" +
                                "    },\n" +
                                "    \"decorators\": []\n" +
                                "  }\n" +
                                "}\n";
                        Provider.generate(id, path + "/configured_feature", content);
                    }
                }
            }
        }


        /*if (!Registries.BLOCK.containsId(new Identifier(namespace, id))) {
            try {
                RegistryKey<ConfiguredFeature<?, ?>> CUSTOM_TREE_KEY = RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(namespace, id));
                SaplingGenerator TREE_GENERATION = new SaplingGenerator() {
                    @Nullable
                    @Override
                    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
                        return CUSTOM_TREE_KEY;
                    }
                };
                Block BLOCK_SAPLING = Registry.register(Registries.BLOCK, new Identifier(namespace, id), new SaplingBlock(TREE_GENERATION, FabricBlockSettings.copyOf(Blocks.OAK_SAPLING)));
                Item ITEM_SAPLING = Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(BLOCK_SAPLING, new FabricItemSettings()));

            } catch (Exception e) {

            }
        }

        if (Reference.EnvType == EnvType.CLIENT) {
            ModelBuilder.Block.getModel(namespace, id, ModelBuilder.Block.Types.PLANTS);
        }
        setupRenderLayer.set(Registries.BLOCK.get(new Identifier(namespace, id)));
        provider.add(Registries.BLOCK.get(new Identifier(namespace, id)), name);
        provider.add(Registries.ITEM.get(new Identifier(namespace, id)), name);
        */
    }
}
