package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelSign {
    public static void generate(String namespace, String id) {

        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, new Identifier(namespace,"entity/signs/"+id)));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, new Identifier(namespace,"entity/signs/hanging/"+id)));

        respacks.addBlockState(new Identifier(namespace, id+"_"+"standing"),
                    BlockStateModelGeneratorExtends.createHangingSignBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "item/"+id)));

        respacks.addBlockState(new Identifier(namespace, id+"_"+"wall"),
                BlockStateModelGeneratorExtends.createHangingSignBlockState(
                        Registries.BLOCK.get(new Identifier(namespace, id+"_"+"wall")),
                        new Identifier(namespace, "item/"+id)));

        respacks.addBlockState(new Identifier(namespace, id+"_"+"hanging"),
                BlockStateModelGeneratorExtends.createHangingSignBlockState(
                        Registries.BLOCK.get(new Identifier(namespace, id+"_"+"hanging")),
                        new Identifier(namespace, "item/"+id)));

        respacks.addBlockState(new Identifier(namespace, id+"_"+"wall_hanging"),
                BlockStateModelGeneratorExtends.createHangingSignBlockState(
                        Registries.BLOCK.get(new Identifier(namespace, id+"_"+"wall_hanging")),
                        new Identifier(namespace, "item/"+id)));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent("item/generated")
                        .addTexture(TextureKey.LAYER0,new Identifier(namespace, "item/"+id)));

        respacks.addModel(new Identifier(namespace, "item/"+id+"_hanging"),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent("item/generated")
                        .addTexture(TextureKey.LAYER0,new Identifier(namespace, "item/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_"+"standing"),
                ModelJsonBuilder.create(Models.CUBE_ALL)
                        .addTexture(TextureKey.PARTICLE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_"+"standing"),
                ModelJsonBuilder.create(Models.CUBE_ALL)
                        .addTexture(TextureKey.PARTICLE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_"+"hanging"),
                ModelJsonBuilder.create(Models.CUBE_ALL)
                        .addTexture(TextureKey.PARTICLE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_"+"wall_hanging"),
                ModelJsonBuilder.create(Models.CUBE_ALL)
                        .addTexture(TextureKey.PARTICLE, new Identifier(namespace, "block/"+id)));

    }
}
