package ru.dor.springcourse.firstrestapp.util;

public class PersonNotCreatedException extends RuntimeException {
    public PersonNotCreatedException(String msg) {
        super(msg);
    }
}
