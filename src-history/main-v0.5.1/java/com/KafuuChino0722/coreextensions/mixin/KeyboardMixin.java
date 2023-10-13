package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Util;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public abstract class KeyboardMixin {

    @Shadow
    MinecraftClient client;
    boolean switchF3State;
    long debugCrashStartTime = -1L;

    @Shadow
    abstract public void debugLog(String key, Object... args);

    public boolean reloadResources(int key) {
        if (this.debugCrashStartTime > 0L && this.debugCrashStartTime < Util.getMeasuringTimeMs() - 100L) {
            return true;
        } else if (key == 84) {
            this.debugLog("debug.reload_resourcepacks.message");
            this.client.reloadResources();
            return true;
        } else {
            return false;
        }
    };

    @Inject(at = @At("HEAD"), method = "Lnet/minecraft/client/Keyboard;onKey(JIIII)V")
    public void onKey(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        if (window == this.client.getWindow().getHandle()) {
            boolean bl2;
            if (action != 0 && this.client.currentScreen != null) {
                bl2 = InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow().getHandle(), 292)
                        && this.reloadResources(key);
                this.switchF3State |= bl2;
            }
        }
    }
}