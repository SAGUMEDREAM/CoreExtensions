package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.Config;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PotionItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(PotionItem.class)
public class PotionItemMixin extends Item {

    public PotionItemMixin(Item.Settings settings) {
        super(settings);
    }

    @Unique
    private static boolean hasGlint = Config.getConfigBoolean("FIX_POTION_GLOWING");

    @Override
    public boolean hasGlint(ItemStack stack) {
        if(hasGlint) return true;
        return false;
    }

}
