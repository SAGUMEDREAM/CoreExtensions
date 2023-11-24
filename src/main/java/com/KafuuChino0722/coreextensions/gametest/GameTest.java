package com.KafuuChino0722.coreextensions.gametest;


import com.KafuuChino0722.coreextensions.core.registrylib.EntityRegistryBuilder;
import com.KafuuChino0722.coreextensions.gametest.beta.B_BOAT;
import com.KafuuChino0722.coreextensions.gametest.beta.B_SIGN;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class GameTest {
    public static void bootstrap() {
        test();
        //RegEnchantment.test();
    }

    public static void test() {

        //B_BOAT.load();
        //B_SIGN.load();
        //Identifier entityId = new Identifier("test");
        //EntityRegistryBuilder.createBuilder(entityId).entity((type, world) -> ZombieEntity::new).dimensions(new EntityDimensions(1.0F,1.0F,true)).egg(0x000000,0x011000).build();
    }
}
