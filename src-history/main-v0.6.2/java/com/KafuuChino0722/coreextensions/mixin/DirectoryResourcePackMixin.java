package com.KafuuChino0722.coreextensions.mixin;

import com.google.common.collect.Sets;
import com.mojang.logging.LogUtils;
import net.minecraft.registry.SimpleRegistry;
import net.minecraft.resource.AbstractFileResourcePack;
import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourceType;
import org.slf4j.Logger;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Mixin(DirectoryResourcePack.class)
public abstract class DirectoryResourcePackMixin extends AbstractFileResourcePack {
    private static final Logger LOGGER = DirectoryResourcePack.LOGGER;

    protected DirectoryResourcePackMixin(String name, boolean alwaysStable) {
        super(name, alwaysStable);
    }

    /**
     * @author
     * @reason
     */
    @Overwrite
    @Override
    public Set<String> getNamespaces(ResourceType type) {
        HashSet<String> set = Sets.newHashSet();
        Path path = ((DirectoryResourcePack)(Object)this).root.resolve(type.getDirectory());
        try (DirectoryStream<Path> directoryStream2 = Files.newDirectoryStream(path);){
            for (Path path2 : directoryStream2) {
                String string = path2.getFileName().toString();
                if (string.equals(string.toLowerCase(Locale.ROOT))) {
                    set.add(string);
                    continue;
                }
                //LOGGER.warn("Ignored non-lowercase namespace: {} in {}", (Object)string, (Object)((DirectoryResourcePack)(Object)this).root);
            }
        } catch (NoSuchFileException directoryStream2) {
        } catch (IOException iOException) {
            LOGGER.error("Failed to list path {}", (Object)path, (Object)iOException);
        }
        return set;
    }
}
