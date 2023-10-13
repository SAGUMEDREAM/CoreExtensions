package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import ru.mountcode.mods.fabricyamlconfiguration.yaml.FabricConfiguration;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class ConfigBuilder {
    public static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");
    public static FabricConfiguration configuration = new FabricConfiguration();
    public static void load() {
        Shell();
    }
    public static void Shell() {
        // 定义文件路径+文件名
        String FILE = "config/coreconfig.yml";

        // 创建配置文件内容
        Map<String, Object> configData = new LinkedHashMap<>();
        Map<String, Object> settings = new LinkedHashMap<>();

        settings.put("FEATURE_ENABLE", true);
        settings.put("FEATURE_MOON", true);
        settings.put("FEATURE_AETHER", true);
        settings.put("CORE_API", true);
        settings.put("DATAGEN_EXPORT", false);


        configData.put("settings", settings);

        // 将配置数据写入文件
        try (FileWriter writer = new FileWriter(FILE)) {
            writer.write("# Configuration File for CoreExtensions\n");
            writer.write("# Settings\n");
            for (Map.Entry<String, Object> entry : configData.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            Info.error("The file coreconfig.yml cannot be created. Please check whether you have the relevant permissions or the storage space is full.");
        }

        Info.custom("初始化完毕，请重新启动Minecraft","CoreExtensions");
        Info.custom("Initialization completed, please restart Minecraft","CoreExtensions");

        try {

            TimeUnit.SECONDS.sleep(3); // 延迟2秒

            // 注册关闭钩子
            Runtime.getRuntime().addShutdownHook(shutdownHook);

            // 终止 Java 进程
            Runtime.getRuntime().halt(0);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static final Thread shutdownHook = new Thread(() -> {

    });
}
