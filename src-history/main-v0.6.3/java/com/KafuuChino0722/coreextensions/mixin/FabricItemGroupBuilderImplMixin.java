package com.KafuuChino0722.coreextensions.mixin;

import net.fabricmc.fabric.impl.itemgroup.FabricItemGroupBuilderImpl;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.text.OrderedText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FabricItemGroupBuilderImpl.class)
public class FabricItemGroupBuilderImplMixin extends ItemGroup.Builder {

    public FabricItemGroupBuilderImplMixin() {
        // Set when building.
        super(null, -1);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    @Override
    public ItemGroup build() {
        return super.build();
    }
}
