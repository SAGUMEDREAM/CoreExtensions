package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.core.RegFluids;
import com.KafuuChino0722.coreextensions.core.zip.*;

public class iZipManager {
    public static void setup(Boolean CORE_API_Enabled) {
        if(CORE_API_Enabled) {
            Info.custom("CoreManager Zip Loaded!","CoreExtensions | ZipInputStream");
            String[] paths = {Reference.Mods,Reference.File};
            iZipCommand.load(paths);
            iZipTags.load(paths);
            iZipBlocks.load(paths);
            iZipItems.load(paths);
            iZipWeapon.load(paths);
            iZipTags.load(paths);
            iZipArmors.load(paths);
            iZipElytra.load(paths);
            iZipTrimMaterials.load(paths);
            iZipItemGroups.load(paths);
            iZipFuels.load(paths);
            iZipComposting.load(paths);
            iZipTags.load(paths);
            iZipPlants.load(paths);
            iZipBlockPainting.load(paths);
            iZipVillager.load(paths);
            iZipGameRule.load(paths);
            iZipPortal.load(paths);
            iZipRecipes.load(paths);
            iZipmodifyItem.load(paths);
            iZipmodifyWeapon.load(paths);
            iZipmodifyTool.load(paths);
            iZipmodifyBlock.load(paths);
            iZipmodifyGroups.load(paths);
            iZipmodifyRecipes.load(paths);
            iZipmodifyLootTable.load(paths);
            iZipmodifyVillager.load(paths);
            iZipRefect.load(paths);
            new iZipFluids().load(paths);
        }
    }
}
