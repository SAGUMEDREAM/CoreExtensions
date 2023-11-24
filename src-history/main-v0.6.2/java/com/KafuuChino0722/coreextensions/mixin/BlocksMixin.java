package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.api.IOFileManager;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mixin(Blocks.class)
public class BlocksMixin {

    @Inject(method = "register", at = @At("HEAD"), cancellable = true)
    private static void register(String id, Block block, CallbackInfoReturnable<Block> cir) {
        try {
            registerYaml(id, block, cir);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Unique
    private static void registerYaml(String id, Block block, CallbackInfoReturnable<Block> cir) {
        Map<String, Map<String, Object>> D = IOFileManager.read("disableFeature.yml");
        load(D, id, block, cir);
        Map<String, Map<String, Object>> IZ = IOFileManager.readZip("disableFeature.yml");
        load(IZ, id, block, cir);
    }

    @Unique
    private static void load(Map<String, Map<String, Object>> itemsData, String id, Block block, CallbackInfoReturnable<Block> cir) {
        if (itemsData != null && itemsData.containsKey("items")) {
            List<String> items = (List<String>) itemsData.getOrDefault("items",null);
            if(items!=null) {
                for (String ItemId : items) {
                    String a = new Identifier(id).toString();
                    String b = new Identifier(ItemId).toString();
                    if(a.equalsIgnoreCase(b)) {
                        block = new Block(FabricBlockSettings.copyOf(Blocks.BEDROCK).strength(-1.0f, 3600000.0f).dropsNothing());
                        cir.setReturnValue(Registry.register(Registries.BLOCK, id, block));
                    }
                }
            }
        }
    }
}
