package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.core.api.model.*;

import java.util.Objects;

public class Models {
    public static void generate(String namespace, String id, String type) {
        //BLOCK
        if(Objects.equals(type, "CUBEALL")) {
            ModelCubeALL.generate(namespace, id);
        } else if (Objects.equals(type, "CUBE")) {
            ModelCube.generate(namespace, id);

        } else if (Objects.equals(type, "CUBECOLUMN")) {
            ModelCubeColumnTreeLog.generate(namespace, id);

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
                Objects.equals(type, "HOE") ||
                Objects.equals(type, "BOW")) {
            ModelHandHeld.generate(namespace, id);
        } else if (Objects.equals(type, "SHIELD")) {
            ModelShield.generate(namespace, id);
            ModelShieldBlocking.generate(namespace, id);
        }
    }

}
