package com.KafuuChino0722.coreextensions.util;

import com.mojang.serialization.Lifecycle;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;

public class SimpleRegistryExtends <T> extends SimpleRegistry<T> {
    public SimpleRegistryExtends(RegistryKey<? extends Registry<T>> key, Lifecycle lifecycle) {
        super(key, lifecycle);
    }

    public SimpleRegistryExtends(RegistryKey<? extends Registry<T>> key, Lifecycle lifecycle, boolean intrusive) {
        super(key, lifecycle, intrusive);
    }

    public Map<T, RegistryEntry.Reference<T>> getIntrusiveValueToEntry() {
        return intrusiveValueToEntry;
    }
}
