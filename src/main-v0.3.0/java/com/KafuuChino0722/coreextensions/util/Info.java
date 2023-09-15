package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Info {
    public static final Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");

    public static final Logger Block = LoggerFactory.getLogger("CoreExtensions | BlockManager");

    public static final Logger Item = LoggerFactory.getLogger("CoreExtensions | ItemManager");

    public static final Logger Armor = LoggerFactory.getLogger("CoreExtensions | ArmorManager");

    public static final Logger Fluid = LoggerFactory.getLogger("CoreExtensions | FluidrManager");

    public static void create(String key) {
        LOGGER.info(key);
    }

    public static void createItem(String key) {
        Item.info(key);
    }

    public static void createBlock(String key) {
        Block.info(key);
    }

    public static void createArmor(String key) {
        Armor.info(key);
    }

    public static void createFluids(String key) {
        Fluid.info(key);
    }
}
