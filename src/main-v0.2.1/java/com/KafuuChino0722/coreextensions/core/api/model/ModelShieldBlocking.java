package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.data.client.Models;
import net.minecraft.data.client.TextureKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;
import pers.solid.brrp.v1.model.ModelOverrideBuilder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ModelShieldBlocking {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_Blocking_Jmdl"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();

            ModelOverrideBuilder override = new ModelOverrideBuilder(new Identifier(namespace, "item/"+id+"_blocking"));

            override.addCondition("blocking", (int) 1);

            packs.addModel(new Identifier(namespace, "item/"+id+"_blocking"),
                    ModelJsonBuilder.create(Models.GENERATED)
                            .parent("fabricshieldlib", "item/fabric_shield_blocking")
                            .addTexture("shield", new Identifier(namespace,"item/"+id))
                            .setOverrides(Collections.singletonList(override))
            );

            b.add(packs);
        });
    }
}
