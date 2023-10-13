package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.core.api.model.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Objects;

public class Models {

    public static void generateCrop(String namespace,String id, String type, int AGE) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            if (Objects.equals(type, "CROP")) {
                ModelCrop.generate(namespace, id, AGE);
            } else if (Objects.equals(type, "HIGHCROP")) {
                ModelHighCrop.generate(namespace, id, AGE);

            }
        }
    }

    public static void generate(String namespace, String id, String type) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            //BLOCK
            if(Objects.equals(type, "CUBEALL")) {
                ModelCubeALL.generate(namespace, id);

            } else if (Objects.equals(type, "CUBE")) {
                ModelCube.generate(namespace, id);

            } else if (Objects.equals(type, "CUBECOLUMN")) {
                ModelCubeTreeLog.generate(namespace, id);

            } else if (Objects.equals(type, "SOULSAND")) {
                ModelSoulSand.generate(namespace, id);

            } else if (Objects.equals(type, "STAIRS")) {
                ModelStair.generate(namespace, id);

            } else if (Objects.equals(type, "SLAB")) {
                ModelSlab.generate(namespace, id);

            } else if (Objects.equals(type, "FENCE")) {
                ModelFence.generate(namespace, id);

            } else if (Objects.equals(type, "FENCEGATE")) {
                ModelFenceGate.generate(namespace, id);

            } else if (Objects.equals(type, "PRESSUREPLATE")) {
                ModelPressurePlate.generate(namespace, id);

            } else if (Objects.equals(type, "WALL")) {
                ModelWall.generate(namespace, id);

            } else if (Objects.equals(type, "BUTTON")) {
                ModelButton.generate(namespace, id);

            } else if (Objects.equals(type, "DOOR")) {
                ModelDoor.generate(namespace, id);

            } else if (Objects.equals(type, "TRAPDOOR")) {
                ModelTrapDoor.generate(namespace, id);

            } else if (Objects.equals(type, "TREELOG")) {
                ModelCubeTreeLog.generate(namespace, id);

            } else if (Objects.equals(type, "LEAVES")) {
                ModelCubeALL.generate(namespace, id);

            } else if (Objects.equals(type, "FIRE")) {
                ModelFire.generate(namespace, id);

            } else if (Objects.equals(type, "TORCH")) {
                ModelTorch.generate(namespace, id);

            } else if (Objects.equals(type, "LAMP")) {
                ModelLamp.generate(namespace, id);

            } else if (Objects.equals(type, "PLANTS")) {
                ModelPlants.generate(namespace, id);

            } else if (Objects.equals(type, "HIGH_PLANTS")) {
                ModelHighPlants.generate(namespace, id);

            } else if (Objects.equals(type, "SEEDS")) {
                ModelPlants.generate(namespace, id);

            } else if (Objects.equals(type, "FRUITBUSH")) {
                ModelFruitBush.generate(namespace, id);

            } else if (Objects.equals(type, "BED")) {
                ModelBed.generate(namespace, id);

            } else if(Objects.equals(type, "CHEST")) {
                ModelChest.generate(namespace, id);
            } else {

            }

            //ITEM
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

}
