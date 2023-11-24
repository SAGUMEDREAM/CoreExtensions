package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import com.KafuuChino0722.coreextensions.core.registry.Registries;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

import java.util.Map;

import static net.minecraft.sound.SoundCategory.PLAYERS;

public class ActionPlaySound implements ActionInterface {
    public static void run(World world, PlayerEntity user, Hand hand, Map<String, Object> Key) {
        Map<String, Object> play_soundsKey = (Map<String, Object>) Key.getOrDefault("play_sound", null);
        if (play_soundsKey != null) {
            for (Map.Entry<String, Object> entry : play_soundsKey.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> sData = (Map<String, Object>) entry.getValue();
                    String play_sound = (String) sData.getOrDefault("name", "block.amethyst_block.break");
                    double pitch = (double) sData.getOrDefault("pitch", 1.0);
                    double volume = (double) sData.getOrDefault("volume", 1.0);
                    SoundEvent registryEntry = Registries.SOUND_EVENT.get(new Identifier(play_sound));
                    world.playSound(user, user.getX(), user.getY(), user.getZ(), registryEntry, PLAYERS, (float) volume, (float) pitch);

                }
            }
        }
    }
}
