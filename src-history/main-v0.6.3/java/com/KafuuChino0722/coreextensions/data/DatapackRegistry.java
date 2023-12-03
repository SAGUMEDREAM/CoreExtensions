package com.KafuuChino0722.coreextensions.data;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.data.world.worldgen.DimensionFeature;
import com.KafuuChino0722.coreextensions.data.world.worldgen.OreFeature;
import com.KafuuChino0722.coreextensions.data.world.worldgen.TreeSaplingFeature;
import com.KafuuChino0722.coreextensions.util.Reference;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class DatapackRegistry {
    public static final String CORE_PATH = Reference.File;
    public static final String JSON = ".json";

    public static void preloader() {
        Provider.createFolder(CORE_PATH+"worldGenerator");
        Provider.createFolder(CORE_PATH+"worldGenerator/data");
        Provider.createFolder(CORE_PATH+"worldGenerator/data/custom_worldgenerator");
        Provider.createFolder(CORE_PATH+"worldGenerator/data/custom_worldgenerator/worldgen");
        Provider.createFolder(CORE_PATH+"worldGenerator/data/custom_worldgenerator/worldgen/placed_feature");
        Provider.createFolder(CORE_PATH+"worldGenerator/data/custom_worldgenerator/worldgen/configured_feature");

        String content = "{\n" +
                "  \"pack\": {\n" +
                "    \"pack_format\": 15,\n" +
                "    \"description\": \"§6CoreExtensions API WorldGenerator\"\n" +
                "  }\n" +
                "}";

        if (!Files.exists(Paths.get(CORE_PATH+"worldGenerator" + "/" + "pack.mcmeta"))) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(CORE_PATH+"worldGenerator" + "/" + "pack.mcmeta"));
                writer.write(content);
                writer.close();
            } catch (IOException e) {
            }
        }
    }

    public static void bootstrap() {
        Map<String, Map<String, Object>> worldsData = IOFileManager.read("world.yml");
        load(worldsData);
        Map<String, Map<String, Object>> worldsDataZ = IOFileManager.readZip("world.yml");
        load(worldsDataZ);

    }

    public static void load(Map<String, Map<String, Object>> worldsData) {
        if (worldsData != null && worldsData.containsKey("world")) {
            Map<String, Object> worlds = worldsData.get("world");
            for (Map.Entry<String, Object> entry : worlds.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> worldData = (Map<String, Object>) entry.getValue();
                    String name = (String) worldData.getOrDefault("name",(String) entry.getKey());

                    String namespace = (String) worldData.getOrDefault("namespace","minecraft");
                    String id = (String) worldData.getOrDefault("id",null);
                    String types = (String) worldData.getOrDefault("types",null);
                    Map<String, Object> properties = (Map<String, Object>) worldData.getOrDefault("properties",null);

                    if(types!=null && id!=null && properties!=null) {
                        Provider.createFolder(CORE_PATH + "worldGenerator/data/"+namespace);
                        Provider.createFolder(CORE_PATH + "worldGenerator/data/"+namespace+"/worldgen");
                        Provider.createFolder(CORE_PATH + "worldGenerator/data/"+namespace+"/worldgen/placed_feature");
                        Provider.createFolder(CORE_PATH + "worldGenerator/data/"+namespace+"/worldgen/configured_feature");

                        switch (types.toLowerCase()) {
                            case "ore","minecraft:ore" -> OreFeature.spawnOre(namespace,id,properties);
                            case "sapling","tree","minecraft:sapling","minecraft:tree" -> TreeSaplingFeature.spawnTree(name,namespace,id,properties);
                            case "world","dimension","dimension_type","minecraft:world","minecraft:dimension","minecraft:dimension_type" -> DimensionFeature.createWorld(name,namespace,id,properties);
                        }
                    }
                }
            }
        }
    }


    public static void spawnFile() {
    }

    public static void spawn() {
    }
}
