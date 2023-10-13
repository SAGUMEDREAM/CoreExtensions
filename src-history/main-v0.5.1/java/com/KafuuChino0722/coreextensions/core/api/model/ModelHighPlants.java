package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
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
public class ModelHighPlants {
    public static void generate(String namespace, String id) {

            respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGeneratorExtends.createHighPlantBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_buttom"),
                            new Identifier(namespace, "block/"+id+"_top")
                    ));

            respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id+"_top")));

            respacks.addModel(new Identifier(namespace, "block/"+id+"_top"),
                    ModelJsonBuilder.create(Models.CROSS)
                            .addTexture(TextureKey.CROSS, new Identifier(namespace, "block/"+id+"_top")));

            respacks.addModel(new Identifier(namespace, "block/"+id+"_buttom"),
                    ModelJsonBuilder.create(Models.CROSS)
                            .addTexture(TextureKey.CROSS, new Identifier(namespace, "block/"+id+"_buttom")));

    }
}
