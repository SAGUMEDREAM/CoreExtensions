package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.core.brrp.Export;
import com.KafuuChino0722.coreextensions.util.*;

import com.KafuuChino0722.coreextensions.core.*;
import com.KafuuChino0722.coreextensions.core.RegBlocks;
import com.KafuuChino0722.coreextensions.core.RegPortal;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;

public class CoreManager {

    public static final RuntimeResourcePack respacks = RuntimeResourcePack.create(new Identifier("mc", "packs"));
    public static final RuntimeResourcePack datapacks = RuntimeResourcePack.create(new Identifier("mc", "datapacks"));

    public static void load() {
        Yaml yaml = new Yaml();
        boolean CORE_API_Enabled = true;
        boolean OLD_CORE_API_Enabled = false;
        boolean DEBUG_Enabled = false;
        boolean DataGenEnabled = true;

        try {
            File configFile = new File("config/coreconfig.yml");

            if (configFile.exists()) {
                Map<String, Map<String, Object>> configData = yaml.load(new FileReader(configFile));

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
                    if (settings.containsKey("DATAGEN_EXPORT")) {
                        Object DATAGEN_Value = settings.get("DATAGEN_EXPORT");
                        if (DATAGEN_Value instanceof Boolean) {
                            DataGenEnabled = (boolean) DATAGEN_Value;
                        }
                    }

                    if (settings.containsKey("OLD")) {
                        Object OLD_CORE_API_Value = settings.get("OLD");
                        if (OLD_CORE_API_Value instanceof Boolean) {
                            OLD_CORE_API_Enabled = (boolean) OLD_CORE_API_Value;
                        }
                    }
                }
            } else {
                System.out.println("配置文件不存在！");
            }

            // 根据 CORE_API_Enabled 的值执行操作
            if (CORE_API_Enabled) {
                if (DEBUG_Enabled) {
                    Info.create("CoreManager Debug Loaded!");
                }
                Info.create("CoreManager Loaded!");
                respacks.clearResources();
                RegTags.load();
                RegBlocks.load();
                RegItems.load();
                RegWeapon.load();
                RegTool.load();
                RegArmors.load();
                RegElytra.load();
                RegTrimMaterials.load();
                RegItemGroups.load();
                RegFuels.load();
                RegComposting.load();
                RegCrop.load();
                RegPlants.load();
                RegBlockPainting.load();
                RegVillager.load();
                RegGameRule.load();
                RegPortal.load();
                RegRecipes.load();
                RegmodifyRecipes.load();
                RegmodifyLootTable.load();
                //RegCommand.load();
                CoreRegReload.load();
                iZipManager.setup(CORE_API_Enabled);

                RRPCallback.BEFORE_VANILLA.register(b -> {
                    b.add(respacks);
                });

                if(DataGenEnabled) {
                    respacks.dump(Export.getPath());
                }

                if (OLD_CORE_API_Enabled) {
                    Info.create("CoreManager OLD-API Will Be Loaded!");
                }
            } else {
                Info.create("CoreManager Disabled!if the setting is not you changing,please check your config/coreconfig.yml");
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}