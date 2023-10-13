package com.LoneDev.itemsadder;

import com.KafuuChino0722.coreextensions.core.brrp.Export;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.LoneDev.itemsadder.api.*;
import com.LoneDev.itemsadder.command.*;
import com.LoneDev.itemsadder.util.IaRegistry;
import net.darkhax.openloaderfcore.config.ConfigSchema;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.*;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static boolean ENABLED;

    public static final String FILE = Reference.File;
    public static final String Mods = Reference.Mods;
    public static final String ItemsAdder = Reference.ItemsAdder;
    public static final String ItemsAdderMods = Reference.ItemsAdderMods;

    public static final RuntimeResourcePack IaPacks = RuntimeResourcePack.create(new Identifier("mc", "itemsadder"));
    public static final LanguageProvider.Impl<HashMap<String, String>> IaLanguageProvider = LanguageProvider.create();

    public static ConfigSchema configIA;
    public static Path configDirIA;

    public static ConfigSchema configIAMods;
    public static Path configDirIAMods;

    public static boolean DATAGEN_EXPORT = true;

    public static void load() {
        Yaml yaml = new Yaml();
        try {
            File configFile = new File("config/coreconfig.yml");

            if (configFile.exists()) {
                Map<String, Map<String, Object>> configData = yaml.load(new FileReader(configFile));

                if (configData != null && configData.containsKey("settings")) {
                    Map<String, Object> settings = configData.get("settings");

                    if (settings.containsKey("FEATURE_ITEMSADDER")) {
                        Object Enabled_Value = settings.get("FEATURE_ITEMSADDER");
                        if (Enabled_Value instanceof Boolean) {
                            ENABLED = (boolean) Enabled_Value;
                        }
                    }
                    if (settings.containsKey("DATAGEN_EXPORT")) {
                        Object Enabled_Value = settings.get("DATAGEN_EXPORT");
                        if (Enabled_Value instanceof Boolean) {
                            DATAGEN_EXPORT = (boolean) Enabled_Value;
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if(ENABLED) {
            new Main().setup(ItemsAdder);
            new Main().setup(ItemsAdderMods);
            new Main().setupZip(ItemsAdder);
            new Main().setupZip(ItemsAdderMods);
            new Main().resources();
            IaRegistry.registerCommand(CommandIaZip::register);
        }

    }

    public void resources() {
        configDirIA = FabricLoader.getInstance().getGameDir().resolve("itemsadder");
        this.configIA = ConfigSchema.load(configDirIA);

        configDirIAMods = FabricLoader.getInstance().getGameDir().resolve("itemsadder");
        this.configIA = ConfigSchema.load(configDirIAMods);

        IaPacks.addLang(new Identifier("itemsadder", "zh_cn"), IaLanguageProvider);
        IaPacks.addLang(new Identifier("itemsadder", "en_us"), IaLanguageProvider);

        IaPacks.clearResources();
        RRPCallback.BEFORE_VANILLA.register(b -> {
            b.add(IaPacks);
        });

        if(DATAGEN_EXPORT) {
            IaPacks.dump(Export.getIaPath());
        }
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
                                new CustomLoots().load(namespace, data);
                                new CustomRecipes().load(namespace, data);
                                new CustomLanguages().load(namespace, data);
                                new CustomTreesPopulators().load(namespace, data);
                                new CustomCategory().load(namespace,data);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void setupZip(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".zip") || file.getName().endsWith(".jar")) {
                        try (ZipFile zip = new ZipFile(file)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (entry.getName().endsWith(".yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry);
                                         InputStreamReader reader = new InputStreamReader(inputStream)) {
                                        Yaml yaml = new Yaml();
                                        Map<String, Object> data = yaml.load(reader);

                                        if (data != null) {
                                            String namespace = "minecraft";
                                            Map<String, Object> info = (Map<String, Object>) data.get("info");

                                            if (info != null && info.containsKey("namespace")) {
                                                namespace = (String) info.get("namespace");
                                            }

                                            new CustomItems().load(namespace, data);
                                            new CustomLoots().load(namespace, data);
                                            new CustomRecipes().load(namespace, data);
                                            new CustomLanguages().load(namespace, data);
                                            new CustomTreesPopulators().load(namespace, data);
                                            new CustomCategory().load(namespace,data);

                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
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
