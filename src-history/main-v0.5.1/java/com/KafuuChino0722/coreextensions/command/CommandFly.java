package com.KafuuChino0722.coreextensions.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.world.GameMode;

import static com.mojang.brigadier.builder.LiteralArgumentBuilder.literal;

public class CommandFly  {

    public static void register(CommandDispatcher<ServerCommandSource> serverCommandSourceCommandDispatcher,
                                CommandRegistryAccess commandRegistryAccess,
                                CommandManager.RegistrationEnvironment registrationEnvironment) {
        serverCommandSourceCommandDispatcher.register(CommandManager.literal("fly")
                .requires(source -> source.hasPermissionLevel(CommandPermissions.VISITOR))
                .executes(CommandFly::run));
    }

    public static int run(CommandContext<ServerCommandSource> context) throws CommandSyntaxException {
        ServerCommandSource source = context.getSource();
        boolean isFlying = source.getPlayer().getAbilities().allowFlying;
        source.getPlayer().getAbilities().allowFlying = !isFlying;
        source.getPlayer().sendAbilitiesUpdate();
        ServerPlayerEntity player = context.getSource().getPlayer();
        player.sendMessage(Text.translatable("commands.fly.done"), false);
        String message = isFlying ? "Disabled flight mode." : "Enabled flight mode.";
        return 1;
    }


}