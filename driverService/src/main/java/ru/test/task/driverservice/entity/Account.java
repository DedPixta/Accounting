package ru.test.task.driverservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter

@Entity
@Table(name = "accounts")
public class Account extends BaseEntity{

    @Schema(description = "Current driver's balance", example = "1000.00")
    private BigDecimal balance;

    @Schema(hidden = true)
    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @JsonIgnore
    @Override
    public Long getId() {
        return super.getId();
    }
}
