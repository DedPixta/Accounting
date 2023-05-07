package ru.test.task.carservice.util;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.test.task.carservice.dto.CarDto;
import ru.test.task.carservice.entity.Car;
import ru.test.task.carservice.repository.CarRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CarDtoValidator implements Validator {
    private final CarRepository carRepository;
    private final PartDtoValidator partDtoValidator;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return CarDto.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        CarDto carDto = (CarDto) target;
        Optional<Car> carByVin = carRepository.findByVin(carDto.vin());
        if (carByVin.isPresent()) {
            errors.rejectValue("vin", String.format("Car with %s vin already exists", carDto.vin()));
        }

        Optional<Car> carByPlateNumber = carRepository.findByPlateNumber(carDto.plateNumber());
        if (carByPlateNumber.isPresent()) {
            errors.rejectValue("plateNumber", String.format("Car with %s plateNumber already exists", carDto.plateNumber()));
        }

        if (carDto.carParts() != null) {
            carDto.carParts().forEach(partDto -> partDtoValidator.validate(partDto, errors));
        }
    }
}
