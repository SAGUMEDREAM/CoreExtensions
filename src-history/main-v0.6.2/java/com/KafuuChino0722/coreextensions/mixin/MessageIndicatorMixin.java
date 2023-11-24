package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.client.gui.hud.MessageIndicator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MessageIndicator.class)
public class MessageIndicatorMixin {
    @Inject(method = "notSecure", at = @At("HEAD"), cancellable = true)
    private static void notSecure(CallbackInfoReturnable<MessageIndicator> cir) {
        return;
    }
}
