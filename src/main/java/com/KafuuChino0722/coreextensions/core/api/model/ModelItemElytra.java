package com.KafuuChino0722.coreextensions.core.api.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;
import pers.solid.brrp.v1.model.ModelOverrideBuilder;

import java.util.Collections;

@Environment(EnvType.CLIENT)
public class ModelItemElytra {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();

            ModelOverrideBuilder override = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_broken"));

            override.addCondition("broken", (int) 1);

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id))
                            .setOverrides(Collections.singletonList(override)));

            packs.addModel(new Identifier(namespace, "item/"+id+"_broken"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_broken")));


            b.add(packs);
        });
    }
}
