package com.KafuuChino0722.coreextensions.util;

import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.rmi.registry.Registry;

public class PortalsManager {
    public static void load(boolean moonEnabled,boolean aetherEnabled) {
        if(moonEnabled) {
            CustomPortalBuilder.beginPortal()
                    .frameBlock(Blocks.END_STONE)
                    //.lightWithItem(Items.FLINT_AND_STEEL)
                    .lightWithFluid(Fluids.WATER)
                    .destDimID(new Identifier("world", "moon"))
                    .tintColor(12, 127, 204)
                    .onlyLightInOverworld()
                    .registerPortal();
        }
        if(aetherEnabled) {
            CustomPortalBuilder.beginPortal()
                    .frameBlock(Blocks.GLOWSTONE)
                    //.lightWithItem(Items.FLINT_AND_STEEL)
                    .lightWithFluid(Fluids.WATER)
                    .destDimID(new Identifier("minecraft", "aether_overworld"))
                    .tintColor(12, 127, 204)
                    .registerPortal();
        }
    }

}
