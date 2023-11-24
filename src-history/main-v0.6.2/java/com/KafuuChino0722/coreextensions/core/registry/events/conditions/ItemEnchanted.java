package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.List;

public class ItemEnchanted implements ConditionsInterface{

    public static boolean get(World world, LivingEntity user, Hand hand, List<String> enchants) {
        ItemStack itemStack = user.getActiveItem();
        if(enchants.contains("*")) return false;
        for (String enchantStr : enchants) {
            Enchantment targetEnchantment = Registries.ENCHANTMENT.get(new Identifier(enchantStr));
            if (Registries.ENCHANTMENT.containsId(new Identifier(enchantStr))) {
                if (hasEnchantment(itemStack,targetEnchantment) > 0) {
                    return false;
                }
            }
        }
        return false;
    }

    public static int hasEnchantment(ItemStack itemStack, Enchantment targetEnchantment) {
        int h = EnchantmentHelper.getLevel(targetEnchantment, itemStack);
        return h;
    }
}
