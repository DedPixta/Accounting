package ru.test.task.driverservice.util;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.test.task.driverservice.dto.DriverDto;
import ru.test.task.driverservice.entity.Driver;
import ru.test.task.driverservice.repository.DriverRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class DriverDtoValidator implements Validator {
    private final DriverRepository driverRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return DriverDto.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        DriverDto driverDto = (DriverDto) target;
        Optional<Driver> dbDriver = driverRepository.findByFullNameAndPassport(driverDto.getFullName(), driverDto.getPassport());
        if (dbDriver.isPresent()) {
            errors.rejectValue("passport", String.format("Driver with %s passport already exists", driverDto.getPassport()));
            errors.rejectValue("fullName", String.format("Driver with %s fullName already exists", driverDto.getPassport()));
        }
    }
}
