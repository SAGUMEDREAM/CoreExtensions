package com.KafuuChino0722.coreextensions.command;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;
import java.awt.*;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import net.fabricmc.api.ModInitializer;

public class CommandExit {
    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("exit")
                .requires(source -> source.hasPermissionLevel(4))
                .executes(CommandExit::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        SwingUtilities.invokeLater(() -> {
            showPopup();
        });
        context.getSource();
        ServerPlayerEntity player =context.getSource().getPlayer();
        player.sendMessage(Text.translatable("commands.boom.done"), false);
        System.out.println("爆");

        try {
            TimeUnit.SECONDS.sleep(1); // 延迟2秒
            // 注册关闭钩子
            Runtime.getRuntime().addShutdownHook(shutdownHook);
            // 终止 Java 进程
            Runtime.getRuntime().halt(1);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }
    private static final Thread shutdownHook = new Thread(() -> {
        // 在这里执行您的清理操作
        System.out.println("爆");
    });

    private static void showPopup() {
        JFrame popupFrame = new JFrame("Minecraft");
        popupFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        Font chineseFont = new Font("微软雅黑", Font.PLAIN, 26);
        JLabel messageLabel = new JLabel("爆");
        //messageLabel.setFont(chineseFont);

        JPanel popupPanel = new JPanel();
        popupPanel.add(messageLabel);

        // 设置弹窗的大小
        popupPanel.setPreferredSize(new Dimension(400, 100));

        popupFrame.add(popupPanel);
        popupFrame.pack();
        popupFrame.setLocationRelativeTo(null); // Center the popup
        popupFrame.setVisible(true);
    }

    //////////////////
}