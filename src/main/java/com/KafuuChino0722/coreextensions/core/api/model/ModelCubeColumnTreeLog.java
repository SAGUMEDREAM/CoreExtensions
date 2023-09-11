package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.block.Blocks;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ModelCubeColumnTreeLog {

    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();

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
