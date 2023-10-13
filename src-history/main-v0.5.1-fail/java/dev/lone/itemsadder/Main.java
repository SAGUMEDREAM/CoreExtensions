package com.LoneDev.itemsadder;

import com.KafuuChino0722.coreextensions.util.Reference;
import com.LoneDev.itemsadder.api.CustomItems;
import net.darkhax.openloaderfcore.config.ConfigSchema;
import net.fabricmc.loader.api.FabricLoader;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Main {
    public static final boolean ENABLED = true;

    public static final String FILE = Reference.File;
    public static final String Mods = Reference.Mods;
    public static final String ItemsAdder = Reference.ItemsAdder;
    public static final String ItemsAdderMods = Reference.ItemsAdderMods;

    public static void load() {

        if(ENABLED) {
            new Main().setup(ItemsAdder);
            new Main().setup(ItemsAdderMods);
            new Main().resources();
        }
    }

    public void resources() {

    }

    public void setup(String readDir) {
        File directory = new File(readDir);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        setup(file.getAbsolutePath());
                    } else if (file.getName().endsWith(".yml")) {
                        try {
                            Yaml yaml = new Yaml();
                            Map<String, Object> data = yaml.load(new FileReader(file));

                            String namespace = "minecraft";

                            if (data != null) {
                                Map<String, Object> info = (Map<String, Object>) data.get("info");
                                if (info != null && info.containsKey("namespace")) {
                                    namespace = (String) info.get("namespace");
                                }

                                new CustomItems().load(namespace, data);
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
