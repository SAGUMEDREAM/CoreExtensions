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
public class ModelDoor {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createDoorBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_bottom_left"),
                            new Identifier(namespace, "block/"+id+"_bottom_left_open"),
                            new Identifier(namespace, "block/"+id+"_bottom_right"),
                            new Identifier(namespace, "block/"+id+"_bottom_right_open"),
                            new Identifier(namespace, "block/"+id+"_top_left"),
                            new Identifier(namespace, "block/"+id+"_top_left_open"),
                            new Identifier(namespace, "block/"+id+"_top_right"),
                            new Identifier(namespace, "block/"+id+"_top_right_open")
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "item/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_bottom_left"),
                    ModelJsonBuilder.create(Models.DOOR_BOTTOM_LEFT)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
                    );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_bottom_right"),
                    ModelJsonBuilder.create(Models.DOOR_BOTTOM_RIGHT)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_bottom_left_open"),
                    ModelJsonBuilder.create(Models.DOOR_BOTTOM_LEFT_OPEN)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_bottom_right_open"),
                    ModelJsonBuilder.create(Models.DOOR_BOTTOM_RIGHT_OPEN)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_top_left"),
                    ModelJsonBuilder.create(Models.DOOR_TOP_LEFT)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_top_right"),
                    ModelJsonBuilder.create(Models.DOOR_TOP_RIGHT)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_top_left_open"),
                    ModelJsonBuilder.create(Models.DOOR_TOP_LEFT_OPEN)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_top_right_open"),
                    ModelJsonBuilder.create(Models.DOOR_TOP_RIGHT_OPEN)
                            .addTexture(TextureKey.BOTTOM, new Identifier(namespace, "block/"+id+"_bottom"))
                            .addTexture(TextureKey.TOP, new Identifier(namespace, "block/"+id+"_top"))
            );


    }
}
