package com.KafuuChino0722.coreextensions;

import com.KafuuChino0722.coreextensions.enchant.Enchantments;
import com.KafuuChino0722.coreextensions.gametest.GameTest;
import com.KafuuChino0722.coreextensions.network.VersionChecker;
import com.KafuuChino0722.coreextensions.proxy.ClientProxy;
import com.KafuuChino0722.coreextensions.proxy.CommonProxy;
import com.KafuuChino0722.coreextensions.util.*;
import net.darkhax.openloaderfcore.config.ConfigSchema;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;

import java.io.File;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;


public class Main implements ModInitializer {

	public static final String MOD_ID = Reference.MODID;
	public static final String FILE = Reference.File;
	public static final String CONFIG = Reference.Config;
	public static final Logger LOGGER = LoggerFactory.getLogger("CoreExtensions");

	public static ConfigSchema config;
	public static Path configDir;
	public static ConfigSchema config2;
	public static Path configDir2;

	public static final RuntimeResourcePack vanillaPacks = RuntimeResourcePack.create(new Identifier("minecraft", "my_pack"));

	/*public static final RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>> TREE_CHERRY = ConfiguredFeaturesExtends.register("tutorial:tree_rich", Feature.TREE,
			new TreeFeatureConfig.Builder(
					BlockStateProvider.of(CHERRY_LOG), // Trunk block provider
					new StraightTrunkPlacer(8, 3, 0), // places a straight trunk
					BlockStateProvider.of(CHERRY_LEAVES), // Foliage block provider
					new BlobFoliagePlacer(ConstantIntProvider.create(6), ConstantIntProvider.create(0), 3), // places leaves as a blob (radius, offset from trunk, height)
					new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
			).build());*/

	public static void Inject() {

	}

	public static void setup() {
		Info.create("CoreExtensions Loading!");

		int maximumSupportedProtocolVersion = 762; // 设置支持的最大协议版本
		int currentProtocolVersion = SharedConstants.getGameVersion().getProtocolVersion();
		if (currentProtocolVersion < maximumSupportedProtocolVersion) {
			Info.create("Minecraft Outdate!!");
		}

		boolean CORE_API_Enabled = Config.getConfigBoolean("CORE_API");
		boolean featureEnabled = Config.getConfigBoolean("FEATURE_ENABLE");
		boolean moonEnabled = Config.getConfigBoolean("FEATURE_MOON");
		boolean aetherEnabled = Config.getConfigBoolean("FEATURE_AETHER");


		if (featureEnabled) {
			MessageLoad.loadOn();
			ItemManager.load();
			Enchantments.load();
			BlockManager.load();
			VanillaManager.load();
			EntityManager.load();
			ItemGroup.load();
			CommandManager.load();
			PortalsManager.load(moonEnabled,aetherEnabled);
		} else {
			MessageLoad.loadOff();
		}

		CoreManager.bootstrap();
		CoreManager.IsInstalled = true;
		//Feature_Gametest.test();

		com.LoneDev.itemsadder.Main.bootstrap();
		new CommonProxy().onInitializeCommon(CORE_API_Enabled);
		if(Reference.EnvType==EnvType.CLIENT) {
			new ClientProxy().onInitializeClient();
		}
		if(Config.getConfigBoolean("FEATURE_GAMETEST")) {
			new GameTest().bootstrap();
		}
		Inject();
	}

	private static void generate() {
		vanillaPacks.addLang(new Identifier("brrp", "en_us"), LanguageProvider.create()
				.add("coreapi", "coreapi is ready"));
	}

	@Override
	public void onInitialize() {
		Config.updateConfig();

		String filePath = "config/coreconfig.yml";

		File file = new File(filePath);

		if (file.exists()) {
			setup();
		} else {
			ConfigBuilder.Build();
			setup();
		}

		CompletableFuture<Void> future = CompletableFuture.runAsync(VersionChecker::check);


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

		LOGGER.info("--------");
		LOGGER.info("本MOD开源！禁止倒卖！!");
		LOGGER.info("作者 (AUTHOR) : "+ Reference.AUTHOR);
		LOGGER.info("Minecraft: "+ Reference.ACCEPTED_VERSIONS);
		LOGGER.info("Version: "+ Reference.VERSION);
		LOGGER.info("--------");
		LOGGER.info("User: "+ Reference.getUserName());
		LOGGER.info("UID: " + Reference.getUID());
		LOGGER.info("--------");

		configDir = FabricLoader.getInstance().getGameDir().resolve("core");
		this.config = ConfigSchema.load(configDir);

		configDir2 = FabricLoader.getInstance().getGameDir().resolve("mods");
		this.config2 = ConfigSchema.load(configDir2);


		RRPCallback.BEFORE_VANILLA.register(b -> {
			vanillaPacks.clearResources();
			generate();
			b.add(vanillaPacks);
		});
	}
}
