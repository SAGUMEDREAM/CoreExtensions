package com.KafuuChino0722.coreextensions.mixin;


import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Registries.class)
public class RegistriesMixin {
    @Inject(method = "freezeRegistries", at = @At("HEAD"), cancellable = true)
    private static void freezeRegistries(CallbackInfo ci) {

        ci.cancel();
    }
}
