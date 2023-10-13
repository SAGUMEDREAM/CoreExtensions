package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelCube {
    public static void generate(String namespace, String id) {

            respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGeneratorExtends.createCubeBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id)));

            respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.CUBE)
                            .parent(namespace, "block/"+id));

            respacks.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CUBE)
                            .addTexture(TextureKey.UP, new Identifier(namespace, "block/"+id+"_up"))
                            .addTexture(TextureKey.DOWN, new Identifier(namespace, "block/"+id+"_down"))
                            .addTexture(TextureKey.WEST, new Identifier(namespace, "block/"+id+"_west"))
                            .addTexture(TextureKey.EAST, new Identifier(namespace, "block/"+id+"_east"))
                            .addTexture(TextureKey.NORTH, new Identifier(namespace, "block/"+id+"_north"))
                            .addTexture(TextureKey.SOUTH, new Identifier(namespace, "block/"+id+"_south"))
                            .addTexture(TextureKey.PARTICLE, new Identifier(namespace, "block/"+id+"_north"))
            );

    }

    public static void generate(String namespace, String id,
                                String down, String east, String north, String south, String up, String west) {

        respacks.addBlockState(new Identifier(namespace, id),
                BlockStateModelGeneratorExtends.createCubeBlockState(
                        Registries.BLOCK.get(new Identifier(namespace, id)),
                        new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.CUBE)
                        .parent(namespace, "block/"+id));

        respacks.addModel(new Identifier(namespace, "block/"+id),
                ModelJsonBuilder.create(Models.CUBE)
                        .addTexture(TextureKey.UP, new Identifier(namespace, up))
                        .addTexture(TextureKey.DOWN, new Identifier(namespace, down))
                        .addTexture(TextureKey.WEST, new Identifier(namespace, west))
                        .addTexture(TextureKey.EAST, new Identifier(namespace, east))
                        .addTexture(TextureKey.NORTH, new Identifier(namespace, north))
                        .addTexture(TextureKey.SOUTH, new Identifier(namespace, south))
                        .addTexture(TextureKey.PARTICLE, new Identifier(namespace, north))
        );
    }

    public static void generateCustom(String namespace, String id, String model_path) {

        respacks.addBlockState(new Identifier(namespace, id),
                BlockStateModelGeneratorExtends.createCubeBlockState(
                        Registries.BLOCK.get(new Identifier(namespace, id)),
                        new Identifier(model_path)));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.CUBE)
                        .parent(model_path));

    }
}
