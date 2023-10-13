package com.KafuuChino0722.coreextensions;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class Config {
    public static Object getConfig(String Key) throws FileNotFoundException {
        Object CONFIG_VALUE;
        Yaml yaml = new Yaml();
        File configFile = new File("config/coreconfig.yml");

        if (configFile.exists()) {
            Map<String, Map<String, Object>> configData = yaml.load(new FileReader(configFile));

            if (configData != null && configData.containsKey("settings")) {
                Map<String, Object> settings = configData.get("settings");

                if (settings.containsKey(Key)) {
                    CONFIG_VALUE = settings.get(Key);
                        return CONFIG_VALUE;
                }
            }
        }
        return null;
    }
}