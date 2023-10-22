package com.LoneDev.itemsadder.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CustomHoeItem extends HoeItem {

    public static String[] tooltipMsg;

    public CustomHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, String[] tooltipMsg) {
        super(material, attackDamage, attackSpeed, settings);
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
