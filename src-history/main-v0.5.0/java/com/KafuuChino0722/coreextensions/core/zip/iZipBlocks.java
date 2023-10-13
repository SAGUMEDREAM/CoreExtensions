package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipBlocks {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/block.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("blocks")) {
                                            Map<String, Object> items = Data.get("blocks");

                                            for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                                if (itemEntry.getValue() instanceof Map) {
                                                    Map<String, Object> blockData = (Map<String, Object>) itemEntry.getValue();
                                                    String name = (String) blockData.get("name");
                                                    //String namespace = blocksData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                                    String namespace = (String) blockData.getOrDefault("namespace", "minecraft");
                                                    String id = (String) blockData.get("id");
                                                    String types = blockData.containsKey("types") ? (String) blockData.get("types") : "CUBE_ALL";
                                                    int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;

                                                    Map<String, Object> properties = blockData.containsKey("properties") ? (Map<String, Object>) blockData.get("properties") : null;
                                                    double hardness = properties.containsKey("hardness") ? (double) properties.get("hardness") : 1.0;
                                                    double resistance = properties.containsKey("resistance") ? (double) properties.get("resistance") : 3.0;
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

                                                    if(!Registries.BLOCK.containsId(new Identifier(namespace, id))&&!Registries.BLOCK.containsId(new Identifier(namespace, "stripped_"+id))) {
                                                        if (types.equalsIgnoreCase("cube_all")||types.equalsIgnoreCase("cubeall")) {
                                                            CubeALL.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("cube")) {
                                                            Cube.register(name, namespace, id, maxCount, properties, blockData, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("button")) {
                                                            Button.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("door")) {
                                                            Door.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("fence")) {
                                                            Fence.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("fence_gate")||types.equalsIgnoreCase("fencegate")) {
                                                            FenceGate.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("pressure_plate")||types.equalsIgnoreCase("pressureplate")) {
                                                            PressurePlate.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("slab")) {
                                                            Slab.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("stairs")) {
                                                            Stairs.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("trapdoor")) {
                                                            TrapDoor.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("wall")) {
                                                            Wall.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("sand")) {
                                                            Sand.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("soulsand")||types.equalsIgnoreCase("soul_sand")) {
                                                            SoulSand.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("fire")) {
                                                            //Fire.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("log")) {
                                                            CubeLog.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("leaves")) {
                                                            Leaves.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("torch")) {
                                                            Torch.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("lamp")||types.equalsIgnoreCase("redstone_lamp")||types.equalsIgnoreCase("redstonelamp")) {
                                                            RedStoneLamp.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else if (types.equalsIgnoreCase("bed")) {
                                                            Bed.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);

                                                        } else {
                                                            ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                                                        }
                                                    } else {
                                                        Info.custom(namespace+":"+id+" has been registered twice and has automatically prevented the game from crashing","BlockManager");
                                                    }

                                                    boolean canFire = properties.containsKey("canFire") ? (boolean) properties.get("canFire") : false;

                                                    if (canFire && properties.containsKey("canFire")) {
                                                        FlammableBlockRegistry.getDefaultInstance().add(Registries.BLOCK.get(new Identifier(namespace,id)),5 , 20);
                                                    }

                                                    RegItemGroupsContents.load(namespace, id, properties);

                                                    String lang_us = name;

                                                    respacks.addLang(new Identifier(namespace + "_" + id + "_lang", "en_us"),
                                                            LanguageProvider.create().add("block." + namespace + "." + id, lang_us));
                                                    respacks.addLang(new Identifier(namespace + "_" + id + "_lang", "zh_cn"),
                                                            LanguageProvider.create().add("block." + namespace + "." + id, name));

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