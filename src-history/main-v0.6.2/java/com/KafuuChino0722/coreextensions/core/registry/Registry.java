package com.KafuuChino0722.coreextensions.core.registry;

import com.mojang.serialization.Lifecycle;
import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public abstract class Registry<T> implements net.minecraft.registry.Registry<T>, MutableRegistry<T> {
}
