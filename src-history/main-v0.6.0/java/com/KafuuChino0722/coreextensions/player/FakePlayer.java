package com.KafuuChino0722.coreextensions.player;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Uuids;

import java.util.List;
import java.util.UUID;

public class FakePlayer extends ServerPlayerEntity {

    public static FakePlayer create(MinecraftServer server, GameProfile profile, boolean canKickExisting) {
        PlayerManager playerManager = server.getPlayerManager();
        UUID uUID = Uuids.getUuidFromProfile(profile);
        List<ServerPlayerEntity> list = Lists.newArrayList();

        for(int i = 0; i < playerManager.players.size(); ++i) {
            ServerPlayerEntity serverPlayerEntity = playerManager.players.get(i);
            if (serverPlayerEntity.getUuid().equals(uUID)) {
                list.add(serverPlayerEntity);
            }
        }

        ServerPlayerEntity serverPlayerEntity2 = playerManager.playerMap.get(profile.getId());
        if (serverPlayerEntity2 != null && !list.contains(serverPlayerEntity2)) {
            list.add(serverPlayerEntity2);
        }

        if (canKickExisting) {
            for (ServerPlayerEntity serverPlayerEntity3 : list) {
                serverPlayerEntity3.networkHandler.disconnect(Text.translatable("multiplayer.disconnect.duplicate_login"));
            }
        }
        else {
            throw new RuntimeException("You tried to login with a duplicate UUID.");
        }

        return new FakePlayer(server, server.getOverworld(), profile);
    }
    public FakePlayer(MinecraftServer server, ServerWorld world, GameProfile profile) {
        super(server, world, profile);
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        super.onDeath(damageSource);
        networkHandler.onDisconnected(Text.of("Fake player died."));
    }

    @Override
    public void playerTick() {
        super.playerTick();
        System.out.println("Fake player tick");
    }
}
