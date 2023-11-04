package com.KafuuChino0722.coreextensions.fluid;

import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.model.ModelFluid;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.util.FluidRenderHandler;
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

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class iZipFluids {
    public static final String FILE = Reference.File;

    public FlowableFluid STILL_WATER;
    public FlowableFluid FLOWING_WATER;
    public Block WATER_BLOCK;
    public Block FLOWING_WATER_BLOCK;
    public Item WATER_BUCKET;
    public boolean isInfinite;
    public int flowSpeed;

    public static final String[] paths = {Reference.Mods,Reference.File};

    public static Object returnO(String KeyTitle, String need) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/fluids.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("blocks")) {
                                            Map<String, Object> blocksData = Data.get("blocks");
                                            Map<String, Object> getKey = (Map<String, Object>) blocksData.get(KeyTitle);
                                            Map<String, Object> getProperties = (Map<String, Object>) getKey.get("properties");
                                            return getProperties.get(need);
                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        } catch (ZipException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        return null;
    }

    public static Object returnN(String KeyTitle, String need) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/fluids.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("blocks")) {
                                            Map<String, Object> blocksData = Data.get("blocks");
                                            Map<String, Object> getKey = (Map<String, Object>) blocksData.get(KeyTitle);
                                            return getKey.get(need);
                                        }
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }
                            }
                        } catch (ZipException e) {
                            throw new RuntimeException(e);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }
        return null;
    }

    public void load(String[] paths) {
        Yaml yaml = new Yaml();

        for (String path : paths) {
            File coreDirectory = new File(path);

            if (coreDirectory.exists() && coreDirectory.isDirectory()) {
                File[] zipFiles = coreDirectory.listFiles((dir, name) ->
                        name.toLowerCase().endsWith(".zip") || name.toLowerCase().endsWith(".jar"));

                if (zipFiles != null) {
                    for (File zipFile : zipFiles) {
                        try (ZipFile zip = new ZipFile(zipFile)) {
                            Enumeration<? extends ZipEntry> entries = zip.entries();

                            while (entries.hasMoreElements()) {
                                ZipEntry entry = entries.nextElement();

                                if (!entry.isDirectory() && entry.getName().equals("data/fluids.yml")) {
                                    try (InputStream inputStream = zip.getInputStream(entry)) {
                                        Map<String, Map<String, Object>> Data = yaml.load(new InputStreamReader(inputStream));

                                        if (Data != null && Data.containsKey("blocks")) {
                                            Map<String, Object> items = Data.get("blocks");

                                            for (Map.Entry<String, Object> itemEntry : items.entrySet()) {
                                                if (itemEntry.getValue() instanceof Map) {
                                                    Map<String, Object> blockData = (Map<String, Object>) itemEntry.getValue();

                                                    String name = (String) blockData.get("name");
                                                    String nameBucket = (String) blockData.get("nameBucket");
                                                    String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                                                    String id = (String) blockData.get("id");
                                                    String KeyTitle = itemEntry.getKey();

                                                    Map<String, Object> properties = blockData.containsKey("properties")? (Map<String, Object>) blockData.get("properties"):null;
                                                    int color = (int) properties.get("color");

                                                    if(!Registries.ITEM.containsId(new Identifier(namespace,id + "_bucket"))) {
                                                        ItemGroupsContents.load(namespace, id, properties);
                                                        ItemGroupsContents.load(namespace, id + "_bucket", properties);
                                                    }

                                                    this.isInfinite = properties.containsKey("isInfinite") ? (boolean) properties.get("isInfinite") : true;
                                                    this.flowSpeed = properties.containsKey("flowSpeed") ? (int) properties.get("flowSpeed") : 5;
                                                    boolean isLava = properties.containsKey("isLava") ? (boolean) properties.get("isLava") : false;

                                                    if(!(Registries.FLUID.containsId(new Identifier(namespace,id))&&Registries.FLUID.containsId(new Identifier(namespace,"flowing_"+id)))) {
                                                        this.STILL_WATER = Registry.register(Registries.FLUID,
                                                                new Identifier(namespace, id), new CustomFluid.Still(KeyTitle));

                                                        this.FLOWING_WATER = Registry.register(Registries.FLUID,
                                                                new Identifier(namespace, "flowing_" + id), new CustomFluid.Flowing(KeyTitle));

                                                        this.WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(namespace, id),
                                                                new FluidBlock(this.STILL_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable()));

                                                        this.FLOWING_WATER_BLOCK = Registry.register(Registries.BLOCK, new Identifier(namespace, "flowing_" + id),
                                                                new FluidBlock(this.FLOWING_WATER, FabricBlockSettings.copyOf(Blocks.WATER).replaceable()));

                                                        this.WATER_BUCKET = Registry.register(Registries.ITEM, new Identifier(namespace, id + "_bucket"),
                                                                new BucketItem(this.STILL_WATER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

                                                        Registry.register(Registries.ITEM, new Identifier(namespace, id), new BlockItem(this.WATER_BLOCK, new FabricItemSettings()));
                                                        Registry.register(Registries.ITEM, new Identifier(namespace, "flowing_" + id), new BlockItem(this.FLOWING_WATER_BLOCK, new FabricItemSettings()));

                                                        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                                                            FluidRenderHandler.UnZip.set(namespace, id, color);
                                                            ModelFluid.generate(namespace, id);
                                                        }
                                                    }
                                                    provider.add("block." + namespace + "." + id, name)
                                                            .add("item." + namespace + "." + id + "_bucket", nameBucket);

                                                    //Tags.generateFluid(namespace,id);
                                                    if(isLava) {
                                                        Tags.generateFluidLava(this.STILL_WATER, this.FLOWING_WATER);
                                                    } else {
                                                        Tags.generateFluid(this.STILL_WATER, this.FLOWING_WATER);
                                                    }
                                                }
                                            }
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
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