package com.KafuuChino0722.coreextensions.core.polymc;

import io.github.theepicblock.polymc.api.PolyRegistry;
import io.github.theepicblock.polymc.api.item.CustomModelDataManager;
import io.github.theepicblock.polymc.impl.poly.item.CustomModelDataPoly;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

public class PMItem {
    public void registerPolysItem(Item item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new CustomModelDataPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, VanillaItem));
    }

    public void registerPolysBallItem(Item item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new CustomModelDataPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, VanillaItem));
    }

    public void registerFoodItem(Item item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new CustomModelDataPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, VanillaItem));
    }

    public void registerStewItem(Item item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new CustomModelDataPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, Items.MUSHROOM_STEW));
    }
}
