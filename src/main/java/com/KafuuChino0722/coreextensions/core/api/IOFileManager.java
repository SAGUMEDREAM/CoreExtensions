package com.KafuuChino0722.coreextensions.core.api;

import com.KafuuChino0722.coreextensions.util.Reference;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.concurrent.CompletableFuture;

public class IOFileManager {
    public static final String FILE = Reference.File;

    public static CompletableFuture<Map<String, Map<String, Object>>> readZipCF(String Filename) {
        return CompletableFuture.supplyAsync(() -> {
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

                                    if (!entry.isDirectory() && entry.getName().equals("data/"+Filename)) {
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
        });
    }

    public static CompletableFuture<Map<String, Map<String, Object>>> readCF(String Filename) {
        return CompletableFuture.supplyAsync(() -> {
            Yaml yaml = new Yaml();
            File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

                if (subdirectories != null) {
                    for (File subdirectory : subdirectories) {
                        File YamlFile = new File(subdirectory, "data/"+Filename);

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
        });
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

                                if (!entry.isDirectory() && entry.getName().equals("data/"+Filename)) {
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
                    File YamlFile = new File(subdirectory, "data/"+Filename);

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
