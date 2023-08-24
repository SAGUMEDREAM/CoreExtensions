package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.server.integrated.IntegratedServerLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(IntegratedServerLoader.class)
public abstract class MixinBypassWarningScreen {
    @Shadow protected abstract void start(Screen parent, String levelName, boolean safeMode, boolean canShowBackupPrompt);

    /**
     * @author maxthetomas
     * @reason skip backup prompt
     */
    @Overwrite
    public void start(Screen parent, String levelName) {
        this.start(parent, levelName, false, false);
    }
}