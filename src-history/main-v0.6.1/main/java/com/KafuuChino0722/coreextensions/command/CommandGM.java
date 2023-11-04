package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.builder.RequiredArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.command.argument.GameModeArgumentType;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;
import net.minecraft.world.GameRules;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class CommandGM {
    public static final int REQUIRED_PERMISSION_LEVEL = 2;

    public CommandGM() {
    }

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder)((LiteralArgumentBuilder)CommandManager.literal("gm").requires((source) -> {
            return source.hasPermissionLevel(2);
        })).then(((RequiredArgumentBuilder) CommandManager.argument("gm", GameModeArgumentType.gameMode()).executes((commandContext) -> {
            return execute(commandContext, Collections.singleton(((ServerCommandSource)commandContext.getSource()).getPlayerOrThrow()), GameModeArgumentType.getGameMode(commandContext, "gm"));
        })).then(CommandManager.argument("target", EntityArgumentType.players()).executes((commandContext) -> {
            return execute(commandContext, EntityArgumentType.getPlayers(commandContext, "target"), GameModeArgumentType.getGameMode(commandContext, "gamemode"));
        }))));
    }

    private static void sendFeedback(ServerCommandSource source, ServerPlayerEntity player, GameMode gameMode) {
        Text text = Text.translatable("gameMode." + gameMode.getName());
        if (source.getEntity() == player) {
            source.sendFeedback(() -> {
                return Text.translatable("commands.gamemode.success.self", new Object[]{text});
            }, true);
        } else {
            if (source.getWorld().getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK)) {
                player.sendMessage(Text.translatable("gameMode.changed", new Object[]{text}));
            }

            source.sendFeedback(() -> {
                return Text.translatable("commands.gamemode.success.other", new Object[]{player.getDisplayName(), text});
            }, true);
        }

    }

    private static int execute(CommandContext<ServerCommandSource> context, Collection<ServerPlayerEntity> targets, GameMode gameMode) {
        int i = 0;
        Iterator var4 = targets.iterator();

        while(var4.hasNext()) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)var4.next();
            if (serverPlayerEntity.changeGameMode(gameMode)) {
                sendFeedback((ServerCommandSource)context.getSource(), serverPlayerEntity, gameMode);
                ++i;
            }
        }

        return i;
    }
}
