package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.util.Info;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mountcode.mods.fabricyamlconfiguration.yaml.FabricConfiguration;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConfigBuilder {
    public static final Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");
    public static FabricConfiguration configuration = new FabricConfiguration();

    public static void Build() {
        String fileName = "config/coreconfig.yml";
        String content = "# Configuration File for CoreExtensions\n" +
                "# Settings\n" +
                "settings:\n" +
                "  ALLOW_EXISTING_REGISTRY_RELOADING: true\n" +
                "  ALLOW_RELOADING_REGISTRY: true\n" +
                "  AUTO-REPLACE_RELOADING_REGISTRY_BLOCKS: false\n" +
                "  CHECKING_FOR_UPDATE: true\n" +
                "  ENABLED_CORE_API: true\n" +
                "  ENABLED_OLD_CORE_API: false\n" +
                "  ENABLED_DATAGEN_EXPORT: true\n" +
                "  ENABLED_DEBUG: false\n" +
                "  FEATURE_ENABLE: true\n" +
                "  FEATURE_MOON: true\n" +
                "  FEATURE_AETHER: true\n" +
                "  FEATURE_ITEMSADDER: true\n" +
                "  FEATURE_GAMETEST: true\n" +
                "  FIX_POTION_GLOWING: true\n" +
                "  CONFIG_VERSION: 1";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(content);
            writer.close();
            Info.custom("初始化完毕","CoreExtensions/Config");
            Info.custom("Initialization completed","CoreExtensions/Config");
        } catch (IOException e) {
            Info.error("The file coreconfig.yml cannot be created. Please check whether you have the relevant permissions or the storage space is full.");
        }
    }
}
