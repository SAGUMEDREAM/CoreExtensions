package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.util.Map;

public class Config {
    public static Object getConfig(String Key) {
        try {
            Object CONFIG_VALUE;
            Yaml yaml = new Yaml();
            File configFile = new File(Reference.Config+"/coreconfig.yml");

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
        } catch (Exception e) {
            return true;
        }
        return null;
    }

    public static void updateConfig() {
        String filePath = "config/coreconfig.yml";

        if(Config.getConfigInt("CONFIG_VERSION") == -1) {
            Info.custom("The configuration file has been updated from old version to the new version.", "Config");
            new File(filePath).delete();
            ConfigBuilder.Build();
        }
    }

    public static int getConfigInt(String Key) {
        try {
            int CONFIG_VALUE;
            Yaml yaml = new Yaml();
            File configFile = new File(Reference.Config+"/coreconfig.yml");

            if (configFile.exists()) {
                Map<String, Map<String, Object>> configData = yaml.load(new FileReader(configFile));

                if (configData != null && configData.containsKey("settings")) {
                    Map<String, Object> settings = configData.get("settings");

                    if (settings.containsKey(Key)) {
                        CONFIG_VALUE = (int) settings.get(Key);
                        return CONFIG_VALUE;
                    }
                }
            }
        } catch (Exception e) {
            return -1;
        }
        return -1;
    }

    public static boolean getConfigBoolean(String Key) {
        try {
            boolean CONFIG_VALUE;
            Yaml yaml = new Yaml();
            File configFile = new File(Reference.Config+"/coreconfig.yml");

            if (configFile.exists()) {
                Map<String, Map<String, Object>> configData = yaml.load(new FileReader(configFile));

                if (configData != null && configData.containsKey("settings")) {
                    Map<String, Object> settings = configData.get("settings");

                    if (settings.containsKey(Key)) {
                        CONFIG_VALUE = (boolean) settings.get(Key);
                        return CONFIG_VALUE;
                    }
                }
            }
        } catch (Exception e) {
            return true;
        }
        return true;
    }
    public static String getConfigString(String Key) {
        try {
            String CONFIG_VALUE;
            Yaml yaml = new Yaml();
            File configFile = new File(Reference.Config+"/coreconfig.yml");

            if (configFile.exists()) {
                Map<String, Map<String, Object>> configData = yaml.load(new FileReader(configFile));

                if (configData != null && configData.containsKey("settings")) {
                    Map<String, Object> settings = configData.get("settings");

                    if (settings.containsKey(Key)) {
                        CONFIG_VALUE = (String) settings.get(Key);
                        return CONFIG_VALUE;
                    }
                }
            }
        } catch (Exception e) {
            return "-1";
        }
        return "-1";
    }
}