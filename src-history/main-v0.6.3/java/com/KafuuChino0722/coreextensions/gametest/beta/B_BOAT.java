package com.KafuuChino0722.coreextensions.gametest.beta;

import com.terraformersmc.terraform.boat.api.TerraformBoatType;
import com.terraformersmc.terraform.boat.api.TerraformBoatTypeRegistry;
import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class B_BOAT {

    public static final String MOD_ID = "coreextensions";

    public static final Identifier CHESTNUT_BOAT_ID = new Identifier(MOD_ID, "chestnut_boat");
    public static final Identifier CHESTNUT_CHEST_BOAT_ID = new Identifier(MOD_ID, "chestnut_chest_boat");

    public static final RegistryKey<TerraformBoatType> CHESTNUT_BOAT_KEY = TerraformBoatTypeRegistry.createKey(CHESTNUT_BOAT_ID);

    public static final Item CHESTNUT_BOAT = TerraformBoatItemHelper.registerBoatItem(CHESTNUT_BOAT_ID, CHESTNUT_BOAT_KEY, false);
    public static final Item CHESTNUT_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(CHESTNUT_CHEST_BOAT_ID, CHESTNUT_BOAT_KEY, true);

    public static void registerBoats() {
        TerraformBoatType chestnutBoat = new TerraformBoatType.Builder()
                .item(CHESTNUT_BOAT)
                .chestItem(CHESTNUT_CHEST_BOAT)
                .planks(Blocks.OAK_PLANKS.asItem())
                .build();

        Registry.register(TerraformBoatTypeRegistry.INSTANCE, CHESTNUT_BOAT_KEY, chestnutBoat);
    }

    public static void load() {
        registerBoats();
        TerraformBoatClientHelper.registerModelLayers(CHESTNUT_BOAT_ID, false);
    }
}
