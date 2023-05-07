package ru.test.task.carservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "cars")
public class Car extends BaseEntity{

    @Column(nullable = false, unique = true, length = 17)
    private String vin;

    @Column(name = "plate_number", nullable = false, unique = true)
    private String plateNumber;

    private String manufacturer;

    private String brand;

    @Column(name = "production_date")
    private LocalDate productionDate;

    @JsonManagedReference
    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Part> carParts;

}
