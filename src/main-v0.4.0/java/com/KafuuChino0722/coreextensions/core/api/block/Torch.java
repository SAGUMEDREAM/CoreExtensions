package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.core.api.util.Loots;
import com.KafuuChino0722.coreextensions.core.api.util.Models;
import com.KafuuChino0722.coreextensions.core.api.util.setupRenderLayer;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.TorchBlock;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

import java.util.Map;

public class Torch {
    public static void register(String name, String namespace, String id, int maxCount, Map<String, Object> blockData, Map<String, Object> properties, Boolean dropsNothing, BlockSoundGroup customSound ,double hardness, double resistance, boolean generate){
        FabricBlockSettings blockSettings = FabricBlockSettings.create()
                .noCollision()
                .breakInstantly()
                .luminance(state -> (int) properties.getOrDefault("lightLevel", 0))
                .sounds(customSound)
                .pistonBehavior(PistonBehavior.DESTROY);

        if (dropsNothing) {
            blockSettings.dropsNothing();
        }

        // 读取更多属性并设置到 blockSettings 中
        // blockSettings = blockSettings.someProperty((SomeType) blockData.getOrDefault("propertyName", defaultValue));
        Block block = new TorchBlock(blockSettings, ParticleTypes.FLAME);
        Block blockwall = new WallTorchBlock(blockSettings, ParticleTypes.FLAME);
        registerBlock(namespace, id, block);
        registerBlock(namespace, "wall_"+id, blockwall);
        registerBlockItem(namespace, id, block, maxCount);

        if(Reference.EnvType == EnvType.CLIENT) {
            setupRenderLayer.setNoData(block);
            setupRenderLayer.setNoData(blockwall);
            // 设置渲染层
        }

        String type = "TORCH";
        if(generate) {
            Models.generate(namespace, id, type);
        }

        Loots.ONLY(namespace, id, blockData, properties);

        ReturnMessage.BlockYMLRegister(name, namespace, id); //returnMessage
    }

    //API-Lib
    public static Block registerBlock(String namespace, String id, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(namespace, id), block);
    }

    public static Item registerBlockItem(String namespace, String id, Block block, int maxCount) {
        return Registry.register(Registries.ITEM, new Identifier(namespace, id),
                new VerticallyAttachableBlockItem(
                        Registries.BLOCK.get(new Identifier(namespace,id)),
                        Registries.BLOCK.get(new Identifier(namespace,"wall_"+id)),
                        new Item.Settings().maxCount(maxCount), Direction.DOWN));
    }
}
