package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.api.painting.BlockPainting;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipBlockPainting {
    public static final String FILE = Reference.File;

    public static void load(String[] paths) {
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

                                if (!entry.isDirectory() && entry.getName().equals("data/painting.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("paintings")) {
                                            Map<String, Object> blocks = Data.get("paintings");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> blockData = (Map<String, Object>) DataEntry.getValue();

                                                    String name = (String) blockData.get("name");
                                                    String author = (String) blockData.get("author");
                                                    String namespace = blockData.containsKey("namespace") ? (String) blockData.get("namespace") : "minecraft";
                                                    String id = (String) blockData.get("id");
                                                    Map<String, Object> properties = (Map<String, Object>) blockData.get("properties");
                                                    int SizeX = properties.containsKey("SizeX") ? (int) properties.get("SizeX") : 1;
                                                    int SizeY = properties.containsKey("SizeY") ? (int) properties.get("SizeY") : 1;

                                                    BlockPainting.registerPainting(namespace, id, SizeX, SizeY);

                                                    String lang_us = name;

                                                    respacks.addLang(new Identifier(namespace + "_" + id + "_lang", "en_us"),
                                                            LanguageProvider.create()
                                                                    .add("painting." + namespace + "." + id + ".title", lang_us)
                                                                    .add("painting." + namespace + "." + id + ".author", author));
                                                    respacks.addLang(new Identifier(namespace + "_" + id + "_lang", "zh_cn"),
                                                            LanguageProvider.create()
                                                                    .add("painting." + namespace + "." + id + ".title", name)
                                                                    .add("painting." + namespace + "." + id + ".author", author));

                                                }
                                            }
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