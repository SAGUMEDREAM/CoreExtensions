package org.penguinencounter.fakemodmod;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigMessagePicker implements MessagePicker {
    private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger("CoreExtensions | ConfigMessagePicker");

    public static class PickerFormat {
        public List<List<String>> entries = List.of();
    }

    private List<PickerFormat> pickFromList = new ArrayList<>();

    @Override
    public List<String> pick() {
        if (pickFromList.isEmpty()) {
            LOGGER.warn("Failed: pickFromList is empty!");
            return List.of("Oh no!",
                    "The configuration file broke",
                    "Why is pickFromList empty??");
        }

        // Randomly select a configuration file from the list
        int index = (int) (Math.random() * pickFromList.size());
        PickerFormat pickFrom = pickFromList.get(index);

        if (pickFrom.entries.isEmpty()) {
            LOGGER.info("No entries in configuration file");
            return List.of(
                    "You didn't configure the mod",
                    "Go to your .minecraft folder",
                    "Config, then fmm_picker.json",
                    "Add some clever messages to the entries list",
                    "(add lists of strings, not just strings)",
                    "Then restart the game"
            );
        }

        LOGGER.info("picking mod message, of " + pickFrom.entries.size() + " entries");
        int entryIndex = (int) (Math.random() * pickFrom.entries.size());
        return pickFrom.entries.get(entryIndex);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void loadConfigsFromDirectory(File directory) throws IOException {
        Gson gson = new GsonBuilder().create();
        File configFile = new File(directory, "data/info.json");

        if (!configFile.exists()) {
            LOGGER.warn("Configuration file does not exist in: " + directory);
            return;
        }

        try (FileReader reader = new FileReader(configFile)) {
            PickerFormat pickFrom = gson.fromJson(reader, PickerFormat.class);
            if (pickFrom != null) {
                pickFromList.add(pickFrom);
            }
        }
    }

    private void loadConfigsFromSubdirectories(File directory) throws IOException {
        File[] subdirectories = directory.listFiles(File::isDirectory);
        if (subdirectories != null) {
            for (File subdirectory : subdirectories) {
                loadConfigsFromDirectory(subdirectory);
            }
        }
    }

    @Override
    public void ready() {
        File coreDirectory = FabricLoader.getInstance().getGameDir().resolve("core").toFile();

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            try {
                loadConfigsFromSubdirectories(coreDirectory);
            } catch (IOException e) {
                LOGGER.warn("Failed to read configuration files from core directory and its subdirectories", e);
            }
        }
    }
}
