package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.core.fabricapi.refect;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class RegRefect {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/reflect.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("reflecting")) {
                                Map<String, Object> items = itemsData.get("reflecting");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {

                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
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
            }
        }
    }
}