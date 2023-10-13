package com.LoneDev.itemsadder.api;

import com.KafuuChino0722.coreextensions.core.api.model.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Objects;

public class CustomModels {

    public static void generateItem(String namespace, String id, String type) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            if(Objects.equals(type, "ITEM")) {
                ModelItem.generate(namespace, id);
            } else if (Objects.equals(type, "HANDHELD") ||
                    Objects.equals(type, "SWORD") ||
                    Objects.equals(type, "PICKAXE") ||
                    Objects.equals(type, "AXE") ||
                    Objects.equals(type, "SHOVEL") ||
                    Objects.equals(type, "HOE")) {
                ModelHandHeld.generate(namespace, id);
            } else if (Objects.equals(type, "SHIELD")) {
                ModelShield.generate(namespace, id);
                ModelShieldBlocking.generate(namespace, id);
            } else if (Objects.equals(type, "ELYTRA")) {
                ModelItemElytra.generate(namespace, id);
            } else if (Objects.equals(type, "BOW")) {
                ModelBow.generate(namespace, id);
            }
        }
    }

    public static void generateVanilla(String namespace, String id, String type, String Identifier) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            ModelItem.generateVanilla(namespace, id, Identifier);
        }
    }

    public static void generateItem(String namespace, String id, String type, String Texture) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            if(Objects.equals(type, "ITEM")) {
                ModelItem.generate(namespace, id, Texture);
            } else if (Objects.equals(type, "HANDHELD") ||
                    Objects.equals(type, "SWORD") ||
                    Objects.equals(type, "PICKAXE") ||
                    Objects.equals(type, "AXE") ||
                    Objects.equals(type, "SHOVEL") ||
                    Objects.equals(type, "HOE")) {
                ModelHandHeld.generate(namespace, id, Texture);
            } else if (Objects.equals(type, "BOW")) {
                ModelBow.generate(namespace, id);
            }
        }
    }

    public static void generateCustom(String namespace, String id, String type, String model_path) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            if (Objects.equals(type, "CUSTOM")) {
                ModelCube.generateCustom(namespace, id, model_path);
            }
        }
    }

    public static void generate(String namespace, String id, String type,
                                String down, String east, String north, String south, String up, String west) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            if (Objects.equals(type, "CUBE")) {
                ModelCube.generate(namespace, id,
                        down, east, north, south, up, west);
            }
        }
    }

    public static void generate(String namespace, String id, String type, String Texture) {
        if(Objects.equals(type, "CUBEALL")) {
            ModelCubeALL.generate(namespace, id, Texture);
        }
    }

    public static void generate(String namespace, String id, String type) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            if(Objects.equals(type, "CUBEALL")) {
                ModelCubeALL.generate(namespace, id);

            } else if (Objects.equals(type, "CUBE")) {
                ModelCube.generate(namespace, id);

            }
        }
    }
}
