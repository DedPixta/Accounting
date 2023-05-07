package ru.test.task.carservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.test.task.carservice.dto.CarDto;
import ru.test.task.carservice.util.CarSpecification;
import ru.test.task.carservice.service.CarService;
import ru.test.task.carservice.util.CarDtoValidator;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = CarController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CarController {
    public static final String REST_URL = "/api/v1/cars";

    private final CarService carService;
    private final CarDtoValidator carDtoValidator;


    @Operation(summary = "Get a page of cars. Can be filtered and sorted by any field.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class))})})
    @GetMapping
    public List<CarDto> findCars(
            @Parameter(description="Page number")
            @RequestParam(name = "page", defaultValue = "0") int page,

            @Parameter(description="Page size")
            @RequestParam(name = "size", defaultValue = "10") int size,

            @Parameter(description="Criteria for order cars. Can be any field: plateNumber, vin, brand, manufacturer. After each field need to specify order asc or desc.")
            @RequestParam(name = "sort", defaultValue = "plateNumber,asc,vin,desc") String[] sort,

            @Parameter(description="Criteria for filtering cars. Can be any field: plateNumber, vin, brand, manufacturer. Stay empty if you don't want to filter")
            @ModelAttribute CarSpecification carSpecification) {

        List<Sort.Order> orders = new ArrayList<>();

        for (int i = 0; i < sort.length; i += 2) {
            String property = sort[i];
            Sort.Direction direction = Sort.Direction.ASC;
            if (i + 1 < sort.length) {
                String dir = sort[i + 1];
                if (dir.equalsIgnoreCase("desc")) {
                    direction = Sort.Direction.DESC;
                }
            }
            orders.add(new Sort.Order(direction, property));
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));
        return carService.getCars(carSpecification, pageable);
    }

    @Operation(summary = "Get a car by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CarDto.class))})})
    @GetMapping("/{id}")
    public CarDto getCarById(@PathVariable("id") Long id) {
        return carService.getCarById(id);
    }

    // TODO change handle incoming PartDto
    @Operation(summary = "Create a new car. Car can have one or more parts. Parts can be created separately.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid CarDto carDto,
                       BindingResult bindingResult) {
        carDtoValidator.validate(carDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getAllErrors().toString());
        }
        carService.create(carDto);
    }

    @Operation(summary = "Update a existed car.")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid CarDto carDto,
                       @PathVariable Long id) {
        carService.update(carDto, id);
    }

    @Operation(summary = "Delete a existed car.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        carService.deleteById(id);
    }
}
