package ru.dor.springcourse.firstrestapp.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import ru.dor.springcourse.firstrestapp.models.Measurement;
import ru.dor.springcourse.firstrestapp.models.Sensor;

import java.time.LocalDateTime;
import java.util.List;

public class MeasurementDTO {

    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be bigger than -100")
    @Max(value = 100, message = "Value should be smaller than 100")
    private Integer value;
    @Column(name = "raining")
    @NotNull(message = "Raining should not be empty")
//    @NotEmpty(message = "Raining should not be empty")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
//    @JsonBackReference
    private Sensor sensor;
    private String name_of_sensor;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
