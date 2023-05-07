package ru.test.task.carservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)

@Entity
@Table(name = "parts")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Part extends BaseEntity{

    @Column(name = "serial_number", nullable = false, unique = true)
    protected String serialNumber;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "car_id")
    protected Car car;
}
