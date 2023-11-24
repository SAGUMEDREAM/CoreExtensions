package com.KafuuChino0722.coreextensions.client;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

import static net.fabricmc.api.EnvType.CLIENT;

public class BoatLayer {
    public static void register(Identifier identifier) {
        if(FabricLoader.getInstance().getEnvironmentType() == CLIENT)
            TerraformBoatClientHelper.registerModelLayers(identifier, false);
    }
}
