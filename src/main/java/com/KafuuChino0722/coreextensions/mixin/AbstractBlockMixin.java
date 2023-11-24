package com.KafuuChino0722.coreextensions.mixin;

import com.KafuuChino0722.coreextensions.core.registry.events.eventsBlock.EventOnUseAbstractBlockRegistry;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.MapColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractBlock.class)
public abstract class AbstractBlockMixin {
    @Shadow public abstract MapColor getDefaultMapColor();

    @Inject(method = "onUse", at = @At("HEAD"))
    public void onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir) {

        if(!EventOnUseAbstractBlockRegistry.isRight) {
            try {
                Identifier block = Registries.BLOCK.getId(state.getBlock());
                String namespace = block.getNamespace();
                String id = block.getPath();
                EventOnUseAbstractBlockRegistry.register(namespace, id, state, world, pos, player, hand, hit);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    @Inject(method = "randomTick", at = @At("HEAD"))
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random, CallbackInfo ci) {
        Identifier block = Registries.BLOCK.getId(state.getBlock());
        String namespace = block.getNamespace();
        String id = block.getPath();

    }

    @Inject(method = "onBlockBreakStart", at = @At("HEAD"))
    public void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player, CallbackInfo ci) {
        Identifier block = Registries.BLOCK.getId(state.getBlock());
        String namespace = block.getNamespace();
        String id = block.getPath();

    }

    @Inject(method = "onStacksDropped", at = @At("HEAD"))
    public void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience, CallbackInfo ci) {
        Identifier block = Registries.BLOCK.getId(state.getBlock());
        String namespace = block.getNamespace();
        String id = block.getPath();

    }

}
