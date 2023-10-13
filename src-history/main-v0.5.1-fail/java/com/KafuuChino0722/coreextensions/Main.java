package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.util.*;
import net.darkhax.openloaderfcore.config.ConfigSchema;
import net.fabricmc.api.EnvType;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.sql.Ref;
import java.util.Map;

public class Main implements ModInitializer {

	public static final String MOD_ID = Reference.MODID;
	public static final String FILE = Reference.File;
	public static final String CONFIG = Reference.Config;
	public static final Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");

	public static ConfigSchema config;
	public static Path configDir;
	public static ConfigSchema config2;
	public static Path configDir2;
	public static ConfigSchema configIA;
	public static Path configDirIA;

	public static final RuntimeResourcePack vanillaPacks = RuntimeResourcePack.create(new Identifier("minecraft", "my_pack"));

	public static void setup(Yaml yaml) {
		Info.create("CoreExtensions Loaded!");
		Info.create("CoreExtensions Mixin Loaded!");

		CommandManager.load();
		CoreManager.load();

		int maximumSupportedProtocolVersion = 762; // 设置支持的最大协议版本
		int currentProtocolVersion = SharedConstants.getGameVersion().getProtocolVersion();
		if (currentProtocolVersion < maximumSupportedProtocolVersion) {
			Info.create("Minecraft Outdate!!");
		}

		Map<String, Map<String, Object>> config;
		try (FileInputStream inputStream = new FileInputStream(CONFIG +"coreconfig.yml")) {
			config = yaml.load(inputStream);
		} catch (IOException ee) {
			ee.printStackTrace();
			return;
		}

		boolean featureEnabled = true;
		try {
			Map<String, Map<String, Object>> configData = yaml.load(new FileReader("config/coreconfig.yml"));

			if (configData != null && configData.containsKey("settings")) {
				Map<String, Object> settings = configData.get("settings");

				if (settings.containsKey("FEATURE_ENABLE")) {
					Object EnableValue = settings.get("FEATURE_ENABLE");
					if (EnableValue instanceof Boolean) {
						featureEnabled = (boolean) EnableValue;
					}
				}
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		boolean moonEnabled = true;
		try {
			Map<String, Map<String, Object>> configData = yaml.load(new FileReader("config/coreconfig.yml"));

			if (configData != null && configData.containsKey("settings")) {
				Map<String, Object> settings = configData.get("settings");

				if (settings.containsKey("FEATURE_MOON")) {
					Object EnableValue = settings.get("FEATURE_MOON");
					if (EnableValue instanceof Boolean) {
						moonEnabled = (boolean) EnableValue;
					}
				}
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		}
		boolean aetherEnabled = true;
		try {
			Map<String, Map<String, Object>> configData = yaml.load(new FileReader("config/coreconfig.yml"));

			if (configData != null && configData.containsKey("settings")) {
				Map<String, Object> settings = configData.get("settings");

				if (settings.containsKey("FEATURE_AETHER")) {
					Object EnableValue = settings.get("FEATURE_AETHER");
					if (EnableValue instanceof Boolean) {
						aetherEnabled = (boolean) EnableValue;
					}
				}
			}
		} catch (IOException ee) {
			ee.printStackTrace();
		}

		if (featureEnabled) {
			MessageLoad.loadOn();
			ItemManager.load();
			BlockManager.load();
			VanillaManager.load();
			EntityManager.load();
			ItemGroup.load();
			CommandManager.load();
			PortalsManager.load(moonEnabled,aetherEnabled);
		} else {
			MessageLoad.loadOff();
		}
	}

	private static void generate() {
		vanillaPacks.addLang(new Identifier("brrp", "en_us"), LanguageProvider.create()
				.add("coreapi", "coreapi is ready"));
	}

	@Override
	public void onInitialize() {
		com.LoneDev.itemsadder.Main.load();

		if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
			Info.custom("Environment Type Client", "FabricLoader");
		} else if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER) {
			Info.custom("Environment Type Server", "FabricLoader");
		} else {
			Info.custom("Environment Type ERROR","FabricLoader");
		}

		if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
			Info.custom("Environment Type Development", "FabricLoader");
		}

		if (FabricLoader.getInstance().isModLoaded("brrp_v1")) {
		} else {
			Info.custom("brrp_v1 cannot be detected, mod functionality may be incomplete","FabricLoader");
		}

		if (FabricLoader.getInstance().isModLoaded("polymc")) {
			Info.custom("PolyMc detected, which will allow players without CoreExtensions installed to enter the server","FabricLoader");
		} else {

		}

		if (FabricLoader.getInstance().isModLoaded("optifabric")) {
			Info.custom("Optifine/OptiFabric detected, which may cause the game to behave abnormally or crash","FabricLoader");
		}

		Yaml yaml = new Yaml();

		File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

		String filePath = "config/coreconfig.yml"; // 将文件路径替换为你要检查的文件的实际路径

		File file = new File(filePath);

		if (file.exists()) {
			setup(yaml);
		} else {
			ConfigBuilder.load();
		}

		String a = "z";
		String bb = "x";
		String c = "c";
		String d = "v";
		String e = "b";
		String f = "n";
		String g = "m";
		String h = "l";
		String i = "k";
		String j = "j";
		String k = "h";
		String l = "g";
		String m = "f";
		String n = "d";
		String o = "s";
		String p = "a";
		String q = "q";
		String r = "w";
		String s = "e";
		String t = "r";
		String u = "t";
		String v = "y";
		String w = "u";
		String x = "i";
		String y = "o";
		String z = "p";
		String aa = "9";
		String ab = "6";
		String ac = "3";
		String ad = "2";
		String ae = "8";
		String af = "5";
		String ag = "0";
		String ah = "7";
		String ai = "1";
		String aj = "4";
		String qa = "Z";
		String qb = "Y";
		String qc = "X";
		String qd = "W";
		String qe = "V";
		String qf = "U";
		String qg = "T";
		String qh = "S";
		String qi = "R";
		String qj = "Q";
		String qk = "P";
		String ql = "O";
		String qm = "N";
		String qn = "M";
		String qo = "L";
		String qp = "K";
		String qq = "J";
		String qr = "I";
		String qs = "H";
		String qt = "G";
		String qu = "F";
		String qv = "E";
		String qw = "D";
		String qx = "C";
		String qy = "B";
		String qz = "A";
		String q0 = " ";

		LOGGER.info("--------");
		LOGGER.info("本MOD开源！禁止倒卖！!");
		LOGGER.info("作者(AUTHOR):稀神灵梦");
		LOGGER.info("Minecraft:"+ Reference.ACCEPTED_VERSIONS);
		LOGGER.info("Version:"+ Reference.VERSION);
		LOGGER.info("--------");
		LOGGER.info("User:"+ Reference.PLAYER_NAME);
		LOGGER.info("--------");
		LOGGER.info(qw + qv + qu + q0 + qq + qe + q0 + qc + qw + qv + qf + qu + qd + qq + q0 + qj + qq + qu + qq + qc + qq + qk + qc + qw + qv + qc + qt + qq + qe + q0 + qe + qc + qq + qc + qq + ql + qf + q0 + qq + ql + qe + qq + qa + qq + qc + q0 + qw + qv + qf + qu + qd + qq + q0 + qe + qf + qd + qc + q0 + qq + qu + qe + qw + qe + qf + qe + qd + qq + q0 + qd + qw + qf + qc + qq + qu + q0 + qq + qu + qe + qc + qq + q0 + qj + qq + qu + qq + qc + qq + qk + qc + qw + qv + qc + qt + qq + qe + q0 + qe + qc + qq + qc + qq + ql + qf + q0 + qq + ql + qe + qk + qc + qq + qk + qc + qq + qh + q0 + qq + qo + qc + qk + q0 + qq + qw + qv + qf + qu + qd + qq + q0 + qe + qc + qq + qc + qq + ql + qf + q0 + qq + ql + qe + qq + qc + qf + qc + qq + qj + qq + qu + qq + qc + qq + qe + q0 + qe + qc + qq + qc + qq + qd + qq + qc + qk + qc + q0 + qq + qq + qk + qq + qe + qc + q0 + qw + qv + qf + qu + qd + qq + q0 + qw + qv + qc + qq + qe + q0 + qc + qq + qk + qc + qw + qv + qf + qu + qd + qq + q0 + qc + qq + qc + qq + ql + qf + q0 + qq + ql + qe + qq + qc + qe + qc + q0 + qw + qv + qf + qu + qd + qq + q0 + qe + qc + qq + qc + qq + ql + qf + q0 + qq + ql + qe + qq + qc + qf + qc + q0 + qq + qj + qq + qu + qq + qc + qq + qk + qc + qw + qv + qc + qt + qq + qe + q0 + qe + qc + qq + qc + qq + ql + qf + q0 + qq + ql + qe + qk + qc + qq + qk + qc + qq + qh + q0 + qq + qo + qc + qk + q0 + qq + qc + qq + ql + qc + qf + qq + q0 + qq + qd + qc + qq + qe + q0 + qf + qc + q0 + qh + qc + qq + qf + qq + q0 + qq + qj + qq + qu + qq + qc + qq + q0 + qh + qc + qq + q0 + qw + qv + qc + qd + qc + q0 + qw + qv + qf + qu + qd + qq + q0 + qh + qc + qq + qe + qc + q0 + qj + qq + qu + qq + qc + qq + q0 + qj + qq + qu + qq + qc + qq + qk + qc + qh + q0 + qj + qq + qu + qq + qc + qq + q0 + qj + qq + qu + qq + qc + qq + qk + qc + qh + q0 + qq + qc + qq + ql + qc + qf + qq + q0 + qq + qd + qc + qq + qe + q0 + qf + qc + q0 + qh + qc + qq + qf + qq + q0 + qq + qj + qq + qu + qq + qc + qq + q0);

		configDir = FabricLoader.getInstance().getGameDir().resolve("core"); //OpenLoader Fabric Fork CoreExtensions
		this.config = ConfigSchema.load(configDir); //OpenLoader Fabric Fork CoreExtensions

		configDir2 = FabricLoader.getInstance().getGameDir().resolve("mods"); //OpenLoader Fabric Fork CoreExtensions
		this.config2 = ConfigSchema.load(configDir2); //OpenLoader Fabric Fork CoreExtensions

		configDirIA = FabricLoader.getInstance().getGameDir().resolve("itemsadder");
		this.configIA = ConfigSchema.load(configDirIA);

		RRPCallback.BEFORE_VANILLA.register(b -> {
			vanillaPacks.clearResources();
			generate();
			b.add(vanillaPacks);
		}); //BRRP
	}
}
