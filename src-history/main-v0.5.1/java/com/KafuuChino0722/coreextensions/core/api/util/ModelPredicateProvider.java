package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.util.Reference;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import static net.fabricmc.api.EnvType.CLIENT;

public class ModelPredicateProvider {
    public static class ItemBow {
        public static void register(Item item) {
            if(Reference.EnvType==CLIENT) {
                ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (itemStack, clientWorld, livingEntity, i) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
                });

                ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (itemStack, clientWorld, livingEntity, i) -> {
                    if (livingEntity == null) {
                        return 0.0F;
                    }
                    return livingEntity.isUsingItem() && livingEntity.getActiveItem() == itemStack ? 1.0F : 0.0F;
                });
            }
        }
    }
}
