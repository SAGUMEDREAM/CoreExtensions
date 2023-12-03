package com.KafuuChino0722.coreextensions.core.registry;

import com.google.gson.*;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.command.argument.ArgumentHelper;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorageRegistry {

    public static List<String> ARMOR = new ArrayList<>();
    public static List<String> BLOCKS = new ArrayList<>();
    public static List<String> ENCHANTMENTS = new ArrayList<>();
    public static List<String> FLUIDS = new ArrayList<>();
    public static List<String> GAME_RULES = new ArrayList<>();
    public static List<String> ITEM_GROUPS = new ArrayList<>();
    public static List<String> ITEMS = new ArrayList<>();
    public static List<String> PARTICLE_TYPES = new ArrayList<>();
    public static List<String> RECIPES = new ArrayList<>();
    public static List<String> STATUS_EFFECTS = new ArrayList<>();
    public static List<String> WORLD_PORTAL = new ArrayList<>();

    public static void dumpAllCommand(CommandContext<ServerCommandSource> context) {
        CommandManager commands = context.getSource().getServer().getCommandManager();
        CommandDispatcher<ServerCommandSource> disp = commands.getDispatcher();

        new File("dump").mkdir();
        try {
            JsonObject obj = ArgumentHelper.toJson(disp, disp.getRoot());
            String objStr = obj.toString();

            JsonElement jsonElement = JsonParser.parseString(objStr);

            String yamlString = convertJsonToYaml(jsonElement);
            outputYamlToFile(yamlString, "./core/dump/command_registry.yml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dumpAll() {
        try {
            dumpRegistries("armor_registry", ARMOR);
            dumpRegistries("block_registry", BLOCKS);
            dumpRegistries("enchantment_registry", ENCHANTMENTS);
            dumpRegistries("fluid_registry", FLUIDS);
            dumpRegistries("game_rules_registry", GAME_RULES);
            dumpRegistries("item_groups_registry", ITEM_GROUPS);
            dumpRegistries("item_registry", ITEMS);
            dumpRegistries("particle_types_registry", PARTICLE_TYPES);
            dumpRegistries("recipe_registry", RECIPES);
            dumpRegistries("status_effects_registry", STATUS_EFFECTS);
            dumpRegistries("world_portal_registry", WORLD_PORTAL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void add(List<String> type, Identifier identifier) {
        String namespace = identifier.getNamespace();
        String path = identifier.getPath();
        String content = namespace+":"+path;
        if(!type.contains(content)) type.add(content);
    }

    public static void dumpRegistries(String registryKey, List<String> type) {
        String path = "./core/dump/";
        Map<String, Object> data = new HashMap<>();
        List<String> cateList = new ArrayList<>();
        for (String key : type) {
            cateList.add(key);
        }
        data.put(registryKey, cateList);

        // 设置DumperOptions，使用块样式并设置缩进为2
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setIndent(2);

        Yaml yaml = new Yaml(dumperOptions);
        try (FileWriter writer = new FileWriter(path + "/" + registryKey + ".yml")) {
            yaml.dump(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertJsonToYaml(JsonElement jsonElement) {
        DumperOptions dumperOptions = new DumperOptions();
        dumperOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        dumperOptions.setIndent(2);

        Yaml yaml = new Yaml(dumperOptions);

        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(jsonElement, Map.class);
        return yaml.dump(map);
    }

    private static void outputYamlToFile(String yamlString, String fileName) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(yamlString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
