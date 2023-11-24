package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

public class ActionDropItem implements ActionInterface {
    public static void run(World world, PlayerEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> dropsKey = (Map<String, Object>) Key.getOrDefault("drop_item", null);
        if (dropsKey != null) {
            for (Map.Entry<String, Object> entry : dropsKey.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> Data = (Map<String, Object>) entry.getValue();
                    String itemKey = (String) Data.getOrDefault("item", "minecraft:self");
                    int amount = (int) Data.getOrDefault("amount", 1);
                    boolean remove = (boolean) Data.getOrDefault("remove", true);
                    if(itemKey.equalsIgnoreCase("self") || itemKey.equalsIgnoreCase("minecraft:self")) {
                        ItemStack itemStack = user.getStackInHand(user.getActiveHand()).copyWithCount(amount);
                        user.dropStack(itemStack);
                        user.getActiveItem().decrement(amount);
                        user.currentScreenHandler.sendContentUpdates();
                    } else {
                        Item item = Registries.ITEM.get(new Identifier(itemKey));
                        ItemStack itemStack = new ItemStack(item);
                        Inventory playerInventory = user.getInventory();
                        ItemStack itemStack3;
                        int ik = 0;
                        if(remove) {
                            for (int i = 0; i < playerInventory.size(); i++) {
                                ItemStack itemStack2 = playerInventory.getStack(i);
                                if(itemStack2.getItem() == item) {
                                    itemStack = itemStack2;
                                    itemStack.setCount(itemStack.getCount()-amount);
                                    ik = i;
                                    break;
                                }
                            }
                        }
                        user.dropStack(itemStack.copyWithCount(amount));
                        if(remove && !user.getAbilities().creativeMode) user.getInventory().getStack(ik).decrement(amount);
                        user.currentScreenHandler.sendContentUpdates();
                    }
                }
            }
        }
    }
}
