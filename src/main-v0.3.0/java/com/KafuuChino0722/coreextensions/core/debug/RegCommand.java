package com.KafuuChino0722.coreextensions.core.debug;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import org.yaml.snakeyaml.Yaml;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class RegCommand {
    public static final String FILE = Reference.File;

    public static void load() {
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, server) -> {
            Yaml yaml = new Yaml();

            try {
                Map<String, Map<String, Object>> commandsData = yaml.load(new FileReader(FILE + "command.yml"));

                if (commandsData != null && commandsData.containsKey("commands")) {
                    Map<String, Object> commands = commandsData.get("commands");

                    for (Map.Entry<String, Object> entry : commands.entrySet()) {
                        if (entry.getValue() instanceof Map) {
                            Map<String, Object> commandData = (Map<String, Object>) entry.getValue();
                            String regname = (String) commandData.get("regname");
                            String command = (String) commandData.get("command");
                            String id = (String) commandData.get("id");

                            dispatcher.register(CommandManager.literal(regname)
                                    .executes(context -> executeCommand(context, command))
                            );

                            Main.LOGGER.info("Command " + regname + " registered!");
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private static int executeCommand(CommandContext<ServerCommandSource> context, String command) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        context.getSource().sendMessage(Text.of(command));;
        return 1;
    }
}
