package com.KafuuChino0722.coreextensions.util;

import com.KafuuChino0722.coreextensions.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Info {
    public static final Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");

    public static void create(String key) {
        LOGGER.info(key);
    }
}
