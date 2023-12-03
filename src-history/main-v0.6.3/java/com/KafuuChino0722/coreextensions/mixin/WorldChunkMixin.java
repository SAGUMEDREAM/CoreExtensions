package com.KafuuChino0722.coreextensions.mixin;

import com.mojang.logging.LogUtils;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.crash.CrashException;
import net.minecraft.util.crash.CrashReport;
import net.minecraft.util.crash.CrashReportSection;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.world.chunk.BlockEntityTickInvoker;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.mojang.text2speech.Narrator.LOGGER;

@Mixin(WorldChunk.DirectBlockEntityTickInvoker.class)
public abstract class WorldChunkMixin implements BlockEntityTickInvoker {

    @Inject(at = @At("HEAD"), method = "tick")
    private void tickFix(CallbackInfo info) {
        ((WorldChunk.DirectBlockEntityTickInvoker)(Object)this).hasWarned = true;
    }
}
