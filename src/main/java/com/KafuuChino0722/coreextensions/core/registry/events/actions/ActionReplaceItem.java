package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

public class ActionReplaceItem implements ActionInterface {
    public static void run(World world, PlayerEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> dropsKey = (Map<String, Object>) Key.getOrDefault("replace_item", null);
        if (dropsKey != null) {
            for (Map.Entry<String, Object> entry : dropsKey.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> Data = (Map<String, Object>) entry.getValue();
                    String itemKey = (String) Data.getOrDefault("item", null);
                    int amount = (int) Data.getOrDefault("amount", 1);
                    if(itemKey!=null) {
                        Item item = Registries.ITEM.get(new Identifier(itemKey));
                        ItemStack itemStack = new ItemStack(item,amount);
                        itemStack.setCount(amount);
                        PlayerInventory inventory = user.getInventory();
                        int ii = inventory.selectedSlot;
                        inventory.setStack(ii, itemStack);
                        user.currentScreenHandler.sendContentUpdates();
                    }
                }
            }
        }
    }
}
