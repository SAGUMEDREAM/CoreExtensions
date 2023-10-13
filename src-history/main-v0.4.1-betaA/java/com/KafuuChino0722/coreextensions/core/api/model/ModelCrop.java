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

@Environment(EnvType.CLIENT)
public class ModelCrop {
    public static void generate(String namespace, String id ,int AGE) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            if (AGE==7) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState7(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3"),
                                new Identifier(namespace, "block/"+id+"_block_stage4"),
                                new Identifier(namespace, "block/"+id+"_block_stage5"),
                                new Identifier(namespace, "block/"+id+"_block_stage6"),
                                new Identifier(namespace, "block/"+id+"_block_stage7")
                        ));
            } else if (AGE==6) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState6(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3"),
                                new Identifier(namespace, "block/"+id+"_block_stage4"),
                                new Identifier(namespace, "block/"+id+"_block_stage5"),
                                new Identifier(namespace, "block/"+id+"_block_stage6")
                        ));
            } else if (AGE==5) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState5(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3"),
                                new Identifier(namespace, "block/"+id+"_block_stage4"),
                                new Identifier(namespace, "block/"+id+"_block_stage5")
                        ));
            } else if (AGE==4) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState4(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3"),
                                new Identifier(namespace, "block/"+id+"_block_stage4")
                        ));
            } else if (AGE==3) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState3(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3")
                        ));
            } else if (AGE==2) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState2(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2")
                        ));
            } else if (AGE==1) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState1(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1")
                        ));
            } else if (AGE>=8) {
                packs.addBlockState(new Identifier(namespace, id),
                        BlockStateModelGeneratorExtends.createCropBlockState7(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3"),
                                new Identifier(namespace, "block/"+id+"_block_stage4"),
                                new Identifier(namespace, "block/"+id+"_block_stage5"),
                                new Identifier(namespace, "block/"+id+"_block_stage6"),
                                new Identifier(namespace, "block/"+id+"_block_stage7")
                        ));
            }

            packs.addModel(new Identifier(namespace, "item/"+id+"_age"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id+"_stage0"))
            );

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage0"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage0")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage1"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage1")));
            if(AGE>=2) {
                packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage2"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage2")));

            }
            if(AGE>=3) {
                packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage3"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage3")));
            }

            if(AGE>=4) {
                packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage4"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage4")));
            }

            if(AGE>=5) {
                packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage5"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage5")));
            }

            if(AGE>=6) {
                packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage6"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage6")));
            }

            if(AGE>=7) {
                packs.addModel(new Identifier(namespace, "block/"+id+"_block_stage7"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage7")));
            }


            b.add(packs);
        });
    }
}
