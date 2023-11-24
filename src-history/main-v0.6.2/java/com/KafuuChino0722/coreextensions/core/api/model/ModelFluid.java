package com.KafuuChino0722.coreextensions.core.api.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class ModelFluid {
    public static void generate(String namespace, String id) {
        if(FabricLoader.getInstance().getEnvironmentType()== EnvType.CLIENT) {

            respacks.addModel(new Identifier(namespace, "item/"+id+"_bucket"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_bucket")));

            respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0 , new Identifier("minecraft","block/"+id+"_still")));

            respacks.addModel(new Identifier(namespace, "item/"+"flowing_"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0 , new Identifier("minecraft","block/"+id+"_flow")));

        }
    }
}
