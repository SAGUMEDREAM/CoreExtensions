package com.KafuuChino0722.coreextensions.core.api;

import net.minecraft.sound.BlockSoundGroup;

public class MethodSound {
    public static BlockSoundGroup getSound(String soundStr) {
        switch (soundStr.toUpperCase()) {
            case "STONE" -> {
                return BlockSoundGroup.STONE;
            }
            case "WOOD" -> {
                return BlockSoundGroup.WOOD;
            }
            case "GRAVEL" -> {
                return BlockSoundGroup.GRAVEL;
            }
            case "METAL" -> {
                return BlockSoundGroup.METAL;
            }
            case "GRASS" -> {
                return BlockSoundGroup.GRASS;
            }
            case "WOOL" -> {
                return BlockSoundGroup.WOOL;
            }
            case "SAND" -> {
                return BlockSoundGroup.SAND;
            }
            case "CROP" -> {
                return BlockSoundGroup.CROP;
            }
            case "GLASS" -> {
                return BlockSoundGroup.GLASS;
            }
            case "MOSS_CARPET" -> {
                return BlockSoundGroup.MOSS_CARPET;
            }
            default -> {
                return BlockSoundGroup.STONE;
            }
        }
    }
}
