package ru.dor.springcourse.firstrestapp.util;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.dor.springcourse.firstrestapp.dto.MeasurementDTO;
import ru.dor.springcourse.firstrestapp.models.Measurement;
import ru.dor.springcourse.firstrestapp.service.SensorService;

@Component
public class MeasurementValidator implements Validator {

    private final SensorService sensorService;

    public MeasurementValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        MeasurementDTO measurementDTO = (MeasurementDTO) target;

        if (sensorService.findByName(measurementDTO.getSensor().getName()).isEmpty())
            errors.rejectValue("sensor", "",
                    "Sensor with this name doesnt exist");

}}
