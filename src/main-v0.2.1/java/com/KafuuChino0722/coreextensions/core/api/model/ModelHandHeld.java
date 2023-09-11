package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

public class ModelHandHeld {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.HANDHELD)
                            .addTexture(TextureKey.LAYER0, new Identifier("item/"+id)));
            b.add(packs);
        });
    }
}
