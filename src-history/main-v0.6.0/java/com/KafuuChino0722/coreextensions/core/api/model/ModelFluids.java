package com.KafuuChino0722.coreextensions.core.api.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelFluids {
    public static void generate(String namespace, String id) {
        if((FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT)) {
            respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id)));

            respacks.addBlockState(new Identifier(namespace, "flowing_"+id),
                    BlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, "flowing_"+id)),
                            new Identifier(namespace, "block/"+id)));
        }
    }
}
