package ru.dor.springcourse.firstrestapp.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Entity
public class Measurement {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    @NotNull(message = "Value should not be empty")
    @Min(value = -100, message = "Value should be bigger than -100")
    @Max(value = 100, message = "Value should be smaller than 100")
    private Integer value;
    @Column(name = "raining")
    @NotNull(message = "Raining should not be empty")
    private Boolean raining;
    @ManyToOne
    @JoinColumn(name = "sensor", referencedColumnName = "name")
//    @JsonBackReference
    private Sensor sensor;
    @Column(name = "created_at")
    private LocalDateTime created_at;

    public Measurement() {
    }

    public Measurement(int value, boolean raining, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }
}
