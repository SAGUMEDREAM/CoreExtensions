package com.KafuuChino0722.coreextensions.core.item;

import com.KafuuChino0722.coreextensions.core.RegItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class RegMusicCD {
    public static final String FILE = Reference.File;

    private static SoundEvent registerSoundEvent(String namespace, String id) {
        Identifier MusicId = new Identifier(namespace, id);
        return Registry.register(Registries.SOUND_EVENT, MusicId, SoundEvent.of(MusicId));
    }

    public static void load() {
            Yaml yaml = new Yaml();

            File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

                if (subdirectories != null) {
                    for (File subdirectory : subdirectories) {
                        File BlockYamlFile = new File(subdirectory, "data/MusicCD.yml");

                        if (BlockYamlFile.exists() && BlockYamlFile.isFile()) {
                            try {
                                Map<String, Map<String, Object>> Data = yaml.load(new FileReader(BlockYamlFile));

                                if (Data != null && Data.containsKey("items")) {
                                    Map<String, Object> blocks = Data.get("items");

                                    for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                        if (entry.getValue() instanceof Map) {
                                            Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
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

                                            if(!Registries.ITEM.containsId(new Identifier(namespace,id))) {
                                                RegItemGroupsContents.load(namespace,"music_disc_"+id,properties);
                                            }

                                            Identifier MusicId = new Identifier(namespace, id);
                                            SoundEvent SOUND;
                                            try {
                                                SOUND = registerSoundEvent(namespace,"music_"+soundId);
                                            } catch (Exception e) {
                                                SOUND = Registries.SOUND_EVENT.get(MusicId);
                                            }

                                            Item item = new MusicDiscItem(comparatorOutput, SOUND,
                                                    new FabricItemSettings().maxCount(1).rarity(rarityType),timeSeconds);
                                            try {
                                                Registry.register(Registries.ITEM, new Identifier(namespace,"music_disc_"+id), item);
                                            } catch (Exception e) {
                                                setRegistry.set(namespace,"music_disc_"+id,item);
                                                Registry.register(Registries.SOUND_EVENT, MusicId, SoundEvent.of(MusicId));
                                            }

                                            if (generate) {
                                                Models.generate(namespace, "music_disc_"+id, "ITEM");
                                            }

                                            provider.add("item." +namespace +"."+"music_disc_"+id, CdName)
                                                    .add("item." +namespace +"."+"music_disc_"+id+".desc", CdDesc);

                                            Tags.generateMusicCD(namespace,"music_disc_"+id);
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
