package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

@Environment(EnvType.CLIENT)
public class ModelTorch {

    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createSingletonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id)));

            packs.addBlockState(new Identifier(namespace, "wall_"+id),
                    BlockStateModelGeneratorExtends.createWallTorchBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, "wall_"+id)),
                            new Identifier(namespace, "block/"+"wall_"+id)));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.CUBE_ALL)
                            .parent(namespace, "block/"+id));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.TEMPLATE_TORCH)
                            .addTexture(TextureKey.TORCH, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+"wall_"+id),
                    ModelJsonBuilder.create(Models.TEMPLATE_TORCH_WALL)
                            .addTexture(TextureKey.TORCH, new Identifier(namespace, "block/"+id)));

            b.add(packs);
        });
    }
}
