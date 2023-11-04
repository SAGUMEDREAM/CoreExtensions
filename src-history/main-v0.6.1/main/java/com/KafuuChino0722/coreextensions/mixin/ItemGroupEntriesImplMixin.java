package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ItemGroup.EntriesImpl.class)
public class ItemGroupEntriesImplMixin implements ItemGroup.Entries {
    /**
     * @author
     * @reason
     */
    @Overwrite
    @Override
    public void add(ItemStack stack, ItemGroup.StackVisibility visibility) {
        boolean bl;
        if (stack.getCount() != 1) {
            throw new IllegalArgumentException("Stack size must be exactly 1");
        }
        boolean bl2 = bl = ((ItemGroup.EntriesImpl)(Object)this).parentTabStacks.contains(stack) && visibility != ItemGroup.StackVisibility.SEARCH_TAB_ONLY;
        if (bl) {
            //throw new IllegalStateException("Accidentally adding the same item stack twice " + stack.toHoverableText().getString() + " to a Creative Mode Tab: " + this.group.getDisplayName().getString());
        }
        if (stack.getItem().isEnabled(((ItemGroup.EntriesImpl)(Object)this).enabledFeatures)) {
            switch (visibility) {
                case PARENT_AND_SEARCH_TABS: {
                    ((ItemGroup.EntriesImpl)(Object)this).parentTabStacks.add(stack);
                    ((ItemGroup.EntriesImpl)(Object)this).searchTabStacks.add(stack);
                    break;
                }
                case PARENT_TAB_ONLY: {
                    ((ItemGroup.EntriesImpl)(Object)this).parentTabStacks.add(stack);
                    break;
                }
                case SEARCH_TAB_ONLY: {
                    ((ItemGroup.EntriesImpl)(Object)this).searchTabStacks.add(stack);
                }
            }
        }
    }
}
