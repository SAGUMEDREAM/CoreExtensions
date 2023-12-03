package com.KafuuChino0722.coreextensions.item;

import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;

import static net.fabricmc.api.EnvType.CLIENT;

public class VanillaBowItem extends BowItem {


    public VanillaBowItem(Settings settings) {
        super(settings);
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
}
