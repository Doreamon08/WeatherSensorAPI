package ru.dor.springcourse.firstrestapp.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dor.springcourse.firstrestapp.models.Sensor;
import ru.dor.springcourse.firstrestapp.repositories.PeopleRepository;
import ru.dor.springcourse.firstrestapp.repositories.SensorRepository;

import java.util.Optional;

@Service
@Transactional
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findSensorByName(name);
    }

    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }


}
