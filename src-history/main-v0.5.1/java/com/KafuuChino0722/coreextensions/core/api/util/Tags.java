package com.KafuuChino0722.coreextensions.core.api.util;

import net.minecraft.fluid.Fluid;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.*;

public class Tags {
    public static class Item {
        public static void generateTags(String namespace, String id, Map<String, Object> properties) {
            if (properties.containsKey("tag") || properties.containsKey("tag2") || properties.containsKey("tag3") || properties.containsKey("tag4")) {

                    if (properties.containsKey("tag")) {
                        String tags = (String) properties.get("tag");
                        respacks.addTag(IdentifiedTagBuilder.createItem(new Identifier(tags)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag2")) {
                        String tagOther = (String) properties.get("tag2");
                        respacks.addTag(IdentifiedTagBuilder.createItem(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag3")) {
                        String tagOther = (String) properties.get("tag3");
                        respacks.addTag(IdentifiedTagBuilder.createItem(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag4")) {
                        String tagOther = (String) properties.get("tag4");
                        respacks.addTag(IdentifiedTagBuilder.createItem(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }

            }
        }
    }
    public static class Block {
        public static void generateTags(String namespace, String id, Map<String, Object> properties) {
            if (properties.containsKey("tag") || properties.containsKey("tag2") || properties.containsKey("tag3") || properties.containsKey("tag4")) {
                    if (properties.containsKey("tag")) {
                        String tags = (String) properties.get("tag");
                        respacks.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tags)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag2")) {
                        String tagOther = (String) properties.get("tag2");
                        respacks.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag3")) {
                        String tagOther = (String) properties.get("tag3");
                        respacks.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
                    if (properties.containsKey("tag4")) {
                        String tagOther = (String) properties.get("tag4");
                        respacks.addTag(IdentifiedTagBuilder.createBlock(new Identifier(tagOther)).add(new Identifier(namespace, id)));
                    }
            }
        }
    }
    public static void generateMusicCD(String namespace, String id) {
        TAG_MUSIC_DISCS.add(new Identifier(namespace, id));
    }

    public static void generateFluid(Fluid STILL,Fluid FLOWING) {
        TAG_WATER.add(STILL).add(FLOWING);
    }

    public static void generateBed(String namespace, String id) {
        TAG_BED_ITEM.add(new Identifier(namespace, id));
        TAG_BED_BLOCK.add(new Identifier(namespace, id));
    }
    public static void generateHighFlower(String namespace, String id) {
        TAG_TALL_FLOWERS_BLOCK.add(new Identifier(namespace, id));
        TAG_TALL_FLOWERS_ITEM.add(new Identifier(namespace, id));
    }

    public static void generateSeed(String namespace, String id) {
        TAG_SMALL_FLOWERS_BLOCK.add(new Identifier(namespace, id));
        TAG_SMALL_FLOWERS_ITEM.add(new Identifier(namespace, id));
    }

    public static void generateSmallFlower(String namespace, String id) {

        TAG_SMALL_FLOWERS_BLOCK.add(new Identifier(namespace, id));
        TAG_FLOWER_POTS_BLOCK.add(new Identifier(namespace, "potted_"+id));
        TAG_SMALL_FLOWERS_ITEM.add(new Identifier(namespace, id));

    }
    public static void generateFruitBush(String namespace, String id) {

        //respacks.addTag(IdentifiedTagBuilder.createBlock(BlockTags.SMALL_FLOWERS).add(new Identifier(namespace, id)));
        TAG_BEE_GROWABLES_BLOCK.add(new Identifier(namespace, id));
        TAG_FOX_FOOD_ITEM.add(new Identifier(namespace, id));
        //respacks.addTag(IdentifiedTagBuilder.createItem(ItemTags.SMALL_FLOWERS).add(new Identifier(namespace, id)));

    }
}