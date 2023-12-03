package com.KafuuChino0722.coreextensions.data.world.worldgen;

import com.KafuuChino0722.coreextensions.core.api.util.Int;
import com.KafuuChino0722.coreextensions.data.DatapackRegistry;
import com.KafuuChino0722.coreextensions.data.Provider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.carver.CaveCarver;

import java.util.Map;

public class DimensionFeature {
    public static void createWorld(String name, String namespace, String id, Map<String, Object> Key) {
        Map<String, Object> ConfigKey = (Map<String, Object>) Key.getOrDefault("config", null);
        if (ConfigKey != null) {
            Map<String, Object> dim_type = (Map<String, Object>) ConfigKey.getOrDefault("dim_type", null);
            Map<String, Object> dim = (Map<String, Object>) ConfigKey.getOrDefault("dim_type", null);
            if (dim_type != null) {
                {
                    Boolean ultrawarm = (Boolean) dim_type.getOrDefault("ultrawarm", false);
                    Boolean natural = (Boolean) dim_type.getOrDefault("natural", true);
                    Boolean piglin_safe = (Boolean) dim_type.getOrDefault("piglin_safe", false);
                    Boolean respawn_anchor_works = (Boolean) dim_type.getOrDefault("respawn_anchor_works", false);
                    Boolean bed_works = (Boolean) dim_type.getOrDefault("bed_works", false);
                    Boolean has_raids = (Boolean) dim_type.getOrDefault("has_raids", false);
                    Boolean has_skylight = (Boolean) dim_type.getOrDefault("has_skylight", false);
                    Boolean has_ceiling = (Boolean) dim_type.getOrDefault("has_ceiling", true);
                    Integer coordinate_scale = (Integer) dim_type.getOrDefault("coordinate_scale", 1);
                    Integer ambient_light = (Integer) dim_type.getOrDefault("ambient_light", 0);
                    Integer logical_height = (Integer) dim_type.getOrDefault("logical_height", 384);
                    String effects = (String) dim_type.getOrDefault("effects", "minecraft:overworld");
                    String infiniburn = (String) dim_type.getOrDefault("infiniburn", "#minecraft:infiniburn_overworld");
                    Integer min_y = (Integer) dim_type.getOrDefault("min_y", -64);
                    Integer height = (Integer) dim_type.getOrDefault("height", 384);
                    Integer monster_spawn_block_light_limit = (Integer) dim_type.getOrDefault("monster_spawn_block_light_limit", 384);
                    {
                        String content = "{\n" +
                                "  \"ultrawarm\": "+ultrawarm.toString()+",\n" +
                                "  \"natural\": "+natural.toString()+",\n" +
                                "  \"piglin_safe\": "+piglin_safe.toString()+",\n" +
                                "  \"respawn_anchor_works\": "+respawn_anchor_works.toString()+",\n" +
                                "  \"bed_works\": "+bed_works.toString()+",\n" +
                                "  \"has_raids\": "+has_raids.toString()+",\n" +
                                "  \"has_skylight\": "+has_skylight.toString()+",\n" +
                                "  \"has_ceiling\": "+has_ceiling.toString()+",\n" +
                                "  \"coordinate_scale\": "+coordinate_scale.toString()+",\n" +
                                "  \"ambient_light\": "+ambient_light.toString()+",\n" +
                                "  \"logical_height\": "+logical_height.toString()+",\n" +
                                "  \"effects\": \""+effects.toString()+"\",\n" +
                                "  \"infiniburn\": \""+infiniburn.toString()+"\",\n" +
                                "  \"min_y\": "+min_y.toString()+",\n" +
                                "  \"height\": "+height.toString()+",\n" +
                                "  \"monster_spawn_light_level\": {\n" +
                                "    \"type\": \"minecraft:uniform\",\n" +
                                "    \"value\": {\n" +
                                "      \"min_inclusive\": 0,\n" +
                                "      \"max_inclusive\": 7\n" +
                                "    }\n" +
                                "  },\n" +
                                "  \"monster_spawn_block_light_limit\": "+monster_spawn_block_light_limit.toString()+"\n" +
                                "}\n";
                        String path = DatapackRegistry.CORE_PATH + "worldGenerator/data/" + namespace + "/dimension_type";
                        Provider.generate(id, path, content);
                    }
                }
            }
            if(dim != null) {
                {
                    String content = (String) dim.getOrDefault("json","{\n" +
                            "  \"type\": \"minecraft:overworld\",\n" +
                            "  \"generator\": {\n" +
                            "    \"type\": \"minecraft:flat\",\n" +
                            "    \"settings\": {\n" +
                            "      \"biome\": \"minecraft:plains\",\n" +
                            "      \"lakes\": false,\n" +
                            "      \"features\": false,\n" +
                            "      \"layers\": [\n" +
                            "        {\n" +
                            "          \"block\": \"minecraft:air\",\n" +
                            "          \"height\": 1\n" +
                            "        }\n" +
                            "      ],\n" +
                            "      \"structure_overrides\": \"minecraft:villages\"\n" +
                            "    }\n" +
                            "  }\n" +
                            "}\n");
                    String path = DatapackRegistry.CORE_PATH + "worldGenerator/data/" + namespace + "/dimension";
                    Provider.generate(id, path, content);
                }
            }
        } else {
            {
                String content = "{\n" +
                        "  \"type\": \"minecraft:overworld\",\n" +
                        "  \"generator\": {\n" +
                        "    \"type\": \"minecraft:flat\",\n" +
                        "    \"settings\": {\n" +
                        "      \"biome\": \"minecraft:plains\",\n" +
                        "      \"lakes\": false,\n" +
                        "      \"features\": false,\n" +
                        "      \"layers\": [\n" +
                        "        {\n" +
                        "          \"block\": \"minecraft:air\",\n" +
                        "          \"height\": 1\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"structure_overrides\": \"minecraft:villages\"\n" +
                        "    }\n" +
                        "  }\n" +
                        "}\n";
                String path = DatapackRegistry.CORE_PATH + "worldGenerator/data/" + namespace + "/dimension";
                Provider.generate(id, path, content);
            }
            {
                String content = "{\n" +
                        "  \"ultrawarm\": false,\n" +
                        "  \"natural\": true,\n" +
                        "  \"piglin_safe\": false,\n" +
                        "  \"respawn_anchor_works\": false,\n" +
                        "  \"bed_works\": true,\n" +
                        "  \"has_raids\": true,\n" +
                        "  \"has_skylight\": true,\n" +
                        "  \"has_ceiling\": false,\n" +
                        "  \"coordinate_scale\": 1,\n" +
                        "  \"ambient_light\": 0,\n" +
                        "  \"logical_height\": 384,\n" +
                        "  \"effects\": \"minecraft:overworld\",\n" +
                        "  \"infiniburn\": \"#minecraft:infiniburn_overworld\",\n" +
                        "  \"min_y\": -64,\n" +
                        "  \"height\": 384,\n" +
                        "  \"monster_spawn_light_level\": {\n" +
                        "    \"type\": \"minecraft:uniform\",\n" +
                        "    \"value\": {\n" +
                        "      \"min_inclusive\": 0,\n" +
                        "      \"max_inclusive\": 7\n" +
                        "    }\n" +
                        "  },\n" +
                        "  \"monster_spawn_block_light_limit\": 0\n" +
                        "}\n";
                String path = DatapackRegistry.CORE_PATH + "worldGenerator/data/" + namespace + "/dimension_type";
                Provider.generate(id, path, content);
            }
        }
        RegistryKey<DimensionOptions> DIM_KEY = RegistryKey.of(RegistryKeys.DIMENSION, new Identifier(namespace,id));
        RegistryKey<World> DIM_LEVEL_KEY = RegistryKey.of(RegistryKeys.WORLD, new Identifier(namespace,id));
        RegistryKey<DimensionType> DIM_TYPE_KEY = RegistryKey.of(RegistryKeys.DIMENSION_TYPE, new Identifier(namespace,id));
    }
}
