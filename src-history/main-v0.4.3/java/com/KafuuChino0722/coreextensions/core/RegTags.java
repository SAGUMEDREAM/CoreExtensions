package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.Plants;
import com.KafuuChino0722.coreextensions.core.api.util.TagsBuilder;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;


public class RegTags {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/tag.yml");

                    if (blockYamlFile.exists() && blockYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(blockYamlFile));

                            if (blocksData != null && blocksData.containsKey("tags")) {
                                Map<String, Object> blocks = blocksData.get("tags");

                                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> tagData = (Map<String, Object>) entry.getValue();
                                        String name = (String) tagData.get("name");
                                        //String namespace = blocksData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                        String namespace = (String) tagData.getOrDefault("namespace","minecraft");
                                        String id = (String) tagData.get("id");
                                        String types = (String) tagData.get("types");
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
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}