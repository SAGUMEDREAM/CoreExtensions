package com.KafuuChino0722.coreextensions.core.oldapi_v1;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.ItemManager;
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

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class RegItemGroup {
    public static final String FILE = Reference.File;
        public static final ItemGroup ITEM_GROUP = Registry.register(Registries.ITEM_GROUP,
                new Identifier(Reference.MODID, "coreextensions"),
                FabricItemGroup.builder()
                        .displayName(Text.translatable("core.modid"))
                        .icon(() -> new ItemStack(Items.GRASS_BLOCK))
                        .entries((displayContext, entries) -> {
                            Yaml yaml = new Yaml();
                            try {
                                Map<String, List<String>> groupsData = yaml.load(new FileReader(FILE +"item_group.yml"));

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
                        }).build());
        public static void load() {

        }
}