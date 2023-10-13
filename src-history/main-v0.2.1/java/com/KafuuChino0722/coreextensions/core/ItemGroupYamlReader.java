package com.KafuuChino0722.coreextensions.core;

import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemGroupYamlReader {

    public static List<String> getItemIdentifiers() {
        List<String> itemIdentifiers = new ArrayList<>();

        // 将“config/coreextensions/item_group.yml”替换为YAML文件的实际路径
        try (FileReader reader = new FileReader("FILE + item_group.yml")) {
            Yaml yaml = new Yaml();
            Map<String, List<String>> data = yaml.load(reader);

            if (data != null && data.containsKey("groups")) {
                List<String> groups = data.get("groups");
                itemIdentifiers.addAll(groups);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemIdentifiers;
    }
}
