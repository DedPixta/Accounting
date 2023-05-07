package ru.test.task.driverservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

public record CarDto (
        @Schema(description = "Unique identifier of Car, which registered in database", example = "5")
        Long id,

        @Schema(hidden = true)
        @Size(min = 17, max = 17, message = "VIN must be 17 characters long")
        @NotNull
        String vin,

        @Schema(hidden = true)
        @Size(min = 8, max = 8, message = "Plate number must be 8 characters long")
        @NonNull
        String plateNumber,

        @Schema(hidden = true)
        String manufacturer,

        @Schema(hidden = true)
        String brand,

        @Schema(hidden = true)
        LocalDate productionDate,

        @Schema(hidden = true)
        Set<? extends PartDto> carParts)
        implements Serializable {
}
