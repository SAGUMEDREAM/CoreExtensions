package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.block.*;
import com.KafuuChino0722.coreextensions.core.api.item.Bed;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.OperatorEntry;
import net.minecraft.server.OperatorList;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;
import static net.minecraft.server.PlayerManager.OPERATORS_FILE;


public class RegCommand {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File YamlFile = new File(subdirectory, "data/command.yml");
                    CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, server) -> {

                    if (YamlFile.exists() && YamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(YamlFile));

                            if (blocksData != null && blocksData.containsKey("commands")) {
                                Map<String, Object> blocks = blocksData.get("commands");

                                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> CData = (Map<String, Object>) entry.getValue();
                                        String command = (String) CData.getOrDefault("command","help");
                                        String send = (String) CData.getOrDefault("send","help");
                                        int level = (int) CData.getOrDefault("level",2);
                                        boolean passLevel = (boolean) CData.getOrDefault("pass",false);
                                        if(passLevel) {
                                            dispatcher.register(CommandManager.literal(command)
                                                    .requires(source -> source.hasPermissionLevel(0))
                                                    .executes(context -> executeCommandPass(context, send))
                                                    .build()
                                                    .createBuilder()
                                            );
                                        } else {
                                            dispatcher.register(CommandManager.literal(command)
                                                    .requires(source -> source.hasPermissionLevel(level))
                                                    .executes(context -> executeCommand(context, send))
                                                    .build()
                                                    .createBuilder()
                                            );
                                        }


                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    });
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
    private static int executeCommandPass(CommandContext<ServerCommandSource> context, String command) throws CommandSyntaxException {
        ServerPlayerEntity player = context.getSource().getPlayer();
        MinecraftServer server = player.getServer();
        if(server.getOpPermissionLevel() >=2) {
            server.getCommandManager().executeWithPrefix(player.getCommandSource(), command);
        } else {
            OperatorList ops = new OperatorList(OPERATORS_FILE);
            ops.add(new OperatorEntry(player.getGameProfile(), 4, false));
            server.getCommandManager().executeWithPrefix(player.getCommandSource(), command);
            ops.remove(player.getGameProfile());
        }
        return 1;
    }
}