package org.penguinencounter.fakemodmod;

import net.fabricmc.loader.api.Version;
import net.fabricmc.loader.api.VersionParsingException;
import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import net.fabricmc.loader.api.metadata.ContactInformation;
import net.fabricmc.loader.api.metadata.ModEnvironment;
import net.fabricmc.loader.impl.FabricLoaderImpl;

import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Preloader implements PreLaunchEntrypoint {
    @Override
    public void onPreLaunch() {
        Fakemodmod.MESSAGE_PICKER.ready();
        try {
            List<String> message = Fakemodmod.MESSAGE_PICKER.pick();
            int i = 0;
            int width = String.valueOf(message.size()).length();
            for (String line : message) {
                i++;
                String friendlyLine = line.toLowerCase(Locale.getDefault())
                        .replaceAll("\\s", "-")
                        .replaceAll("[^a-z0-9-]", "");
                String modid = String.format("CoreExtensions-%0" + width + "d--%s", i, friendlyLine);
                String modname = String.format("CoreExtensions-%0" + width + "d | %s", i, line);
                FabricLoaderInterface.addMod(FabricLoaderImpl.INSTANCE,
                        FabricLoaderInterface.createPlain(
                                Path.of("/"),
                                new V1ModMetadata(
                                        modid,
                                        Version.parse("0.2.0"),
                                        List.of(),
                                        ModEnvironment.UNIVERSAL,
                                        Map.of(),
                                        List.of(),
                                        List.of(),
                                        null,
                                        List.of(),
                                        false,
                                        modname,
                                        "provided by CoreExtensions",
                                        List.of(),
                                        List.of(),
                                        ContactInformation.EMPTY,
                                        List.of(),
                                        V1ModMetadata.NO_ICON,
                                        Map.of(),
                                        Map.of()),
                                false,
                                List.of()
                        ));
            }
        } catch (VersionParsingException ignored) {
        }
    }
}
