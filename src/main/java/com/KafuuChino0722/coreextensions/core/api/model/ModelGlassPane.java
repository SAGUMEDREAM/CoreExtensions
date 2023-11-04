package com.KafuuChino0722.coreextensions.core.api.model;

import com.KafuuChino0722.coreextensions.core.api.util.BlockStateModelGeneratorExtends;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelGlassPane {
    public static void generate(String namespace, String id) {

        Identifier post = new Identifier(namespace, "block/"+id+"_post");
        Identifier side = new Identifier(namespace, "block/"+id+"_side");
        Identifier side_alt = new Identifier(namespace, "block/"+id+"_side_alt");
        Identifier noside = new Identifier(namespace, "block/"+id+"_noside");
        Identifier noside_alt = new Identifier(namespace, "block/"+id+"_noside_alt");

        respacks.addBlockState(new Identifier(namespace, id),
                BlockStateModelGeneratorExtends.createGlassPaneBlockState(
                        Registries.BLOCK.get(new Identifier(namespace, id)),
                        post,
                        side,
                        side_alt,
                        noside,
                        noside_alt
                ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent(Models.GENERATED)
                            .addTexture(TextureKey.LAYER0, new Identifier(namespace,"block/"+id))
        );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_noside"),
                ModelJsonBuilder.create(Models.TEMPLATE_GLASS_PANE_NOSIDE)
                        .parent(Models.TEMPLATE_GLASS_PANE_NOSIDE)
                        .addTexture(TextureKey.PANE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_noside_alt"),
                ModelJsonBuilder.create(Models.TEMPLATE_GLASS_PANE_NOSIDE_ALT)
                        .parent(Models.TEMPLATE_GLASS_PANE_NOSIDE_ALT)
                        .addTexture(TextureKey.PANE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_post"),
                ModelJsonBuilder.create(Models.TEMPLATE_GLASS_PANE_POST)
                        .parent(Models.TEMPLATE_GLASS_PANE_POST)
                        .addTexture(TextureKey.PANE, new Identifier(namespace, "block/"+id))
                        .addTexture(TextureKey.EDGE, new Identifier(namespace, "block/"+id))
        );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_side"),
                ModelJsonBuilder.create(Models.TEMPLATE_GLASS_PANE_SIDE)
                        .parent(Models.TEMPLATE_GLASS_PANE_SIDE)
                        .addTexture(TextureKey.PANE, new Identifier(namespace, "block/"+id))
                        .addTexture(TextureKey.EDGE, new Identifier(namespace, "block/"+id))
        );

        respacks.addModel(new Identifier(namespace, "block/"+id+"_side_alt"),
                ModelJsonBuilder.create(Models.TEMPLATE_GLASS_PANE_SIDE_ALT)
                        .parent(Models.TEMPLATE_GLASS_PANE_SIDE_ALT)
                        .addTexture(TextureKey.PANE, new Identifier(namespace, "block/"+id))
                        .addTexture(TextureKey.EDGE, new Identifier(namespace, "block/"+id))
        );

    }
}
