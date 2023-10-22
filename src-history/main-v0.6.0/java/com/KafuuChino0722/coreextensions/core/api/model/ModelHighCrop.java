package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelHighCrop {
    public static void generate(String namespace, String id ,int AGE) {
        respacks.addBlockState(new Identifier(namespace, id+"_block"),
                        BlockStateModelGeneratorExtends.createCropBlockState8(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3"),
                                new Identifier(namespace, "block/"+id+"_block_stage4"),
                                new Identifier(namespace, "block/"+id+"_block_stage5"),
                                new Identifier(namespace, "block/"+id+"_block_stage6"),
                                new Identifier(namespace, "block/"+id+"_block_stage7"),
                                new Identifier(namespace, "block/"+id+"_block_stage8")
                        ));

        respacks.addModel(new Identifier(namespace, "item/"+id+"_age"),
                ModelJsonBuilder.create(Models.CROP)
                        .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage6")));


        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage0"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage0")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage1"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage1")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage2"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage2")));


        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage3"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage3")));


        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage4"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage4")));


        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage5"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage5")));


        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage6"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage6")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage7"),
                    ModelJsonBuilder.create(Models.CROP)
                    .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage7")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage8"),
                    ModelJsonBuilder.create(Models.CROP)
                    .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_block_stage8")));

    }
}
