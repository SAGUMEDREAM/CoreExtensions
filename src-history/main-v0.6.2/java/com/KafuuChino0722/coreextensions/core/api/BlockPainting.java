package com.KafuuChino0722.coreextensions.core.api;

import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.registry.*;
import net.minecraft.util.Identifier;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PAINTINGS;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;

public class BlockPainting {


    public BlockPainting(DefaultedRegistry<PaintingVariant> paintingVariant, Identifier identifier) {
    }

    private static RegistryKey<PaintingVariant> of(String id) {
        return RegistryKey.of(RegistryKeys.PAINTING_VARIANT, new Identifier(id));
    }

    public static void registerPainting(String namespace, String id, int SizeX, int SizeY) {
        try {
            Registry.register(Registries.PAINTING_VARIANT,
                    new Identifier(namespace,id),
                    new PaintingVariant(SizeX,SizeY)
            );
        } catch (Exception e) {
            if(Registries.PAINTING_VARIANT.containsId(new Identifier(namespace,id))&&AllowExistingReloading) {
                setRegistry.set(namespace, id, new PaintingVariant(SizeX, SizeY));
            }
        }
        TAG_PAINTINGS.add(new Identifier(namespace,id));
    }
}
