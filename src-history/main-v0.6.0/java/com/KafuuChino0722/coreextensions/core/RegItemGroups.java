package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class RegItemGroups {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File itemYamlFile = new File(subdirectory, "data/itemGroups.yml");

                    if (itemYamlFile.exists() && itemYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> itemsData = yaml.load(new FileReader(itemYamlFile));

                            if (itemsData != null && itemsData.containsKey("groups")) {
                                Map<String, Object> items = itemsData.get("groups");

                                for (Map.Entry<String, Object> entry : items.entrySet()) {
                                    if (entry.getValue() instanceof Map) {

                                        Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                                        String name = (String) itemData.get("name");
                                        String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                                        String id = (String) itemData.get("id");
                                        String icon = (String) itemData.get("icon");
                                        try {
                                            Registry.register(Registries.ITEM_GROUP,
                                                    new Identifier(namespace, id),
                                                    FabricItemGroup.builder()
                                                            .icon(() -> new ItemStack(Registries.ITEM.get(new Identifier(icon))))
                                                            .displayName(Text.translatable("itemGroup."+namespace+"."+id))
                                                            .build()
                                            );
                                            ReturnMessage.FuelYMLRegister(name, namespace, id); //returnMessage
                                        } catch (Exception e) {

                                        }

                                        provider.add("itemGroup." +namespace +"."+id, name);

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