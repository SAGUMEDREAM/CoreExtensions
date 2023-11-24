package com.KafuuChino0722.coreextensions.core.polymc;

import io.github.theepicblock.polymc.api.PolyRegistry;
import io.github.theepicblock.polymc.api.item.CustomModelDataManager;
import io.github.theepicblock.polymc.impl.poly.item.DamageableItemPoly;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolItem;

public class PMTool {

    public static void registerPolysShears(ShearsItem item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new DamageableItemPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, Items.SHEARS));
    }

    public static void registerPolysTool(ToolItem item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new DamageableItemPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, VanillaItem));
    }

    public static void registerPolysSword(ToolItem item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new DamageableItemPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, VanillaItem));
    }

    public static void registerPolysShield(Item item, Item VanillaItem) {
        PolyRegistry registry = new PolyRegistry();
        registry.registerItemPoly(item, new DamageableItemPoly(registry.getSharedValues(CustomModelDataManager.KEY), item, Items.SHIELD));
    }

}
