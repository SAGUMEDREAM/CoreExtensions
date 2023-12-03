package com.KafuuChino0722.coreextensions.data.world.worldgen;

import com.KafuuChino0722.coreextensions.data.DatapackRegistry;
import com.KafuuChino0722.coreextensions.data.Provider;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;

import java.util.Map;

public class OreFeature {

    public static void spawnOre(String namespace, String id, Map<String, Object> Key) {
        String path = DatapackRegistry.CORE_PATH+"worldGenerator/data/"+namespace+"/worldgen";
        String block = (String) Key.getOrDefault("block","minecraft:stone");
        String deepslate_block = (String) Key.getOrDefault("deepslate_block","minecraft:stone");
        Map<String, Object> ConfigKey = (Map<String, Object>) Key.getOrDefault("config",null);
        if(ConfigKey!=null) {
            Map<String, Object> configured_feature = (Map<String, Object>) ConfigKey.getOrDefault("configured_feature", null);
            Map<String, Object> placed_feature = (Map<String, Object>) ConfigKey.getOrDefault("placed_feature", null);
            if (configured_feature != null && placed_feature != null) {
                {
                    Integer size = (Integer) configured_feature.getOrDefault("size", 4);
                    Double discard_chance_on_air_exposure = (Double) configured_feature.getOrDefault("discard_chance_on_air_exposure", 0);

                    String content = "{\n" +
                            "  \"type\": \"minecraft:ore\",\n" +
                            "  \"config\": {\n" +
                            "    \"size\": " + size.toString() + ",\n" +
                            "    \"discard_chance_on_air_exposure\": "+discard_chance_on_air_exposure.toString()+",\n" +
                            "    \"targets\": [\n" +
                            "      {\n" +
                            "        \"target\": {\n" +
                            "          \"predicate_type\": \"minecraft:tag_match\",\n" +
                            "          \"tag\": \"minecraft:stone_ore_replaceables\"\n" +
                            "        },\n" +
                            "        \"state\": {\n" +
                            "          \"Name\": \"" + block + "\"\n" +
                            "        }\n" +
                            "      },\n" +
                            "      {\n" +
                            "        \"target\": {\n" +
                            "          \"predicate_type\": \"minecraft:tag_match\",\n" +
                            "          \"tag\": \"minecraft:deepslate_ore_replaceables\"\n" +
                            "        },\n" +
                            "        \"state\": {\n" +
                            "          \"Name\": \"" + deepslate_block + "\"\n" +
                            "        }\n" +
                            "      }\n" +
                            "    ]\n" +
                            "  }\n" +
                            "}\n";
                    Provider.generate(id, path + "/configured_feature", content);
                }
                {
                    Integer size = (Integer) placed_feature.getOrDefault("size", 10);
                    Integer max = (Integer) placed_feature.getOrDefault("max", 56);
                    Integer min = (Integer) placed_feature.getOrDefault("min", -24);

                    String content = "{\n" +
                            "  \"feature\": \"" + namespace + ":" + id + "\",\n" +
                            "  \"placement\": [\n" +
                            "    {\n" +
                            "      \"type\": \"minecraft:count\",\n" +
                            "      \"count\": " + size.toString() + "\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"type\": \"minecraft:in_square\"\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"type\": \"minecraft:height_range\",\n" +
                            "      \"height\": {\n" +
                            "        \"type\": \"minecraft:trapezoid\",\n" +
                            "        \"min_inclusive\": {\n" +
                            "          \"absolute\": " + min.toString() + "\n" +
                            "        },\n" +
                            "        \"max_inclusive\": {\n" +
                            "          \"absolute\": " + max.toString() + "\n" +
                            "        }\n" +
                            "      }\n" +
                            "    },\n" +
                            "    {\n" +
                            "      \"type\": \"minecraft:biome\"\n" +
                            "    }\n" +
                            "  ]\n" +
                            "}\n";
                    Provider.generate(id, path + "/placed_feature", content);
                }
                RegistryKey<PlacedFeature> ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(namespace, id));
                BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, ORE_PLACED_KEY);
            }
        }
    }
}
