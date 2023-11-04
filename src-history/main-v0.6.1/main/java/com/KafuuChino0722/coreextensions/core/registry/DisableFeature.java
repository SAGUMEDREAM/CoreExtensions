package com.KafuuChino0722.coreextensions.core.registry;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry._Fix.WorldRegistryDataReloading;
import com.KafuuChino0722.coreextensions.util.setRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static com.KafuuChino0722.coreextensions.core.registry.Registries.AllowExistingReloading;

public class DisableFeature {

    public static void register() {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("disableFeature.yml");
        load(itemsData);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("disableFeature.yml");
        load(itemsDataZ);
    }

    public static void load(Map<String, Map<String, Object>> itemsData) {
        if (itemsData != null && itemsData.containsKey("items")) {
            List<String> items = (List<String>) itemsData.getOrDefault("items",null);
            if(items!=null) {
                for (String ItemId : items) {
                    if (Registries.BLOCK.containsId(new Identifier(ItemId))) {
                        try {
                            Block Disabledblock = new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK));
                            Item DisabledItem = new Item(new FabricItemSettings()) {
                                @Override
                                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                    tooltip.add(Text.translatable("item.disabled").formatted(Formatting.RED));
                                }
                            };
                            if(Registries.BLOCK.containsId(new Identifier(ItemId))&&AllowExistingReloading) {
                                Item oldItem = Registries.ITEM.get(new Identifier(ItemId));
                                setRegistry.set(ItemId, Disabledblock);
                                setRegistry.set(ItemId, DisabledItem);
                                WorldRegistryDataReloading.run(DisabledItem, oldItem);
                            }

                        } catch (Exception e) {

                        }
                    } else if (Registries.ITEM.containsId(new Identifier(ItemId))) {
                        try {
                            Item DisabledItem = new Item(new FabricItemSettings()) {
                                @Override
                                public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                    tooltip.add(Text.translatable("item.disabled").formatted(Formatting.RED));
                                }
                            };
                            if(Registries.ITEM.containsId(new Identifier(ItemId))&&AllowExistingReloading) {
                                Item oldItem = Registries.ITEM.get(new Identifier(ItemId));
                                setRegistry.set(ItemId, DisabledItem);
                                WorldRegistryDataReloading.run(DisabledItem, oldItem);
                            }
                        } catch (Exception e) {

                        }
                    }
                }
            }
        }
    }
}
