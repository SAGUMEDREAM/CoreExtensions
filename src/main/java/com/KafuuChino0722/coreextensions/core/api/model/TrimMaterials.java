package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

public class TrimMaterials {
    public static void generate(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id+"_TM"));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            /*packs.addAsset("data/minecraft");*/
            packs.addTag(IdentifiedTagBuilder.createItem(ItemTags.TRIM_MATERIALS).add(new Identifier(namespace,id)));

            b.add(packs);
        });
    }
}
