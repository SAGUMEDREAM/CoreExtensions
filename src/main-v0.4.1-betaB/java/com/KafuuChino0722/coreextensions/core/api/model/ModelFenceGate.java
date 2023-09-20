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

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelFenceGate {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createFenceGateBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_open"),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_wall_open"),
                            new Identifier(namespace, "block/"+id+"_wall"),
                            true
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE)
                            .parent(namespace, "block/"+id));

        respacks.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_open"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE_OPEN)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_wall"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE_WALL)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_wall_open"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FENCE_GATE_WALL_OPEN)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

    }
}
