package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.core.polymc.PMBlock;
import com.KafuuChino0722.coreextensions.core.polymc.PMItem;
import com.KafuuChino0722.coreextensions.core.polymc.PMTool;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ShearsItem;
import net.minecraft.item.ToolItem;
import org.jetbrains.annotations.Nullable;

public class PolyMcLoader {
    public static class loadItem {
        public static void item(@Nullable Item item, Item VanillaItem) {
            PMItem PMItem = new PMItem();
            PMItem.registerPolysItem(item, VanillaItem);
        }
        public static void snowball(@Nullable Item item, Item VanillaItem) {
            PMItem PMItem = new PMItem();
            PMItem.registerPolysBallItem(item, VanillaItem);
        }
        public static void food(@Nullable Item item, Item VanillaItem) {
            PMItem PMItem = new PMItem();
            PMItem.registerFoodItem(item, VanillaItem);
        }
        public static void stew(@Nullable Item item, Item VanillaItem) {
            PMItem PMItem = new PMItem();
            PMItem.registerStewItem(item, VanillaItem);
        }
    }

    public static class loadTool {
        public static void sword(@Nullable ToolItem item, Item VanillaItem) {
            PMTool.registerPolysSword(item,VanillaItem);
        }

        public static void shield(@Nullable Item item, Item VanillaItem) {
            PMTool.registerPolysShield(item,VanillaItem);
        }

        public static void tool(@Nullable ToolItem item, Item VanillaItem) {
            PMTool.registerPolysTool(item,VanillaItem);
        }

        public static void shears(@Nullable ShearsItem item, Item VanillaItem) {
            PMTool.registerPolysShears(item,VanillaItem);
        }
    }

    public static class loadBlock {
        public static void block(@Nullable Block block, Block vanillaBlock) {
            PMBlock.registerBlock(block,vanillaBlock);
        }
    }

}
