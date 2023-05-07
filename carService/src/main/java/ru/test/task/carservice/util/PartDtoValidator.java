package ru.test.task.carservice.util;

import lombok.AllArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.test.task.carservice.dto.PartDto;
import ru.test.task.carservice.entity.Part;
import ru.test.task.carservice.repository.PartRepository;

import java.util.Optional;

@Component
@AllArgsConstructor
public class PartDtoValidator implements Validator {
    private final PartRepository partRepository;

    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return PartDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        PartDto partDto = (PartDto) target;
        Optional<Part> part = partRepository.findBySerialNumber(partDto.getSerialNumber());
        if (part.isPresent()) {
            errors.rejectValue("serialNumber", String.format("Part with %s serial number already exists", partDto.getSerialNumber()));
        }
    }
}
