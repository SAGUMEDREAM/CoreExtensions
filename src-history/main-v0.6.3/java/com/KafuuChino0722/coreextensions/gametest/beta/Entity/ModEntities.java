package com.KafuuChino0722.coreextensions.gametest.beta.Entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {

    public static Identifier Id = new Identifier("test","test");

    /*public static final EntityType<ChomperEntity> CHOMPER = Registry.register(Registries.ENTITY_TYPE,
            Id,
            FabricEntityTypeBuilder.create(SpawnGroup.MONSTER,ChomperEntity::new)
                    .dimensions(EntityDimensions.fixed(0.4f,1.5f)).build());*/
    public static void test() {
    }
}
