package com.KafuuChino0722.coreextensions.core.brrp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class MethodExport {
    public static Path getPath() {
        return Paths.get("core/datagen");
    }
    public static Path getIaPath() {
        return Paths.get("itemsadder/datagen");
    }
    public static Path getPathMods() {
        return Paths.get("mods/");
    }
}
