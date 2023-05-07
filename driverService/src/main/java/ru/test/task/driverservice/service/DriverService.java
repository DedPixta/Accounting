package ru.test.task.driverservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import ru.test.task.driverservice.dto.CarDto;
import ru.test.task.driverservice.dto.DriverDto;
import ru.test.task.driverservice.entity.Driver;
import ru.test.task.driverservice.exception.NotFoundException;
import ru.test.task.driverservice.exception.RestRequestException;
import ru.test.task.driverservice.mapper.DriverMapper;
import ru.test.task.driverservice.repository.DriverRepository;
import ru.test.task.driverservice.util.DriverSpecification;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class DriverService {

    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${car.service.url}")
    public String carServiceUrl;

    public List<DriverDto> findDrivers(DriverSpecification driverSpecification, Pageable pageable) {
        return driverRepository.findAll(driverSpecification, pageable)
                .getContent()
                .stream()
                .map(driverMapper::toDto)
                .toList();
    }

    public DriverDto getDriverById(Long id) {
        DriverDto driverDto = driverRepository.findById(id)
                .map(driverMapper::toDto)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Driver with id %s not found", id
                )));

        if (driverDto.getCars() != null && !driverDto.getCars().isEmpty()) {
            try {
                StringBuilder requestUrl = new StringBuilder();
                requestUrl.append(carServiceUrl);
                requestUrl.append("?");

                Set<CarDto> cars = driverDto.getCars();
                Iterator<CarDto> iterator = cars.iterator();
                while (iterator.hasNext()) {
                    CarDto carDto = iterator.next();
                    requestUrl.append("id=");
                    requestUrl.append(carDto.id());
                    if (iterator.hasNext()) {
                        requestUrl.append("&");
                    }
                }
                String response = restTemplate.getForObject(requestUrl.toString(), String.class);
                List<CarDto> carDtoList = objectMapper.readValue(response, new TypeReference<>() {
                });
                driverDto.setCars(new HashSet<>(carDtoList));
            } catch (JsonProcessingException e) {
                throw new RestRequestException(e);
            }
        }

        return driverDto;
    }

    @Transactional
    public void create(DriverDto driverDto) {
        Driver driver = driverMapper.toEntity(driverDto);
        driver.getAccount().setDriver(driver);
        driver.getAccount().setDriver(driver);
        driverRepository.save(driver);
    }

    @Transactional
    public void update(DriverDto driverDto, Long id) {
        Driver dbDriver = driverRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(
                        "Driver with id %s not found", id
                )));

        dbDriver.setFullName(driverDto.getFullName());
        dbDriver.setDateOfBirth(driverDto.getDateOfBirth());
        dbDriver.setPassport(driverDto.getPassport());
        driverRepository.save(dbDriver);
    }

    @Transactional
    public void deleteById(long id) {
        driverRepository.deleteById(id);
    }
}
