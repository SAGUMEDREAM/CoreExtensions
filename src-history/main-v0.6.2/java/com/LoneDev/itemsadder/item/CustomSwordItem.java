package com.LoneDev.itemsadder.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomSwordItem extends SwordItem {

    public static String[] tooltipMsg;

    public CustomSwordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings, String[] tooltipMsg) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.tooltipMsg=tooltipMsg;
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
