package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.KafuuChino0722.coreextensions.core.registry.events.actions.ActionShotItem.tempDamage;

@Mixin(SnowballEntity.class)
public class SnowballEntityMixin {


    @Inject(method = "onEntityHit", at = @At("HEAD"))
    private void onEntityHit(EntityHitResult entityHitResult, CallbackInfo ci) {
        Entity entity = entityHitResult.getEntity();
        int i = tempDamage;
        entity.damage(((SnowballEntity)(Object)this).getDamageSources().thrown(((SnowballEntity)(Object)this), ((SnowballEntity)(Object)this).getOwner()), i);
        tempDamage = 0;
    }

}
