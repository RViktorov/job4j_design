package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    public List<String> filter() {
        List<String> list = new ArrayList<>();
        try (
                BufferedReader input = new BufferedReader(new FileReader(file))) {
            while (input.read() != -1) {
                String string = input.readLine();
                String[] arrayString = string.split(" ");
                if ("404".equals(arrayString[arrayString.length - 2])) {
                    list.add(string);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}