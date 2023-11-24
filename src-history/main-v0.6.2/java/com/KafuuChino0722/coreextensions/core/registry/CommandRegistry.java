package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.util.Info;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.List;
import java.util.Map;


public class CommandRegistry {

    public static void register() {
        Map<String, Map<String, Object>> commandData = IOFileManager.read("command.yml");
        load(commandData);
        Map<String, Map<String, Object>> commandDataZ = IOFileManager.readZip("command.yml");
        load(commandDataZ);
    }

    public static void load(Map<String, Map<String, Object>> blocksData) {
        if (blocksData != null && blocksData.containsKey("commands")) {
            Map<String, Object> blocks = blocksData.get("commands");

            for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> CData = (Map<String, Object>) entry.getValue();
                    String command = (String) CData.getOrDefault("command","help");
                    boolean passLevel = (boolean) CData.getOrDefault("passPerm",false);
                    List<String> execute;
                    String[] sendArray;
                    if(CData.containsKey("execute")) {
                        execute = (List<String>) CData.get("execute");
                        sendArray = execute.toArray(new String[execute.size()]);
                    } else {
                        sendArray = new String[]{"help"};
                    }
                    int level = (int) CData.getOrDefault("level",2);
                    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, server) -> {
                        if(passLevel) {
                            dispatcher.register(CommandManager.literal(command)
                                    .requires(source -> source.hasPermissionLevel(0))
                                    .executes(context -> executeCommandPass(context, sendArray))
                                    .build()
                                    .createBuilder()
                            );
                        } else {
                            dispatcher.register(CommandManager.literal(command)
                                    .requires(source -> source.hasPermissionLevel(level))
                                    .executes(context -> executeCommand(context, sendArray))
                                    .build()
                                    .createBuilder()
                            );
                        }
                    });
                    Info.custom("Command " + command + " registered!","Commands");

                }
            }
        }
    }

    private static int executeCommand(CommandContext<ServerCommandSource> context, String[] command) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        MinecraftServer server = player.getServer();
        for(String Line : command) {
            server.getCommandManager().executeWithPrefix(player.getCommandSource(), Line);
        }
        return 1;
    }
    /*
     * 申明:这不是后门,这是配置过自定义命令后于关于执行命令是否绕过所需权限的方法,并且会添加临时管理员权限,并在后续删除
     * Disclaimer: This is not a backdoor. This is a method to bypass the required permissions after configuring a custom command. Temporary administrator permissions will be added and deleted later.
     * */
    private static int executeCommandPass(CommandContext<ServerCommandSource> context, String[] command) throws CommandSyntaxException {
        PlayerManager playerManager = context.getSource().getServer().getPlayerManager();
        ServerPlayerEntity player = context.getSource().getPlayer();
        GameProfile gameProfile = player.getGameProfile();
        MinecraftServer server = player.getServer();
        if(player.hasPermissionLevel(1)) {
            for(String Line : command) {
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), Line);
            }
        } else {
            playerManager.addToOperators(gameProfile);
            for(String Line : command) {
                server.getCommandManager().executeWithPrefix(player.getCommandSource(), Line);
            }
            playerManager.removeFromOperators(gameProfile);
        }
        return 1;
    }
}