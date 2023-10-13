package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.util.Reference;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
                                                    String send = (String) CData.getOrDefault("send","help");
                                                    int level = (int) CData.getOrDefault("level",2);


                                                    dispatcher.register(CommandManager.literal(command)
                                                            .requires(source -> source.hasPermissionLevel(level))
                                                            .executes(context -> executeCommand(context, send))
                                                            .build()
                                                            .createBuilder()
                                                    );

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
    private static int executeCommand(CommandContext<ServerCommandSource> context, String command) throws CommandSyntaxException {
        ServerPlayerEntity player =context.getSource().getPlayer();
        MinecraftServer server = player.getServer();
        server.getCommandManager().executeWithPrefix(player.getCommandSource(), command);
        return 1;
    }
}