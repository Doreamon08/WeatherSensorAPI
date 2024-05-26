package ru.dor.springcourse.firstrestapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dor.springcourse.firstrestapp.models.Measurement;
import ru.dor.springcourse.firstrestapp.models.Sensor;
import ru.dor.springcourse.firstrestapp.repositories.MeasurementRepository;
import ru.dor.springcourse.firstrestapp.repositories.SensorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeasurementService {

    private final MeasurementRepository measurementRepository;
    private final SensorRepository sensorRepository;

    public MeasurementService(MeasurementRepository measurementRepository, SensorRepository sensorRepository) {
        this.measurementRepository = measurementRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Measurement measurement) {
        measurement.setCreated_at(LocalDateTime.now());
        Optional<Sensor> sensor = sensorRepository.findSensorByName(measurement.getSensor().getName());
        measurement.setSensor(sensor.get());
        measurementRepository.save(measurement);
    }

    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    public int getRainyDaysCount() {
        int countOfRainyDays = 0;

        List<Measurement> measurements = findAll();
        for (Measurement measurement : measurements) {
            if (measurement.isRaining())
                countOfRainyDays++;
        }

        return countOfRainyDays;
    }
}
