package com.KafuuChino0722.coreextensions.mixin;


import com.mojang.patchy.MojangBlockListSupplier;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MojangBlockListSupplier.class)
abstract class BypassMojang {

    /**
     * Prevent the blocklist from being created by returning nothing.
     *
     * @author Svenar
     * @reason Do not restrict player where they want to play
     * @return null
     */
    /*@Overwrite(remap = false)
    public Predicate<String> createBlockList() {
        return null;
    }*/
}