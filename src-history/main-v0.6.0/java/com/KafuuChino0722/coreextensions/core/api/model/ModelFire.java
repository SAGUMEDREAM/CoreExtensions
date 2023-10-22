package com.KafuuChino0722.coreextensions.core.api.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelFire {
    public static void generate(String namespace, String id) {
            /*packs.addBlockState(new Identifier(namespace, id),
                    CoreBlockStateModelGenerator.createFireBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/" + id + "_up"),
                            new Identifier(namespace, "block/" + id + "_floor"),
                            new Identifier(namespace, "block/" + id + "_up_alt"),
                            new Identifier(namespace, "block/" + id + "_side_alt"),
                            new Identifier(namespace, "block/" + id + "_side")

                    ));*/

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace, "block/"+id)));
            /*
            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent(namespace, "block/"+id));
*/
        respacks.addModel(new Identifier(namespace, "block/"+id+"_up"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_UP)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_floor"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_FLOOR)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_up_alt"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_UP_ALT)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_side_alt"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_SIDE_ALT)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_side"),
                    ModelJsonBuilder.create(Models.TEMPLATE_FIRE_SIDE)
                            .addTexture(TextureKey.FIRE, new Identifier(namespace, "block/"+id)));
    }
}
