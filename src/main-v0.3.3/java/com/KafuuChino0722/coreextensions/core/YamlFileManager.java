package com.KafuuChino0722.coreextensions.core;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class YamlFileManager {
    public static List<String> readAllYamlFiles(String directory) {
        List<String> yamlContents = new ArrayList<>();

        File dir = new File(directory);
        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".yml")) {
                        yamlContents.add(readYamlFile(file));
                    } else if (file.isDirectory()) {
                        yamlContents.addAll(readAllYamlFiles(file.getAbsolutePath()));
                    }
                }
            }
        }

        return yamlContents;
    }

    private static String readYamlFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
            reader.close();
            return content.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
