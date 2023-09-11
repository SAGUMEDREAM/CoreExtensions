package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

public class ModelTrapDoor {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createTrapdoorBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_top"),
                            new Identifier(namespace, "block/"+id+"_bottom"),
                            new Identifier(namespace, "block/"+id+"_open")
                    ));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.TEMPLATE_TRAPDOOR_TOP)
                            .parent(namespace, "block/"+id+"_bottom"));

            packs.addModel(new Identifier(namespace, "block/"+id+"_bottom"),
                    ModelJsonBuilder.create(Models.TEMPLATE_TRAPDOOR_BOTTOM)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_open"),
                    ModelJsonBuilder.create(Models.TEMPLATE_TRAPDOOR_OPEN)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_top"),
                    ModelJsonBuilder.create(Models.TEMPLATE_TRAPDOOR_TOP)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));


            b.add(packs);
        });
    }
}
