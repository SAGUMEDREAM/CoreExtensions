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
public class ModelStair {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createStairsBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_inner"),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_outer")
                    ));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.STAIRS)
                            .parent(namespace, "block/"+id));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.STAIRS)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id))
            );

            packs.addModel(new Identifier(namespace, "block/"+id+"_inner"),
                    ModelJsonBuilder.create(Models.INNER_STAIRS)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id))
            );

            packs.addModel(new Identifier(namespace, "block/"+id+"_outer"),
                    ModelJsonBuilder.create(Models.OUTER_STAIRS)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id))

            );

            b.add(packs);
        });
    }
}
