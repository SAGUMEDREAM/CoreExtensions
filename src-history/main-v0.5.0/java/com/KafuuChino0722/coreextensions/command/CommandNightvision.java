package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class CommandNightvision {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("nightvison")
                .requires(source -> source.hasPermissionLevel(CommandPermissions.VISITOR))
                .executes(CommandNightvision::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        ServerPlayerEntity player = context.getSource().getPlayer();
        StatusEffectInstance isOn = player.getStatusEffect(StatusEffects.NIGHT_VISION);

        if (isOn != null) {
            // 玩家拥有夜视效果
            player.removeStatusEffect(StatusEffects.NIGHT_VISION);
            player.sendMessage(Text.translatable("commands.nightvision.done.off"), false);
        } else {
            // 玩家没有夜视效果
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, Integer.MAX_VALUE, 0, true, false));
            player.sendMessage(Text.translatable("commands.nightvision.done.on"), false);
        }

        return 1;
    }


}