package com.KafuuChino0722.coreextensions.keyboard;

import com.KafuuChino0722.coreextensions.CoreManager;
import com.KafuuChino0722.coreextensions.command.CommandDataGen;
import com.google.common.collect.Lists;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.SaveProperties;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RegistryKeyBinding {
    public static KeyBinding keyBindingReloadRegistries;

    public static void bootstrap() {
        keyBindingReloadRegistries = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "minecraft.registry.reload",
                InputUtil.UNKNOWN_KEY.getCode(),
                "category.coreextensions"
        ));
        ClientTickEvents.START_CLIENT_TICK.register(client -> {
            while (keyBindingReloadRegistries.wasPressed()) {
                try {
                    client.player.sendMessage(Text.translatable("commands.registry.done"), false);
                    CoreManager.load(false);
                    try {
                        MinecraftClient.getInstance().player.networkHandler.sendChatCommand("coreextensions:reloaddp");
                    } catch (Exception e) {
                        client.player.sendMessage(Text.literal("重新加载数据包失败").formatted(Formatting.DARK_RED), false);
                    }
                } catch (Exception e) {
                    client.player.sendMessage(Text.translatable("commands.registry.failed").formatted(Formatting.DARK_RED), false);
                }
            }
        });
    }
}
