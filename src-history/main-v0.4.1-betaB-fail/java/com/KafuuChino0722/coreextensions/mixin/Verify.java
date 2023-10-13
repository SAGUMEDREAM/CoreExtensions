package com.KafuuChino0722.coreextensions.mixin;

import com.mojang.blocklist.BlockListSupplier;
import com.mojang.patchy.BlockedServers;
import com.mojang.patchy.MojangBlockListSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Mixin(MojangBlockListSupplier.class)
public abstract class Verify implements BlockListSupplier {

    @Inject(method = "createBlockList", at = @At("HEAD"), cancellable = true, remap = false)
    private void modifyURLConnection(CallbackInfoReturnable<Predicate<String>> cir) {
        try {
            URLConnection urlConnection = new URL("https://www.otomads.top/blockedservers/list.txt").openConnection();
            InputStream is = urlConnection.getInputStream();
            Throwable var3 = null;

            BlockedServers var5;
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, BlockedServers.HASH_CHARSET));
                var5 = new BlockedServers((Collection<String>) reader.lines().collect(Collectors.toSet())); // 修改为适当的数据结构
            } catch (Throwable var15) {
                var3 = var15;
                throw var15;
            } finally {
                if (is != null) {
                    if (var3 != null) {
                        try {
                            is.close();
                        } catch (Throwable var14) {
                            var3.addSuppressed(var14);
                        }
                    } else {
                        is.close();
                    }
                }
            }

            cir.setReturnValue(var5);
        } catch (IOException var17) {
            cir.setReturnValue(null);
        }
    }
}