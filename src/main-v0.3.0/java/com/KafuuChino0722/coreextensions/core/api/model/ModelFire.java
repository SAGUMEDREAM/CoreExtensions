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
public class ModelFire {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            /*packs.addBlockState(new Identifier(namespace, id),
                    CoreBlockStateModelGenerator.createFireBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/" + id + "_up"),
                            new Identifier(namespace, "block/" + id + "_floor"),
                            new Identifier(namespace, "block/" + id + "_up_alt"),
                            new Identifier(namespace, "block/" + id + "_side_alt"),
                            new Identifier(namespace, "block/" + id + "_side")

                    ));*/

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id)));
            /*
            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent(namespace, "block/"+id));
*/
            packs.addModel(new Identifier(namespace, "block/"+id+"_up"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_UP)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_floor"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_FLOOR)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_up_alt"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_UP_ALT)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_side_alt"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_SIDE_ALT)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_side"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_SIDE)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

            b.add(packs);
        });
    }
}
