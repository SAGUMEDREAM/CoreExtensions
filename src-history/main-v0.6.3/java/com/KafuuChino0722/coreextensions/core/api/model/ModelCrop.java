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
public class ModelCrop {
    public static void generate(String namespace, String id ,int AGE) {

            if (AGE==7) {
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
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
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
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
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
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
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
                        BlockStateModelGeneratorExtends.createCropBlockState4(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3"),
                                new Identifier(namespace, "block/"+id+"_block_stage4")
                        ));
            } else if (AGE==3) {
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
                        BlockStateModelGeneratorExtends.createCropBlockState3(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2"),
                                new Identifier(namespace, "block/"+id+"_block_stage3")
                        ));
            } else if (AGE==2) {
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
                        BlockStateModelGeneratorExtends.createCropBlockState2(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1"),
                                new Identifier(namespace, "block/"+id+"_block_stage2")
                        ));
            } else if (AGE==1) {
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
                        BlockStateModelGeneratorExtends.createCropBlockState1(
                                Registries.BLOCK.get(new Identifier(namespace, id+"_block")),
                                new Identifier(namespace, "block/"+id+"_block_stage0"),
                                new Identifier(namespace, "block/"+id+"_block_stage1")
                        ));
            } else if (AGE>=8) {
                respacks.addBlockState(new Identifier(namespace, id+"_block"),
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

        respacks.addModel(new Identifier(namespace, "item/"+id+"_age"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("minecraft", "item/generated")
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id+"_stage0"))
            );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage0"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage0")));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage1"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage1")));
            if(AGE>=2) {
                respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage2"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage2")));

            }
            if(AGE>=3) {
                respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage3"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage3")));
            }

            if(AGE>=4) {
                respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage4"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage4")));
            }

            if(AGE>=5) {
                respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage5"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage5")));
            }

            if(AGE>=6) {
                respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage6"),
                        ModelJsonBuilder.create(Models.CROP)
                                .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage6")));
            }

            if(AGE>=7) {
                respacks.addModel(new Identifier(namespace, "block/"+id+"_block_stage7"),
                    ModelJsonBuilder.create(Models.CROP)
                            .addTexture(TextureKey.CROP, new Identifier(namespace, "block/"+id+"_stage7")));
            }
    }
}
