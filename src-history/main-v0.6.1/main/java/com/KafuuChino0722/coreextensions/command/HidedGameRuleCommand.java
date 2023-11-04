/*
 * Decompiled with CFR 0.2.1 (FabricMC 53fa44c9).
 */
package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

public class HidedGameRuleCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        final LiteralArgumentBuilder literalArgumentBuilder = (LiteralArgumentBuilder)CommandManager.literal("gamerule-noshow").requires(source -> source.hasPermissionLevel(2));
        GameRules.accept(new GameRules.Visitor(){

            @Override
            public <T extends GameRules.Rule<T>> void visit(GameRules.Key<T> key, GameRules.Type<T> type) {
                literalArgumentBuilder.then(((LiteralArgumentBuilder)CommandManager.literal(key.getName()).executes(context -> HidedGameRuleCommand.executeQuery((ServerCommandSource)context.getSource(), key))).then(type.argument("value").executes(context -> HidedGameRuleCommand.executeSet(context, key))));
            }
        });
        dispatcher.register(literalArgumentBuilder);
    }

    static <T extends GameRules.Rule<T>> int executeSet(CommandContext<ServerCommandSource> context, GameRules.Key<T> key) {
        ServerCommandSource serverCommandSource = context.getSource();
        Object rule = serverCommandSource.getServer().getGameRules().get(key);
        ((GameRules.Rule)rule).set(context, "value");
        //serverCommandSource.sendFeedback(() -> Text.translatable("commands.gamerule.set", key.getName(), rule.toString()), true);
        return ((GameRules.Rule)rule).getCommandResult();
    }

    static <T extends GameRules.Rule<T>> int executeQuery(ServerCommandSource source, GameRules.Key<T> key) {
        Object rule = source.getServer().getGameRules().get(key);
        //source.sendFeedback(() -> Text.translatable("commands.gamerule.query", key.getName(), rule.toString()), false);
        return ((GameRules.Rule)rule).getCommandResult();
    }
}

