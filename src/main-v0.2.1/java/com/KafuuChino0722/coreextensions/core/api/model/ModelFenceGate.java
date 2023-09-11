package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

public class ModelFenceGate {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createFenceGateBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_open"),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_wall_open"),
                            new Identifier(namespace, "block/"+id+"_wall"),
                            true
                    ));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE)
                            .parent(namespace, "block/"+id));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_open"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE_OPEN)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_wall"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE_WALL)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_wall_open"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE_WALL_OPEN)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));


            b.add(packs);
        });
    }
}
