package com.KafuuChino0722.coreextensions.core.zip.block;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.block.Plants;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class iZipPlants {
    public static final String FILE = Reference.File;

    public static void load(String[] paths) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/plant.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("blocks")) {
                                            Map<String, Object> blocks = Data.get("blocks");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> blockData = (Map<String, Object>) DataEntry.getValue();
                                                    String name = (String) blockData.get("name");
                                                    //String namespace = blocksData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                                    String namespace = (String) blockData.getOrDefault("namespace", "minecraft");
                                                    String id = (String) blockData.get("id");
                                                    String types = blockData.containsKey("types") ? (String) blockData.get("types") : "normal";
                                                    int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;

                                                    Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
                                                    boolean dropsNothing = properties.containsKey("dropsNothing") && (boolean) properties.get("dropsNothing");
                                                    String sound = (String) properties.get("sound");

                                                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                                                    String soundStr = sound.toUpperCase();
                                                    BlockSoundGroup customSound = null;
                                                    if (Objects.equals(soundStr, "STONE")) {
                                                        customSound = BlockSoundGroup.STONE;
                                                    } else if (Objects.equals(soundStr, "WOOD")) {
                                                        customSound = BlockSoundGroup.WOOD;
                                                    } else if (Objects.equals(soundStr, "GRAVEL")) {
                                                        customSound = BlockSoundGroup.GRAVEL;
                                                    } else if (Objects.equals(soundStr, "METAL")) {
                                                        customSound = BlockSoundGroup.METAL;
                                                    } else if (Objects.equals(soundStr, "GRASS")) {
                                                        customSound = BlockSoundGroup.GRASS;
                                                    } else if (Objects.equals(soundStr, "WOOL")) {
                                                        customSound = BlockSoundGroup.WOOL;
                                                    } else if (Objects.equals(soundStr, "SAND")) {
                                                        customSound = BlockSoundGroup.SAND;
                                                    } else if (Objects.equals(soundStr, "CROP")) {
                                                        customSound = BlockSoundGroup.CROP;
                                                    } else {
                                                        customSound = BlockSoundGroup.STONE;
                                                    }

                                                    if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                        RegItemGroupsContents.load(namespace,id,properties);
                                                    }

                                                    if (types.equalsIgnoreCase("normal")) {
                                                        Plants.normal.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                                    } else if (types.equalsIgnoreCase("high") || types.equalsIgnoreCase("highblock") || types.equalsIgnoreCase("high_block")) {
                                                        Plants.high.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                                    } else if (types.equalsIgnoreCase("seed")) {
                                                        Plants.seeds.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, generate);
                                                    }



                                                    provider.add("item." + namespace + "." + id, name)
                                                            .add("block." + namespace + "." + id, name)
                                                            .add("block." + namespace + ".potted_" + id, name)
                                                    ;

                                                }
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}