package ru.dor.springcourse.firstrestapp.util;

public class MeasurementNotCreatedException extends RuntimeException {
    public MeasurementNotCreatedException(String msg) {
        super(msg);
    }
}
