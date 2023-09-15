package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static net.minecraft.fluid.Fluids.*;

public class RegPortal {
    public static final String FILE = Reference.File;

    public static void NormalType(String name, String types, Map<String, Object> portals, Block frameBlock, Item lightWithItem, Identifier destDimID, int tintColorR, int tintColorG, int tintColorB) {
        CustomPortalBuilder.beginPortal()
                .frameBlock(frameBlock)
                .lightWithItem(lightWithItem)
                .destDimID(destDimID)
                .tintColor(tintColorR, tintColorG, tintColorB)
                .registerPortal();
        ReturnMessage.PortalYMLRegister(name, types, portals);
    }

    public static void FlatType(String name, String types, Map<String, Object> portals, Block frameBlock, Item lightWithItem, Identifier destDimID, int tintColorR, int tintColorG, int tintColorB) {
        CustomPortalBuilder.beginPortal()
                .frameBlock(frameBlock)
                .flatPortal()
                .lightWithItem(lightWithItem)
                .destDimID(destDimID)
                .tintColor(tintColorR, tintColorG, tintColorB)
                .registerPortal();
        ReturnMessage.PortalYMLRegister(name, types, portals);
    }

    public static void FluidTypes(String name, String types, Map<String, Object> portals, Block frameBlock, Item lightWithItem, Identifier destDimID, int tintColorR, int tintColorG, int tintColorB, Map<String, Object> portalData) {
        String StringlightWithFluid = (String) portalData.get("lightWithFluid");
        Fluid lightWithFluid = EMPTY;
        if (Objects.equals(StringlightWithFluid, "flowing_water") || Objects.equals(StringlightWithFluid, "FLOWING_WATER")) {
            lightWithFluid = FLOWING_WATER;
        } else if (Objects.equals(StringlightWithFluid, "water") || Objects.equals(StringlightWithFluid, "WATER")) {
            lightWithFluid = WATER;
        } else if (Objects.equals(StringlightWithFluid, "flowing_lava") || Objects.equals(StringlightWithFluid, "FLOWING_LAVA")) {
            lightWithFluid = FLOWING_LAVA;
        } else if (Objects.equals(StringlightWithFluid, "lava") || Objects.equals(StringlightWithFluid, "LAVA")) {
            lightWithFluid = LAVA;
        } else {
            ReturnMessage.PortalYMLlightWithFluidERROR(name, types, portals);
        }

        CustomPortalBuilder.beginPortal()
                .lightWithFluid(lightWithFluid)
                .frameBlock(frameBlock)
                .lightWithItem(lightWithItem)
                .destDimID(destDimID)
                .tintColor(tintColorR, tintColorG, tintColorB)
                .registerPortal();
        ReturnMessage.PortalYMLRegister(name, types, portals);
    }

    public static void load() {
            Yaml yaml = new Yaml();
            ModelJsonBuilder model;

            File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

                if (subdirectories != null) {
                    for (File subdirectory : subdirectories) {
                        File portalsYamlFile = new File(subdirectory, "data/portals.yml");

                        if (portalsYamlFile.exists() && portalsYamlFile.isFile()) {
                            try {
                                Map<String, Map<String, Object>> Data = yaml.load(new FileReader(portalsYamlFile));

                                if (Data != null && Data.containsKey("portals")) {
                                    Map<String, Object> portals = Data.get("portals");

                                    for (Map.Entry<String, Object> entry : portals.entrySet()) {
                                        if (entry.getValue() instanceof Map) {
                                            Map<String, Object> portalData = (Map<String, Object>) entry.getValue();
                                            String portalName = entry.getKey();

                                            String types = (String) portalData.get("types");



                                            String name = (String) portalData.get("name");
                                            String frameBlockStr = (String) portalData.get("frameBlock");
                                            Block frameBlock = Registries.BLOCK.get(new Identifier(frameBlockStr));
                                            if (frameBlock == null) {
                                                frameBlock = Blocks.BEDROCK; // 默认方块
                                            }

                                            String lightWithItemStr = (String) portalData.get("lightWithItem");
                                            Item lightWithItem = Registries.ITEM.get(new Identifier(lightWithItemStr));
                                            if (lightWithItem == null) {
                                                lightWithItem = Items.POTATO; // 默认物品
                                            }

                                            String destDimIDStr = (String) portalData.get("destDimID");
                                            Identifier destDimID = new Identifier(destDimIDStr);

                                            List<Integer> tintColorList = (List<Integer>) portalData.get("tintColor");
                                            int tintColorR = tintColorList.get(0);
                                            int tintColorG = tintColorList.get(1);
                                            int tintColorB = tintColorList.get(2);

                                            // 使用上面获取到的信息来创建传送门

                                            if(Objects.equals(types, "normal")) {
                                                NormalType(name, types, portals ,frameBlock ,lightWithItem ,destDimID ,tintColorR ,tintColorG ,tintColorB);
                                            } else if (Objects.equals(types, "flat")) {
                                                FlatType(name, types, portals ,frameBlock ,lightWithItem ,destDimID ,tintColorR ,tintColorG ,tintColorB);
                                            } else if (Objects.equals(types, "Fluid")) {
                                                FluidTypes(name, types, portals, frameBlock, lightWithItem, destDimID , tintColorR, tintColorG,tintColorB, portalData);
                                            } else {
                                                ReturnMessage.PortalYMLRegister(name, types, portals);
                                            }


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
