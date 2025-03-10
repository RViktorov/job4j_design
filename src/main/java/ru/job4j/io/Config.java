package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            String stringText;
            while ((stringText = reader.readLine()) != null) {
                boolean noEmptyLineAndComments = !stringText.isEmpty() && !stringText.startsWith("#");
                if (noEmptyLineAndComments
                        && (!stringText.contains("=")
                        || stringText.startsWith("=")
                        || stringText.endsWith("="))) {
                    throw new IllegalArgumentException();
                } else if (noEmptyLineAndComments) {
                    values.put(stringText.substring(0, stringText.indexOf("=")),
                            stringText.substring(stringText.indexOf("=") + 1));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner output = new StringJoiner(System.lineSeparator());
        try (BufferedReader reader = new BufferedReader(new FileReader(this.path))) {
            reader.lines().forEach(output::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public static void main(String[] args) {
        Config conf = new Config("data/app.properties");
        conf.load();
    }
}