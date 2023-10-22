package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.SharedConstants;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.client.resource.language.I18n;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {
    @Inject(method = "getWindowTitle", at = @At("HEAD"), cancellable = true)
    private void modifyWindowTitle(CallbackInfoReturnable<String> ci) {
        MinecraftClient client = (MinecraftClient) (Object) this;

        StringBuilder stringBuilder = new StringBuilder("Minecraft");
        stringBuilder.append(" ");
        stringBuilder.append(SharedConstants.getGameVersion().getName());
        ClientPlayNetworkHandler clientPlayNetworkHandler = client.getNetworkHandler();

        if (clientPlayNetworkHandler != null && clientPlayNetworkHandler.getConnection().isOpen()) {
            stringBuilder.append(" - ");
            if (client.getServer() != null && !client.getServer().isRemote()) {
                stringBuilder.append(I18n.translate("title.singleplayer", new Object[0]));
            } else if (client.isConnectedToRealms()) {
                stringBuilder.append(I18n.translate("title.multiplayer.realms", new Object[0]));
            } else if (client.getServer() == null && (client.getCurrentServerEntry() == null || !client.getCurrentServerEntry().isLocal())) {
                stringBuilder.append(I18n.translate("title.multiplayer.other", new Object[0]));
            } else {
                stringBuilder.append(I18n.translate("title.multiplayer.lan", new Object[0]));
            }
        }
        ci.setReturnValue(stringBuilder.toString());
    }

}
