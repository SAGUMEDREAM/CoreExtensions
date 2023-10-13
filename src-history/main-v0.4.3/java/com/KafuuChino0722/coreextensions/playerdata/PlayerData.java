package com.KafuuChino0722.coreextensions.playerdata;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.world.PersistentState;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

public class PlayerData extends PersistentState {
    public static PlayerData access(@NotNull ServerPlayerEntity player) {
        return ((ServerPlayerEntityAccess) player).ec$getPlayerData();
    }
    interface ServerPlayerEntityAccess {
        PlayerData ec$getPlayerData();

        void ec$setPlayerData(PlayerData playerData);
    }



    @Override
    public NbtCompound writeNbt(NbtCompound nbt) {
        return null;
    }

    // ServerPlayerEntity
    private ServerPlayerEntity player;
    private UUID pUuid;
    private  File saveFile;
}
