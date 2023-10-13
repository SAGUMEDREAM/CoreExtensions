package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.block.entity.StructureBlockBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(StructureBlockBlockEntity.class)
public class StructureBlockMixin {

    @ModifyConstant(method = "readNbt", constant = @Constant(intValue = 48), require = 6, allow = 6)
    public int onReadNBT(int constant) {
        return 4096;
    }

}