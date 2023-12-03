package com.KafuuChino0722.coreextensions.core.api.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelPlants {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id)));

        respacks.addBlockState(new Identifier(namespace, "potted_"+id),
                    BlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, "potted_"+id)),
                            new Identifier(namespace, "block/potted_"+id)));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "item/potted_"+id),
                    ModelJsonBuilder.create(Models.FLOWER_POT_CROSS)
                            .parent(namespace, "block/potted_"+id));

        respacks.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROSS)
                            .addTexture(TextureKey.CROSS, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/potted_"+id),
                    ModelJsonBuilder.create(Models.FLOWER_POT_CROSS)
                            .addTexture(TextureKey.PLANT, new Identifier(namespace, "block/"+id)));

    }
}
