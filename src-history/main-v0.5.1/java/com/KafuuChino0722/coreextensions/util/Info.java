package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Info {
    public static final Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");

    public static final Logger Block = LoggerFactory.getLogger("CoreExtensions | BlockManager");

    public static final Logger Item = LoggerFactory.getLogger("CoreExtensions | ItemManager");

    public static final Logger Loot = LoggerFactory.getLogger("CoreExtensions | LootManager");

    public static final Logger Recipe = LoggerFactory.getLogger("CoreExtensions | RecipeManager");

    public static final Logger Armor = LoggerFactory.getLogger("CoreExtensions | ArmorManager");

    public static final Logger Fluid = LoggerFactory.getLogger("CoreExtensions | FluidrManager");

    public static final Logger CLIENT = LoggerFactory.getLogger("CoreExtensions | Client");

    public static final Logger SERVER = LoggerFactory.getLogger("CoreExtensions | Server");

    public static final Logger NETWORK = LoggerFactory.getLogger("CoreExtensions | NetWork");

    public static final Logger UPDATER = LoggerFactory.getLogger("CoreExtensions | Version Checker");

    public static final Logger ERROR = LoggerFactory.getLogger("CoreExtensions | ERROR");

    public static void create(String key) {
        LOGGER.info(key);
    }

    public static void error(String key) {
        ERROR.info(key);
    }

    public static void custom(String key, String id) {
        Logger CUSTOM = LoggerFactory.getLogger("CoreExtensions | "+id);
        CUSTOM.info(key);
    }

    public static void createItem(String key) {
        Item.info(key);
    }

    public static void createLoot(String key) {
        Loot.info(key);
    }

    public static void createRecipe(String key) {
        Recipe.info(key);
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
