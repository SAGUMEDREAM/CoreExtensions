package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.ModelBuilder;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.CoreManager.TAG_PIGLIN_LOVED;
import static com.KafuuChino0722.coreextensions.CoreManager.provider;
import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;

public class ItemRecordRegistry {

    private static SoundEvent registerSoundEvent(String namespace, String id) {
        Identifier MusicId = new Identifier(namespace, id);
        return Registry.register(Registries.SOUND_EVENT, MusicId, SoundEvent.of(MusicId));
    }

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("itemRecord.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("itemRecord.yml");
        load(itemsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("items")) {
            Map<String, Object> items = itemsData.get("items");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> itemData = (Map<String, Object>) entry.getValue();
                    String CdName = (String) itemData.get("name");
                    String CdDesc = (String) itemData.get("desc");
                    String namespace = (String) itemData.getOrDefault("namespace", "minecraft");
                    String id = (String) itemData.get("id");

                    Map<String, Object> properties = itemData.containsKey("properties") ? (Map<String, Object>) itemData.get("properties") : null;

                    String rarity = properties.containsKey("rarity") ? (String) properties.get("rarity") : "COMMON";
                    List<String> tooltipMsg = properties.containsKey("tooltipMsg") ? (List<String>) properties.get("tooltipMsg") : null;

                    boolean generate = properties.containsKey("generate") ? (boolean) properties.get("generate") : true;

                    boolean hasGlint = (boolean) properties.getOrDefault("hasGlint",false);

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

                    if (!Registries.ITEM.containsId(new Identifier(namespace, id))) {
                        ItemGroupsContents.load(namespace, "music_disc_" + id, properties);
                    }

                    Identifier MusicId = new Identifier(namespace, "music_" + soundId);
                    SoundEvent SOUND;
                    try {
                        SOUND = registerSoundEvent(namespace, "music_" + soundId);
                    } catch (Exception e) {
                        SOUND = Registries.SOUND_EVENT.get(MusicId);
                    }

                    Item item = new MusicDiscItem(comparatorOutput, SOUND,
                            new FabricItemSettings().maxCount(1).rarity(rarityType), timeSeconds) {
                        @Override
                        public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                            if (tooltipMsg != null) {
                                for (String Line : tooltipMsg) {
                                    tooltip.add(Text.of("§5§o" + Line));
                                }
                            }
                        }

                        @Override
                        public boolean hasGlint(ItemStack stack) {
                            if(hasGlint) return true;
                            return false;
                        }
                    };
                    try {
                        Registry.register(Registries.ITEM, new Identifier(namespace, "music_disc_" + id), item);
                    } catch (Exception e) {
                        if(Registries.ITEM.containsId(new Identifier(namespace,"music_disc_"+id))&&AllowExistingReloading) {
                            Item oldItem = Registries.ITEM.get(new Identifier(namespace,id));
                            setRegistry.set(namespace, "music_disc_" + id, item);
                            WorldRegistryDataReloading.run(item,oldItem);
                        }
                    }

                    if (properties.containsKey("piglinLoved")) {
                        boolean piglinLoved;
                        if (properties.get("piglinLoved") != null) {
                            piglinLoved = (boolean) properties.get("piglinLoved");
                            if (Registries.ITEM.containsId(new Identifier(namespace, id))) {
                                if (piglinLoved) {
                                    TAG_PIGLIN_LOVED.add(new Identifier(namespace, id));
                                }
                            }
                        }
                    }

                    if (generate) {
                        ModelBuilder.Item.getModel(namespace,"music_disc_" + id, ModelBuilder.Item.Types.GENERATE);
                    }

                    provider.add("item." + namespace + "." + "music_disc_" + id, CdName)
                            .add("item." + namespace + "." + "music_disc_" + id + ".desc", CdDesc);

                    Tags.generateMusicCD(namespace, "music_disc_" + id);
                }
            }
        }
    }
}