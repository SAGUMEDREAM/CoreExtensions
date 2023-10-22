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
public class ModelChest {
    public static final TextureKey WOOD_TYPE = TextureKey.of("wood_type", TextureKey.TEXTURE);

    public static void generate(String namespace, String id) {
        respacks.addBlockState(new Identifier(namespace, id),
                BlockStateModelGeneratorExtends.createCubeBlockState(
                        Registries.BLOCK.get(new Identifier(namespace, id)),
                        new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.CUBE_ALL)
                        .parent(namespace, "block/"+id));

        respacks.addModel(new Identifier(namespace, "block/"+id),
                ModelJsonBuilder.create(Models.CUBE_ALL)
                        .parent(new Identifier("minecraft","block/chests/chest_base"))
                        .addTexture(TextureKey.PARTICLE, new Identifier("minecraft","block/oak_planks"))
                        .addTexture(WOOD_TYPE, new Identifier(namespace,"block/chest/"+id))

        );
    }
}
