package com.KafuuChino0722.coreextensions.util;


import com.KafuuChino0722.coreextensions.command.*;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;


public class CommandManager {

    public static final String[] NAMESPACE = {"coreextensions","cex"};

    public static void register(CommandRegistrationCallback Method) {
        CommandRegistrationCallback.EVENT.register(Method);
    }
    
    public static void load() {
        register(CommandExit::register);
        register(CommandDay::register);
        register(CommandNight::register);
        register(CommandGMS::register);
        register(CommandGMC::register);
        register(CommandGMA::register);
        register(CommandGMSP::register);
        register(CommandFly::register);
        register(CommandGod::register);
        register(CommandHeal::register);
        register(CommandFeed::register);
        register(CommandHunger::register);
        register(CommandSun::register);
        register(CommandRain::register);
        register(CommandThundering::register);
        register(CommandRepair::register);
        register(CommandEnderchest::register);
        register(CommandFakePlayer::register);
        register(CommandNightvision::register);
        register(CommandHat::register);
        register(CommandWait::register);
        register(CommandWait_60::register);
        register(CommandWait_120::register);
        register(CommandWait_600::register);
        register(CommandDataGen::register);
        register(CommandReloadNoReport::register);
        register(CommandRegistry::register);
        register(CommandCoreExtensions::register);
    }
}