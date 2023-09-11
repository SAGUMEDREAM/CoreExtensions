package com.KafuuChino0722.coreextensions.util;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.slf4j.LoggerFactory;
import ru.mountcode.libraries.yaml.InvalidConfigurationException;
import ru.mountcode.mods.fabricyamlconfiguration.yaml.FabricConfiguration;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FirstShell {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");
    public static FabricConfiguration configuration = new FabricConfiguration();
    public static void load() {
        Shell();
    }
    public static void Shell() {
        // Getting default configuration file from mod assets
        InputStream defaultConfigurationFile = FirstShell.class.getResourceAsStream("/config/config.yml");

        // Setting default configuration file to configuration
        configuration.setDefault(defaultConfigurationFile);
        // Setting configuration file path
        configuration.setFile("config/core/config.yml");
        // Initialize configuration (load, if file exists else generate configuration)
        try {
            configuration.initialize();
        } catch (IOException e) {
            LOGGER.error("Configuration file cannot be generated", e);
        } catch (InvalidConfigurationException e) {
            LOGGER.error("Configuration file cannot be loaded", e);
        }
    }
}
