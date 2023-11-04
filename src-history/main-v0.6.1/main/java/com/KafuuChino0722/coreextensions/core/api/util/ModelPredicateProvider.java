package com.KafuuChino0722.coreextensions.core.api.util;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static net.fabricmc.api.EnvType.CLIENT;

public class ModelPredicateProvider {
    public enum Types {
        BOW,
        CROSSBOW;

        public static void create(net.minecraft.item.Item item,Types ModelTypes) {
            if(FabricLoader.getInstance().getEnvironmentType()==CLIENT) {
                if(ModelTypes==BOW) {
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
                if(ModelTypes==CROSSBOW) {

                    ModelPredicateProviderRegistry.register(item, new Identifier("pull"), (stack, world, entity, seed) -> {
                        if (entity == null) {
                            return 0.0f;
                        }
                        if (CrossbowItem.isCharged(stack)) {
                            return 0.0f;
                        }
                        return (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack);
                    });

                    ModelPredicateProviderRegistry.register(item, new Identifier("pulling"), (stack, world, entity, seed) -> entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);
                    ModelPredicateProviderRegistry.register(item, new Identifier("charged"), (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) ? 1.0f : 0.0f);
                    ModelPredicateProviderRegistry.register(item, new Identifier("firework"), (stack, world, entity, seed) -> CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0f : 0.0f);

                }
            }
        }
    }
}
