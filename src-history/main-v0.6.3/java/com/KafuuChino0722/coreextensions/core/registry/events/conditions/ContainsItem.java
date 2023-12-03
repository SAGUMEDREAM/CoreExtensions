package com.KafuuChino0722.coreextensions.core.registry.events.conditions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class ContainsItem implements ConditionsInterface{

    public static boolean get(@Nullable PlayerEntity user, List<String> contains) {
        if (user != null && user.isPlayer()) {
            if (user.isCreative()) return false;
            for (String item : contains) {
                Item T = Registries.ITEM.get(new Identifier(item));
                boolean find = false;

                Inventory playerInventory = user.getInventory();
                for (int i = 0; i < playerInventory.size(); i++) {
                    ItemStack itemStack = playerInventory.getStack(i);
                    if (itemStack.getItem() == T) {
                        find = true;
                        return false;
                    } else {
                        find = false;
                    }
                }
            }
            return false;
        }
        return true;
    }
}
