package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import com.KafuuChino0722.coreextensions.core.registry.DisableFeatureRegistry;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mixin(Items.class)
public class ItemsMixin {
    @Inject(method = "register(Lnet/minecraft/util/Identifier;Lnet/minecraft/item/Item;)Lnet/minecraft/item/Item;", at = @At("HEAD"), cancellable = true)
    private static void register(Identifier id, Item item, CallbackInfoReturnable<Item> cir) {
        try {
            registerYaml(id, cir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Unique
    private static void registerYaml(Identifier id, CallbackInfoReturnable<Item> cir) {
        Map<String, Map<String, Object>> itemsData = IOFileManager.read("disableFeature.yml");
        load(itemsData, id, cir);
        Map<String, Map<String, Object>> itemsDataZ = IOFileManager.readZip("disableFeature.yml");
        load(itemsDataZ, id, cir);
    }

    @Unique
    private static void load(Map<String, Map<String, Object>> itemsData, Identifier id, CallbackInfoReturnable<Item> cir) {
        if (itemsData != null && itemsData.containsKey("items")) {
            List<String> items = (List<String>) itemsData.getOrDefault("items",null);
            if(items!=null) {
                for (String ItemId : items) {
                    Identifier getId = new Identifier(ItemId);
                    if(Objects.equals(id, getId)) {
                        Item DisabledItem = new Item(new FabricItemSettings()) {
                            @Override
                            public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
                                tooltip.add(Text.translatable("item.disabled").formatted(Formatting.RED));
                            }
                        };
                        cir.setReturnValue(Registry.register(Registries.ITEM, id, DisabledItem));
                    }
                }
            }
        }
    }
}
