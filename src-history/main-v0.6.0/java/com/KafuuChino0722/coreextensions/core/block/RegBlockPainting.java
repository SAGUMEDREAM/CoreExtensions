package com.KafuuChino0722.coreextensions.core.block;

import com.KafuuChino0722.coreextensions.core.api.painting.BlockPainting;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;


public class RegBlockPainting {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/painting.yml");

                    if (blockYamlFile.exists() && blockYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(blockYamlFile));

                            if (blocksData != null && blocksData.containsKey("paintings")) {
                                Map<String, Object> blocks = blocksData.get("paintings");

                                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                                        String name = (String) blockData.get("name");
                                        String author = (String) blockData.get("author");
                                        String namespace = blockData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                        String id = (String) blockData.get("id");
                                        Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
                                        int SizeX = properties.containsKey("SizeX") ? (int) properties.get("SizeX") : 1;
                                        int SizeY = properties.containsKey("SizeY") ? (int) properties.get("SizeY") : 1;

                                        BlockPainting.registerPainting(namespace, id ,SizeX, SizeY);

                                        provider.add("painting." +namespace +"."+id+".title", name)
                                                .add("painting." +namespace +"."+id+".author", author);

                                        Info.custom("BlockPainting " + name + "<->" + namespace + ":" + id + " registered!","BlockPainting");
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