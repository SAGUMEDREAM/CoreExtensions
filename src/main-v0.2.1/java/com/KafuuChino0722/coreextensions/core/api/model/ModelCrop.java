package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.CoreBlockStateModelGenerator;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

public class ModelCrop {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            /*packs.addBlockState(new Identifier(namespace, id),
                    CoreBlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id)));*/


            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.BUTTON)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"0")));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"1")));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"2")));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"3")));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"4")));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"5")));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"6")));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"7")));


            b.add(packs);
        });
    }
}
