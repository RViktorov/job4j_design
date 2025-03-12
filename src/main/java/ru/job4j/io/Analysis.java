package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(new FileReader(source));
             BufferedWriter output = new BufferedWriter(new FileWriter(target))) {
            String stringText;
            boolean flag = false;
            while ((stringText = input.readLine()) != null) {
                boolean error = ("500".equals(stringText.substring(0, 3))
                             || ("400".equals(stringText.substring(0, 3))));
                if (error && !flag) {
                    output.write(stringText.substring(4) + ";");
                    flag = true;
                }
                if (!error && flag) {
                    output.write(stringText.substring(4) + ";");
                    output.newLine();
                    flag = false;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}