package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.block.FluidBlock;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import com.KafuuChino0722.coreextensions.util.VanillaManager;
import com.KafuuChino0722.coreextensions.util.VanillaRegManager;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import pers.solid.brrp.v1.api.RuntimeResourcePack;
import pers.solid.brrp.v1.fabric.api.RRPCallback;
import pers.solid.brrp.v1.tag.IdentifiedTagBuilder;

import java.util.Map;

public class Fluids {
    //You Can Create A API TO Get Your Value And Set Your Action From It,I Recommend You Should Copy It To Use.
    public static void register(String name, String namespace, String id, Map<String, Object> blockData, Boolean dropsNothing, BlockSoundGroup customSound ,double hardness, double resistance, boolean generate){

        /*FabricBlockSettings blockSettings = FabricBlockSettings.copyOf(Blocks.WATER)
                .lightLevel((int) blockData.getOrDefault("lightLevel", 0))
                .strength((float) hardness, (float) resistance)
                .sounds(customSound);

        Item item = new Item(new Item.Settings().maxCount(64));

        Item itembucket = new BucketItem(Registries.FLUID.get(new Identifier(namespace,id+"_bucket")) ,new Item.Settings().maxCount(1));

        Fluid block = new FluidBlock.Flowing(namespace, id);

        RuntimeResourcePack packs = RuntimeResourcePack.create(new Identifier("coreextensions", "fluids_"+namespace+id));

        RRPCallback.BEFORE_VANILLA.register(b -> {
            packs.clearResources();
            packs.addTag(IdentifiedTagBuilder.createBlock(FluidTags.WATER.id()).add(new Identifier(namespace,id)));
            b.add(packs);
        });

        registerFluidsFlowing(namespace, id);
        registerItem(namespace ,id ,itembucket);

        ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib

    public static net.minecraft.item.Item registerItem(String namespace, String id, net.minecraft.item.Item item) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id), item);
    }

    public static FlowableFluid registerFluidsFlowing(String namespace, String id) {
        return Registry.register(Registries.FLUID, new Identifier(namespace, id), new FluidBlock.Flowing(namespace, id));
    }

    public static FlowableFluid registerFluidsStill(String namespace, String id) {
        return Registry.register(Registries.FLUID, new Identifier(namespace, id), new FluidBlock.Still(namespace, id));
    }

    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }


    public static void setupRenderLayer(Block block, Map<String, Object> blockData) {
        boolean shouldUseCutoutLayer = (boolean) blockData.getOrDefault("useCutoutLayer", false);

        if (shouldUseCutoutLayer) {
            BlockRenderLayerMap.INSTANCE.putBlock(block, RenderLayer.getCutout());
        }*/
    }
}
