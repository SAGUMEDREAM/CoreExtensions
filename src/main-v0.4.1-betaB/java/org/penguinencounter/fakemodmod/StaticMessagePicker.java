package org.penguinencounter.fakemodmod;

import java.util.List;

public class StaticMessagePicker implements MessagePicker {
    @Override
    public List<String> pick() {
        return List.of(
                "We're no strangers to love",
                "You know the rules, and so do I",
                "A full commitment's what I'm thinking of",
                "You wouldn't get this from any other guy",
                "I just wanna tell you how I'm feeling",
                "Gotta make you understand",
                "",
                "Never gonna give you up",
                "Never gonna let you down",
                "Never gonna run around",
                "And desert you",
                "Never gonna make you cry",
                "Never gonna say goodbye",
                "Never gonna tell a lie",
                "And hurt you"
        );
    }
}
