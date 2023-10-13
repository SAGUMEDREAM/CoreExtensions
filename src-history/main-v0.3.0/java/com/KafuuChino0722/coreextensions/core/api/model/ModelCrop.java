package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.CoreBlockStateModelGenerator;
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
public class ModelCrop {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            /*packs.addBlockState(new Identifier(namespace, id),
                    CoreBlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id)));*/
            packs.addModel(new Identifier(namespace, "item/"+id+"_age"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id+"_stage7"))
            );

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage0"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage0")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage1"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage1")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage2"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage2")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage3"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage3")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage4"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage4")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage5"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage5")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage6"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage6")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage7"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage7")));


            b.add(packs);
        });
    }
}
