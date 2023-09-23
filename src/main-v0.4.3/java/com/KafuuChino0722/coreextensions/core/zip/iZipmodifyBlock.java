package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.IdentifierManager;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.api.EnvType;
import net.minecraft.block.*;
import net.minecraft.item.Item;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.function.ToIntFunction;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipmodifyBlock {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/block@Overwrite.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("blocks")) {
                                            Map<String, Object> blocks = Data.get("blocks");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> blockData = (Map<String, Object>) DataEntry.getValue();

                                                    String name = (String) blockData.get("name");
                                                    String lang_us = (String) blockData.get("name");
                                                    //String namespace = (String) itemData.get("namespace");
                                                    String namespace = blockData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                                    String id = (String) blockData.get("id");
                                                    String types = blockData.containsKey("types") ? (String) blockData.get("types") : "CUBE_ALL";
                                                    int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;
                                                    Map<String, Object> properties = blockData.containsKey("properties")? (Map<String, Object>) blockData.get("properties"):null;
                                                    String sound = (String) properties.get("sound");

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

                                                    Block block = IdentifierManager.getBlock(namespace,id);
                                                    if(properties != null) {
                                                        if(blockData.containsKey("maxCount")) {
                                                            Item item = IdentifierManager.getItem(namespace,id);
                                                            Field field;
                                                            try {
                                                                field = Item.class.getDeclaredField("maxCount");
                                                            } catch (NoSuchFieldException e) {
                                                                field = Item.class.getDeclaredField("field_8013");
                                                            }
                                                            field.setAccessible(true);
                                                            field.set(item, maxCount);
                                                        }
                                                        if (properties.containsKey("resistance")) {
                                                            double resistance = properties.containsKey("resistance") ? (double) properties.get("resistance") : 3.0;
                                                            Field field;
                                                            try {
                                                                field = AbstractBlock.class.getDeclaredField("resistance");
                                                            } catch (NoSuchFieldException e) {
                                                                field = AbstractBlock.class.getDeclaredField("field_23160");
                                                            }
                                                            field.setAccessible(true);
                                                            field.set(block, (float) resistance);
                                                        }
                                                        if (properties.containsKey("hardness")) {
                                                            double hardness = properties.containsKey("hardness") ? (double) properties.get("hardness") : 3.0;
                                                            Field field;
                                                            try {
                                                                field = AbstractBlock.Settings.class.getDeclaredField("hardness");
                                                            } catch (NoSuchFieldException e) {
                                                                field = AbstractBlock.Settings.class.getDeclaredField("field_23172");
                                                            }
                                                            field.setAccessible(true);
                                                            field.set(block, (float) hardness);
                                                        }
                                                        if (properties.containsKey("sound")) {
                                                            Field field;
                                                            try {
                                                                field = AbstractBlock.Settings.class.getDeclaredField("soundGroup");
                                                            } catch (NoSuchFieldException e) {
                                                                field = AbstractBlock.Settings.class.getDeclaredField("field_23162");
                                                            }
                                                            field.setAccessible(true);
                                                            field.set(block, customSound);
                                                        }
                                                        if (properties.containsKey("lightLevel")) {
                                                            int lightLevel = properties.containsKey("lightLevel") ? (int) properties.get("lightLevel") : 0;
                                                            ToIntFunction<BlockState> lightLevelGet = state -> lightLevel;
                                                            Field field;
                                                            try {
                                                                field = AbstractBlock.Settings.class.getDeclaredField("luminance");
                                                            } catch (NoSuchFieldException e) {
                                                                field = AbstractBlock.Settings.class.getDeclaredField("field_10663");
                                                            }
                                                            field.setAccessible(true);
                                                            field.set(block, lightLevelGet);
                                                        }
                                                        if (properties.containsKey("dropsNothing")) {
                                                            boolean dropsNothing = properties.containsKey("dropsNothing") ? (boolean) properties.get("dropsNothing") : false;
                                                            if(Reference.EnvType == EnvType.CLIENT) {
                                                                setupRenderLayer.set(block, properties); // 设置渲染层
                                                            }
                                                        }
                                                        if(types.equalsIgnoreCase("button")) {
                                                            if (properties.containsKey("wooden")) {
                                                                boolean wooden = properties.containsKey("wooden") ? (boolean) properties.get("wooden") : false;
                                                                Field field;
                                                                try {
                                                                    field = ButtonBlock.class.getDeclaredField("wooden");
                                                                } catch (NoSuchFieldException e) {
                                                                    field = ButtonBlock.class.getDeclaredField("field_40299");
                                                                }
                                                                field.setAccessible(true);
                                                                field.set(block, wooden);
                                                            }
                                                            if (properties.containsKey("pressTicks")) {
                                                                int pressTicks = properties.containsKey("pressTicks") ? (int) properties.get("pressTicks") : 20;
                                                                Field field;
                                                                try {
                                                                    field = ButtonBlock.class.getDeclaredField("pressTicks");
                                                                } catch (NoSuchFieldException e) {
                                                                    field = ButtonBlock.class.getDeclaredField("field_40298");
                                                                }
                                                                field.setAccessible(true);
                                                                field.set(block, pressTicks);
                                                            }
                                                        }
                                                        if(types.equalsIgnoreCase("door")) {
                                                            if(properties.containsKey("canOpenByHand")) {
                                                                boolean canOpenByHand = properties.containsKey("canOpenByHand") ? (boolean) properties.get("canOpenByHand") : true;
                                                                BlockSetType blockType;
                                                                if (canOpenByHand) {
                                                                    blockType = BlockSetType.OAK;
                                                                } else {
                                                                    blockType = BlockSetType.IRON;
                                                                }
                                                                Field field;
                                                                try {
                                                                    field = DoorBlock.class.getDeclaredField("blockSetType");
                                                                } catch (NoSuchFieldException e) {
                                                                    field = DoorBlock.class.getDeclaredField("field_42757");
                                                                }
                                                                field.setAccessible(true);
                                                                field.set(block, blockType);
                                                            }
                                                        }
                                                        if(types.equalsIgnoreCase("trapdoor")) {
                                                            if(properties.containsKey("canOpenByHand")) {
                                                                boolean canOpenByHand = properties.containsKey("canOpenByHand") ? (boolean) properties.get("canOpenByHand") : true;
                                                                BlockSetType blockType;
                                                                if (canOpenByHand) {
                                                                    blockType = BlockSetType.OAK;
                                                                } else {
                                                                    blockType = BlockSetType.IRON;
                                                                }
                                                                Field field;
                                                                try {
                                                                    field = TrapdoorBlock.class.getDeclaredField("blockSetType");
                                                                } catch (NoSuchFieldException e) {
                                                                    field = TrapdoorBlock.class.getDeclaredField("field_42779");
                                                                }
                                                                field.setAccessible(true);
                                                                field.set(block, blockType);
                                                            }
                                                        }
                                                    }

                                                    if(Reference.EnvType == EnvType.CLIENT) {
                                                        setupRenderLayer.set(block, properties);
                                                    }

                                                    RegItemGroupsContents.load(namespace,id,properties);

                                                    respacks.addLang(new Identifier(namespace+"_"+id+"_mlang", "en_us"),
                                                            LanguageProvider.create().add("block." +namespace +"."+id, lang_us));
                                                    respacks.addLang(new Identifier(namespace+"_"+id+"_mlang", "zh_cn"),
                                                            LanguageProvider.create().add("block." +namespace +"."+id, name));
                                                }
                                            }
                                        }
                                    } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
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