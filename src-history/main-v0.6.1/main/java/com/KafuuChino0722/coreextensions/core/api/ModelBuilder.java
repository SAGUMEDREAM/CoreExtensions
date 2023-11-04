package com.KafuuChino0722.coreextensions.core.api;

import com.KafuuChino0722.coreextensions.core.api.model.*;
import com.KafuuChino0722.coreextensions.core.api.util.ModelPredicateProvider;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static net.fabricmc.api.EnvType.CLIENT;

public class ModelBuilder {
    public static class Block {
        public enum Types {
            CUBE_ALL,
            CUBE,
            CUBECOLUMN,
            STAIRS,
            SLAB,
            FENCE,
            FENCEGATE,
            PRESSUREPLATE,
            WALL,
            BUTTON,
            DOOR,
            TRAPDOOR,
            FIRE,
            TORCH,
            REDSTONE_LAMP,
            PLANTS,
            HIGH_PLANTS,
            SEEDS,
            FRUITBUSH,
            BED,
            CHEST,
            GLASSPANE,
            CARPETS,
            CROP,
            HIGHCROP;
        }


        public static void getModel(String namespace, String id, Types ModelType) {
            if (FabricLoader.getInstance().getEnvironmentType() == CLIENT) {
                if (ModelType == Types.CUBE_ALL) ModelCubeALL.generate(namespace, id);
                if (ModelType == Types.CUBE) ModelCube.generate(namespace, id);
                if (ModelType == Types.CUBECOLUMN) ModelCubeTreeLog.generate(namespace, id);
                if (ModelType == Types.CUBECOLUMN) ModelCubeTreeLog.generate(namespace, "stripped_"+id);
                if (ModelType == Types.STAIRS) ModelStair.generate(namespace, id);
                if (ModelType == Types.SLAB) ModelSlab.generate(namespace, id);
                if (ModelType == Types.FENCE) ModelFence.generate(namespace, id);
                if (ModelType == Types.FENCEGATE) ModelFenceGate.generate(namespace, id);
                if (ModelType == Types.PRESSUREPLATE) ModelPressurePlate.generate(namespace, id);
                if (ModelType == Types.WALL) ModelWall.generate(namespace, id);
                if (ModelType == Types.DOOR) ModelDoor.generate(namespace, id);
                if (ModelType == Types.TRAPDOOR) ModelTrapDoor.generate(namespace, id);
                if (ModelType == Types.BUTTON) ModelButton.generate(namespace, id);
                if (ModelType == Types.FIRE) ;
                if (ModelType == Types.TORCH) ModelTorch.generate(namespace, id);
                if (ModelType == Types.REDSTONE_LAMP) ModelLamp.generate(namespace, id);
                if (ModelType == Types.PLANTS) ModelPlants.generate(namespace, id);
                if (ModelType == Types.HIGH_PLANTS) ModelHighPlants.generate(namespace, id);
                if (ModelType == Types.SEEDS) ModelPlants.generate(namespace, id);
                if (ModelType == Types.FRUITBUSH) ModelFruitBush.generate(namespace, id);
                if (ModelType == Types.BED) ModelBed.generate(namespace, id);
                if (ModelType == Types.CHEST) ModelChest.generate(namespace, id);
                if (ModelType == Types.GLASSPANE) ModelGlassPane.generate(namespace, id);
                if (ModelType == Types.CARPETS) ModelCarpet.generate(namespace, id);
            }
        }

        public static void getModel(String namespace, String id, Types ModelType, int AGE) {
            if (FabricLoader.getInstance().getEnvironmentType() == CLIENT) {
                if (ModelType == Types.CROP) ModelCrop.generate(namespace, id, AGE);
                if (ModelType == Types.HIGHCROP) ModelHighCrop.generate(namespace, id, AGE);
            }
        }
    }

    public static class Item {
        public enum Types {
            GENERATE,
            HANDHELD,
            SHIELD,
            ELYTRA,
            BOW,
            CROSSBOW;
        }

        public static void getModel(String namespace, String id, Item.Types ModelType) {
            if (FabricLoader.getInstance().getEnvironmentType() == CLIENT) {
                if (ModelType == Types.GENERATE) ModelItem.generate(namespace, id);
                if (ModelType == Types.HANDHELD) ModelHandHeld.generate(namespace, id);
                if (ModelType == Types.SHIELD) ModelShield.generate(namespace, id);
                if (ModelType == Types.SHIELD) ModelShieldBlocking.generate(namespace, id);
                if (ModelType == Types.ELYTRA) ModelItemElytra.generate(namespace, id);
                if (ModelType == Types.BOW) ModelBow.generate(namespace, id);
                if (ModelType == Types.BOW)
                    ModelPredicateProvider.Types.create(Registries.ITEM.get(new Identifier(namespace, id)), ModelPredicateProvider.Types.BOW);
                if (ModelType == Types.CROSSBOW) ModelCrossBow.generate(namespace, id);
                if (ModelType == Types.CROSSBOW)
                    ModelPredicateProvider.Types.create(Registries.ITEM.get(new Identifier(namespace, id)), ModelPredicateProvider.Types.CROSSBOW);
            }
        }
    }

    public static class Crop {
        public enum Types {
            CROP,
            HIGHCROP
        }

        public static void getModel(String namespace, String id, Types ModelType, int AGE) {
            if (FabricLoader.getInstance().getEnvironmentType() == CLIENT) {
                if (ModelType == Types.CROP) ModelCrop.generate(namespace, id, AGE);
                if (ModelType == Types.HIGHCROP) ModelHighCrop.generate(namespace, id, AGE);
            }
        }

    /*public Object getModel(String namespace, String id) {
        return null;
    }*/
    }
    public static void test() {
        ModelBuilder.Block.getModel("minecraft", "cubeall", Block.Types.CUBE_ALL);
        ModelBuilder.Item.getModel("minecraft", "cubeall", Item.Types.GENERATE);
    }
}
