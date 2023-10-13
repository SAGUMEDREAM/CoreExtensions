package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.util.Reference;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegVoldAPI {
    public static final String FILE = Reference.File;



    public static void load() {
            Yaml yaml = new Yaml();

            File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

                if (subdirectories != null) {
                    for (File subdirectory : subdirectories) {
                        File BlockYamlFile = new File(subdirectory, "data/block.yml");

                        if (BlockYamlFile.exists() && BlockYamlFile.isFile()) {
                            try {
                                Map<String, Map<String, Object>> Data = yaml.load(new FileReader(BlockYamlFile));

                                if (Data != null && Data.containsKey("blocks")) {
                                    Map<String, Object> blocks = Data.get("blocks");

                                    for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                        if (entry.getValue() instanceof Map) {
                                            Map<String, Object> blockData = (Map<String, Object>) entry.getValue();

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
