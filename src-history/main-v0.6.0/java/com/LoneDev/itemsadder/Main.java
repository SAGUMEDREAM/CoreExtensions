package com.LoneDev.itemsadder;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.core.brrp.Export;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.LoneDev.itemsadder.api.*;
import com.LoneDev.itemsadder.command.CommandItemsAdder;
import com.LoneDev.itemsadder.util.IaRegistry;
import net.darkhax.openloaderfcore.config.ConfigSchema;
import net.fabricmc.fabric.api.mininglevel.v1.FabricMineableTags;
import net.fabricmc.fabric.api.mininglevel.v1.MiningLevelManager;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.MinecraftVersion;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.resource.ResourceType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.io.*;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class Main {


    public static final String FILE = Reference.File;
    public static final String Mods = Reference.Mods;

    public static final String ItemsAdderDir = "itemsadder/";
    public static final String ItemsAdderMods = "mods/itemsadder/";

    public static final RuntimeResourcePack IaPacks = RuntimeResourcePack.create(new Identifier("mc", "itemsadder"));
    public static final LanguageProvider.Impl<HashMap<String, String>> IaLanguageProvider = LanguageProvider.create();

    public static Path configDirIA = FabricLoader.getInstance().getGameDir().resolve("itemsadder");
    public static ConfigSchema configIA = ConfigSchema.load(configDirIA);

    public static Path configDirIAMods = FabricLoader.getInstance().getGameDir().resolve("itemsadder");
    public static ConfigSchema configIAMods = ConfigSchema.load(configDirIAMods);

    public static boolean ENABLED = Config.getConfigBoolean("FEATURE_ITEMSADDER");
    public static boolean DATAGEN_EXPORT = Config.getConfigBoolean("ENABLED_DATAGEN_EXPORT");

    public static final Logger ItemsAdder = LoggerFactory.getLogger("CoreExtensions/ItemsAdder");

    public static final IdentifiedTagBuilder<Item> IA_TAG_ITEM_ARROW = IdentifiedTagBuilder.createItem(ItemTags.ARROWS);
    public static final IdentifiedTagBuilder<Item> IA_TAG_ITEM_MUSIC_CD = IdentifiedTagBuilder.createItem(ItemTags.MUSIC_DISCS);

    public static final IdentifiedTagBuilder<Block> IA_TAG_AXE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.AXE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> IA_TAG_PICKAXE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.PICKAXE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> IA_TAG_SHOVEL_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.SHOVEL_MINEABLE);
    public static final IdentifiedTagBuilder<Block> IA_TAG_HOE_MINEABLE = IdentifiedTagBuilder.createBlock(BlockTags.HOE_MINEABLE);
    public static final IdentifiedTagBuilder<Block> IA_TAG_SWORD_MINEABLE = IdentifiedTagBuilder.createBlock(FabricMineableTags.SWORD_MINEABLE);
    public static final IdentifiedTagBuilder<Block> IA_TAG_SHEARS_MINEABLE = IdentifiedTagBuilder.createBlock(FabricMineableTags.SHEARS_MINEABLE);

    public static final IdentifiedTagBuilder<Item> IA_TAG_TOOL = IdentifiedTagBuilder.createItem(ItemTags.TOOLS);
    public static final IdentifiedTagBuilder<Item> IA_TAG_TOOL_SWORD = IdentifiedTagBuilder.createItem(ItemTags.SWORDS);
    public static final IdentifiedTagBuilder<Item> IA_TAG_TOOL_AXE = IdentifiedTagBuilder.createItem(ItemTags.AXES);
    public static final IdentifiedTagBuilder<Item> IA_TAG_TOOL_PICKAXE = IdentifiedTagBuilder.createItem(ItemTags.PICKAXES);
    public static final IdentifiedTagBuilder<Item> IA_TAG_TOOL_SHOVEL = IdentifiedTagBuilder.createItem(ItemTags.SHOVELS);
    public static final IdentifiedTagBuilder<Item> IA_TAG_TOOL_HOE = IdentifiedTagBuilder.createItem(ItemTags.HOES);

    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_STONE_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_STONE_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_IRON_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_IRON_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_DIAMOND_TOOL = IdentifiedTagBuilder.createBlock(BlockTags.NEEDS_DIAMOND_TOOL);
    public static final IdentifiedTagBuilder<Block> TAG_NEEDS_NETHERITE_TOOL = IdentifiedTagBuilder.createBlock(MiningLevelManager.getBlockTag(4));

    public static void bootstrap() {
        ENABLED = Config.getConfigBoolean("FEATURE_ITEMSADDER");
        DATAGEN_EXPORT = Config.getConfigBoolean("ENABLED_DATAGEN_EXPORT");
        ItemsAdder.info("Loading ItemsAdder");
        if(ENABLED) {
            load();
            new Main().resources();
            IaRegistry.registerCommand(CommandItemsAdder::register);
            new Main().openloader();
        }
    }

    public static void load() {
        if(ENABLED) {
            new Main().setup(ItemsAdderDir);
            new Main().setup(ItemsAdderMods);
            new Main().setupZip(ItemsAdderDir);
            new Main().setupZip(ItemsAdderMods);
            new Main().resources();
        }

    }

    public void openloader() {
        configDirIA = FabricLoader.getInstance().getGameDir().resolve("itemsadder");
        this.configIA = ConfigSchema.load(configDirIA);

        configDirIAMods = FabricLoader.getInstance().getGameDir().resolve("itemsadder");
        this.configIAMods = ConfigSchema.load(configDirIAMods);
    }

    public void resources() {
        IaPacks.clearResources();

        IaPacks.setDisplayName(Text.literal("ItemsAdder"));
        IaPacks.setDescription(Text.translatable("brrp.pack.defaultName","[mc:itemsadder] ItemsAdder+Packs"));
        IaPacks.setPackVersion(MinecraftVersion.create().getResourceVersion(ResourceType.SERVER_DATA));

        IaPacks.addLang(new Identifier("itemsadder", "zh_cn"), IaLanguageProvider);
        IaPacks.addLang(new Identifier("itemsadder", "en_us"), IaLanguageProvider);

        RRPCallback.BEFORE_VANILLA.register(b -> {
            b.remove(IaPacks);
            b.add(IaPacks);
        });
        RRPCallback.BEFORE_USER.register(b -> {
            b.remove(IaPacks);
            b.add(IaPacks);
        });

        if(DATAGEN_EXPORT) {
            IaPacks.dump(Export.getIaPath());
        }
    }

    public void setup(String readDir) {
        File directory = new File(readDir);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        setup(file.getAbsolutePath());
                    } else if (file.getName().endsWith(".yml")) {
                        try {
                            Yaml yaml = new Yaml();
                            Map<String, Object> data = yaml.load(new FileReader(file));

                            String namespace = "minecraft";

                            if (data != null) {
                                Map<String, Object> info = (Map<String, Object>) data.get("info");
                                if (info != null && info.containsKey("namespace")) {
                                    namespace = (String) info.get("namespace");
                                }

                                new CustomItems().load(namespace, data);
                                new CustomLoots().load(namespace, data);
                                new CustomRecipes().load(namespace, data);
                                new CustomLanguages().load(namespace, data);
                                new CustomTreesPopulators().load(namespace, data);
                                new CustomCategory().load(namespace,data);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    public void setupZip(String directoryPath) {
        File directory = new File(directoryPath);

        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();

            if (files != null) {
                for (File file : files) {
                    if (file.getName().endsWith(".zip") || file.getName().endsWith(".jar")) {
                        try (ZipFile zip = new ZipFile(file)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (entry.getName().endsWith(".yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry);
                                         InputStreamReader reader = new InputStreamReader(inputStream)) {
                                        Yaml yaml = new Yaml();
                                        Map<String, Object> data = yaml.load(reader);

                                        if (data != null) {
                                            String namespace = "minecraft";
                                            Map<String, Object> info = (Map<String, Object>) data.get("info");

                                            if (info != null && info.containsKey("namespace")) {
                                                namespace = (String) info.get("namespace");
                                            }

                                            new CustomItems().load(namespace, data);
                                            new CustomLoots().load(namespace, data);
                                            new CustomRecipes().load(namespace, data);
                                            new CustomLanguages().load(namespace, data);
                                            new CustomTreesPopulators().load(namespace, data);
                                            new CustomCategory().load(namespace,data);

                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
