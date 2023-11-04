package com.KafuuChino0722.coreextensions.extensions;

import io.github.theepicblock.polymc.PolyMc;
import io.github.theepicblock.polymc.api.PolyMap;
import io.github.theepicblock.polymc.api.misc.PolyMapProvider;
import io.github.theepicblock.polymc.impl.ConfigManager;
import io.github.theepicblock.polymc.impl.PolyMcCommands;
import io.github.theepicblock.polymc.impl.misc.BlockIdRemapper;
import io.github.theepicblock.polymc.impl.misc.QslRegistryCompat;
import io.github.theepicblock.polymc.impl.misc.logging.Log4JWrapper;
import io.github.theepicblock.polymc.impl.misc.logging.SimpleLogger;
import io.github.theepicblock.polymc.impl.poly.wizard.PacketCountManager;
import io.github.theepicblock.polymc.impl.poly.wizard.RegularWizardUpdater;
import io.github.theepicblock.polymc.impl.poly.wizard.ThreadedWizardUpdater;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.loader.api.FabricLoader;
import org.apache.logging.log4j.LogManager;

public class PolyReload {
    public static final String MODID = "polymc";
    public static final SimpleLogger LOGGER = new Log4JWrapper(LogManager.getLogger("PolyMc"));
    private static PolyMap map;

    public static void Reload() {
        new PolyMc().onInitialize();
    }
}
