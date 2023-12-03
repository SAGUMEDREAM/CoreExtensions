package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.network.message.MessageType;
import net.minecraft.network.message.SentMessage;
import net.minecraft.network.message.SignedMessage;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.util.function.Predicate;

import static net.minecraft.server.PlayerManager.FILTERED_FULL_TEXT;

@Mixin(PlayerManager.class)
public class PlayerManagerMixin {
    /**
     * @author
     * @reason
     */
    @Overwrite
    public void broadcast(SignedMessage message, Predicate<ServerPlayerEntity> shouldSendFiltered, @Nullable ServerPlayerEntity sender, MessageType.Parameters params) {
        boolean bl = ((PlayerManager)(Object)this).verify(message);
        //((PlayerManager)(Object)this).server.logChatMessage(message.getContent(), params, bl ? null : "Not Secure");
        SentMessage sentMessage = SentMessage.of(message);
        boolean bl2 = false;
        for (ServerPlayerEntity serverPlayerEntity : ((PlayerManager)(Object)this).players) {
            boolean bl3 = shouldSendFiltered.test(serverPlayerEntity);
            serverPlayerEntity.sendChatMessage(sentMessage, bl3, params);
            bl2 |= bl3 && message.isFullyFiltered();
        }
        if (bl2 && sender != null) {
            sender.sendMessage(FILTERED_FULL_TEXT);
        }
    }
}
