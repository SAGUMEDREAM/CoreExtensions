package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;
import pers.solid.brrp.v1.model.ModelOverrideBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class ModelBow {
    public static void generate(String namespace, String id) {

        ModelOverrideBuilder override0 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_pulling_0"));
        override0.addCondition("pulling", (int) 1);

        ModelOverrideBuilder override1 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_pulling_1"));
        override1.addCondition("pulling", (int) 1).addCondition("pull", 0.65F);

        ModelOverrideBuilder override2 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_pulling_2"));
        override2.addCondition("pulling", (int) 1).addCondition("pull", 0.9F);

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.GENERATED)
                        //.parent("minecraft", "item/generated")
                        .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id))
                        .addOverride(override0)
                        .addOverride(override1)
                        .addOverride(override2)
        );
        respacks.addModel(new Identifier(namespace, "item/"+id+"_pulling_0"),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent(namespace, "item/"+id)
                        .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_pulling_0"))
        );
        respacks.addModel(new Identifier(namespace, "item/"+id+"_pulling_1"),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent(namespace, "item/"+id)
                        .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_pulling_1"))
        );
        respacks.addModel(new Identifier(namespace, "item/"+id+"_pulling_2"),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent(namespace, "item/"+id)
                        .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_pulling_2"))
        );
    }
}
