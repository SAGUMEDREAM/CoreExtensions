package com.KafuuChino0722.coreextensions.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageLoad {
    public static final Logger LOGGER = LoggerFactory.getLogger("coreextensions");
    public static void loadOn() {
        LOGGER.info("Feature Enabled!");
    }
    public static void loadOff() {
        LOGGER.info("Feature Disabled!");
    }
}
