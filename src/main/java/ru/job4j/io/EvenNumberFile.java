package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

import static java.lang.Character.getNumericValue;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream input = new FileInputStream("data/even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = input.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            for (String line : lines) {
                if (Integer.parseInt(line) % 2 == 0) {
                    System.out.println(line + "- even number");
                } else {
                    System.out.println(line + "- odd number");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}