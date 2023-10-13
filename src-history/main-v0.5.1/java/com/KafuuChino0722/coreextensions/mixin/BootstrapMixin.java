package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.Bootstrap;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.command.EntitySelectorOptions;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemGroups;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@Mixin(Bootstrap.class)
public class BootstrapMixin {
    private static volatile boolean initialized = Bootstrap.initialized;
    private static final AtomicLong LOAD_TIME = Bootstrap.LOAD_TIME;

    @Inject(method = "initialize", at = @At("HEAD"), cancellable = true)
    private static void modifyInitialize(CallbackInfo ci) {
        if (initialized) {
            return;
        }
        initialized = true;
        Instant instant = Instant.now();
        if (Registries.REGISTRIES.getIds().isEmpty()) {
            throw new IllegalStateException("Unable to load registries");
        }
        FireBlock.registerDefaultFlammables();
        ComposterBlock.registerDefaultCompostableItems();
        if (EntityType.getId(EntityType.PLAYER) == null) {
            throw new IllegalStateException("Failed loading EntityTypes");
        }
        BrewingRecipeRegistry.registerDefaults();
        EntitySelectorOptions.register();
        DispenserBehavior.registerDefaults();
        CauldronBehavior.registerBehavior();
        Registries.bootstrap();
        ItemGroups.collect();
        Bootstrap.setOutputStreams();
        LOAD_TIME.set(Duration.between(instant, Instant.now()).toMillis());
        initialized = false;
    }
}
