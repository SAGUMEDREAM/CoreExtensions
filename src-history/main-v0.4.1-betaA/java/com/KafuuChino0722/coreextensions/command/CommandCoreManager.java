package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
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

public class CommandCoreManager {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("core")
                .requires(source -> source.hasPermissionLevel(2))
                .executes(CommandCoreManager::run));
    }
    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        // 获取玩家
        ServerPlayerEntity player = context.getSource().getPlayer();

        // 获取游戏目录
        File gameDir = FabricLoader.getInstance().getGameDirectory();
        File coreExtensionsDir = new File(gameDir, "config/coreextensions");

        // 读取 YAML 文件内容
        //配置
        Yaml yaml = new Yaml();
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "config.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        //物品
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "item.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        //可用物品
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "clickitem.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "sword.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "pickaxe.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "axe.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "shovel.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "armor.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "hoe.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        //方块
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "block.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
        //方向性方块
        try (FileInputStream inputStream = new FileInputStream(new File(coreExtensionsDir, "directblock.yml"))) {
            Map<String, Object> data = yaml.load(inputStream);
            // 将数据发送给玩家
            player.sendMessage(Text.translatable(data.toString()), false);
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }



        return 1;
    }


}

