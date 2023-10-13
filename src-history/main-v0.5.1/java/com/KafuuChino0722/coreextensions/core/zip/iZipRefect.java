package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.fabricapi.refect;
import com.KafuuChino0722.coreextensions.util.Reference;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class iZipRefect {
    public static final String FILE = Reference.File;

    public static void load(String[] paths) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/reflect.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("reflecting")) {
                                            Map<String, Object> blocks = Data.get("reflecting");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> itemData = (Map<String, Object>) DataEntry.getValue();

                                                    String types = (String) itemData.get("name");
                                                    String classDevImport = (String) itemData.get("classDevImport");
                                                    String classImImport = (String) itemData.get("classImImport");
                                                    String DevField = (String) itemData.get("DevField");
                                                    String ImField = (String) itemData.get("ImField");
                                                    Object Value = (Object) itemData.get("Value");
                                                    String Target = (String) itemData.get("Target");
                                                    String namespace = (String) itemData.get("namespace");
                                                    String id = (String) itemData.get("id");
                                                    if(types.equalsIgnoreCase("block")) {
                                                        refect.Block.set(classDevImport,classImImport,
                                                                DevField,ImField,Value,Target,namespace,id);
                                                    }
                                                    if(types.equalsIgnoreCase("item")) {
                                                        refect.Item.set(classDevImport,classImImport,
                                                                DevField,ImField,Value,Target,namespace,id);
                                                    }
                                                }
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    } catch (NoSuchFieldException e) {
                                        throw new RuntimeException(e);
                                    } catch (ClassNotFoundException e) {
                                        throw new RuntimeException(e);
                                    } catch (IllegalAccessException e) {
                                        throw new RuntimeException(e);
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