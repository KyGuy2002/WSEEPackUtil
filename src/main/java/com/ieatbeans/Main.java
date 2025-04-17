package com.ieatbeans;

import net.minestom.server.MinecraftServer;
import net.minestom.server.item.Material;
import net.worldseed.multipart.ModelEngine;
import net.worldseed.resourcepack.PackBuilder;
import org.apache.commons.io.FileUtils;

import java.nio.charset.Charset;
import java.nio.file.Path;

public class Main {

    private static final Path OUT_PACK_PATH = Path.of("./wsee_injected_resourcepack");
    private static final Path BBMODELS_PATH = Path.of("./wsee_entity_bbmodels");

    private static final Path OUT_MODELS_PATH = Path.of("./wsee_models_out");
    private static final Path OUT_MAPPING_FILE = Path.of("./wsee_mappings_out.json");

    public static void main(String[] args) throws Exception {

        final Path inPackPath;
        if (args.length > 0) inPackPath = Path.of(args[0]);
        else inPackPath = Path.of("./resourcepack");

        System.out.println("Injecting WSEE models into resourcepack...");

        MinecraftServer.init();
        ModelEngine.setModelMaterial(Material.MAGMA_CREAM);

        // Deletes any output files from previous runs.
        try {
            FileUtils.deleteDirectory(OUT_PACK_PATH.toFile());
            FileUtils.deleteDirectory(OUT_MODELS_PATH.toFile());
            FileUtils.deleteDirectory(OUT_MAPPING_FILE.toFile());
        } catch (IllegalArgumentException ignored) {}

        // Duplicates the IN_PACK_PATH folder to the OUT_PACK_PATH folder.
        FileUtils.copyDirectory(inPackPath.toFile(), OUT_PACK_PATH.toFile());

        // Generates the OUT_MODELS_PATH folder and injects the OUT_PACK_PATH folder.
        var config = PackBuilder.Generate(BBMODELS_PATH, OUT_PACK_PATH, OUT_MODELS_PATH);

        // Generates the OUT_MAPPING_FILE file.
        FileUtils.writeStringToFile(OUT_MAPPING_FILE.toFile(), config.modelMappings(), Charset.defaultCharset());

        System.out.println("Done!  Resource pack has been injected and output files were generated.");
        System.out.println("Please provide the " + OUT_MODELS_PATH + " folder and the " + OUT_MAPPING_FILE + " file to your Minestom server in the .\\wsee folder.");
        System.out.println("Use the " + OUT_PACK_PATH + " folder as the resource pack for your client.");

        System.exit(0);

    }
}