package ru.dor.springcourse.firstrestapp.repositories;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dor.springcourse.firstrestapp.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    public Optional<Sensor> findSensorByName(@NotEmpty(message = "Name should not be empty") @Size(min = 2, max = 30, message = "Name should be between 2 and 30 characters") String name);
}
