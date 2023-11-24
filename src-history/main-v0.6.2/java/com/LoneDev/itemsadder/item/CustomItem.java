package com.LoneDev.itemsadder.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomItem extends Item {
    public static String[] tooltipMsg;

    public CustomItem(Settings settings, String[] tooltipMsg) {
        super(settings);
        this.tooltipMsg = tooltipMsg;
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
