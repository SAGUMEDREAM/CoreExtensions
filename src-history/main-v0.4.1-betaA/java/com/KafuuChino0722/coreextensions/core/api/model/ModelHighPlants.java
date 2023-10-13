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

@Environment(EnvType.CLIENT)
public class ModelHighPlants {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGeneratorExtends.createHighPlantBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id+"_buttom"),
                            new Identifier(namespace, "block/"+id+"_top")
                    ));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id+"_top")));

            packs.addModel(new Identifier(namespace, "block/"+id+"_top"),
                    ModelJsonBuilder.create(Models.CROSS)
                            .addTexture(TextureKey.CROSS, new Identifier(namespace, "block/"+id)));
            packs.addModel(new Identifier(namespace, "block/"+id+"_buttom"),
                    ModelJsonBuilder.create(Models.CROSS)
                            .addTexture(TextureKey.CROSS, new Identifier(namespace, "block/"+id)));


            b.add(packs);
        });
    }
}
