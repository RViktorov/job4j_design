package ru.job4j.serialization.json;

import jakarta.xml.bind.annotation.*;

import java.util.Arrays;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class Car {
    @XmlAttribute
    private boolean newCar;
    private int yearCarProduction;
    private String model;
    private Engine engine;
    @XmlElementWrapper(name = "serviceMaintenances")
    @XmlElement(name = "serviceMaintenance")
    private String[] serviceMaintenance;


    public Car(boolean newCar, int yearCarProduction, String model, Engine engine, String[] serviceMaintenance) {
        this.newCar = newCar;
        this.yearCarProduction = yearCarProduction;
        this.model = model;
        this.engine = engine;
        this.serviceMaintenance = serviceMaintenance;
    }

    public Car() {

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