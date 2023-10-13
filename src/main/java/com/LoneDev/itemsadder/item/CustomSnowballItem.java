package com.LoneDev.itemsadder.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SnowballItem;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomSnowballItem extends SnowballItem {
    public static String[] tooltipMsg;

    public CustomSnowballItem(Settings settings, String[] tooltipMsg) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (tooltipMsg != null) {
            for (String loreLine : tooltipMsg) {
                tooltip.add(Text.of(loreLine));
            }
        }
    }
}
