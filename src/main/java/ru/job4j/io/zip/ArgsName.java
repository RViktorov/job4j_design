package ru.job4j.io.zip;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    public final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("This key: '" + key + "' is missing");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String data : args) {
            if (!data.contains("=")) {
                throw new IllegalArgumentException("Error: This argument '" + data + "' does not contain an equal sign");
            }
            if (!data.startsWith("-")) {
                throw new IllegalArgumentException("Error: This argument '" + data + "' does not start with a '-' character");
            }
            if (data.startsWith("-") && data.indexOf("=") == 1) {
                throw new IllegalArgumentException("Error: This argument '" + data + "' does not contain a key");
            }
            if (data.indexOf("=") == data.length() - 1) {
                throw new IllegalArgumentException("Error: This argument '" + data + "' does not contain a value");
            }
            values.put(data.substring(0, data.indexOf('=')), data.substring(data.indexOf('=') + 1));
        }
    }

    public static ArgsName of(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

}