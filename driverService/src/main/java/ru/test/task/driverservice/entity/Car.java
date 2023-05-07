package ru.test.task.driverservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;
}
