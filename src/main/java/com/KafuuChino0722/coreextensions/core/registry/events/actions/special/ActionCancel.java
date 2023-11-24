package com.KafuuChino0722.coreextensions.core.registry.events.actions.special;

import com.KafuuChino0722.coreextensions.core.registry.events.actions.ActionInterface;
import net.minecraft.util.ActionResult;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

public class ActionCancel implements ActionInterface {
    public static void run(CallbackInfoReturnable ci, Map<String, Object> Key) {
        Map<String, Object> cancelKey = (Map<String, Object>) Key.getOrDefault("cancel", null);
        if(cancelKey!=null) {
            boolean I = (boolean) cancelKey.getOrDefault("enable",false);
            if (I) {
                ci.setReturnValue(ActionResult.FAIL);
            }
        }
    }
}
