package com.KafuuChino0722.coreextensions.keyboard;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.CoreManager;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

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
                if(Config.getConfigBoolean("ALLOW_RELOADING_REGISTRY")) {
                    try {
                        if(client.player.hasPermissionLevel(2)) {
                            CoreManager.bootstrap();
                            client.player.sendMessage(Text.translatable("commands.registry.done"), false);
                            try {
                                if (MinecraftClient.getInstance().player != null) {
                                    MinecraftClient.getInstance().player.networkHandler.sendChatCommand("coreextensions:reloaddp");
                                }
                            } catch (Exception e) {
                                client.player.sendMessage(Text.literal("ERROR?!IN REGISTRY RELOADING").formatted(Formatting.DARK_RED), false);
                            }
                        }
                    } catch (Exception e) {
                        client.player.sendMessage(Text.translatable("commands.registry.failed").formatted(Formatting.RED), false);
                    }
                } else {
                    client.player.sendMessage(Text.translatable("commands.registry.off").formatted(Formatting.RED), false);
                }
            }
        });
    }
}
