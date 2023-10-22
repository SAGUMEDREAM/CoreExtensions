package com.KafuuChino0722.coreextensions.core.api.model;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.model.ModelJsonBuilder;
import pers.solid.brrp.v1.model.ModelOverrideBuilder;

import java.util.Collections;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

@Environment(EnvType.CLIENT)
public class ModelShield {
    public static void generate(String namespace, String id) {


        ModelOverrideBuilder override = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_blocking"));

        override.addCondition("blocking", (int) 1);

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent("fabricshieldlib", "item/fabric_shield")
                        .addTexture("shield", new Identifier(namespace,"item/"+id))
                        .setOverrides(Collections.singletonList(override))
        );

    }

    public static void generate(String namespace, String id, String Texture) {


        ModelOverrideBuilder override = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_blocking"));

        override.addCondition("blocking", (int) 1);

        respacks.addModel(new Identifier(namespace, "item/"+id),
                ModelJsonBuilder.create(Models.GENERATED)
                        .parent("fabricshieldlib", "item/fabric_shield")
                        .addTexture("shield", new Identifier(namespace,"item/"+id))
                        .setOverrides(Collections.singletonList(override))
        );

    }
}
