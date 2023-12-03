package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.MethodGameRuleCategory;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.rule.DoubleRule;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;

import java.util.Map;

public class GameRuleRegistry {
    public static final String FILE = Reference.File;

    public static void register() {
        Map<String, Map<String, Object>> fuelData = IOFileManager.read("rules.yml");
        load(fuelData);
        Map<String, Map<String, Object>> fuelDataZ = IOFileManager.readZip("rules.yml");
        load(fuelDataZ);
    }

    public static void load(Map<String, Map<String, Object>> gameRulesData) {

        if (gameRulesData != null && gameRulesData.containsKey("gamerules")) {
            Map<String, Object> rules = gameRulesData.get("gamerules");

            for (Map.Entry<String, Object> entry : rules.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> RData = (Map<String, Object>) entry.getValue();
                    String ruleName = (String) RData.getOrDefault("id",null);
                    Object defaultValue = (Object) RData.getOrDefault("defaultValue",false);
                    Object GameRuleCategory = MethodGameRuleCategory.getCatergory((String) RData.getOrDefault("types","misc"));

                    if(!net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry.hasRegistration(ruleName)&&ruleName!=null) {
                        if (defaultValue instanceof Integer) {
                            GameRules.Key<GameRules.IntRule> gameRuleKey = net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry.register(ruleName, (GameRules.Category) GameRuleCategory,
                                    GameRuleFactory.createIntRule((int) defaultValue));
                        } else if (defaultValue instanceof Double) {
                            GameRules.Key<DoubleRule> gameRuleKey = net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry.register(ruleName, (GameRules.Category) GameRuleCategory,
                                    GameRuleFactory.createDoubleRule((double) defaultValue));
                        } else if (defaultValue instanceof Boolean) {
                            GameRules.Key<GameRules.BooleanRule> gameRuleKey = net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry.register(ruleName, (GameRules.Category) GameRuleCategory,
                                    GameRuleFactory.createBooleanRule((Boolean) defaultValue));
                        }

                        Info.custom("Game-rule " + ruleName + defaultValue + " registered!","Gamerules");

                        StorageRegistry.add(StorageRegistry.GAME_RULES, new Identifier(ruleName));
                    }
                }
            }
        }
    }
}
