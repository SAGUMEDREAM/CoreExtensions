package com.tags_binder.mixin;

import com.tags_binder.impl.TagsCallbackInvoker;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import net.minecraft.registry.tag.TagManagerLoader;
import net.minecraft.server.DataPackContents;

@Mixin(DataPackContents.class)
public class DataPackContentsMixin {
    @ModifyVariable(method = "repopulateTags", at = @At(value = "HEAD"), argsOnly = true)
    private static <T> TagManagerLoader.RegistryTags<T> populate(TagManagerLoader.RegistryTags<T> value) {
        return TagsCallbackInvoker.call(value);
    }
}
