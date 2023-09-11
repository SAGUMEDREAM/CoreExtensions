package com.KafuuChino0722.coreextensions.mixin;

import com.mojang.patchy.BlockedServers;
import com.mojang.patchy.MojangBlockListSupplier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import com.google.common.collect.ImmutableSet;
import com.mojang.blocklist.BlockListSupplier;
import java.util.function.Predicate;

@Mixin(MojangBlockListSupplier.class)
abstract class Verify {

}
