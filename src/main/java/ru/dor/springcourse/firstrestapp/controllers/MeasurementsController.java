package ru.dor.springcourse.firstrestapp.controllers;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.dor.springcourse.firstrestapp.dto.MeasurementDTO;
import ru.dor.springcourse.firstrestapp.models.Measurement;
import ru.dor.springcourse.firstrestapp.service.MeasurementService;
import ru.dor.springcourse.firstrestapp.util.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementsController {

    private MeasurementService measurementService;
    private ModelMapper modelMapper;
    private MeasurementValidator measurementValidator;

    public MeasurementsController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
    }

    @GetMapping
    public List<MeasurementDTO> getMeasurements() {
//        List<Measurement> measurements = measurementService.findAll();
//        List<MeasurementDTO> measurementDTOS ;
//        measurementDTOS = new ArrayList<>();
//        for (Measurement measurement : measurements)
//            measurementDTOS.add(convertToMeasurementDTO(measurement));
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getRainyDaysCount() {
        return measurementService.getRainyDaysCount();
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult) {
        measurementValidator.validate(measurementDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();

            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors)
                errorMsg.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append(";");

            throw new MeasurementNotCreatedException(errorMsg.toString());
        }
        Measurement measurement = convertToMeasurement(measurementDTO);

        measurementService.save(measurement);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<MeasurementErrorResponse> handleException(MeasurementNotCreatedException e) {
        MeasurementErrorResponse measurementErrorResponse = new MeasurementErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(measurementErrorResponse, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}
