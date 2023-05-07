package ru.test.task.carservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Builder
public record CarDto (

        Long id,

        @Schema(description = "Unique identifier of the Car", example = "4ZJ705FMROGAQ289S")
        @Size(min = 17, max = 17, message = "VIN must be 17 characters long")
        @NotNull
        String vin,

        @Schema(description = "Plate number of the Car", example = "P032DB96")
        @Size(min = 8, max = 8, message = "Plate number must be 8 characters long")
        @NonNull
        String plateNumber,

        @Schema(description = "Manufacturer of the Car", example = "FCA")
        String manufacturer,

        @Schema(description = "Brand of the Car", example = "Chrysler")
        String brand,

        @Schema(description = "Production date of the Car", example = "2010-11-10")
        LocalDate productionDate,
        Set<PartDto> carParts)
        implements Serializable {
}
