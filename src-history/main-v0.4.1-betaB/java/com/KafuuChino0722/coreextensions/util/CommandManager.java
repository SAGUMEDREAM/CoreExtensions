package com.KafuuChino0722.coreextensions.util;


import com.KafuuChino0722.coreextensions.command.*;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;


public class CommandManager {
    public static void load() {
        CommandRegistrationCallback.EVENT.register(CommandExit::register);
        CommandRegistrationCallback.EVENT.register(CommandDay::register);
        CommandRegistrationCallback.EVENT.register(CommandNight::register);
        CommandRegistrationCallback.EVENT.register(CommandGMS::register);
        CommandRegistrationCallback.EVENT.register(CommandGMC::register);
        CommandRegistrationCallback.EVENT.register(CommandGMA::register);
        CommandRegistrationCallback.EVENT.register(CommandGMSP::register);
        CommandRegistrationCallback.EVENT.register(CommandFly::register);
        CommandRegistrationCallback.EVENT.register(CommandGod::register);
        CommandRegistrationCallback.EVENT.register(CommandHeal::register);
        CommandRegistrationCallback.EVENT.register(CommandFeed::register);
        CommandRegistrationCallback.EVENT.register(CommandSun::register);
        CommandRegistrationCallback.EVENT.register(CommandRain::register);
        CommandRegistrationCallback.EVENT.register(CommandThundering::register);
        CommandRegistrationCallback.EVENT.register(CommandRepair::register);
        CommandRegistrationCallback.EVENT.register(CommandEnderchest::register);
        CommandRegistrationCallback.EVENT.register(CommandFakePlayer::register);
        CommandRegistrationCallback.EVENT.register(CommandNightvision::register);
        CommandRegistrationCallback.EVENT.register(CommandReadYaml::register);
        CommandRegistrationCallback.EVENT.register(CommandDataGen::register);


    }
}