package com.KafuuChino0722.coreextensions.util;

import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.decoration.painting.PaintingVariant;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.poi.PointOfInterestType;

public class setRegistry {
    public static Object set(String namespace, String id, Potion potion) {
        int rawId = Registries.POTION.getRawId(Registries.POTION.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.POTION, rawId, Id, potion);
    }
    public static StatusEffect set(String namespace, String id, StatusEffect statusEffect) {
        int rawId = Registries.STATUS_EFFECT.getRawId(Registries.STATUS_EFFECT.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.STATUS_EFFECT, rawId, Id, statusEffect);
    }
    public static Block set(String namespace, String id, Block block) {
        int rawId = Registries.BLOCK.getRawId(Registries.BLOCK.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.BLOCK, rawId, Id, block);
    }
    public static Item set(String namespace, String id, Item item) {
        int rawId = Registries.ITEM.getRawId(Registries.ITEM.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.ITEM, rawId, Id, item);
    }
    public static PaintingVariant set(String namespace, String id, PaintingVariant Painting) {
        int rawId = Registries.PAINTING_VARIANT.getRawId(Registries.PAINTING_VARIANT.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.PAINTING_VARIANT, rawId, Id, Painting);
    }
    public static PointOfInterestType set(String namespace, String id, PointOfInterestType Painting) {
        int rawId = Registries.POINT_OF_INTEREST_TYPE.getRawId(Registries.POINT_OF_INTEREST_TYPE.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.POINT_OF_INTEREST_TYPE, rawId, Id, Painting);
    }
    public static VillagerProfession set(String namespace, String id, VillagerProfession MASTER) {
        int rawId = Registries.VILLAGER_PROFESSION.getRawId(Registries.VILLAGER_PROFESSION.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.VILLAGER_PROFESSION, rawId, Id, MASTER);
    }
    public static ItemGroup set(String namespace, String id, ItemGroup ITEM_GROUP) {
        int rawId = Registries.ITEM_GROUP.getRawId(Registries.ITEM_GROUP.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.ITEM_GROUP, rawId, Id, ITEM_GROUP);
    }
    public static SoundEvent set(String namespace, String id, SoundEvent SOUND_EVENT) {
        int rawId = Registries.SOUND_EVENT.getRawId(Registries.SOUND_EVENT.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.SOUND_EVENT, rawId, Id, SOUND_EVENT);
    }
    public static Enchantment set(String namespace, String id, Enchantment ENCHANTMENT) {
        int rawId = Registries.ENCHANTMENT.getRawId(Registries.ENCHANTMENT.get(new Identifier(namespace,id)));
        String Id = namespace+":"+id;
        return Registry.register(Registries.ENCHANTMENT, rawId, Id, ENCHANTMENT);
    }

    public static Potion set(String id, Potion potion) {
        int rawId = Registries.POTION.getRawId(Registries.POTION.get(new Identifier(id)));
        return Registry.register(Registries.POTION, rawId, id, potion);
    }
    public static StatusEffect set(String id, StatusEffect statusEffect) {
        int rawId = Registries.STATUS_EFFECT.getRawId(Registries.STATUS_EFFECT.get(new Identifier(id)));
        return Registry.register(Registries.STATUS_EFFECT, rawId, id, statusEffect);
    }
    public static Block set(String id, Block block) {
        int rawId = Registries.BLOCK.getRawId(Registries.BLOCK.get(new Identifier(id)));
        return Registry.register(Registries.BLOCK, rawId, id, block);
    }
    public static Item set(String id, Item item) {
        int rawId = Registries.ITEM.getRawId(Registries.ITEM.get(new Identifier(id)));
        return Registry.register(Registries.ITEM, rawId, id, item);
    }
    public static PaintingVariant set(String id, PaintingVariant Painting) {
        int rawId = Registries.PAINTING_VARIANT.getRawId(Registries.PAINTING_VARIANT.get(new Identifier(id)));
        return Registry.register(Registries.PAINTING_VARIANT, rawId, id, Painting);
    }
    public static PointOfInterestType set(String id, PointOfInterestType POI) {
        int rawId = Registries.POINT_OF_INTEREST_TYPE.getRawId(Registries.POINT_OF_INTEREST_TYPE.get(new Identifier(id)));
        return Registry.register(Registries.POINT_OF_INTEREST_TYPE, rawId, id, POI);
    }
    public static VillagerProfession set(String id, VillagerProfession MASTER) {
        int rawId = Registries.VILLAGER_PROFESSION.getRawId(Registries.VILLAGER_PROFESSION.get(new Identifier(id)));
        return Registry.register(Registries.VILLAGER_PROFESSION, rawId, id, MASTER);
    }
    public static ItemGroup set(String id, ItemGroup ITEM_GROUP) {
        int rawId = Registries.ITEM_GROUP.getRawId(Registries.ITEM_GROUP.get(new Identifier(id)));
        return Registry.register(Registries.ITEM_GROUP, rawId, id, ITEM_GROUP);
    }
    public static SoundEvent set(String id, SoundEvent SoundEvent) {
        int rawId = Registries.SOUND_EVENT.getRawId(Registries.SOUND_EVENT.get(new Identifier(id)));
        return Registry.register(Registries.SOUND_EVENT, rawId, id, SoundEvent);
    }
    public static Enchantment set(String id, Enchantment block) {
        int rawId = Registries.ENCHANTMENT.getRawId(Registries.ENCHANTMENT.get(new Identifier(id)));
        return Registry.register(Registries.ENCHANTMENT, rawId, id, block);
    }
}

