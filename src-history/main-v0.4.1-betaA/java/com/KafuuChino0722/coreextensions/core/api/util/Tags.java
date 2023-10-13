package com.KafuuChino0722.coreextensions.core.api.util;

import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.util.Map;

public class Tags {
    public static class Item {
        public static void generateTags(String namespace, String id, Map<String, Object> properties) {
            if (properties.containsKey("tag") || properties.containsKey("tag2") || properties.containsKey("tag3") || properties.containsKey("tag4")) {
                RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id + "_tags"));
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs.clearResources();
                    if (properties.containsKey("tag2")) {
                        String tags = (String) properties.get("tag");
                        packs.addTag(IdentifiedTagBuilder.createItem(new Identifier(tags)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag2")) {
                        String tagOther = (String) properties.get("tag2");
                        packs.addTag(IdentifiedTagBuilder.createItem(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag3")) {
                        String tagOther = (String) properties.get("tag3");
                        packs.addTag(IdentifiedTagBuilder.createItem(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag4")) {
                        String tagOther = (String) properties.get("tag4");
                        packs.addTag(IdentifiedTagBuilder.createItem(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    b.add(packs);
                });
            }
        }
    }
    public static class Block {
        public static void generateTags(String namespace, String id, Map<String, Object> properties) {
            if (properties.containsKey("tag") || properties.containsKey("tag2") || properties.containsKey("tag3") || properties.containsKey("tag4")) {
                RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id + "_tags"));
                RRPCallback.BEFORE_VANILLA.register(b -> {
                    packs.clearResources();
                    if (properties.containsKey("tag2")) {
                        String tags = (String) properties.get("tag");
                        packs.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tags)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag2")) {
                        String tagOther = (String) properties.get("tag2");
                        packs.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag3")) {
                        String tagOther = (String) properties.get("tag3");
                        packs.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag4")) {
                        String tagOther = (String) properties.get("tag4");
                        packs.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    b.add(packs);
                });
            }
        }
    }
    public static void generateBed(String namespace, String id) {RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id + "_flowertags"));RRPCallback.BEFORE_VANILLA.register(b -> {
        packs.clearResources();
        packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.BEDS).add(new Identifier(namespace, id)));
        packs.addTag(IdentifiedTagBuilder.createItem(ItemTags.BEDS).add(new Identifier(namespace, id)));
        b.add(packs);
    });
    }
    public static void generateHighFlower(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id + "_flowertags"));
        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.TALL_FLOWERS).add(new Identifier(namespace, id)));
            packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.FLOWER_POTS).add(new Identifier(namespace, "potted_" + id)));
            packs.addTag(IdentifiedTagBuilder.createItem(ItemTags.TALL_FLOWERS).add(new Identifier(namespace, id)));
            b.add(packs);
        });
    }
    public static void generateSmallFlower(String namespace, String id) {RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id + "_flowertags"));RRPCallback.BEFORE_VANILLA.register(b -> {
        packs.clearResources();
        packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SMALL_FLOWERS).add(new Identifier(namespace, id)));
        packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.FLOWER_POTS).add(new Identifier(namespace, "potted_" + id)));
        packs.addTag(IdentifiedTagBuilder.createItem(ItemTags.SMALL_FLOWERS).add(new Identifier(namespace, id)));
        b.add(packs);
    });
    }
    public static void generateFruitBush(String namespace, String id) {
        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier(namespace, id + "_flowertags"));
        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SMALL_FLOWERS).add(new Identifier(namespace, id)));
            packs.addTag(IdentifiedTagBuilder.createBlock(BlockTags.BEE_GROWABLES).add(new Identifier(namespace, id)));
            packs.addTag(IdentifiedTagBuilder.createItem(ItemTags.FOX_FOOD).add(new Identifier(namespace, id)));
            packs.addTag(IdentifiedTagBuilder.createItem(ItemTags.SMALL_FLOWERS).add(new Identifier(namespace, id)));
            b.add(packs);
        });
    }
    }