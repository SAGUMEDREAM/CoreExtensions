package com.KafuuChino0722.coreextensions.core.api.painting;

import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.decoration.painting.PaintingVariants;
import net.minecraft.registry.*;
import net.minecraft.registry.tag.PaintingVariantTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

public class BlockPainting {


    public BlockPainting(DefaultedRegistry<PaintingVariant> paintingVariant, Identifier identifier) {
    }

    private static RegistryKey<PaintingVariant> of(String id) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, new Identifier(id));
    }

    public static void registerPainting(String namespace, String id, int SizeX, int SizeY) {
        Registry.register(Registries.PAINTING_VARIANT,
                new Identifier(namespace,id),
                new PaintingVariant(SizeX,SizeY)
        );
    }
}
