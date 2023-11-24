package net.darkhax.openloaderfcore.mixin;

import com.KafuuChino0722.coreextensions.Main;
import net.darkhax.openloaderfcore.packs.OpenLoaderRepositorySource;
import net.darkhax.openloaderfcore.packs.RepoType;
import net.fabricmc.fabric.impl.resource.loader.ModResourcePackCreator;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourceType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;

import static com.LoneDev.itemsadder.Main.*;

@Mixin(ModResourcePackCreator.class)
public class MixinModResourcePackCreator {

    @Final
    @Shadow
    private ResourceType type;

    @Unique
    private OpenLoaderRepositorySource newSource;
    private OpenLoaderRepositorySource newSource2;
    private OpenLoaderRepositorySource newSource3;
    private OpenLoaderRepositorySource newSource4;

    @Inject(method = "<init>(Lnet/minecraft/resource/ResourceType;)V", at = @At("RETURN"))
    private void onConstruction(ResourceType type, CallbackInfo callback) {

        if (type == ResourceType.SERVER_DATA) {

            this.newSource = new OpenLoaderRepositorySource(RepoType.DATA, Main.config.dataPacks, Main.configDir);
            this.newSource2 = new OpenLoaderRepositorySource(RepoType.DATA, Main.config2.dataPacks, Main.configDir2);
            if(ENABLED) {
                this.newSource3 = new OpenLoaderRepositorySource(RepoType.DATA, configIA.dataPacks, configDirIA);
                this.newSource4 = new OpenLoaderRepositorySource(RepoType.DATA, configIAMods.dataPacks, configDirIAMods);
            }
        }

        else if (type == ResourceType.CLIENT_RESOURCES) {

            this.newSource = new OpenLoaderRepositorySource(RepoType.RESOURCES, Main.config.resourcePacks, Main.configDir);
            this.newSource2 = new OpenLoaderRepositorySource(RepoType.RESOURCES, Main.config2.resourcePacks, Main.configDir2);

            if(ENABLED) {
                this.newSource3 = new OpenLoaderRepositorySource(RepoType.RESOURCES, configIA.resourcePacks, configDirIA);
                this.newSource4 = new OpenLoaderRepositorySource(RepoType.RESOURCES, configIAMods.resourcePacks, configDirIAMods);
            }

        }
    }

    @Inject(method = "register(Ljava/util/function/Consumer;)V", at = @At("RETURN"))
    private void register(Consumer<ResourcePackProfile> consumer, CallbackInfo callback) {

        if (this.newSource != null) {

            this.newSource.register(consumer);
        }

        if (this.newSource2 != null) {

            this.newSource2.register(consumer);
        }

        if (this.newSource3 != null && ENABLED) {

            this.newSource3.register(consumer);
        }

        if (this.newSource4 != null && ENABLED) {

            this.newSource4.register(consumer);
        }
    }
}