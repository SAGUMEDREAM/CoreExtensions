package com.KafuuChino0722.coreextensions.mixin;

import io.netty.channel.ChannelHandlerContext;
import net.minecraft.network.ClientConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientConnection.class)
public class ClientConnectionMixin {

    @Inject(method = "exceptionCaught", at = @At("HEAD")/*, cancellable = true*/)
    private void onTimeout(ChannelHandlerContext context, Throwable ex, CallbackInfo ci) {
        //ci.cancel();
    }
}
