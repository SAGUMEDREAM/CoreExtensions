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

@Environment(EnvType.CLIENT)
public class ModelWall {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createWallBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_post"),
                            new Identifier(namespace, "block/"+id+"_side"),
                            new Identifier(namespace, "block/"+id+"_side_tall")
                    ));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.WALL_INVENTORY)
                            .parent(namespace, "block/"+id+"_inventory"));

            packs.addModel(new Identifier(namespace, "block/"+id+"_post"),
                    ModelJsonBuilder.create(Models.TEMPLATE_WALL_POST)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );

            packs.addModel(new Identifier(namespace, "block/"+id+"_side"),
                    ModelJsonBuilder.create(Models.TEMPLATE_WALL_SIDE)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );

            packs.addModel(new Identifier(namespace, "block/"+id+"_side_tall"),
                    ModelJsonBuilder.create(Models.TEMPLATE_WALL_SIDE_TALL)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );

            packs.addModel(new Identifier(namespace, "block/"+id+"_inventory"),
                    ModelJsonBuilder.create(Models.WALL_INVENTORY)
                            .addTexture(TextureKey.WALL, new Identifier(namespace, "block/"+id))
            );

            b.add(packs);
        });
    }
}
