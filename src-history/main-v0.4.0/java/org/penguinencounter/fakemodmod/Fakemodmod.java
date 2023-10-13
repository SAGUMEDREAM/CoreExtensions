package org.penguinencounter.fakemodmod;

import net.fabricmc.api.ModInitializer;

public class Fakemodmod implements ModInitializer {
    public static MessagePicker MESSAGE_PICKER = new ConfigMessagePicker();

    @Override
    public void onInitialize() {

    }
}
