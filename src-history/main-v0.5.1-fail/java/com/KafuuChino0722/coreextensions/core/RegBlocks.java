package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.core.api.item.Bed;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;


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
                                        boolean dropsNothing = properties.containsKey("dropsNothing") && (boolean) properties.get("dropsNothing");
                                        String sound = (String) properties.get("sound");

                                        boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                                        String soundStr = sound.toUpperCase();
                                        BlockSoundGroup customSound = switch (soundStr) {
                                            case "STONE" -> BlockSoundGroup.STONE;
                                            case "WOOD" -> BlockSoundGroup.WOOD;
                                            case "GRAVEL" -> BlockSoundGroup.GRAVEL;
                                            case "METAL" -> BlockSoundGroup.METAL;
                                            case "GRASS" -> BlockSoundGroup.GRASS;
                                            case "WOOL" -> BlockSoundGroup.WOOL;
                                            case "SAND" -> BlockSoundGroup.SAND;
                                            case "CROP" -> BlockSoundGroup.CROP;
                                            default -> BlockSoundGroup.STONE;
                                        };

                                        if(!Registries.BLOCK.containsId(new Identifier(namespace, id))&&!Registries.BLOCK.containsId(new Identifier(namespace, "stripped_"+id))) {
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
                                                case "bed" ->
                                                        Bed.register(name, namespace, id, maxCount, blockData, properties, dropsNothing, customSound, hardness, resistance, generate);
                                                default -> ReturnMessage.BlockYMLTYPEERROR(name, namespace, id);
                                            }

                                        } else {
                                            Info.custom(namespace+":"+id+" has been registered twice and has automatically prevented the game from crashing","BlockManager");
                                        }

                                        boolean canFire = properties.containsKey("canFire") ? (boolean) properties.get("canFire") : false;

                                        if (canFire && properties.containsKey("canFire")) {
                                            FlammableBlockRegistry.getDefaultInstance().add(Registries.BLOCK.get(new Identifier(namespace,id)),5 , 20);
                                        }

                                        RegItemGroupsContents.load(namespace,id,properties);

                                        String lang_us = name;

                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "en_us"),
                                                    LanguageProvider.create().add("block." +namespace +"."+id, lang_us));
                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang","zh_cn"),
                                                    LanguageProvider.create().add("block." +namespace +"."+id, name));

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