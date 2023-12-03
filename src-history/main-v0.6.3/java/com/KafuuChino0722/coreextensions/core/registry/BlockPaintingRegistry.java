package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.BlockPainting;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;


public class BlockPaintingRegistry {
    public static final String FILE = Reference.File;

    public static void register() {
        Map<String, Map<String, Object>> blocksData = IOFileManager.read("painting.yml");
        load(blocksData);
        Map<String, Map<String, Object>> blocksDataZ = IOFileManager.readZip("painting.yml");
        load(blocksDataZ);
    }

    public static void load(Map<String, Map<String, Object>> blocksData) {
        if (blocksData != null && blocksData.containsKey("paintings")) {
            Map<String, Object> blocks = blocksData.get("paintings");

            for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                    String name = (String) blockData.get("name");
                    String author = (String) blockData.get("author");
                    String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                    String id = (String) blockData.get("id");
                    Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
                    int SizeX = properties.containsKey("SizeX") ? (int) properties.get("SizeX") : 1;
                    int SizeY = properties.containsKey("SizeY") ? (int) properties.get("SizeY") : 1;

                    BlockPainting.registerPainting(namespace, id ,SizeX, SizeY);

                    provider.add("painting." +namespace +"."+id+".title", name)
                            .add("painting." +namespace +"."+id+".author", author);

                    Info.custom("BlockPainting " + name + "<->" + namespace + ":" + id + " registered!","BlockPainting");
                    if(Registries.PARTICLE_TYPE.containsId(new Identifier(namespace,id))) {
                        StorageRegistry.add(StorageRegistry.PARTICLE_TYPES, new Identifier(namespace, id));
                    }
                }
            }
        }
    }
}