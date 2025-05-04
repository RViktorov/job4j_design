package ru.job4j.io.zip;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static ru.job4j.io.zip.Search.search;

public class Zip {
    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                ZipEntry entry = new ZipEntry(source.toString());
                zip.putNextEntry(entry);
                try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(source.toFile()))) {
                    zip.write(in.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        ArgsName parameters = ArgsName.of(args);
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER");
        }
        if (args.length < 3) {
            throw new IllegalArgumentException("3 parameters must be specified (-d - directory -e - exclude -o - output");
        }
        File file = new File(parameters.get("-d"));
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("The directory does not exist: %s", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("This is not a directory: %s", file.getAbsoluteFile()));
        }
        String exclude = parameters.get("-e");
        if (!exclude.startsWith(".")) {
            throw new IllegalArgumentException("Please specify the parameter with the extension correctly (.class)");
        }
        String extension = parameters.get("-o");
        if (!extension.endsWith(".zip")) {
            throw new IllegalArgumentException("Please specify the correct archive format (.zip)");
        }
        System.out.println(args[0] + args[1] + args[2]);
        if (!args[0].startsWith("-d") || !args[1].startsWith("-e") || !args[2].startsWith("-o")) {
            throw new IllegalArgumentException("Usage: -d=<directory> -e=<exclude_extension> -o=<output.zip>");
        }
    }

    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsName zipArgsName = ArgsName.of(args);
        Path sourceDir = Path.of(zipArgsName.get("-d"));
        String excludedExtension = zipArgsName.get("-e");
        File targetZip = new File(zipArgsName.get("-o"));

        List<Path> result = search(sourceDir, path -> !path.toFile().getName().endsWith(excludedExtension));
        System.out.println(result);
        new Zip().packFiles(result, targetZip);
    }

}