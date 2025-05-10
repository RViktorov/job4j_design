package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class ConsoleChat {

    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final List<String> conversation = new ArrayList<>();
    private final Random rand = new Random();
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        List<String> botPhrases = readPhrases();
        Scanner scanner = new Scanner(System.in);
        String userInput = null;
        boolean botActive = true;
        while (!Objects.equals(userInput, OUT)) {
            userInput = scanner.nextLine();
            conversation.add(userInput);
            if (Objects.equals(userInput, OUT)) {
                break;
            }
            if (Objects.equals(userInput, STOP)) {
                botActive = false;
            }
            if (Objects.equals(userInput, CONTINUE)) {
                botActive = true;
            }
            if (botActive) {
                if (!botPhrases.isEmpty()) {
                    String botResponse = botPhrases.get(rand.nextInt(botPhrases.size()));
                    conversation.add(botResponse);
                    System.out.println(botResponse);
                } else {
                    System.out.println("The answer file is empty");
                }
            }
        }
        saveLog(conversation);
    }

    private List<String> readPhrases() {
        List<String> arrayPhrases = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(botAnswers))) {
            reader.lines().map(string -> string + System.lineSeparator()).forEach(arrayPhrases::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return arrayPhrases;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(path, Charset.forName("WINDOWS-1251"), true))) {
            for (String data : log) {
                writer.println(data);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("./data/text.txt", "./data/botAnswers.txt");
        consoleChat.run();
    }
}