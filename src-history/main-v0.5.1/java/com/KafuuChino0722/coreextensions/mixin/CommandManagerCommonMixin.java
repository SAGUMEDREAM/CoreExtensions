package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.command.CommandGM;
import com.KafuuChino0722.coreextensions.command.CommandTrigger;
import com.KafuuChino0722.coreextensions.command.oplevel.*;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.dedicated.command.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public abstract class CommandManagerCommonMixin {
    @Shadow @Final private CommandDispatcher<ServerCommandSource> dispatcher;

    @Inject(method = "<init>(Lnet/minecraft/server/command/CommandManager$RegistrationEnvironment;Lnet/minecraft/command/CommandRegistryAccess;)V", at = @At("RETURN"))
    private void injectCustomCode(CommandManager.RegistrationEnvironment environment, CommandRegistryAccess commandRegistryAccess, CallbackInfo info) {
        CommandDispatcher<ServerCommandSource> dispatcher = ((CommandManager) (Object) this).getDispatcher();

        // 注册各个命令
        CommandGM.register(dispatcher);
        NoneCommand.register(dispatcher);
        VisitorCommand.register(dispatcher);
        NormalCommand.register(dispatcher);
        ModeratorCommand.register(dispatcher);
        AdminCommand.register(dispatcher);
        CommandTrigger.register(dispatcher);
    }
}
