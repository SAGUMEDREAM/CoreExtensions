package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_TRIM_MATERIALS;
import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class TrimMaterials {
    public static void generate(String namespace, String id) {

            /*packs.addAsset("data/minecraft");*/
        TAG_TRIM_MATERIALS.add(new Identifier(namespace,id));

    }
}
