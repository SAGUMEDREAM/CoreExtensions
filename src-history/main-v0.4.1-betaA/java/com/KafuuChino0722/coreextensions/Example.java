package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.util.*;
import net.darkhax.openloaderfcore.config.ConfigSchema;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Example implements ModInitializer {

	public static final String MOD_ID = Reference.MODID;
	public static final String FILE = Reference.File;
	public static final String CONFIG = Reference.Config;
	public static final Logger LOGGER = LoggerFactory.getLogger("MODID");

	public static ConfigSchema config; //OpenLoader Fabric Fork CoreExtensions
	public static Path configDir; //OpenLoader Fabric Fork CoreExtensions

	@Override
	public void onInitialize() {
		Info.create("!");
	}

}
