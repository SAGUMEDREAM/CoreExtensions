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

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelItemElytra {
    public static void generate(String namespace, String id) {

            ModelOverrideBuilder override = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_broken"));

            override.addCondition("broken", (int) 1);

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id))
                            .setOverrides(Collections.singletonList(override)));

        respacks.addModel(new Identifier(namespace, "item/"+id+"_broken"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_broken")));

    }
}
