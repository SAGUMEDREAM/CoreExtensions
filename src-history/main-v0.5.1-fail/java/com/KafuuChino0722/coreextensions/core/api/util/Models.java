package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.core.api.model.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

import java.util.Objects;

public class Models {

    public static void generateCrop(String namespace,String id, String type, int AGE) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            type = type.toUpperCase();

            switch (type) {
                case "CROP" -> ModelCrop.generate(namespace, id, AGE);
                case "HIGHCROP" -> ModelHighCrop.generate(namespace, id, AGE);
            }
        }
    }

    public static void generate(String namespace, String id, String type) {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            //BLOCK
            type = type.toUpperCase();

            switch (type) {
                case "CUBEALL" -> ModelCubeALL.generate(namespace, id);
                case "CUBE" -> ModelCube.generate(namespace, id);
                case "CUBECOLUMN" -> ModelCubeTreeLog.generate(namespace, id);
                case "SOULSAND" -> ModelSoulSand.generate(namespace, id);
                case "STAIRS" -> ModelStair.generate(namespace, id);
                case "SLAB" -> ModelSlab.generate(namespace, id);
                case "FENCE" -> ModelFence.generate(namespace, id);
                case "FENCEGATE" -> ModelFenceGate.generate(namespace, id);
                case "PRESSUREPLATE" -> ModelPressurePlate.generate(namespace, id);
                case "WALL" -> ModelWall.generate(namespace, id);
                case "BUTTON" -> ModelButton.generate(namespace, id);
                case "DOOR" -> ModelDoor.generate(namespace, id);
                case "TRAPDOOR" -> ModelTrapDoor.generate(namespace, id);
                case "TREELOG" -> ModelCubeTreeLog.generate(namespace, id);
                case "LEAVES" -> ModelCubeALL.generate(namespace, id);
                case "FIRE" -> ModelFire.generate(namespace, id);
                case "TORCH" -> ModelTorch.generate(namespace, id);
                case "LAMP" -> ModelLamp.generate(namespace, id);
                case "PLANTS" -> ModelPlants.generate(namespace, id);
                case "HIGH_PLANTS" -> ModelHighPlants.generate(namespace, id);
                case "SEEDS" -> ModelPlants.generate(namespace, id);
                case "FRUITBUSH" -> ModelFruitBush.generate(namespace, id);
                case "BED" -> ModelBed.generate(namespace, id);
                default -> {
                }
            }

            //ITEM
            switch (type) {
                case "ITEM" -> ModelItem.generate(namespace, id);
                case "HANDHELD", "SWORD", "PICKAXE", "AXE", "SHOVEL", "HOE" -> ModelHandHeld.generate(namespace, id);
                case "SHIELD" -> {
                    ModelShield.generate(namespace, id);
                    ModelShieldBlocking.generate(namespace, id);
                }
                case "ELYTRA" -> ModelItemElytra.generate(namespace, id);
                case "BOW" -> ModelBow.generate(namespace, id);
            }
        }
    }

}
