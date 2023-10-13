package com.KafuuChino0722.coreextensions.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShearsItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomShearsItem extends ShearsItem {

    public static String tooltipMsg;

    public CustomShearsItem(Settings settings, String tooltipMsg) {
        super(settings);
        this.tooltipMsg=tooltipMsg;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(!(tooltipMsg ==null)) {
            tooltip.add(Text.translatable(this.tooltipMsg));
            super.appendTooltip(stack, world, tooltip, context);
        }
    }

}
