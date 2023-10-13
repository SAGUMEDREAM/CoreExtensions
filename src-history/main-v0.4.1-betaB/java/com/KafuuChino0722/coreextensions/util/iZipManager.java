package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.core.*;
import com.KafuuChino0722.coreextensions.core.zip.*;

import java.util.zip.ZipInputStream;

public class iZipManager {
    public static void setup(Boolean CORE_API_Enabled) {
        if(CORE_API_Enabled) {
            Info.custom("CoreManager ZipFile Loaded!","CoreExtensions | ZipInputStream");
            iZipTags.load();
            iZipBlocks.load();
            iZipItems.load();
            iZipWeapon.load();
            iZipTags.load();
            iZipArmors.load();
            iZipElytra.load();
            iZipTrimMaterials.load();
            iZipItemGroups.load();
            iZipFuels.load();
            iZipComposting.load();
            iZipTags.load();
            iZipPlants.load();
            iZipBlockPainting.load();
            iZipVillager.load();
            iZipGameRule.load();
            iZipPortal.load();
            iZipRecipes.load();
            iZipmodifyRecipes.load();
            iZipmodifyLootTable.load();
        }
    }
}
