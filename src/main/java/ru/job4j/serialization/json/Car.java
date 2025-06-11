package ru.job4j.serialization.json;

import java.util.Arrays;

public class Car {
    private final boolean newCar;
    private final int yearCarProduction;
    private final String model;
    private final Engine engine;
    private final String[] serviceMaintenance;

    public Car(boolean newCar, int yearCarProduction, String model, Engine engine, String[] serviceMaintenance) {
        this.newCar = newCar;
        this.yearCarProduction = yearCarProduction;
        this.model = model;
        this.engine = engine;
        this.serviceMaintenance = serviceMaintenance;
    }

    @Override
    public String toString() {
        return "Car{"
                + "newСar=" + newCar
                + ", yearСarProduction=" + yearCarProduction
                + ", model=" + model
                + ", engine=" + engine
                + ", serviceMaintenance=" + Arrays.toString(serviceMaintenance)
                + '}';
    }
}