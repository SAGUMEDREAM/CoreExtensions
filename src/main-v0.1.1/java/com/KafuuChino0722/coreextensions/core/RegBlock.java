package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.Main;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.sound.SoundManager;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.TextureKey;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.generator.BlockResourceGenerator;
import pers.solid.brrp.v1.model.ModelJsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import static com.KafuuChino0722.coreextensions.Main.MOD_ID;
import static com.KafuuChino0722.coreextensions.Main.pack;


public class RegBlock {
    public static final String FILE = Reference.File;

    private static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    private static Item registerBlockItem(String namespace, String id, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(block, new FabricItemSettings()));
    }

    private static void setupRenderLayer(Block block, Map<String, Object> blockData) {
        boolean shouldUseCutoutLayer = (boolean) blockData.getOrDefault("useCutoutLayer", false);

        if (shouldUseCutoutLayer) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }
    }

    private static void generate() {

    }

    public static void load() {
        Yaml yaml = new Yaml();
        ModelJsonBuilder model;

        try {
            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(FILE + "block.yml"));

            if (blocksData != null && blocksData.containsKey("blocks")) {
                Map<String, Object> blocks = blocksData.get("blocks");

                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                    if (entry.getValue() instanceof Map) {
                        Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                        String name = (String) blockData.get("name");
                        String namespace = (String) blockData.get("namespace");
                        String id = (String) blockData.get("id");
                        double hardness = (Double) blockData.get("hardness");
                        double resistance = (Double) blockData.get("resistance");
                        boolean dropsNothing = (boolean) blockData.get("dropsNothing");
                        String sound = (String) blockData.get("sound");

                        BlockSoundGroup customSound = BlockSoundGroup.STONE;
                        if (Objects.equals(sound, "STONE")) {
                            customSound = BlockSoundGroup.STONE;
                        }
                        if (Objects.equals(sound, "WOOD")) {
                            customSound = BlockSoundGroup.WOOD;
                        }
                        if (Objects.equals(sound, "GRAVEL")) {
                            customSound = BlockSoundGroup.GRAVEL;
                        }
                        if (Objects.equals(sound, "METAL")) {
                            customSound = BlockSoundGroup.METAL;
                        }
                        if (Objects.equals(sound, "GRASS")) {
                            customSound = BlockSoundGroup.GRASS;
                        }
                        if (Objects.equals(sound, "WOOL")) {
                            customSound = BlockSoundGroup.WOOL;
                        }
                        if (Objects.equals(sound, "SAND")) {
                            customSound = BlockSoundGroup.SAND;
                        }
                        
                        FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(net.minecraft.block.Blocks.STONE)
                                .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                                .strength((float) hardness, (float) resistance)
                                .sounds(customSound);

                        
                        if (dropsNothing) {
                            blockSettings.dropsNothing();
                        }

                        // 读取更多属性并设置到 blockSettings 中
                        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
                        Block block = new Block(blockSettings);
                        registerBlock(namespace, id, block);
                        registerBlockItem(namespace, id, block);
                        setupRenderLayer(block, blockData); // 设置渲染层
                        Main.LOGGER.info("Block " + name + "<->" + namespace + ":" + id + " registered!");



                        RRPCallback.BEFORE_VANILLA.register(b -> {
                            pack.clearResources();
                            pack.addLang(new Identifier(namespace, "en_us"), LanguageProvider.create()
                                    .add("block."+namespace+"."+id, name));
                            pack.addLang(new Identifier(namespace, "zh_cn"), LanguageProvider.create()
                                    .add("block."+namespace+"."+id, name));
                            b.add(pack);
                        });


                        //String textureReference = namespace + ":" + "block/" + id;

                        //ModelJsonBuilder.create("minecraft","block/cube")
                                //.addTexture(TextureKey.ALL, textureReference);

                        //pack.addBlockState("", BlockStateModelGenerator.createStoneState(, new Identifier(namespace, "block/" + id)));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
