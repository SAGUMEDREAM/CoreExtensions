package com.KafuuChino0722.coreextensions.core.api.util;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public class IdentifierManager {
    public static int getRawItem(@Nullable String namespace, String id) {
        return Registries.ITEM.getRawId(getItem(namespace,id));
    }
    public static int getRawBlock(@Nullable String namespace, String id) {
        return Registries.BLOCK.getRawId(getBlock(namespace,id));
    }
    public static Item getItem(@Nullable String namespace, String id) {
        return Registries.ITEM.get(new Identifier(namespace,id));
    }
    public static Item getItem(@Nullable String id) {
        return Registries.ITEM.get(new Identifier(id));
    }
    public static Block getBlock(@Nullable String namespace, String id) {
        return Registries.BLOCK.get(new Identifier(namespace,id));
    }
    public static Block getBlock(@Nullable String id) {
        return Registries.BLOCK.get(new Identifier(id));
    }
    public static Fluid getFluid(@Nullable String namespace, String id) {
        return Registries.FLUID.get(new Identifier(namespace,id));
    }
    public static Fluid getFluid(@Nullable String id) {
        return Registries.FLUID.get(new Identifier(id));
    }
    public static StatusEffect getEffect(@Nullable String namespace, String id) {
        return Registries.STATUS_EFFECT.get(new Identifier(namespace,id));
    }
    public static StatusEffect getEffect(@Nullable String id) {
        return Registries.STATUS_EFFECT.get(new Identifier(id));
    }
    public static Enchantment getEnchantment(@Nullable String namespace, String id) {
        return Registries.ENCHANTMENT.get(new Identifier(namespace,id));
    }
    public static Enchantment getEnchantment(@Nullable String id) {
        return Registries.ENCHANTMENT.get(new Identifier(id));
    }
    public static SoundEvent getSoundEvent(@Nullable String namespace, String id) {
        return Registries.SOUND_EVENT.get(new Identifier(namespace,id));
    }
    public static SoundEvent getSoundEvent(@Nullable String id) {
        return Registries.SOUND_EVENT.get(new Identifier(id));
    }
    public static BlockEntityType<?> getBlockEntityType(@Nullable String namespace, String id) {
        return Registries.BLOCK_ENTITY_TYPE.get(new Identifier(namespace,id));
    }
    public static BlockEntityType<?> getBlockEntityType(@Nullable String id) {
        return Registries.BLOCK_ENTITY_TYPE.get(new Identifier(id));
    }
    public static EntityType<?> getEntityType(@Nullable String namespace, String id) {
        return Registries.ENTITY_TYPE.get(new Identifier(namespace,id));
    }
    public static EntityType<?> getEntityType(@Nullable String id) {
        return Registries.ENTITY_TYPE.get(new Identifier(id));
    }
    public static ItemGroup getItemGroup(@Nullable String namespace, String id) {
        return Registries.ITEM_GROUP.get(new Identifier(namespace,id));
    }
    public static ItemGroup getItemGroup(@Nullable String id) {
        return Registries.ITEM_GROUP.get(new Identifier(id));
    }
}
