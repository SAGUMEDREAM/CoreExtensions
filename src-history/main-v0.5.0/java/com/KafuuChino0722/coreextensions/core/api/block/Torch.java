package com.KafuuChino0722.coreextensions.core.api.block;

import com.KafuuChino0722.coreextensions.PolyMcLoader;
import com.KafuuChino0722.coreextensions.core.api.util.*;
import com.KafuuChino0722.coreextensions.util.Reference;
import com.KafuuChino0722.coreextensions.util.ReturnMessage;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
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

        if(FabricLoader.getInstance().isModLoaded("polymc")&&properties.containsKey("polyinfo")) {
            Map<String, Object> polyinfo = (Map<String, Object>) properties.get("polyinfo");
            Block vanillaBlock = IdentifierManager.getBlock((String) polyinfo.getOrDefault("vanilla","minecraft:stone"));
            PolyMcLoader.loadBlock.block(block,vanillaBlock);
        }

        if(Reference.EnvType == EnvType.CLIENT) {
            setupRenderLayer.set(block);
            setupRenderLayer.set(blockwall);
            // 设置渲染层
        }

        String type = "TORCH";
        if(generate) {
            Models.generate(namespace, id, type);
        }

        Tags.Block.generateTags(namespace,id,properties);
        Loots.addDrop(namespace, id, blockData, properties);

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
