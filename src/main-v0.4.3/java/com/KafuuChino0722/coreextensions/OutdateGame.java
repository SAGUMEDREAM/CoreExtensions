package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.outdate.BlockManager;
import com.KafuuChino0722.coreextensions.outdate.ItemManager;

public class OutdateGame {
    public static void load() {
        BlockManager.load();
        ItemManager.load();
    }
}