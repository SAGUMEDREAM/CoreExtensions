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
public class ModelWall {
    public static void generate(String namespace, String id) {
        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createWallBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_post"),
                            new Identifier(namespace, "block/"+id+"_side"),
                            new Identifier(namespace, "block/"+id+"_side_tall")
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.WALL_INVENTORY)
                            .parent(namespace, "block/"+id+"_inventory"));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_post"),
                    ModelJsonBuilder.create(Models.TEMPLATE_WALL_POST)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_side"),
                    ModelJsonBuilder.create(Models.TEMPLATE_WALL_SIDE)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_side_tall"),
                    ModelJsonBuilder.create(Models.TEMPLATE_WALL_SIDE_TALL)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_inventory"),
                    ModelJsonBuilder.create(Models.WALL_INVENTORY)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );
    }
}
