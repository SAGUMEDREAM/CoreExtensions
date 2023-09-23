package com.KafuuChino0722.coreextensions.core.brrp;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.DosFileAttributes;

public class Export {
    public static Path getPath() {
        return Paths.get("core/datagen");
    }
    public static Path getPathMods() {
        return Paths.get("mods/");
    }
}
