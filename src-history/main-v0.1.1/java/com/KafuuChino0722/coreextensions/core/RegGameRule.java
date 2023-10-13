package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegGameRule {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        try {
            Map<String, Map<String, Object>> gameRulesData = yaml.load(new FileReader(FILE + "rules.yml"));

            if (gameRulesData != null && gameRulesData.containsKey("gamerules")) {
                Map<String, Object> gamerules = gameRulesData.get("gamerules");

                for (Map.Entry<String, Object> entry : gamerules.entrySet()) {
                    if (entry.getValue() instanceof Boolean) {
                        String ruleName = entry.getKey();
                        boolean defaultValue = (boolean) entry.getValue();

                        GameRules.Key<GameRules.BooleanRule> gameRuleKey = GameRuleRegistry.register(ruleName, GameRules.Category.MOBS,
                                GameRuleFactory.createBooleanRule(defaultValue));
                        Main.LOGGER.info("Gamerule " + gameRuleKey + " registered!");

                        // 可以在这里根据需要对 gameRuleKey 进行操作
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
