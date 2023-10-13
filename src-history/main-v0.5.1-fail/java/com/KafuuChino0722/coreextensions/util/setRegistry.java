package com.KafuuChino0722.coreextensions.util;

import com.mojang.serialization.Lifecycle;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class setRegistry {
    public static Block set(String namespace, String id, Block block) {
        int rawId = Registries.BLOCK.getRawId(Registries.BLOCK.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.BLOCK, rawId, Id, block);
    }
    public static Item set(String namespace, String id, Item item) {
        int rawId = Registries.ITEM.getRawId(Registries.ITEM.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.ITEM, rawId, Id, item);
    }
    public static Block set(String id, Block block) {
        int rawId = Registries.BLOCK.getRawId(Registries.BLOCK.get(new Identifier(id)));
        return Registry.register(Registries.BLOCK, rawId, id, block);
    }
    public static Item set(String id, Item item) {
        int rawId = Registries.ITEM.getRawId(Registries.ITEM.get(new Identifier(id)));
        return Registry.register(Registries.ITEM, rawId, id, item);
    }
}

