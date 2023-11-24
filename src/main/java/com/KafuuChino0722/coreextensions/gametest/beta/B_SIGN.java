package com.KafuuChino0722.coreextensions.gametest.beta;

import com.terraformersmc.terraform.sign.SpriteIdentifierRegistry;
import com.terraformersmc.terraform.sign.block.TerraformHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallHangingSignBlock;
import com.terraformersmc.terraform.sign.block.TerraformWallSignBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.HangingSignItem;
import net.minecraft.item.Item;
import net.minecraft.item.SignItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class B_SIGN {

    public static final String namespace = "gametest";

    public static final Identifier CHESTNUT_SIGN_TEXTURE = new Identifier(namespace,"entity/signs/chestnut");
    public static final Identifier CHESTNUT_HANGING_SIGN_TEXTURE = new Identifier(namespace,"entity/signs/hanging/chestnut");
    public static final Identifier CHESTNUT_HANGING_GUI_SIGN_TEXTURE = new Identifier(namespace,"textures/gui/hanging_signs/chestnut");

    public static final Block STANDING_CHESTNUT_SIGN = Registry.register(Registries.BLOCK, new Identifier(namespace,"chestnut_standing_sign"),
            new TerraformSignBlock(CHESTNUT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_SIGN)));
    public static final Block WALL_CHESTNUT_SIGN = Registry.register(Registries.BLOCK, new Identifier(namespace,"chestnut_wall_sign"),
            new TerraformWallSignBlock(CHESTNUT_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_SIGN)));
    public static final Block HANGING_CHESTNUT_SIGN = Registry.register(Registries.BLOCK, new Identifier(namespace, "chestnut_hanging_sign"),
            new TerraformHangingSignBlock(CHESTNUT_HANGING_SIGN_TEXTURE, CHESTNUT_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_HANGING_SIGN)));
    public static final Block WALL_HANGING_CHESTNUT_SIGN = Registry.register(Registries.BLOCK, new Identifier(namespace, "chestnut_wall_hanging_sign"),
            new TerraformWallHangingSignBlock(CHESTNUT_HANGING_SIGN_TEXTURE, CHESTNUT_HANGING_GUI_SIGN_TEXTURE, FabricBlockSettings.copyOf(Blocks.OAK_WALL_HANGING_SIGN)));

    public static final Item CHESTNUT_SIGN = Registry.register(Registries.ITEM, new Identifier(namespace, "chestnut_sign"), new SignItem(new FabricItemSettings().maxCount(16), STANDING_CHESTNUT_SIGN, WALL_CHESTNUT_SIGN));

    public static final Item HANGING_CHESTNUT_SIGN_ITEM = Registry.register(Registries.ITEM, new Identifier(namespace, "chestnut_hanging_sign"), new HangingSignItem(HANGING_CHESTNUT_SIGN, WALL_HANGING_CHESTNUT_SIGN, new FabricItemSettings().maxCount(16)));


    public static void load() {
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, CHESTNUT_SIGN_TEXTURE));
        SpriteIdentifierRegistry.INSTANCE.addIdentifier(new SpriteIdentifier(TexturedRenderLayers.SIGNS_ATLAS_TEXTURE, CHESTNUT_HANGING_SIGN_TEXTURE));
    }
}
