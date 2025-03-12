package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    Analysis analysis = new Analysis();

    @Test
    void drop(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        try (PrintWriter output = new PrintWriter(source)) {
            output.println("400 10:50:01");
            output.println("200 10:56:00");
            output.println("500 10:59:00");
            output.println("400 11:05:00");
            output.println("300 11:15:02");
        }
        File target = tempDir.resolve("target.txt").toFile();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());

        StringBuilder result = new StringBuilder();
        try (BufferedReader input = new BufferedReader(new FileReader(target))) {
            input.lines().forEach(result::append);
        }
        assertThat("10:50:01;10:56:00;10:59:00;11:15:02;").hasToString(result.toString());
    }
}