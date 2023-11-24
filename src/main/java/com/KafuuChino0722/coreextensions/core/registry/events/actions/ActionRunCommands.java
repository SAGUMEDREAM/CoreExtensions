package com.KafuuChino0722.coreextensions.core.registry.events.actions;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading.server;

public class ActionRunCommands implements ActionInterface {
    public static void run(World world, LivingEntity user, Hand hand, Map<String, Object> Key) {
        try {
            List<String> run_commandsKey = (List<String>) Key.getOrDefault("run_commands", null);

            if (run_commandsKey != null) {
                for (String run_command : run_commandsKey) {
                    if (server != null) {
                        if (server.getProfiler() != null) {
                            if (user != null && user.getCommandSource() != null && user.getCommandSource().getServer() != null) {
                                String playerName = user.getEntityName();
                                String C = "execute as " + playerName + " at @s run " + run_command;
                                server.getCommandManager().executeWithPrefix(server.getCommandSource(), C);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {

        }
    }
}
