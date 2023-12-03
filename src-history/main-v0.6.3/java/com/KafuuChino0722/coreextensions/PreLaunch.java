package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.api.metadata.ContactInformation;
import net.fabricmc.loader.api.metadata.ModEnvironment;
import net.fabricmc.loader.api.metadata.Person;
import net.fabricmc.loader.impl.FabricLoaderImpl;
import org.penguinencounter.fakemodmod.FabricLoaderInterface;
import org.penguinencounter.fakemodmod.V1ModMetadata;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PreLaunch implements PreLaunchEntrypoint {

    public static final String FILE = Reference.File;

    @Override
    public void onPreLaunch() {
        bootstrap();
    }

    public static void bootstrap() {
        Map<String, Map<String, Object>> iMData = read("info.yml");
        load(iMData);
        Map<String, Map<String, Object>> iMDataZ = readZip("info.yml");
        load(iMDataZ);
    }

    public static void load(Map<String, Map<String, Object>> CEX_DATA) {
        if (CEX_DATA != null && CEX_DATA.containsKey("info")) {
            Map<String, Object> Key = CEX_DATA.getOrDefault("info",null);
            if(Key!=null) {
                String name = (String) Key.getOrDefault("name","ModName");
                String id = (String) Key.getOrDefault("id",null);
                String version = (String) Key.getOrDefault("version","1.0.0");
                String description = (String) Key.getOrDefault("description","example description");
                String ClazzStr = (String) Key.getOrDefault("entrypoints",null);
                if(id!=null && !FabricLoader.getInstance().isModLoaded("id")) {
                    registerModID(name,id,version,description);
                }
                /*if(ClazzStr!=null) {
                    try {
                        Object Clazz = new Resources(Class.forName(ClazzStr));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }*/
            }
        }
    }

    public static void registerModID(String name, String id, String version, String description) {
        try {
            FabricLoaderInterface.addMod(FabricLoaderImpl.INSTANCE,
                    FabricLoaderInterface.createPlain(
                            Path.of("/"),
                            new V1ModMetadata(
                                    id,
                                    Version.parse(version),
                                    List.of(),
                                    ModEnvironment.UNIVERSAL,
                                    Map.of(),
                                    List.of(),
                                    List.of(),
                                    null,
                                    List.of(),
                                    false,
                                    name,
                                    description,
                                    List.of(),
                                    List.of(),
                                    ContactInformation.EMPTY,
                                    List.of(),
                                    V1ModMetadata.NO_ICON,
                                    Map.of(),
                                    Map.of()),
                            false,
                            List.of()
                    ));
        } catch (Exception e) {

        }
    }

    public static Map<String, Map<String, Object>> readZip(String Filename) {
        String[] paths = {Reference.Mods,Reference.File};
        Yaml yaml = new Yaml();
        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("/"+Filename)) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        return yaml.load(new InputStreamReader(inputStream));
                                    }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
    public static Map<String, Map<String, Object>> read(String Filename) {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File YamlFile = new File(subdirectory, "/"+Filename);

                    if (YamlFile.exists() && YamlFile.isFile()) {
                        try {
                            return yaml.load(new FileReader(YamlFile));

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }


}
