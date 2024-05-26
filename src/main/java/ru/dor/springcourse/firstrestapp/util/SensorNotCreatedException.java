package ru.dor.springcourse.firstrestapp.util;

public class SensorNotCreatedException extends RuntimeException {
    public SensorNotCreatedException(String msg) {
        super(msg);
    }
}
