package ru.job4j.io;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {

        Map<String, Integer> headerMap = new HashMap<>();
        List<Integer> filterIndexes = new ArrayList<>();
        List<String> result = new ArrayList<>();

        String path = argsName.get("path");
        String delimiter = argsName.get("delimiter");
        String out = argsName.get("out");
        String[] filters = argsName.get("filter").split(",");

        try (Scanner scanner = new Scanner(new File(path))) {
            if (!scanner.hasNextLine()) {
                throw new IllegalArgumentException("file is empty.");
            }

            String headerLine = scanner.nextLine();
            String[] headers = headerLine.split(delimiter);
            for (int i = 0; i < headers.length; i++) {
                headerMap.put(headers[i], i);
            }

            for (String filter : filters) {
                if (!headerMap.containsKey(filter)) {
                    throw new IllegalArgumentException("column with filter is missing " + filter);
                }
                filterIndexes.add(headerMap.get(filter));
            }
            result.add(String.join(delimiter, filters));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(delimiter);
                List<String> filtered = new ArrayList<>();
                for (int index : filterIndexes) {
                    filtered.add(index < parts.length ? parts[index] : "");
                }
                result.add(String.join(delimiter, filtered));
            }
        }

        if ("stdout".equalsIgnoreCase(out)) {
            result.forEach(System.out::println);
        } else {
            Path outputPath = Path.of(out);
            Files.write(outputPath, result);
        }
    }

    private static void validate(String[] args) {
        ArgsName parameters = ArgsName.of(args);
        if (args.length == 0) {
            throw new IllegalArgumentException("no parameters");
        }
        if (args.length < 4) {
            throw new IllegalArgumentException("4 parameters must be specified");
        }

        String path = parameters.get("path");
        if (!path.endsWith(".csv")) {
            throw new IllegalArgumentException("Please specify the file with the extension in the parameters (.csv)");
        }

        System.out.println(args[0] + args[1] + args[2] + args[3]);
        if (!args[0].startsWith("-path") || !args[1].startsWith("-delimiter") || !args[2].startsWith("-out") || !args[3].startsWith("-filter")) {
            throw new IllegalArgumentException("Usage: - -path= -delimiter=  -out= -filter=");
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(args.length);
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
