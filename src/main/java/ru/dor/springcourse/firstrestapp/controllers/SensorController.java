package ru.dor.springcourse.firstrestapp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.dor.springcourse.firstrestapp.dto.SensorDTO;
import ru.dor.springcourse.firstrestapp.models.Sensor;
import ru.dor.springcourse.firstrestapp.service.SensorService;
import ru.dor.springcourse.firstrestapp.util.PersonNotCreatedException;
import ru.dor.springcourse.firstrestapp.util.SensorErrorResponse;
import ru.dor.springcourse.firstrestapp.util.SensorNotCreatedException;
import ru.dor.springcourse.firstrestapp.util.SensorValidator;

import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private SensorValidator sensorValidator;
    private SensorService sensorService;
    private ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorValidator sensorValidator, SensorService sensorService, ModelMapper modelMapper) {
        this.sensorValidator = sensorValidator;
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid SensorDTO sensorDTO,
                                                   BindingResult bindingResult) {
        Sensor sensor = convertToSensor(sensorDTO);
        sensorValidator.validate(sensor, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");

            throw new SensorNotCreatedException(errorMsg.toString());
        }

        sensorService.save(sensor);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotCreatedException e) {
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(sensorErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

}
