package com.KafuuChino0722.coreextensions.core.api;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.stat.StatType;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class MethodLanguage {
    public static Object add(@NotNull Identifier object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull String object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull Item object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull Block object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull EntityType<?> object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull Enchantment object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull EntityAttribute object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull StatType<?> object, String value) {
        return provider.add(object, value);
    }

    public static Object add(@NotNull StatusEffect object, String value) {
        return provider.add(object, value);
    }


}
