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
public class ModelPressurePlate {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createPressurePlateBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_down")
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.PRESSURE_PLATE_UP)
                            .parent(namespace, "block/"+id));

        respacks.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.PRESSURE_PLATE_UP)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_down"),
                    ModelJsonBuilder.create(Models.PRESSURE_PLATE_DOWN)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

    }
}
