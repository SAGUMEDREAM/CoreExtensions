package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelFruitBush {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGeneratorExtends.createFruitBushBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_block_stage0"),
                            new Identifier(namespace, "block/"+id+"_block_stage1"),
                            new Identifier(namespace, "block/"+id+"_block_stage2"),
                            new Identifier(namespace, "block/"+id+"_block_stage2")
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id+"_age"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id+"_stage7")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage0"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage0")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage1"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage1")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage2"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage2")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage3"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage3")));

    }
}
