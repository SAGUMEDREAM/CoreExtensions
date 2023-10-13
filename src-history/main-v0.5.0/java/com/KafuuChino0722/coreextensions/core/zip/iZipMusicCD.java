package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipMusicCD {
    public static final String FILE = Reference.File;

    private static SoundEvent registerSoundEvent(String namespace, String id) {
        Identifier MusicId = new Identifier(namespace, id);
        return Registry.register(Registries.SOUND_EVENT, MusicId, SoundEvent.of(MusicId));
    }


    public static void load(String[] paths) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/MusicCD.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("items")) {
                                            Map<String, Object> blocks = Data.get("items");

                                            for (Map.Entry<String, Object> DataEntry : blocks.entrySet()) {
                                                if (DataEntry.getValue() instanceof Map) {
                                                    Map<String, Object> itemData = (Map<String, Object>) DataEntry.getValue();

                                                    String CdName = (String) itemData.get("name");
                                                    String CdDesc = (String) itemData.get("desc");
                                                    String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                                                    String id = (String) itemData.get("id");

                                                    Map<String, Object> properties = itemData.containsKey("properties")? (Map<String, Object>) itemData.get("properties"):null;

                                                    String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";
                                                    String tooltipMsg = properties.containsKey("tooltipMsg") ? (String) properties.get("tooltipMsg") : null;

                                                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                                                    Map<String, Object> music = (Map<String, Object>) properties.get("music");

                                                    int comparatorOutput = music.containsKey("comparatorOutput") ? (int) music.get("comparatorOutput") : 7;
                                                    int timeSeconds = music.containsKey("timeSeconds") ? (int) music.get("timeSeconds") : 200;
                                                    String soundId = music.containsKey("soundId") ? (String) music.get("soundId") : null;

                                                    Rarity rarityType = null;
                                                    if (Objects.equals(rarity, "COMMON") || Objects.equals(rarity, "common")) {
                                                        rarityType = Rarity.COMMON;
                                                    } else if (Objects.equals(rarity, "UNCOMMON") || Objects.equals(rarity, "uncommon")) {
                                                        rarityType = Rarity.UNCOMMON;
                                                    } else if (Objects.equals(rarity, "RARE") || Objects.equals(rarity, "rare")) {
                                                        rarityType = Rarity.RARE;
                                                    } else if (Objects.equals(rarity, "EPIC") || Objects.equals(rarity, "epic")) {
                                                        rarityType = Rarity.EPIC;
                                                    } else {
                                                        rarityType = Rarity.COMMON;
                                                    }

                                                    SoundEvent SOUND = registerSoundEvent(namespace,"music_"+soundId);

                                                    Registry.register(Registries.ITEM, new Identifier(namespace,"music_disc_"+id),
                                                            new MusicDiscItem(comparatorOutput, SOUND,
                                                                    new FabricItemSettings().maxCount(1).rarity(rarityType),timeSeconds));

                                                    String type = "ITEM";
                                                    if (generate) {
                                                        Models.generate(namespace, id, type);
                                                    }

                                                    respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "en_us"),
                                                            LanguageProvider.create()
                                                                    .add("item." +namespace +"."+"music_disc_"+id, CdName)
                                                                    .add("item." +namespace +"."+"music_disc_"+id+".desc", CdDesc)
                                                    );
                                                    respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "zh_cn"),
                                                            LanguageProvider.create()
                                                                    .add("item." +namespace +"."+"music_disc_"+id, CdName)
                                                                    .add("item." +namespace +"."+"music_disc_"+id+".desc", CdDesc)
                                                    );

                                                    Tags.generateMusicCD(namespace,"music_disc_"+id);

                                                    RegItemGroupsContents.load(namespace,"music_disc_"+id,properties);
                                                }
                                            }
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