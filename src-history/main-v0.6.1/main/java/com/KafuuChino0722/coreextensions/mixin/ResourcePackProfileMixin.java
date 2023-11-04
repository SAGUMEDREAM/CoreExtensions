package com.KafuuChino0722.coreextensions.mixin;

import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourcePack;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.metadata.PackFeatureSetMetadata;
import net.minecraft.resource.metadata.PackResourceMetadata;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(ResourcePackProfile.class)
public class ResourcePackProfileMixin {
    private static final Logger LOGGER = ResourcePackProfile.LOGGER;

    /**
     * @author
     * @reason
     */
    @Overwrite
    @Nullable
    public static ResourcePackProfile.Metadata loadMetadata(String name, ResourcePackProfile.PackFactory packFactory) {
        try (ResourcePack resourcePack = packFactory.open(name);){
            PackResourceMetadata packResourceMetadata = resourcePack.parseMetadata(PackResourceMetadata.SERIALIZER);
            if (packResourceMetadata == null) {
                //LOGGER.warn("Missing metadata in pack {}", (Object)name);
                ResourcePackProfile.Metadata metadata = null;
                return metadata;
            }
            PackFeatureSetMetadata packFeatureSetMetadata = resourcePack.parseMetadata(PackFeatureSetMetadata.SERIALIZER);
            FeatureSet featureSet = packFeatureSetMetadata != null ? packFeatureSetMetadata.flags() : FeatureSet.empty();
            ResourcePackProfile.Metadata metadata = new ResourcePackProfile.Metadata(packResourceMetadata.getDescription(), packResourceMetadata.getPackFormat(), featureSet);
            return metadata;
        } catch (Exception exception) {
            LOGGER.warn("Failed to read pack metadata", exception);
            return null;
        }
    }
}
