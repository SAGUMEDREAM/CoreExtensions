package com.github.crimsondawn45.fabricshieldlib.lib.object;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.world.World;

public interface FabricShieldDecoratorRecipe1 {
    boolean matches(CraftingInventory craftingInventory, World world);

    ItemStack craft(CraftingInventory craftingInventory, DynamicRegistryManager dynamicRegistryManager);
}
