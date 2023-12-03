package com.KafuuChino0722.coreextensions.loader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Example implements PluginBootstrap {

    public Logger LOGGER = LoggerFactory.getLogger("CoreExtensions/ExampleClassPlugin");

    public void bootstrap() {
        LOGGER.info("Hello World");
    }

}
