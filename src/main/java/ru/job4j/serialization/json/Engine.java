package ru.job4j.serialization.json;

public class Engine {
    private final String engineType;

    public Engine(String engineType) {
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "engineType='" + engineType + '\''
                + '}';
    }
}