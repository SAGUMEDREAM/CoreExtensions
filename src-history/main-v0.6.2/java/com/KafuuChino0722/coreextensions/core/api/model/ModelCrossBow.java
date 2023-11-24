package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelElementBuilder;
import pers.solid.brrp.v1.model.ModelJsonBuilder;
import pers.solid.brrp.v1.model.ModelOverrideBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class ModelCrossBow {
    public static void generate(String namespace, String id) {

        ModelOverrideBuilder override0 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_pulling_0"));
        override0.addCondition("pulling", (int) 1);

        ModelOverrideBuilder override1 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_pulling_1"));
        override1.addCondition("pulling", (int) 1)
                .addCondition("pull", 0.58F);

        ModelOverrideBuilder override2 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_pulling_2"));
        override2.addCondition("pulling", (int) 1)
                .addCondition("pull", 1.0F);

        ModelOverrideBuilder override3 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_arrow"));
        override3.addCondition("charged", (int) 1);

        ModelOverrideBuilder override4 = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_firework"));
        override4.addCondition("charged", (int) 1)
                .addCondition("firework", (int) 1);

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent("minecraft","item/crossbow_template")
                        .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_standby"))
                        .addOverride(override0)
                        .addOverride(override1)
                        .addOverride(override2)
                        .addOverride(override3)
                        .addOverride(override4)
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
        respacks.addModel(new Identifier(namespace, "item/"+id+"_firework"),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent(namespace, "item/"+id)
                        .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_firework"))
        );
        respacks.addModel(new Identifier(namespace, "item/"+id+"_arrow"),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent(namespace, "item/"+id)
                        .addTexture(TextureKey.LAYER0, new Identifier(namespace,"item/"+id+"_arrow"))
        );

    }
}
