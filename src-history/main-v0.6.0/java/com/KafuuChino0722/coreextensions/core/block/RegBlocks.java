package com.KafuuChino0722.coreextensions.core.block;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.core.api.entity.Chest;
import com.KafuuChino0722.coreextensions.core.api.item.Bed;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PIGLIN_LOVED;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;


public class RegBlocks {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/block.yml");

                    if (blockYamlFile.exists() && blockYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(blockYamlFile));

                            if (blocksData != null && blocksData.containsKey("blocks")) {
                                Map<String, Object> blocks = blocksData.get("blocks");

                                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                                        String name = (String) blockData.get("name");
                                        //String namespace = blocksData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                        String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                                        String id = (String) blockData.get("id");
                                        String types = blockData.containsKey("types") ? (String) blockData.get("types") : "CUBE_ALL";
                                        int maxCount = blockData.containsKey("maxCount") ? (int) blockData.get("maxCount") : 64;

                                        Map<String, Object> properties = blockData.containsKey("properties")? (Map<String, Object>) blockData.get("properties"):null;
                                        double hardness = properties.containsKey("hardness") ? (double) properties.get("hardness") : 1.0;
                                        double resistance = properties.containsKey("resistance") ? (double) properties.get("resistance") : 3.0;
                                        boolean dropsNothing = properties.containsKey("dropsNothing") ? (boolean) properties.get("dropsNothing") : false;
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
                                        } else if (Objects.equals(soundStr, "GLASS")) {
                                            customSound = BlockSoundGroup.GLASS;
                                        } else {
                                            customSound = BlockSoundGroup.STONE;
                                        }

                                        if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                            RegItemGroupsContents.load(namespace,id,properties);
                                        }

                                        switch (types.toLowerCase()) {
                                            case "cube_all", "cubeall" ->
                                                    CubeALL.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "cube" ->
                                                    Cube.register(name, namespace, id, maxCount, properties, blockData, dropsNothing, customSound, hardness, resistance, generate);
                                            case "button" ->
                                                    Button.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "door" ->
                                                    Door.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "fence" ->
                                                    Fence.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "fence_gate", "fencegate" ->
                                                    FenceGate.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "pressure_plate", "pressureplate" ->
                                                    PressurePlate.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "slab" ->
                                                    Slab.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "uprightslab", "upright_slab" ->
                                                    UprightSlab.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "stairs" ->
                                                    Stairs.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "trapdoor" ->
                                                    TrapDoor.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "wall" ->
                                                    Wall.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "sand" ->
                                                    Sand.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "soulsand", "soul_sand" ->
                                                    SoulSand.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "fire" -> {
                                            }
                                            //Fire.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "log" ->
                                                    CubeLog.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "leaves" ->
                                                    Leaves.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "torch" ->
                                                    Torch.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "lamp", "redstone_lamp", "redstonelamp" ->
                                                    RedStoneLamp.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "power","powerblock","power_block" ->
                                                    CubePower.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "bed" ->
                                                    Bed.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "chest" ->
                                                    Chest.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "glass" ->
                                                    CubeGlass.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            case "glass_pane","glasspane" ->
                                                    CubeGlassPane.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                            default -> ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                                        }

                                        boolean canFire = properties.containsKey("canFire") ? (boolean) properties.get("canFire") : false;

                                        if(properties.containsKey("piglinLoved")) {
                                            boolean piglinLoved;
                                            if(properties.get("piglinLoved")!=null) {
                                                piglinLoved = (boolean) properties.get("piglinLoved");
                                                if(Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                    if (piglinLoved) {
                                                        TAG_PIGLIN_LOVED.add(new Identifier(namespace,id));
                                                    }
                                                }
                                            }
                                        }

                                        if (canFire && properties.containsKey("canFire")) {
                                            FlammableBlockRegistry.getDefaultInstance().add(Registries.BLOCK.get(new Identifier(namespace,id)),5 , 20);
                                        }

                                        provider.add("block." +namespace +"."+id, name);

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