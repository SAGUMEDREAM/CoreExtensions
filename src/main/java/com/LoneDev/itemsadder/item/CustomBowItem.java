package com.LoneDev.itemsadder.item;

import com.KafuuChino0722.coreextensions.item.BowItem;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.fabricmc.api.EnvType.CLIENT;

public class CustomBowItem extends BowItem {

    public static String tooltipMsg;

    public CustomBowItem(Settings settings, String tooltipMsg) {
        super(settings);
        this.tooltipMsg=tooltipMsg;
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
            this.RegisterModelPredicate();
        }
    }

    private void RegisterModelPredicate() {
        if(Reference.EnvType==CLIENT) {
            ModelPredicateProviderRegistry.register(new Identifier("pull"), (itemStack, clientWorld, livingEntity, i) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }
                return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
            });

            ModelPredicateProviderRegistry.register(new Identifier("pulling"), (itemStack, clientWorld, livingEntity, i) -> {
                if (livingEntity == null) {
                    return 0.0F;
                }
                return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
            });
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if(!(tooltipMsg ==null)) {
            tooltip.add(Text.translatable(this.tooltipMsg));
            super.appendTooltip(stack, world, tooltip, context);
        }
    }
}
