package ru.test.task.driverservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import ru.test.task.driverservice.entity.Category;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class DriverDto {
    private Long id;

    @Schema(description = "Driver's full name", example = "Salma Lams")
    @NotNull
    private String fullName;

    @Schema(description = "Driver's passport", example = "6105455677")
    @NotNull
    private String passport;

    @Schema(description = "Driver's date of birth", example = "1983-07-18")
    @NotNull
    private LocalDate dateOfBirth;

    @Schema(hidden = true)
    private Integer experience;

    @Schema(hidden = true)
    private Set<CarDto> cars;
    private List<Category> categories;
}
