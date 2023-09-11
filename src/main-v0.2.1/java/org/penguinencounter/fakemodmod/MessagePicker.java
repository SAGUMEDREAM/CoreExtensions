package org.penguinencounter.fakemodmod;

import java.util.List;

public interface MessagePicker {
    List<String> pick();

    default void ready() {
    }
}
