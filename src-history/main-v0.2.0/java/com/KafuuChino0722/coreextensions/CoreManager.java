package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.util.*;

import com.KafuuChino0722.coreextensions.core.*;
import com.KafuuChino0722.coreextensions.core.RegBlocks;
import com.KafuuChino0722.coreextensions.core.debug.RegPortal;
import com.KafuuChino0722.coreextensions.core.oldapi_v1.RegItemGroup;
import com.KafuuChino0722.coreextensions.core.oldapi_v1.block.*;
import com.KafuuChino0722.coreextensions.core.oldapi_v1.item.*;
import com.KafuuChino0722.coreextensions.core.oldapi_v1.RegFuel;
import com.KafuuChino0722.coreextensions.core.oldapi_v1.item.RegArmor;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class CoreManager {
    public static void load() {
        Yaml yaml = new Yaml();
        boolean CORE_API_Enabled = true;
        boolean OLD_CORE_API_Enabled = false;
        boolean DEBUG_Enabled = false;

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
                if (settings.containsKey("DEBUG")) {
                    Object DEBUG_Enabled_Value = settings.get("DEBUG");
                    if (DEBUG_Enabled_Value instanceof Boolean) {
                        DEBUG_Enabled = (boolean) DEBUG_Enabled_Value;
                    }
                }
                if (settings.containsKey("OLD")) {
                    Object OLD_CORE_API_Value = settings.get("OLD");
                    if (OLD_CORE_API_Value instanceof Boolean) {
                        OLD_CORE_API_Enabled = (boolean) OLD_CORE_API_Value;
                    }
                }
            }
        } catch (IOException ee) {
            ee.printStackTrace();
        }

        // 根据 CORE_API_Enabled 的值执行操作
        if (CORE_API_Enabled) {
            if(DEBUG_Enabled){
                Info.create("CoreManager Debug Loaded!");
            }
            Info.create("CoreManager Loaded!");
            RegBlocks.load();
            RegItems.load();
            com.KafuuChino0722.coreextensions.core.RegFuel.load();
            RegItemGroups.load();
            RegGameRule.load();
            RegPortal.load();
            //RegCommand.load();
            CoreRegReload.load();
            if(OLD_CORE_API_Enabled){
                Info.create("CoreManager Old-API Loaded!");
                RegItem.load();
                RegBallItem.load();
                RegItemCanUse.load();
                RegFood.load();
                RegBlock.load();
                RegDirectionalBlock.load();
                //@v0.1.0Updated[CodeStart]
                RegFuel.load();
                RegStairsBlock.load();
                RegSlabBlock.load();
                RegFenceBlock.load();
                RegFenceGateBlock.load();
                RegWallBlock.load();
                RegDoorBlock.load();
                RegBlockButton.load();
                RegTrapdoorBlock.load();
                RegPressurePlate.load();
                //@v0.1.0Updated[CodeEnd]
                //RegLiquid.load();
                RegSword.load();
                RegPickaxe.load();
                RegAxe.load();
                RegShovel.load();
                RegHoe.load();
                RegArmor.load();
            }
        } else {
            Info.create("CoreManager Disabled!if the setting is not you changing,please check your config/coreconfig.yml");
        }

    }
}
