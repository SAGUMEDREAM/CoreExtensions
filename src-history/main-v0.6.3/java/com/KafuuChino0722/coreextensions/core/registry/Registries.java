package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.CoreManager;
import com.KafuuChino0722.coreextensions.core.registry._Fix.PolyReloading;
import com.KafuuChino0722.coreextensions.core.registry._Modify.*;
import com.KafuuChino0722.coreextensions.data.DatapackRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Registries extends net.minecraft.registry.Registries {

    public static boolean AllowExistingReloading = Config.getConfigBoolean("ALLOW_EXISTING_REGISTRY_RELOADING");
    public static int registriesCount = 0;

    public static void bootstrap() {
        boolean IsInstalled = CoreManager.IsInstalled;

        CommandRegistry.register();
        TagRegistry.register();
        BlockRegistry.register();
        ItemRegistry.register();
        ItemRecordRegistry.register();
        ItemWeaponsRegistry.register();
        ItemToolsRegistry.register();
        ArmorRegistry.register();
        ArmorElytraRegistry.register();
        TrimMaterialRegistry.register();
        StatusEffectRegistry.register();
        PotionRegistry.register();
        if(!IsInstalled) ItemGroupRegistry.register();
        FuelRegistry.register();
        CompostingRegistry.register();
        BlockCropRegistry.register();
        PlantRegistry.register();
        BlockPaintingRegistry.register();
        EnchantRegistry.register();
        EntityVillagerRegistry.register();
        GameRuleRegistry.register();
        RecipeRegistry.register();
        _OverWrite_Item.register();
        _OverWrite_Tool.register();
        _OverWrite_Weapon.register();
        _OverWrite_Block.register();
        if(!IsInstalled) _Extend_ItemGroup.register();
        _OverWrite_Recipe.register();
        _OverWrite_LootTables.register();
        _Extend_Villager.register();
        _Extend_Tag.register();
        //RegRefect.load();
        FluidRegistry.register();
        DisableFeatureRegistry.register();

        if(IsInstalled && FabricLoader.getInstance().isModLoaded("polymc")) {
            PolyReloading.reload();
        }

        DatapackRegistry.bootstrap();
        WorldPortalRegistry.register();

        registriesCount = registriesCount + 1;
    }
}
