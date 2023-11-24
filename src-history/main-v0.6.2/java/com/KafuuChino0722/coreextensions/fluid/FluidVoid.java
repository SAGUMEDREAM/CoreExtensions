package com.KafuuChino0722.coreextensions.fluid;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class FluidVoid {

    public Identifier id;
    public String namespace;
    public String path;
    public FlowableFluid STILL_WATER;
    public FlowableFluid FLOWING_WATER;
    public Block WATER_BLOCK;
    public Item WATER_BUCKET;

    public int color = 0x20B2AA;
    public int flowSpeed = 5;
    public boolean isInfinite = true;
    public boolean isLava = false;
    public boolean SUPER = false;

    public FluidVoid(Identifier id, int color, int flowSpeed, boolean isInfinite, boolean isLava ,boolean SUPER) {
        this.id = id;
        this.namespace = id.getNamespace();
        this.path = id.getPath();
        this.color = color;
        this.flowSpeed = flowSpeed;
        this.isInfinite = isInfinite;
        this.isLava = isLava;
        this.SUPER = SUPER;

        this.STILL_WATER = Registry.register(Registries.FLUID,
                new Identifier(namespace, path), new CustomFluid.Still(this));

        this.FLOWING_WATER = Registry.register(Registries.FLUID,
                new Identifier(namespace,"flowing_" + path), new CustomFluid.Flowing(this));

        this.WATER_BLOCK = Registry.register(Registries.BLOCK,new Identifier(namespace,path),
                new FluidBlock(STILL_WATER, FabricBlockSettings.create()
                        .mapColor(MapColor.WATER_BLUE)
                        .replaceable()
                        .noCollision()
                        .strength(100.0f)
                        .pistonBehavior(PistonBehavior.DESTROY)
                        .dropsNothing()
                        .liquid()
                        .sounds(BlockSoundGroup.INTENTIONALLY_EMPTY)));

        this.WATER_BUCKET = Registry.register(Registries.ITEM,new Identifier(namespace,path + "_bucket"),
                new BucketItem(STILL_WATER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

        Registry.register(Registries.ITEM, id, new BlockItem(WATER_BLOCK, new FabricItemSettings()));
    }
}
