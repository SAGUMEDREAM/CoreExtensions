package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.SharedConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SharedConstants.class)
public class Color {
    @Inject(method = "isValidChar", at = @At(value = "HEAD"), cancellable = true)
    private static void isValidChar(CallbackInfoReturnable<Boolean> info) {
        info.setReturnValue(true);
    }
}