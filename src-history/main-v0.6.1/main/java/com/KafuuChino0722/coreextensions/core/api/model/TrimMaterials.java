package com.KafuuChino0722.coreextensions.core.api.model;

import net.minecraft.util.Identifier;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_TRIM_MATERIALS;

public class TrimMaterials {
    public static void generate(String namespace, String id) {

            /*packs.addAsset("data/minecraft");*/
        TAG_TRIM_MATERIALS.add(new Identifier(namespace,id));

    }
}
