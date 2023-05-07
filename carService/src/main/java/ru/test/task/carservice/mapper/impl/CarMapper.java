package ru.test.task.carservice.mapper.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.test.task.carservice.dto.CarDto;
import ru.test.task.carservice.dto.PartDto;
import ru.test.task.carservice.entity.Car;
import ru.test.task.carservice.entity.Part;
import ru.test.task.carservice.mapper.Mapper;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Component
public class CarMapper implements Mapper<CarDto, Car> {

    private final PartMapper partMapper;

    @Override
    public Car toEntity(CarDto carDto) {
        if (carDto == null) {
            throw new NullPointerException("Missing carDto");
        }
        return Car.builder()
                .vin(carDto.vin())
                .plateNumber(carDto.plateNumber())
                .manufacturer(carDto.manufacturer())
                .brand(carDto.brand())
                .productionDate(carDto.productionDate())
                .carParts(this.partDtoSetToPartSet(carDto.carParts()))
                .build();
    }

    @Override
    public CarDto toDto(Car car) {
        if (car == null) {
            throw new NullPointerException("Missing car");
        }

        return CarDto.builder()
                .id(car.getId())
                .vin(car.getVin())
                .plateNumber(car.getPlateNumber())
                .manufacturer(car.getManufacturer())
                .brand(car.getBrand())
                .productionDate(car.getProductionDate())
                .carParts(this.partSetToPartDtoSet(car.getCarParts()))
                .build();
    }

    protected Set<Part> partDtoSetToPartSet(Set<PartDto> partsDto) {
        if (partsDto == null) {
            return null;
        }

        Set<Part> parts = new HashSet<>();
        partsDto.forEach(partDto -> parts.add(this.partMapper.toEntity(partDto)));
        return parts;
    }

    protected Set<PartDto> partSetToPartDtoSet(Set<Part> parts) {
        if (parts == null) {
            return null;
        }

        Set<PartDto> partsDto = new HashSet<>();
        parts.forEach(part -> partsDto.add(this.partMapper.toDto(part)));
        return partsDto;
    }
}
