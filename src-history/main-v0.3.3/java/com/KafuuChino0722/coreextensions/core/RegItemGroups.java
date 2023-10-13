package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RegItemGroups {
    public static final String FILE = Reference.File; // 设置要递归搜索的文件夹路径

    public static ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Reference.MODID, "coreextensions"),
            FabricItemGroup.builder()
                    .displayName(Text.translatable("core.modid"))
                    .icon(() -> new ItemStack(Items.GRASS_BLOCK))
                    .entries((displayContext, entries) -> {
                        loadYamlFilesRecursively(new File(FILE), entries); // 递归加载 YAML 文件
                    }).build());

    private static void loadYamlFilesRecursively(File directory, ItemGroup.Entries entries) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    loadYamlFilesRecursively(file, entries);
                } else if (file.getName().equals("itemGroups.yml")) {
                    loadYamlFile(file, entries);
                }
            }
        }
    }

    private static void loadYamlFile(File yamlFile, ItemGroup.Entries entries) {
        Yaml yaml = new Yaml();
        try {
            Map<String, List<String>> groupsData = yaml.load(new FileReader(yamlFile));

            if (groupsData != null && groupsData.containsKey("groups")) {
                List<String> itemIdentifiers = groupsData.get("groups");
                for (String itemId : itemIdentifiers) {
                    Identifier itemIdentifier = new Identifier(itemId);
                    if (Registries.ITEM.containsId(itemIdentifier)) {
                        entries.add(() -> new ItemStack(Registries.ITEM.get(itemIdentifier)).getItem());

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {

    }
}
