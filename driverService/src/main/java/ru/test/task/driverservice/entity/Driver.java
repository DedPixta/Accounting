package ru.test.task.driverservice.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Set;

@Getter
@Setter

@Entity
@Table(name = "drivers")
public class Driver extends BaseEntity{

    @NotNull
    @Column(name = "full_name")
    private String fullName;

    @NotNull
    private String passport;

    @NotNull
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Transient
    private Integer experience;

    @JsonManagedReference
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Car> cars;

    @JsonManagedReference
    @OneToMany(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Category> categories;

    @JsonManagedReference
    @OneToOne(mappedBy = "driver", cascade = CascadeType.ALL, orphanRemoval = true)
    private Account account;

    @PostLoad
    public void calculateAge() {
        LocalDate now = LocalDate.now();
        this.experience = Period.between(this.dateOfBirth, now).getYears();
    }
}
