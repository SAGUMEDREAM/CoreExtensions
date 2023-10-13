package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegComposting {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File FuelYamlFile = new File(subdirectory, "data/composting.yml");

                    if (FuelYamlFile.exists() && FuelYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> Data = yaml.load(new FileReader(FuelYamlFile));

                            if (Data != null && Data.containsKey("items")) {
                                Map<String, Object> items = Data.get("items");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                                        String name = (String) itemData.get("name");
                                        String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                                        String id = (String) itemData.get("id");
                                        double chance = (double) itemData.get("chance");

                                        Item ItemResources = Registries.ITEM.get(new Identifier(namespace, id));
                                        CompostingChanceRegistry.INSTANCE.add(ItemResources,(float) chance);

                                        ReturnMessage.CompostingYMLRegister(name, namespace, id); //returnMessage
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
