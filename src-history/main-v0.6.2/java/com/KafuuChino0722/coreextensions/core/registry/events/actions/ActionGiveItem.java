package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

public class ActionGiveItem implements ActionInterface {
    public static void run(World world, PlayerEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> givesKey = (Map<String, Object>) Key.getOrDefault("give_item", null);
        if (givesKey != null) {
            for (Map.Entry<String, Object> entry : givesKey.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> gData = (Map<String, Object>) entry.getValue();
                    String give_item = (String) gData.getOrDefault("item", "minecraft:stone");
                    int amount = (int) gData.getOrDefault("amount", 1);
                    Item item = Registries.ITEM.get(new Identifier(give_item));
                    ItemStack itemStack = new ItemStack(item,amount);
                    user.giveItemStack(itemStack);
                    user.currentScreenHandler.sendContentUpdates();
                }
            }
        }
    }
}
