package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.OperatorList;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static net.minecraft.server.PlayerManager.OPERATORS_FILE;

public class iZipCommand {
    public static final String FILE = Reference.File;

    public static void load(String[] paths) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) -> name.toLowerCase().endsWith(".zip"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, server) -> {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/command.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("commands")) {
                                            Map<String, Object> blocks = Data.get("commands");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> CData = (Map<String, Object>) DataEntry.getValue();
                                                    String command = (String) CData.getOrDefault("command","help");
                                                    List<String> execute;
                                                    String[] sendArray;
                                                    if(CData.containsKey("execute")) {
                                                        execute = (List<String>) CData.get("execute");
                                                        sendArray = execute.toArray(new String[execute.size()]);
                                                    } else {
                                                        sendArray = new String[]{"help"};
                                                    }
                                                    int level = (int) CData.getOrDefault("level",2);
                                                    boolean passLevel = (boolean) CData.getOrDefault("passPerm",false);
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

                                                    Info.custom("Command " + command + " registered!","Commands");

                                                }
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }})
                        ;} catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
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