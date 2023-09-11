package net.darkhax.openloaderfcore.packs;

import java.io.File;
import java.nio.file.Path;
import net.minecraft.resource.ResourceType;

public enum RepoType {

    DATA(ResourceType.SERVER_DATA, "Data Pack", ""),
    RESOURCES(ResourceType.SERVER_DATA, "Resource Pack", "");

    final ResourceType type;
    final String displayName;
    final String path;

    RepoType(ResourceType type, String name, String path) {

        this.type = type;
        this.displayName = name;
        this.path = path;
    }

    public ResourceType getPackType() {

        return this.type;
    }

    public String getName() {

        return this.displayName;
    }

    public String getPath() {

        return this.path;
    }

    public void createDirectory(Path configDir) {

        final File file = configDir.resolve(this.getPath()).toFile();

        if (!file.exists()) {

            file.mkdirs();
        }
    }
}
