package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.registry.MutableRegistry;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(SimpleRegistry.class)
public abstract class SimpleRegistryMixin<T> implements MutableRegistry<T> {


    @Shadow
    public boolean frozen = false;


    private Map<T, RegistryEntry.Reference<T>> getIntrusiveValueToEntry() {
        return ((SimpleRegistry<T>)(Object)this).intrusiveValueToEntry;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    @Override
    public RegistryEntry.Reference<T> createEntry(T value) {
        //((SimpleRegistry<T>)(Object)this).assertNotFrozen();
        return ((SimpleRegistry<T>)(Object)this).intrusiveValueToEntry.computeIfAbsent(value, valuex -> RegistryEntry.Reference.intrusive(((SimpleRegistry<T>)(Object)this).getReadOnlyWrapper(), valuex));
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public Registry<T> freeze() {
        return ((SimpleRegistry<T>)(Object)this);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final void assertNotFrozen(RegistryKey<T> key) {

    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    public final void assertNotFrozen() {

    }

}
