package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private Map<FileProperty, List<Path>> table = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
        long size = attributes.size();
        String fileName = file.getFileName().toString();
        FileProperty fileProperty = new FileProperty(size, fileName);
        if (table.containsKey((fileProperty))) {
            List<Path> temp = table.get(fileProperty);
            temp.add(file);
            table.put(fileProperty, temp);
        } else {
            table.put(fileProperty, new ArrayList<>());
            table.get(fileProperty).add(file);
        }
        return FileVisitResult.CONTINUE;
    }

    public void filePrint() {
        for (Map.Entry<FileProperty, List<Path>> entry : table.entrySet()) {
            List<Path> paths = entry.getValue();
            if (paths.size() > 1) {
                System.out.println(entry.getKey());
                for (Path path : paths) {
                    System.out.println(path);
                }
                System.out.println();
            }
        }
    }
}