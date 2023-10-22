package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Identifier.class)
public class IdentifierMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    private static boolean isNamespaceValid(String namespace) {
        return true;
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    private static boolean isPathValid(String namespace) {
        return true;
    }
}
