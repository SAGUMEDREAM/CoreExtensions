package com.KafuuChino0722.coreextensions.core.api.util;

import com.KafuuChino0722.coreextensions.util.Reference;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class setupRenderLayer {
    public static void set(@Nullable Block block, Map<String, Object> properties) {
        boolean shouldUseCutoutLayer = (boolean) properties.getOrDefault("useCutoutLayer", false);
        if (shouldUseCutoutLayer && Reference.EnvType == EnvType.CLIENT) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }
    }
    public static void set(@Nullable Block block) {
        if (Reference.EnvType == EnvType.CLIENT) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }
    }
}
