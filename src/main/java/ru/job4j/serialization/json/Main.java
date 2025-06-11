package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
    public static void main(String[] args) {
        final Car car = new Car(false, 2021, "BMW x7", new Engine("V8"),
                new String[]{"TO-0", "TO-1"});
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(car));

        final String carJson =
                "{"
                        + "\"newCar\":false,"
                        + "\"yearСarProduction\":2021,"
                        + "\"model\":BMWx7,"
                        + "\"engine\":"
                        + "{"
                        + "\"engineType\":\"V8\""
                        + "},"
                        + "\"serviceMaintenance\":"
                        + "[\"TO-0\",\"TO-1\"]"
                        + "}";

        final Car carMod = gson.fromJson(carJson, Car.class);
        System.out.println(carMod);
    }
}