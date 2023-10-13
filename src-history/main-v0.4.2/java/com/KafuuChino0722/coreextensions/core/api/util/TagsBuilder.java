package com.KafuuChino0722.coreextensions.core.api.util;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagsBuilder {
    public static class Blocks {
        public static TagKey<Block> createTag(String namespace, String id) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(namespace, id));
        }
    }

    public static class Items {
        public static TagKey<Item> createTag(String namespace, String id) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier(namespace, id));
        }
    }
    public static class Fluid {
        public static TagKey<net.minecraft.fluid.Fluid> createTag(String namespace, String id) {
            return TagKey.of(RegistryKeys.FLUID, new Identifier(namespace, id));
        }
    }
}
