package com.KafuuChino0722.coreextensions.core;

import com.KafuuChino0722.coreextensions.core.api.model.ModelFluid;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.fluid.CustomFluidUnzip;
import com.KafuuChino0722.coreextensions.util.FluidRenderHandler;
import com.KafuuChino0722.coreextensions.util.Info;
import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.yaml.snakeyaml.Yaml;
import pers.solid.brrp.v1.api.LanguageProvider;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.respacks;

public class RegFluids {

    public static FlowableFluid STILL_WATER;
    public static FlowableFluid FLOWING_WATER;
    public static Block WATER_BLOCK;
    public static Block FLOWING_WATER_BLOCK;
    public static Item WATER_BUCKET;
    public static boolean isInfinite;
    public static int flowSpeed;

    public static final String FILE = Reference.File;

    public void load() {
        Yaml yaml = new Yaml();

        File coreDirectory = new File(FILE); // 获取 core 文件夹的 File 对象

        if (coreDirectory.exists() && coreDirectory.isDirectory()) {
            File[] subdirectories = coreDirectory.listFiles(File::isDirectory);

            if (subdirectories != null) {
                for (File subdirectory : subdirectories) {
                    File blockYamlFile = new File(subdirectory, "data/fluids.yml");

                    if (blockYamlFile.exists() && blockYamlFile.isFile()) {
                        try {
                            Map<String, Map<String, Object>> blocksData = yaml.load(new FileReader(blockYamlFile));

                            if (blocksData != null && blocksData.containsKey("blocks")) {
                                Map<String, Object> blocks = blocksData.get("blocks");

                                for (Map.Entry<String, Object> entry : blocks.entrySet()) {
                                    if (entry.getValue() instanceof Map) {
                                        Map<String, Object> blockData = (Map<String, Object>) entry.getValue();
                                        String name = (String) blockData.get("name");
                                        String nameBucket = (String) blockData.get("nameBucket");
                                        String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                                        String id = (String) blockData.get("id");

                                        Map<String, Object> properties = blockData.containsKey("properties")? (Map<String, Object>) blockData.get("properties"):null;
                                        int color = (int) properties.get("color");
                                        this.isInfinite = properties.containsKey("isInfinite") ? (boolean) properties.get("isInfinite") : true;
                                        this.flowSpeed = properties.containsKey("flowSpeed") ? (int) properties.get("flowSpeed") : 5;
                                        this.STILL_WATER = Registry.register(Registries.FLUID,
                                                new Identifier(namespace,id), new CustomFluidUnzip.Still());

                                        this.FLOWING_WATER = Registry.register(Registries.FLUID,
                                                new Identifier(namespace,"flowing_"+id), new CustomFluidUnzip.Flowing());

                                        this.WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(namespace,id),
                                                new FluidBlock(this.STILL_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable()));

                                        this.FLOWING_WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(namespace,"flowing_"+id),
                                                new FluidBlock(this.FLOWING_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable()));

                                        this.WATER_BUCKET = Registry.register(Registries.ITEM,new Identifier(namespace,id+"_bucket"),
                                                new BucketItem(this.STILL_WATER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

                                        Registry.register(Registries.ITEM,new Identifier(namespace,id), new BlockItem(this.WATER_BLOCK, new FabricItemSettings()));
                                        Registry.register(Registries.ITEM,new Identifier(namespace,"flowing_"+id), new BlockItem(this.FLOWING_WATER_BLOCK, new FabricItemSettings()));

                                        RegItemGroupsContents.load(namespace,id,properties);
                                        RegItemGroupsContents.load(namespace,id+"_bucket",properties);

                                        if(FabricLoader.getInstance().getEnvironmentType()==EnvType.CLIENT){
                                            FluidRenderHandler.UnZip.set(namespace,id,color);
                                            ModelFluid.generate(namespace,id);
                                        }

                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang", "en_us"),
                                                LanguageProvider.create()
                                                        .add("block." +namespace +"."+id, name)
                                                        .add("item." +namespace +"."+id+"_bucket", nameBucket)
                                        );

                                        respacks.addLang(new Identifier(namespace+"_"+id+"_lang","zh_cn"),
                                                LanguageProvider.create()
                                                        .add("block." +namespace +"."+id, name)
                                                        .add("item." +namespace +"."+id+"_bucket", nameBucket)
                                        );

                                        //Tags.generateFluid(namespace,id);
                                        Tags.generateFluid(this.STILL_WATER,this.FLOWING_WATER);

                                    }
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}