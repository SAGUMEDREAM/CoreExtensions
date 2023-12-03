package com.KafuuChino0722.coreextensions.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class FluidRenderHandler {
    public static void set(String namespace, String id, int color) {
        if(FabricLoader.getInstance().getEnvironmentType()==EnvType.CLIENT) {
            FluidRenderHandlerRegistry.INSTANCE.register(
                    //RegFluids.STILL_WATER, RegFluids.FLOWING_WATER
                    Registries.FLUID.get((new Identifier(namespace,id))),
                    Registries.FLUID.get((new Identifier(namespace,"flowing_"+id))),
                    new SimpleFluidRenderHandler(
                            new Identifier("block/water_still"),
                            new Identifier("block/water_flow"),
                            color
                    ));
            BlockRenderLayerMap.INSTANCE.putFluids(RenderLayer.getTranslucent(),
                    //RegFluids.STILL_WATER, RegFluids.FLOWING_WATER
                    Registries.FLUID.get((new Identifier(namespace,id))),
                    Registries.FLUID.get((new Identifier(namespace,"flowing_"+id)))
            );
        }
    }
}
