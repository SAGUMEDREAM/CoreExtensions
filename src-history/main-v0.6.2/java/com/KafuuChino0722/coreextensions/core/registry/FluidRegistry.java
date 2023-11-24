package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.api.ItemGroupsContents;
import com.KafuuChino0722.coreextensions.core.api.model.ModelFluid;
import com.KafuuChino0722.coreextensions.core.api.model.ModelFluids;
import com.KafuuChino0722.coreextensions.core.api.util.Tags;
import com.KafuuChino0722.coreextensions.fluid.FluidVoid;
import com.KafuuChino0722.coreextensions.util.FluidRenderHandler;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Map;

import static com.KafuuChino0722.coreextensions.CoreManager.provider;

public class FluidRegistry {

    public static void register() {
        Map<String, Map<String, Object>> fluidsData = IOFileManager.read("fluids.yml");
        load(fluidsData);
        Map<String, Map<String, Object>> fluidsDataZ = IOFileManager.readZip("fluids.yml");
        load(fluidsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("blocks")) {
            Map<String, Object> items = itemsData.get("blocks");

            for (Map.Entry<String, Object> entry : items.entrySet()) {
                if (entry.getValue() instanceof Map) {
                    Map<String, Object> blockData = (Map<String, Object>) entry.getValue();

                    String name = (String) blockData.get("name");
                    String nameBucket = (String) blockData.get("nameBucket");
                    String namespace = (String) blockData.getOrDefault("namespace","minecraft");
                    String id = (String) blockData.get("id");

                    Map<String, Object> properties = blockData.containsKey("properties")? (Map<String, Object>) blockData.get("properties"):null;
                    int color = (int) properties.getOrDefault("color",0x20B2AA);
                    boolean isInfinite = (boolean) properties.getOrDefault("isInfinite", true);
                    int flowSpeed = (int) properties.getOrDefault("flowSpeed",5);
                    boolean isLava = (boolean) properties.getOrDefault("isLava",false);
                    boolean SUPER = (boolean) properties.getOrDefault("SUPER",false);

                    if(!(net.minecraft.registry.Registries.FLUID.containsId(new Identifier(namespace,id))&& Registries.FLUID.containsId(new Identifier(namespace,"flowing_"+id)))) {

                        FluidVoid Void = new FluidVoid(new Identifier(namespace, id), color, flowSpeed, isInfinite, isLava, SUPER);

                        ItemGroupsContents.load(namespace, id, properties);
                        ItemGroupsContents.load(namespace, id + "_bucket", properties);

                        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                            FluidRenderHandler.set(namespace, id, color);
                            ModelFluid.generate(namespace, id);
                            ModelFluids.generate(namespace,id);
                        }
                    } else {
                        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) {
                            ModelFluid.generate(namespace, id);
                            ModelFluids.generate(namespace,id);
                        }
                    }

                    provider.add("block." + namespace + "." + id, name)
                            .add("item." + namespace + "." + id + "_bucket", nameBucket);

                    if(isLava) {
                        Tags.generateFluidLava(Registries.FLUID.get(new Identifier(namespace,id)), Registries.FLUID.get(new Identifier(namespace,"flowing_" + id)));
                    } else {
                        Tags.generateFluid(Registries.FLUID.get(new Identifier(namespace,id)), Registries.FLUID.get(new Identifier(namespace,"flowing_" + id)));
                    }
                }
            }
        }
    }
}