package net.darkhax.openloaderfcore.packs;

import net.darkhax.openloaderfcore.Constants;
import net.darkhax.openloaderfcore.config.ConfigSchema;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;
import net.minecraft.resource.ZipResourcePack;
import net.minecraft.resource.DirectoryResourcePack;
import net.minecraft.resource.ResourcePackProvider;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.resource.ResourcePackSource;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class OpenLoaderRepositorySource implements ResourcePackProvider {

    private static final ResourcePackSource SOURCE = ResourcePackSource.create((name) -> Text.translatable("pack.nameAndSource", name, Text.translatable("pack.source.openloader")).formatted(Formatting.GREEN), true);
    private final RepoType type;
    private final List<File> directories;
    private final ConfigSchema.PackConfig config;

    public OpenLoaderRepositorySource(RepoType type, ConfigSchema.PackConfig config, Path configDir) {

        this.type = type;
        this.config = config;

        this.directories = new ArrayList<>();
        this.directories.add(configDir.resolve(type.getPath()).toFile());

        for (String customDir : config.additionalFolders) {

            this.directories.add(new File(customDir));
        }

        for (File directory : directories) {

            if (!directory.exists()) {

                //Constants.LOG.info("Generating new {} folder at {}.", type.displayName, directory.getAbsolutePath());
                directory.mkdirs();
            }

            if (!directory.isDirectory()) {

                //Constants.LOG.error("Improper {} folder specified. Must be a directory! See {}.", type.displayName, directory.getAbsolutePath());
                throw new IllegalStateException("Can not load " + type.displayName + " from non-directory. " + directory.getAbsolutePath());
            }
        }
    }

    @Override
    public void register(Consumer<ResourcePackProfile> consumer) {

        if (this.config.enabled) {

            Constants.LOG.info("Preparing {} injection.", this.type.displayName);

            for (File parentDirectory : this.directories) {

                int newPackCount = 0;
                int failedPacks = 0;

                for (File packCandidate : Objects.requireNonNull(parentDirectory.listFiles())) {

                    final boolean isArchivePack = isArchivePack(packCandidate, false);
                    final boolean isFolderPack = !isArchivePack && isFolderPack(packCandidate, false);
                    final String typeName = isArchivePack ? "archive" : isFolderPack ? "folder" : "invalid";

                    if (isArchivePack || isFolderPack) {

                        final String packName = this.type.getPath() + "/" + packCandidate.getName();
                        final Text displayName = Text.literal(packName);

                        final ResourcePackProfile pack = ResourcePackProfile.create(packName, displayName, true, createPackSupplier(packCandidate), this.type.getPackType(), ResourcePackProfile.InsertionPosition.TOP, SOURCE);

                        if (pack != null) {

                            consumer.accept(pack);
                            newPackCount++;
                            Constants.LOG.info("Loaded {} {} from {}.", typeName, this.type.getName(), packCandidate.getAbsolutePath());
                        }
                    }

                    else {

                        //Constants.LOG.error("Skipping {}. It is not a valid {}!", packCandidate.getAbsolutePath(), this.type.getName());
                        isArchivePack(packCandidate, true);
                        isFolderPack(packCandidate, true);
                        failedPacks++;
                    }
                }

                Constants.LOG.info("Successfully injected {}/{} packs from {}.", newPackCount, newPackCount + failedPacks, parentDirectory.getAbsolutePath());
            }
        }

        else {

            Constants.LOG.info("Skipping {}. Disabled by user config.", this.type.displayName);
        }
    }

    private ResourcePackProfile.PackFactory createPackSupplier (File packFile) {

        return name -> packFile.isDirectory() ?
                new DirectoryResourcePack(name, packFile.toPath(), false) :
                new ZipResourcePack(name, packFile, false);
    }

    private boolean isArchivePack(File candidate, boolean logIssues) {

        if (candidate.isFile()) {

            final String fileName = candidate.getName();
            boolean isZipCompatibleArchive = endsWithIgnoreCase(fileName, ".zip") || endsWithIgnoreCase(fileName, ".yml") || endsWithIgnoreCase(fileName, ".jar");

            if (!isZipCompatibleArchive && logIssues) {

                //Constants.LOG.warn("Can not load {} as an archive. It must be a .zip or .jar file!", candidate.getAbsolutePath());
            }

            return isZipCompatibleArchive;
        }

        else if (logIssues) {

            Constants.LOG.warn("Can not load {} as an archive. It is not a file.", candidate.getAbsolutePath());
        }

        return false;
    }

    private static boolean isFolderPack(File candidate, boolean logIssues) {

        if (candidate.isDirectory()) {

            if (new File(candidate, "pack.mcmeta").isFile()) {

                return true;
            }

            else if (logIssues) {

                Constants.LOG.warn("Can not load {} as a folder pack. It is missing a pack.mcmeta file!", candidate.getAbsolutePath());
            }
        }

        else if (logIssues) {

            //Constants.LOG.warn("Can not load {} as folder. It is not a directory.", candidate.getAbsolutePath());
        }

        return false;
    }

    private static boolean endsWithIgnoreCase(String str, String suffix) {

        final int suffixLength = suffix.length();
        return str.regionMatches(true, str.length() - suffixLength, suffix, 0, suffixLength);
    }
}