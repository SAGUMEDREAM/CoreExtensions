package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.Config;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.SleepManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(SleepManager.class)
public class SleepManagerMixin {
    @Inject(method = "update", at = @At("HEAD"), cancellable = true)
    public void update(List<ServerPlayerEntity> players, CallbackInfoReturnable<Boolean> cir) {
        if(Config.getConfigBoolean("ALLOW_SINGLE_PLAYER_SLEEP_SKIP_NIGHT")) {
            cir.setReturnValue(true);
        }
    }
}
