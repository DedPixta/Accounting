package ru.test.task.driverservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.test.task.driverservice.dto.DriverDto;
import ru.test.task.driverservice.entity.Driver;

@Mapper(componentModel = "spring")
public interface DriverMapper {

    @Mapping(target = "cars", ignore = true)
    DriverDto toDto(Driver driver);

    @Mapping(target = "cars", ignore = true)
    Driver toEntity(DriverDto driverDto);
}
