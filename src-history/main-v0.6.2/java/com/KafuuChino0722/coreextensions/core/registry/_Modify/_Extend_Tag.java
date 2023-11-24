package com.KafuuChino0722.coreextensions.core.registry._Modify;

import com.KafuuChino0722.coreextensions.Config;
import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.brrp.MethodExport;
import com.KafuuChino0722.coreextensions.core.registry.Registries;
import com.tags_binder.api.LoadTagsCallback;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

public class _Extend_Tag {

    public static final RuntimeResourcePack tagPacks = RuntimeResourcePack.create(new Identifier("mc", "custom_tags"));

    public static boolean DataGenEnabled = Config.getConfigBoolean("ENABLED_DATAGEN_EXPORT");

    public static void register() {
        Map<String, Map<String, Object>> tagData = IOFileManager.read("tag@Extends.yml");
        load(tagData);
        Map<String, Map<String, Object>> tagDataZ = IOFileManager.readZip("tag@Extends.yml");
        load(tagDataZ);
    }

    public static void load(Map<String, Map<String, Object>> tagsData) {
        tagPacks.clearResources();
        tagPacks.setAllowsDuplicateResource(true);
        String mcmeta = "{\n" +
                "  \"pack\": {\n" +
                "    \"pack_format\": 15,\n" +
                "    \"description\": \"[mc:custom_tags] CoreExtensions Tags\"\n" +
                "  }\n" +
                "}";
        tagPacks.addRootResource("pack.mcmeta", mcmeta.getBytes());

        if (tagsData != null && tagsData.containsKey("tags")) {
            Map<String, Object> tags = tagsData.get("tags");

            for (Map.Entry<String, Object> entry : tags.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> tagData = (Map<String, Object>) entry.getValue();
                    String Identifier = (String) tagData.get("id");

                    TagKey<Item> TagKey1 = ofItem(Identifier);
                    TagKey<Block> TagKey2 = ofBlock(Identifier);
                    List<String> registryArray = (List<String>) tagData.get("list");
                    for (String registry : registryArray) {
                        Identifier IdentifierMC = new Identifier(registry.toLowerCase());
                        if (Registries.ITEM.containsId(IdentifierMC)) {
                            LoadTagsCallback.ITEM.register(handler -> {
                                handler.remove(TagKey1, Registries.ITEM.get(IdentifierMC));
                                handler.register(TagKey1, Registries.ITEM.get(IdentifierMC));
                            });
                        }
                        if (Registries.BLOCK.containsId(IdentifierMC)) {
                            LoadTagsCallback.BLOCK.register(handler -> {
                                handler.remove(TagKey2, Registries.BLOCK.get(IdentifierMC));
                                handler.register(TagKey2, Registries.BLOCK.get(IdentifierMC));
                            });
                        }
                    }
                }
            }
        }

        RRPCallback.BEFORE_VANILLA.register(b -> {
            b.remove(tagPacks);
            b.add(tagPacks);
        });

        RRPCallback.BEFORE_USER.register(b -> {
            b.remove(tagPacks);
            b.add(tagPacks);
        });

        if(DataGenEnabled) {
            tagPacks.dump(MethodExport.getPath());
            try {
                tagPacks.dump(new ZipOutputStream(new FileOutputStream(MethodExport.getPath()+"/ResourcePack+DataPacks+CustomTags.zip")));
            } catch (IOException e) {

            }
        }
    }

    private static TagKey<Item> ofItem(String id) {
        return TagKey.of(RegistryKeys.ITEM, new Identifier(id));
    }

    private static TagKey<Block> ofBlock(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(id));
    }
}
