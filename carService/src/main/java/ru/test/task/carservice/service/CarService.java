package ru.test.task.carservice.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.test.task.carservice.dto.CarDto;
import ru.test.task.carservice.entity.Car;
import ru.test.task.carservice.util.CarSpecification;
import ru.test.task.carservice.entity.Part;
import ru.test.task.carservice.exception.NotFoundException;
import ru.test.task.carservice.mapper.impl.CarMapper;
import ru.test.task.carservice.repository.CarRepository;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public List<CarDto> getCars(CarSpecification carSpecification, Pageable pageable) {
        return carRepository.findAll(carSpecification, pageable)
                .getContent()
                .stream()
                .map(carMapper::toDto)
                .toList();
    }

    public CarDto getCarById(Long id) {
        return carRepository.findById(id)
                .map(carMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Car with id %s not found", id
                )));
    }

    @Transactional
    public void deleteById(Long id) {
        carRepository.deleteById(id);
    }

    @Transactional
    public void create(CarDto carDto) {
        Car car = carMapper.toEntity(carDto);
        Set<Part> carParts = car.getCarParts();
        if (carParts != null) {
            carParts.forEach(part -> part.setCar(car));
        }
        carRepository.save(car);
    }

    @Transactional
    public void update(CarDto carDto, Long id) {
        Car dbCar = carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Car with id %s not found", id
                )));
        Car carUpdate = carMapper.toEntity(carDto);
        dbCar.setVin(carUpdate.getVin());
        dbCar.setPlateNumber(carUpdate.getPlateNumber());
        dbCar.setBrand(carUpdate.getBrand());
        dbCar.setProductionDate(carUpdate.getProductionDate());
        dbCar.setManufacturer(carUpdate.getManufacturer());
        dbCar.setCarParts(carUpdate.getCarParts());
        carRepository.save(dbCar);
    }

    public List<CarDto> getCarsById(List<Long> ids) {
        return carRepository.findCarsById(ids)
                .stream()
                .map(carMapper::toDto)
                .toList();
    }
}
