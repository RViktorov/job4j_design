package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        int age = 33;
        short number = 126;
        long year = 1990L;
        float speed = 345.345f;
        double speed1 = 456.456;
        byte weight = 55;
        boolean flag = true;
        char letter = 'a';

        LOG.debug("User info age : {}, number : {}, year : {}, speed : {}, speed1 : {}, weight : {}, flag : {}, letter : {}",
                age, number, year, speed, speed1, weight, flag, letter);
    }
}