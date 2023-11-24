package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.util.TagsBuilder;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;

import java.util.Map;


public class TagRegistry {

    public static void register() {
        Map<String, Map<String, Object>> tagData = IOFileManager.read("tag.yml");
        load(tagData);
        Map<String, Map<String, Object>> tagDataZ = IOFileManager.readZip("tag.yml");
        load(tagDataZ);
    }

    public static void load(Map<String, Map<String, Object>> blocksData) {
        if (blocksData != null && blocksData.containsKey("tags")) {
            Map<String, Object> blocks = blocksData.get("tags");

            for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> tagData = (Map<String, Object>) entry.getValue();
                    String namespace = (String) tagData.getOrDefault("namespace","minecraft");
                    String id = (String) tagData.get("id");
                    String types = (String) tagData.getOrDefault("types","item");
                    if(types.equalsIgnoreCase("item")){
                        TagsBuilder.Blocks.createTag(namespace,id);
                    } else if (types.equalsIgnoreCase("block")){
                        TagsBuilder.Items.createTag(namespace,id);
                    } else if (types.equalsIgnoreCase("fluid")){
                        TagsBuilder.Fluid.createTag(namespace,id);
                    }
                }
            }
        }
    }
}