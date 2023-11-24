package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.core.registry.events.eventsWorld.EventTickRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {
    @Inject(method = "tickEntity",at = @At("HEAD"))
    public void tickEntity(Entity entity, CallbackInfo ci) {
        /*boolean Z = Config.getConfigBoolean("TICK_EVENTS");
        if(Z) {
            try {
                EventTickRegistry.register();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }
}
