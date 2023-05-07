package ru.test.task.driverservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.NonNull;
import ru.test.task.driverservice.util.PartType;

@SuperBuilder
@Getter
@Setter
public abstract class PartDto {
    private Long id;

    @Schema(description = "Serial number of car part", example = "Su6nyxJgLJ")
    @NonNull
    private String serialNumber;

    @Schema(description = "Type of car part. Can be on of MUFFLE or ENGINE", example = "ENGINE")
    private PartType partType;
}
