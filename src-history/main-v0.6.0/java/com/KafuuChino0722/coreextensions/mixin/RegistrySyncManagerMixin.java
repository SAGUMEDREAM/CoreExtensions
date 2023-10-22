package com.KafuuChino0722.coreextensions.mixin;

import io.github.theepicblock.polymc.impl.Util;
import net.fabricmc.fabric.impl.registry.sync.RegistrySyncManager;
import net.fabricmc.fabric.impl.registry.sync.packet.RegistryPacketHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({RegistrySyncManager.class})
public class RegistrySyncManagerMixin {
    public RegistrySyncManagerMixin() {
    }

    @Inject(
            method = {"sendPacket(Lnet/minecraft/server/network/ServerPlayerEntity;Lnet/fabricmc/fabric/impl/registry/sync/packet/RegistryPacketHandler;)V"},
            at = {@At("HEAD")},
            cancellable = true
    )
    private static void sendPacketInject(ServerPlayerEntity player, RegistryPacketHandler handler, CallbackInfo ci) {
        if (Util.isPolyMapVanillaLike(player)) {
            ci.cancel();
        }

    }
}
