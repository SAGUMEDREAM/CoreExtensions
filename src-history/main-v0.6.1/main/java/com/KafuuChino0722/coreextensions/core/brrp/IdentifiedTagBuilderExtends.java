package com.KafuuChino0722.coreextensions.core.brrp;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.poi.PointOfInterestType;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;
import pers.solid.brrp.v1.tag.ObjectTagBuilder;

public class IdentifiedTagBuilderExtends<T>  extends ObjectTagBuilder<T, IdentifiedTagBuilder<T>> {

    public final Registry<T> registry;

    public final Identifier identifier;

    public IdentifiedTagBuilderExtends(Registry<T> registry, Identifier identifier) {
        super(registry::getId);
        this.registry = registry;
        this.identifier = identifier;
    }

    public static IdentifiedTagBuilder<PaintingVariant> createPainting(Identifier identifier) {
        return new IdentifiedTagBuilder<>(Registries.PAINTING_VARIANT, identifier);
    }

    public static IdentifiedTagBuilder<PaintingVariant> createPainting(TagKey<PaintingVariant> TagKey) {
        return createPainting(TagKey.id());
    }

    public static IdentifiedTagBuilder<PointOfInterestType> createVillagerJobSite(Identifier identifier) {
        return new IdentifiedTagBuilder<>(Registries.POINT_OF_INTEREST_TYPE, identifier);
    }

    public static IdentifiedTagBuilder<PointOfInterestType> createVillagerJobSite(TagKey<PointOfInterestType> TagKey) {
        return createVillagerJobSite(TagKey.id());
    }
}

