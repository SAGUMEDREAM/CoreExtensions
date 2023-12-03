package com.KafuuChino0722.coreextensions.data;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Provider {

    public static void generate(String filename, String path, String content) {
        if (!Files.exists(Paths.get(path + "/" + filename + DatapackRegistry.JSON))) {
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter(path + "/" + filename + DatapackRegistry.JSON));
                writer.write(content);
                writer.close();
            } catch (IOException e) {
            }
        }
    }

    public static void createFolder(String folderPath) {
        Path path = Paths.get(folderPath);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
