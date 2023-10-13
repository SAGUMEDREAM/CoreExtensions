package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.item.*;
import com.KafuuChino0722.coreextensions.core.api.item.Items;
import com.KafuuChino0722.coreextensions.core.api.item.FoodStewItem;
import com.KafuuChino0722.coreextensions.core.api.itemgroups.itemGroupsContents;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class RegItemGroupsContents {
    public static final String FILE = Reference.File;

    public static void load(@Nullable String namespace, String id, Map<String, Object> Data, Map<String, Object> properties) {
        String groupid = properties.containsKey("groups") ? (String) properties.get("groups") : "INGREDIENTS";
        if (properties.containsKey("groups")) {
            itemGroupsContents.generate(namespace, id, groupid, properties);
        }
    }
    public static void load(@Nullable String namespace, String id, Map<String, Object> properties) {
        String groupid = properties.containsKey("groups") ? (String) properties.get("groups") : "INGREDIENTS";
        if (properties.containsKey("groups")) {
            itemGroupsContents.generate(namespace, id, groupid, properties);
        }
    }
    public static void itemDataload(@Nullable String namespace, String id, Map<String, Object> itemData) {
        String groupid = itemData.containsKey("groups") ? (String) itemData.get("groups") : "INGREDIENTS";
        if (itemData.containsKey("groups")) {
            itemGroupsContents.generate(namespace, id, groupid, itemData);
        }
    }
}