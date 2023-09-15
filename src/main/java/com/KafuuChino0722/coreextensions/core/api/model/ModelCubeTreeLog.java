package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
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
public class ModelCubeTreeLog {

    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();

            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createAxisRotatedBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_horizontal")
                    ));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.CUBE_COLUMN)
                            .parent(namespace, "block/"+id));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.CUBE_COLUMN)
                            .addTexture(TextureKey.END, new Identifier(namespace, "block/"+id+"_top"))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
            );
            packs.addModel(new Identifier(namespace, "block/"+id+"_horizontal"),
                    ModelJsonBuilder.create(Models.CUBE_COLUMN)
                            .addTexture(TextureKey.END, new Identifier(namespace, "block/"+id+"_top"))
                            .addTexture(TextureKey.SIDE, new Identifier(namespace, "block/"+id))
            );
            b.add(packs);
        });
    }
}
