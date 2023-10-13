package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;

public class CommandReadYaml {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("coremods")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(
                        CommandReadYaml::run
                ));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        // 获取玩家
        ServerPlayerEntity player = context.getSource().getPlayer();

        // 获取游戏目录
        File gameDir = FabricLoader.getInstance().getGameDirectory();
        File coreExtensionsDir = new File(gameDir, "core/");

        // 读取 core/ 下的所有 YAML 文件
        Yaml yaml = new Yaml();
        StringBuilder result = new StringBuilder();
        readYamlFiles(coreExtensionsDir, yaml, result);

        // 将反馈发送给玩家
        player.sendMessage(Text.of(result.toString()), false);

        return 1;
    }

    private static void readYamlFiles(File directory, Yaml yaml, StringBuilder result) {
        if (directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(".yml")) {
                        result.append("Reading file: ").append(file.getName()).append("\n");

                        try (FileInputStream inputStream = new FileInputStream(file)) {
                            Map<String, Object> data = yaml.load(inputStream);
                            result.append(data.toString()).append("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (file.isDirectory()) {
                        // 如果是文件夹，则递归调用以处理其中的文件
                        readYamlFiles(file, yaml, result);
                    }
                }
            }
        }
    }
}
