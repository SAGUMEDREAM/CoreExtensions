package com.KafuuChino0722.coreextensions.core.zip;

import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class iZipItemGroups {
    public static final String FILE = Reference.File;

    public static void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                    name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

            if (zipFiles != null) {
                for (File zipFile : zipFiles) {
                    try (ZipFile zip = new ZipFile(zipFile)) {
                        Enumeration<? extends ZipEntry> entries = zip.entries();

                        while (entries.hasMoreElements()) {
                            ZipEntry entry = entries.nextElement();

                            if (!entry.isDirectory() && entry.getName().equals("data/itemGroups.yml")) {
                                try (InputStream inputStream = zip.getInputStream(entry)) {
                                    Map<String, Map<String, Object>> itemsData = yaml.load(new InputStreamReader(inputStream));

                                    if (itemsData != null && itemsData.containsKey("groups")) {
                                        Map<String, Object> items = itemsData.get("groups");

                                        for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                            if (itemEntry.getValue() instanceof Map) {
                                                Map<String, Object> itemData = (Map<String, Object>) itemEntry.getValue();

                                                String name = (String) itemData.get("name");
                                                String lang_us = (String) itemData.get("name");
                                                String namespace = (String) itemData.getOrDefault("namespace","minecraft");
                                                String id = (String) itemData.get("id");
                                                String icon = (String) itemData.get("icon");

                                                Registry.register(Registries.ITEM_GROUP,
                                                        new Identifier(namespace, id),
                                                        FabricItemGroup.builder()
                                                                .icon(() -> new ItemStack(Registries.ITEM.get(new Identifier(icon))))
                                                                .displayName(Text.translatable("itemGroup."+namespace+"."+id))
                                                                .build()
                                                );

                                                respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "en_us"),
                                                        LanguageProvider.create().add("itemGroup." +namespace +"."+id, lang_us));
                                                respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "zh_cn"),
                                                        LanguageProvider.create().add("itemGroup." +namespace +"."+id, name));


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
