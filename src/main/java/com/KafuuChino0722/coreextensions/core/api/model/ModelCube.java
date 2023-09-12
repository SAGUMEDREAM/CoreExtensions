package com.KafuuChino0722.coreextensions.core.api.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

@Environment(EnvType.CLIENT)
public class ModelCube {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.CUBE)
                            .parent(namespace, "block/"+id));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CUBE)
                            .addTexture(TextureKey.UP, new Identifier(namespace, "block/"+id+"_up"))
                            .addTexture(TextureKey.DOWN, new Identifier(namespace, "block/"+id+"_down"))
                            .addTexture(TextureKey.WEST, new Identifier(namespace, "block/"+id+"_west"))
                            .addTexture(TextureKey.EAST, new Identifier(namespace, "block/"+id+"_east"))
                            .addTexture(TextureKey.NORTH, new Identifier(namespace, "block/"+id+"_north"))
                            .addTexture(TextureKey.SOUTH, new Identifier(namespace, "block/"+id+"_south"))
                            .addTexture(TextureKey.PARTICLE, new Identifier(namespace, "block/"+id+"_north"))
            );
            b.add(packs);
        });
    }
}
