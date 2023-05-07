package ru.test.task.driverservice.controller;

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
import ru.test.task.driverservice.dto.DriverDto;
import ru.test.task.driverservice.service.AccountService;
import ru.test.task.driverservice.service.DriverService;
import ru.test.task.driverservice.util.BalanceOperationRequest;
import ru.test.task.driverservice.util.CurrencyType;
import ru.test.task.driverservice.util.DriverDtoValidator;
import ru.test.task.driverservice.util.DriverSpecification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(value = DriverController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DriverController {
    public static final String REST_URL = "/api/v1/drivers";

    private final DriverService driverService;
    private final AccountService accountService;
    private final DriverDtoValidator driverDtoValidator;


    @Operation(summary = "Get a page of drivers. Can be filtered and sorted by any field.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found drivers",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DriverDto.class))})})
    @GetMapping
    public List<DriverDto> findDrivers(
            @Parameter(description = "Page number")
            @RequestParam(name = "page", defaultValue = "0") int page,

            @Parameter(description = "Page size")
            @RequestParam(name = "size", defaultValue = "10") int size,

            @Parameter(description = "Criteria for order drivers. Can be any field: fullName, dateOfBirth. After each field need to specify order asc or desc.")
            @RequestParam(name = "sort", defaultValue = "dateOfBirth,asc,fullName,desc") String[] sort,

            @Parameter(description = "Criteria for filtering drivers. Can be any field: fullName, dateOfBirth. Stay empty if you don't want to filter")
            @ModelAttribute DriverSpecification driverSpecification) {

        List<Sort.Order> orders = new ArrayList<>();

        for (int i = 0; i < sort.length; i += 2) {
            String property = sort[i];
            Sort.Direction direction = Sort.Direction.ASC;
            if (i + 1 < sort.length) {
                direction = Sort.Direction.fromString(sort[i + 1]);
            }
            orders.add(new Sort.Order(direction, property));
        }

        Pageable pageable = PageRequest.of(page, size, Sort.by(orders));

        return driverService.findDrivers(driverSpecification, pageable);
    }

    @Operation(summary = "Get a driver by id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Driver found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DriverDto.class))})})
    @GetMapping("/{id}")
    public DriverDto getDriverById(@PathVariable("id") Long id) {
        return driverService.getDriverById(id);
    }

    @Operation(summary = "Create a new driver. Driver can have one or more driver licence categories.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid DriverDto driverDto,
                       BindingResult bindingResult) {
        driverDtoValidator.validate(driverDto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException(bindingResult.getAllErrors().toString());
        }
        driverService.create(driverDto);
    }

    @Operation(summary = "Update a existed driver.")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid DriverDto driverDto,
                       @PathVariable Long id) {
        driverService.update(driverDto, id);
    }

    @Operation(summary = "Delete a existed driver.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        driverService.deleteById(id);
    }

    @Operation(summary = "Get driver's balance")
    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(@PathVariable Long id,
                                 @RequestParam CurrencyType currencyType) {
        return accountService.getBalance(id, currencyType);
    }

    @Operation(summary = "Withdraw money from driver's account")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/withdraw", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void withdraw(@RequestBody BalanceOperationRequest request) {
        accountService.withdraw(request);
    }

    @Operation(summary = "Deposit money to driver's account")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/deposit", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deposit(@RequestBody BalanceOperationRequest request) {
        accountService.deposit(request);
    }
}
