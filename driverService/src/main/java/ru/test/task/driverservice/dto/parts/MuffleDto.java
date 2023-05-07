package ru.test.task.driverservice.dto.parts;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import ru.test.task.driverservice.dto.PartDto;

@SuperBuilder
@Getter
@Setter
public class MuffleDto extends PartDto {
    private Integer length;
    private String type;
}
