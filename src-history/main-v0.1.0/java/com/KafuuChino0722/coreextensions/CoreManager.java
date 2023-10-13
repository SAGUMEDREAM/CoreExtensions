package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.command.CommandReadYaml;
import com.KafuuChino0722.coreextensions.core.*;
import com.KafuuChino0722.coreextensions.util.*;
import net.fabricmc.fabric.api.client.rendering.v1.CoreShaderRegistrationCallback;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.item.Item;
import net.minecraft.item.SwordItem;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CoreManager {
    public static void load() {
        Yaml yaml = new Yaml();
        boolean CORE_API_Enabled = true;

        try {
            Map<String, Map<String, Object>> configData = yaml.load(new FileReader("config/coreconfig.yml"));

            if (configData != null && configData.containsKey("settings")) {
                Map<String, Object> settings = configData.get("settings");

                if (settings.containsKey("CORE_API")) {
                    Object CORE_API_Enabled_Value = settings.get("CORE_API");
                    if (CORE_API_Enabled_Value instanceof Boolean) {
                        CORE_API_Enabled = (boolean) CORE_API_Enabled_Value;
                    }
                }
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }

        // 根据 CORE_API_Enabled 的值执行操作
        if (CORE_API_Enabled) {
            Main.LOGGER.info("CoreManager Loaded!");
            RegItem.load();
            RegItemCanUse.load();
            RegFood.load();
            RegBlock.load();
            RegDirectionalBlock.load();
            //RegLiquid.load();
            RegSword.load();
            RegPickaxe.load();
            RegAxe.load();
            RegShovel.load();
            RegHoe.load();
            RegArmor.load();
            RegItemGroup.load();
            RegGameRule.load();
            RegPortal.load();
            //RegCommand.load();
            CoreRegReload.load();
        } else {
            Main.LOGGER.info("CoreManager Disabled!if the setting is not you changing,please check your config/coreconfig.yml");
        }

    }
}
