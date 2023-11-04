package com.KafuuChino0722.coreextensions.mixin;

import net.fabricmc.fabric.api.item.v1.FabricItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Item.class)
public abstract class ItemMixin
        implements ToggleableFeature,
        ItemConvertible,
        FabricItem {
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {

    }
}
