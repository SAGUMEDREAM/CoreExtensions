package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class iZipGameRule {
    public static final String FILE = Reference.File;

    public static void load() {
        loadGameRulesFromZipFiles(new File(FILE));
    }

    private static void loadGameRulesFromZipFiles(File directory) {
        File[] zipFiles = directory.listFiles((dir, name) ->
                name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

        if (zipFiles != null) {
            for (File zipFile : zipFiles) {
                try (ZipFile zip = new ZipFile(zipFile)) {
                    Enumeration<? extends ZipEntry> entries = zip.entries();

                    while (entries.hasMoreElements()) {
                        ZipEntry entry = entries.nextElement();

                        if (!entry.isDirectory() && entry.getName().equals("rules.yml")) {
                            try (InputStream inputStream = zip.getInputStream(entry)) {
                                loadGameRulesFromYamlStream(inputStream);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void loadGameRulesFromYamlStream(InputStream inputStream) {
        Yaml yaml = new Yaml();

        Map<String, Map<String, Object>> gameRulesData = yaml.load(new InputStreamReader(inputStream));

        if (gameRulesData != null && gameRulesData.containsKey("gamerules")) {
            Map<String, Object> gamerules = gameRulesData.get("gamerules");

            for (Map.Entry<String, Object> entry : gamerules.entrySet()) {
                if (entry.getValue() instanceof Boolean) {
                    String ruleName = entry.getKey();
                    boolean defaultValue = (boolean) entry.getValue();

                    GameRules.Key<GameRules.BooleanRule> gameRuleKey = GameRuleRegistry.register(ruleName, GameRules.Category.MOBS,
                            GameRuleFactory.createBooleanRule(defaultValue));
                    Info.custom("Gamerule " + gameRuleKey + " registered!", "GamerulesManager");
                }
            }
        }
    }
}
