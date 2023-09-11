package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

public class ModelButton {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createButtonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_pressed")
                    ));

            packs.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.BUTTON_INVENTORY)
                            .parent(namespace, "block/"+id));

            packs.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.BUTTON)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_pressed"),
                    ModelJsonBuilder.create(Models.BUTTON_PRESSED)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            packs.addModel(new Identifier(namespace, "block/"+id+"_inventory"),
                    ModelJsonBuilder.create(Models.BUTTON_INVENTORY)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

            b.add(packs);
        });
    }
}
