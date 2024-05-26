package ru.dor.springcourse.firstrestapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dor.springcourse.firstrestapp.models.Sensor;
import ru.dor.springcourse.firstrestapp.service.SensorService;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(sensorService.findByName(sensor.getName()).isPresent())
            errors.rejectValue("name", "", "This name already taken");

    }
}
