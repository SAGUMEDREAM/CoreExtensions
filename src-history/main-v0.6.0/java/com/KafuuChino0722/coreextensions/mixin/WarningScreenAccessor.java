package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.screen.WarningScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(WarningScreen.class)
public interface WarningScreenAccessor {

    @Accessor
    MultilineText getMessageText();

    @Accessor("messageText")
    void setMessageText(MultilineText messageText);
}