package com.KafuuChino0722.coreextensions.core.api.model;

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
public class ModelButton {
    public static void generate(String namespace, String id) {

        respacks.addBlockState(new Identifier(namespace, id),
                    BlockStateModelGenerator.createButtonBlockState(
                            Registries.BLOCK.get(new Identifier(namespace, id)),
                            new Identifier(namespace, "block/"+id),
                            new Identifier(namespace, "block/"+id+"_pressed")
                    ));

        respacks.addModel(new Identifier(namespace, "item/"+id),
                    ModelJsonBuilder.create(Models.BUTTON_INVENTORY)
                            .parent(namespace, "block/"+id+"_inventory"));

        respacks.addModel(new Identifier(namespace, "block/"+id),
                    ModelJsonBuilder.create(Models.BUTTON)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_pressed"),
                    ModelJsonBuilder.create(Models.BUTTON_PRESSED)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

        respacks.addModel(new Identifier(namespace, "block/"+id+"_inventory"),
                    ModelJsonBuilder.create(Models.BUTTON_INVENTORY)
                            .addTexture(TextureKey.TEXTURE, new Identifier(namespace, "block/"+id)));

    }
}
