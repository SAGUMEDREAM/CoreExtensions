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
public class ModelFence {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createFenceBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_post"),
                            new Identifier(namespace, "block/"+id+"_side")
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(net.minecraft.data.client.Models.FENCE_INVENTORY)
                            .parent(namespace, "block/"+id+"_inventory"));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_inventory"),
                    ModelJsonBuilder.create(Models.FENCE_INVENTORY)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_post"),
                    ModelJsonBuilder.create(Models.FENCE_POST)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_side"),
                    ModelJsonBuilder.create(Models.FENCE_SIDE)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

    }
}
