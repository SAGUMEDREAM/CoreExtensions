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
public class ModelStair {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createStairsBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_inner"),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_outer")
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.STAIRS)
                            .parent(namespace, "block/"+id));

        respacks.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.STAIRS)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_inner"),
                    ModelJsonBuilder.create(Models.INNER_STAIRS)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_outer"),
                    ModelJsonBuilder.create(Models.OUTER_STAIRS)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id))

            );
    }
}
